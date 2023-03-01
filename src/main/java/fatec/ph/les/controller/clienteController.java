package fatec.ph.les.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.servicos.connectBD;

@Controller
@RequestMapping("/cliHome")
public class clienteController {
    private String uidcli;

    @GetMapping("/singup/form")
    public String CliForm() {

        // System.out.println(cacheManager.getCacheNames());
        return "cliPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView insCli(ModelMap model, HttpServletRequest request, @RequestParam Map<String, ?> param) {

        System.out.println("part 1:");
        Cliente cli = new Cliente(param);
        Cliente.CreateTable();

        System.out.println("part 2:");
        Cliente.InserirCBD(cli);

        System.out.println("part 3:");
        uidcli = Cliente.cliUID(cli);
        model.addAttribute("uid", uidcli);

        request.getSession().setAttribute("uidcli", uidcli);

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

}
