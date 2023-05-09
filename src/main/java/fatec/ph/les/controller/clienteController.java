package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;
import fatec.ph.les.servicos.manyTmany;

@Controller
@RequestMapping("/cliHome")
public class clienteController {

    private String uidcli = "1";
    ArrayList<Endereco> enderecos = new ArrayList<>();
    ArrayList<ArrayList<String>> row = new ArrayList<>();

    Map<String, ArrayList<String>> rowMapDetails = new HashMap<>();
    Map<String, ArrayList<String>> collumMapDetails = new HashMap<>();
    private String json1 = "";

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
        int i = 0;
        for (Entry<String, ?> iterable_element : param.entrySet()) {
            if (iterable_element.getValue().toString().contains("admin")) {
                i++;
            }
            if (i == param.size()) {
                init.setUid("admin");
                return new ModelAndView("redirect:/admin/admin", model);
            }
        }

        uidcli = Cliente.cliUID(null, param);
        init.setUid(uidcli);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/cliProfile")
    public String cliProfile(Model model, @RequestParam Map<String, ?> param) {
        if (init.getUid().equals("") || init.getUid().contains("admin")) {

        }
        switch (init.getUid()) {
            case "":
                return "cliPages/login";
            case "admin":
                toAdmin();
            default:
                break;
        }
        clear();
        cliModel(model);

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

    public void cliOrdens() {

        ArrayList<ArrayList<String>> tables = new ArrayList<>();
        ArrayList<String> indexamento = new ArrayList<>();

        indexamento.add("0");
        indexamento.add(manyTmany.GenericTable(row, 0));

        tables.add(indexamento);

        ArrayList<ArrayList<String>> rowSize = connectBD
                .mrows("Select DISTINCT ORDEM_ID FROM ORDDETAILS join LIVRO on IDLIVRO = LIVROID where CLI_ID =  "
                        + Integer.parseInt(init.getUid()));

        for (ArrayList<String> iterable_element : rowSize) {

            ArrayList<ArrayList<String>> rowToTable = new ArrayList<>();
            int i = Integer.parseInt(iterable_element.get(0));

            String query = "SELECT ORDEM_ID as IdenticadorPedido, IDLIVRO as IdenticadorLivro, TITULO, LIVRO.PRECIFICACAO as Preco_Por_Livro,"
                    + " ORDDETAILS.QUANT as Quantidade_Livros, (LIVRO.PRECIFICACAO * ORDDETAILS.QUANT) as Preco_Total_Livros"
                    + " FROM ORDDETAILS join LIVRO on IDLIVRO = LIVROID where CLI_ID =  "
                    + Integer.parseInt(init.getUid())
                    + " AND ORDEM_ID = " + i;

            rowToTable.addAll(connectBD.mcolum(query));
            rowToTable.addAll(connectBD.mrows(query));

            indexamento = new ArrayList<>();

            indexamento.add(String.valueOf(i));
            indexamento.add(manyTmany.GenericTable(rowToTable, i));

            tables.add(indexamento);

        }

        json1 = manyTmany.ArrayListToJson(tables);

    }

    @GetMapping("/reqTroca/{id}/{livro}/{quant}")
    public ModelAndView reqTroca(ModelMap model, @RequestParam Map<String, String> param,
            @PathVariable(value = "id") String ncard, @PathVariable(value = "livro") String titulo) {

        String troca = "create table TROCA (TROCA_id int primary key AUTO_INCREMENT, ordem_id int unique, valorTroca NUMERIC(20, 2));";
        connectBD.EXEquery(troca);

        Map<String, ArrayList<String>> Maptroca = connectBD
                .EXE_Map("select Precificacao from Livro where TITULO = '" + titulo + "';");

        troca = "insert into TROCA (ordem_id, valorTroca) values (" + Integer.parseInt(ncard) + ", "
                + Maptroca.get("PRECIFICACAO").get(0) + ")";
        connectBD.EXEquery(troca);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @PostMapping("/TrocaForm")
    public ModelAndView TrocaFormTable(ModelMap model, @RequestParam Map<String, ?> param) {

        Map<String, ArrayList<String>> Maptroca = connectBD
                .EXE_Map("select Precificacao from Livro where idlivro = '" + param.get("livroid").toString() + "';");

        connectBD.EXEquery(
                "insert into TROCA (ordem_id, QUANTIDADE_TROCA ,valorTroca) values ("
                        + Integer.parseInt(param.get("ordem_id").toString())
                        + ", "
                        + param.get("trocaQuant")
                        + ", "
                        + Maptroca.get("PRECIFICACAO").get(0) + ")");

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    private void cliModel(Model model) {
        ArrayList<Cliente> array = Cliente.cliente(Integer.parseInt(uidcli), null);
        model.addAttribute("Cliente", array.get(0));

        ArrayList<Cartao> arrayCartao = Cartao.cartao(Integer.parseInt(uidcli), null);
        model.addAttribute("cartoes", arrayCartao);

        enderecos = Endereco.endereco(Integer.parseInt(uidcli), null);
        model.addAttribute("enderecos", enderecos);

        if (connectBD.mrows("SELECT * FROM ORDEM  where CLI_ID = " + Integer.parseInt(init.getUid())) != null
                && !connectBD.mrows("SELECT * FROM ORDEM  where CLI_ID = " + Integer.parseInt(init.getUid()))
                        .isEmpty()) {
            row.clear();
            row.addAll(
                    connectBD.mcolum("SELECT ORDEM_ID, DATA_PEDIDO, ENDERECO, TOTAL, STATUS FROM ORDEM  where CLI_ID = "
                            + Integer.parseInt(init.getUid())));

            row.addAll(connectBD.mrows(
                    "SELECT ORDEM_ID, DATA_PEDIDO, RUA, TOTAL, STATUS FROM ORDEM join Endereco on endereco = IDENDERECO where CLI_ID = "
                            + Integer.parseInt(init.getUid())));

            model.addAttribute("ORDEM", row);
            cliOrdens();

        } else {
            row.add(null);
            row.add(null);
        }

        if (!json1.equalsIgnoreCase("") && !json1.isEmpty()) {
            model.addAttribute("table", json1);
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

    private static ModelAndView toAdmin() {
        return new ModelAndView("redirect:/admin/admin");
    }
}
