package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.ModelMapExtensionsKt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Categoria;
import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/cliHome")

public class clienteController {
    private String uidcli = "1";

    @GetMapping("/singup/form")
    public String CliForm() {

        connectBD.CreateTableX(Cliente.class);
        connectBD.CreateTableX(Endereco.class);
        connectBD.CreateTableX(Cartao.class);
        connectBD.CreateTableX(Livro.class);
        connectBD.CreateTableX(Categoria.class);

        return "cliPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView insCli(ModelMap model, @RequestParam Map<String, ?> param,
            RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Cliente cliente = new Cliente(param);
        Cliente.InserirCBD(cliente);
        uidcli = Cliente.cliUID(cliente);

        init.setUid(uidcli);

        // model.addAttribute("model_uid", uidcli);
        redirectAttributes.addFlashAttribute("flash_uid", uidcli);
        // request.getSession().setAttribute("uidcli", uidcli);

        return new ModelAndView("redirect:/endereco/singup/form", model);
    }

    @GetMapping("/login/form")
    public String logCli(Model model, @RequestParam Map<String, ?> param) {
        // Cliente.Deletar(Integer.parseInt(uid));

        return "cliPages/login";
    }

    @PostMapping("/login")
    public ModelAndView loginCli(ModelMap model, @RequestParam Map<String, ?> param) {

        uidcli = Cliente.cliUID(null, param);
        init.setUid(uidcli);
        System.out.println(init.getUid());
        // Cliente.Deletar(Integer.parseInt(uid));

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/cliProfile")
    public String cliProfile(Model model, @RequestParam Map<String, ?> param) {

        // connectBD.EXE_Select(Cliente.class, Integer.parseInt(uidcli), null);

        // NavigableMap<String, String> map = new TreeMap<String, String>();
        // map.put("senha", "2");

        ArrayList<Cliente> array = Cliente.cliente(Integer.parseInt(uidcli), null);
        model.addAttribute("Cliente", array.get(0));

        ArrayList<Cartao> arrayCartao = Cartao.cartao(Integer.parseInt(uidcli), null);
        model.addAttribute("cartoes", arrayCartao);

        ArrayList<Endereco> enderecos = Endereco.endereco(Integer.parseInt(uidcli), null);
        model.addAttribute("enderecos", enderecos);

        // Cliente.Deletar(Integer.parseInt(uid));

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

}
