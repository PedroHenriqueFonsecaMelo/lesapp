package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/cliHome")

public class clienteController {
    private String uidcli = "1";
    ArrayList<Endereco> enderecos = new ArrayList<>();
    ArrayList<ArrayList<String>> row = new ArrayList<>();
    ArrayList<ArrayList<String>> rowDetails = new ArrayList<>();

    private void clear() {
        enderecos.clear();
        row.clear();
    }

    @GetMapping("/singup/form")
    public String CliForm() {

        return "cliPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView insCli(ModelMap model, @RequestParam Map<String, ?> param,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Cliente cliente = new Cliente(param);
        Cliente.InserirCBD(cliente);
        uidcli = Cliente.cliUID(cliente);

        init.setUid(uidcli);
        redirectAttributes.addFlashAttribute("flash_uid", uidcli);

        return new ModelAndView("redirect:/endereco/singup/form", model);
    }

    @GetMapping("/login/form")
    public String logCli(Model model, @RequestParam Map<String, ?> param) {

        return "cliPages/login";
    }

    @PostMapping("/login")
    public ModelAndView loginCli(ModelMap model, @RequestParam Map<String, ?> param) {

        uidcli = Cliente.cliUID(null, param);
        init.setUid(uidcli);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/cliProfile")
    public String cliProfile(Model model, @RequestParam Map<String, ?> param) {
        clear();
        cliModel(model);

        return "cliPages/cliProfile";
    }

    @GetMapping("/cliProfile2")
    public String cliProfile2(Model model, @SessionAttribute(name = "uidcli", required = false) String uid) {
        return "cliPages/cliProfile";
    }

    @PostMapping("/update")
    public ModelAndView update(ModelMap model, @RequestParam Map<String, String> param) {
        TreeMap<String, String> ls = new TreeMap<>();
        ls.putAll(param);

        Cliente.update(Integer.parseInt(init.getUid()), (NavigableMap<String, String>) ls);
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/delete")
    public ModelAndView del(ModelMap model, @RequestParam Map<String, String> param) {

        Cliente.Deletar(Integer.parseInt(init.getUid()));
        Cartao.Deletar();
        Endereco.Deletar();
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/cliDetails/{id}")
    public ModelAndView cliOrdens(ModelMap model, @PathVariable(value = "id") String ncard) {

        rowDetails.clear();

        rowDetails.addAll(connectBD.mcolum("SELECT ORDEM_ID, LIVROID FROM ORDDETAILS where CLI_ID = "
                + Integer.parseInt(init.getUid())
                + " AND ORDEM_ID = " + Integer.parseInt(ncard)));

        rowDetails.addAll(connectBD.mrows("SELECT ORDEM_ID, LIVROID FROM ORDDETAILS where CLI_ID = "
                + Integer.parseInt(init.getUid())
                + " AND ORDEM_ID = " + Integer.parseInt(ncard)));

        ArrayList<ArrayList<String>> collum = new ArrayList<>();

        collum.addAll(connectBD.mcolum("SELECT ORDEM_ID, LIVROID, QUANT FROM ORDDETAILS where CLI_ID = "
                + Integer.parseInt(init.getUid())
                + " AND ORDEM_ID = " + Integer.parseInt(ncard)));
        collum.addAll(connectBD.mrows("SELECT ORDEM_ID, LIVROID, QUANT FROM ORDDETAILS where CLI_ID = "
                + Integer.parseInt(init.getUid())
                + " AND ORDEM_ID = " + Integer.parseInt(ncard)));
        ;

        String titulo = "";
        for (int i = 0; i < collum.get(0).size(); i++) {
            for (int j = 0; j < collum.get(0).size(); j++) {
                if (collum.get(0).get(j).equalsIgnoreCase("LIVROID")) {
                    titulo = Livro.livro(Integer.parseInt(collum.get(1).get(j)), null).get(0).getTitulo();
                }
            }
            if (collum.get(0).get(i).equalsIgnoreCase("QUANT")) {
                rowDetails.remove(1);
                for (int j = 0; j < Integer.parseInt(collum.get(1).get(i)); j++) {
                    ArrayList<String> aux = new ArrayList<>();
                    aux.add(collum.get(1).get(0));
                    aux.add(titulo);

                    rowDetails.add(aux);
                }
            }

        }

        return new ModelAndView("redirect:/cliHome/cliProfile", model);

    }

    @GetMapping("/reqTroca/{id}/{livro}")
    public ModelAndView reqTroca(ModelMap model, @RequestParam Map<String, String> param,
            @PathVariable(value = "id") String ncard, @PathVariable(value = "id") String livro) {
        String troca = "create table TROCA (TROCA_id int primary key AUTO_INCREMENT, ordem_id int unique, valorTroca NUMERIC(20, 2));";
        connectBD.EXEquery(troca);

        troca = "insert into TROCA (ordem_id, valorTroca) values (" + Integer.parseInt(ncard) + ", "
                + Livro.livroCLIUID(Integer.parseInt(livro), 0).get(0).getPrecificacao() + ")";
        connectBD.EXEquery(troca);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    private void cliModel(Model model) {
        ArrayList<Cliente> array = Cliente.cliente(Integer.parseInt(uidcli), null);
        model.addAttribute("Cliente", array.get(0));

        ArrayList<Cartao> arrayCartao = Cartao.cartao(Integer.parseInt(uidcli), null);
        model.addAttribute("cartoes", arrayCartao);

        enderecos = Endereco.endereco(Integer.parseInt(uidcli), null);
        model.addAttribute("enderecos", enderecos);

        if (connectBD.mrows("SELECT * FROM ORDEM  where CLI_ID = " + Integer.parseInt(init.getUid())) != null) {
            row.clear();
            row.addAll(
                    connectBD.mcolum("SELECT ORDEM_ID, ENDERECO, TOTAL, STATUS FROM ORDEM  where CLI_ID = "
                            + Integer.parseInt(init.getUid())));

            row.addAll(connectBD.mrows("SELECT ORDEM_ID, ENDERECO, TOTAL, STATUS FROM ORDEM  where CLI_ID = "
                    + Integer.parseInt(init.getUid())));

            for (int i = 0; i < row.get(1).size(); i++) {
                if (row.get(0).get(i).equalsIgnoreCase("endereco")) {
                    for (Endereco arrayList : enderecos) {
                        if (arrayList.getIdEndereco() == Integer.parseInt(row.get(1).get(i))
                                && !row.get(1).get(i).equalsIgnoreCase(arrayList.getRua())) {
                            row.get(1).set(i, arrayList.getRua());
                        }
                    }
                }
            }
            System.out.println("endereco " + row);
            model.addAttribute("ORDEM", row);
        }

        if (!rowDetails.isEmpty()) {
            System.out.println("!rowDetails.isEmpty() " + rowDetails);
            model.addAttribute("details", rowDetails);
        }

    }

    @GetMapping("/add/cartao/form")
    public String addCartao() {

        return "cartaoPages/add";
    }

    @GetMapping("/add/endereco/form")
    public String addEndereco() {
        return "endPages/add";
    }

    @PostMapping("/add/endereco")
    public ModelAndView enderecoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        Endereco endereco = new Endereco(param);
        endereco.setCliuid(Integer.parseInt(init.getUid()));
        Endereco.InserirCBD(endereco);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @PostMapping("/add/cartao")
    public ModelAndView cartaoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        Cartao cartao = new Cartao(param);

        cartao.setCli_id(Integer.parseInt(init.getUid()));
        if (connectBD.EXE_Select("select * from cartao").isEmpty()) {
            cartao.setPreferencial(1);
        } else
            cartao.setPreferencial(0);

        Cartao.InserirCBD(cartao);
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }
}
