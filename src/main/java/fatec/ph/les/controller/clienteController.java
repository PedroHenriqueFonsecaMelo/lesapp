package fatec.ph.les.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

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
    public ModelAndView insCli(@ModelAttribute Cliente firstName, ModelMap model, Model m2) {

        // System.out.println("part 1:" + firstName.getEmail() + " :: " +
        // firstName.getNome() + " :: " + firstName.getSenha());
        connectBD.CreateTableX(firstName.getClass());

        System.out.println("part 2:");
        Cliente.InserirCBD(firstName);

        System.out.println("part 3:");

        uidcli = Cliente.cliUID(firstName);

        model.addAttribute("uid", uidcli);

        // model.addFlashAttribute("uid", Cliente.cliUID(firstName));

        // System.out.println(firstName.getClass().equals(Object.class));
        // Cliente.Inserir(clinome, clisenha, cliemail);

        return new ModelAndView("redirect:/endereco/singup/form", model);
    }

    @GetMapping("/login")
    public String logCli(Model model) {

        model.addAttribute("uid", uidcli);
        System.out.println("part 5:  ");
        System.out.println(model.asMap().get("uid"));

        // Cliente.Deletar(Integer.parseInt(uid));

        return "cliPages/login";
    }
}
