package fatec.ph.les.controller;

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

import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.servicos.connectBD;

@Controller
@RequestMapping("/cliHome")

public class clienteController {
    private String uidcli;

    @GetMapping("/singup/form")
    public String CliForm() {

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

        System.out.println("cliPages part 1:");

        Cliente.CreateTable();

        System.out.println("cliPages part 2:");

        Cliente.InserirCBD(cliente);
        System.out.println("To string " + cliente.toString2());

        System.out.println("cliPages part 3:");

        uidcli = Cliente.cliUID(cliente);

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

        connectBD.EXE_Select(Cliente.class, Integer.parseInt(uidcli), null);
        System.out.println("uidcli " + uidcli);

        // Cliente.Deletar(Integer.parseInt(uid));

        return "cliPages/cliProfile";
    }

}
