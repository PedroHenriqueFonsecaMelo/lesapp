package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

        for (Entry<String, ?> iterable_element : param.entrySet()) {
            System.out.println(iterable_element.getKey() + " / " +
                    iterable_element.getValue() + " / "
                    + iterable_element.getValue().getClass().getSimpleName());
        }

        Cliente cliente = new Cliente(param);
        Cliente.InserirCBD(cliente);
        uidcli = Cliente.cliUID(cliente);

        init.setUid(uidcli);

        // model.addAttribute("model_uid", uidcli);
        redirectAttributes.addFlashAttribute("flash_uid", uidcli);
        // request.getSession().setAttribute("uidcli", uidcli);

        return new ModelAndView("redirect:/endereco/singup/form", model);
    }

    @GetMapping("/login")
    public String logCli(Model model, @SessionAttribute(name = "uidcli", required = false) String uid) {

        model.addAttribute("uid", uidcli);
        System.out.println("part 5:  ");
        System.out.println(model.asMap().get("uid"));
        System.out.println(uid);

        // Cliente.Deletar(Integer.parseInt(uid));

        return "cliPages/login";
    }

    @GetMapping("/cliProfile")
    public String cliProfile(Model model, @SessionAttribute(name = "uidcli", required = false) String uid) {

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

}
