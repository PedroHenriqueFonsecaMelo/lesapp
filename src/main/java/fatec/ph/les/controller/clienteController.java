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
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.connect.connectBD;
import fatec.ph.les.entidade.Cliente;

@Controller
@RequestMapping("/cliHome")
public class clienteController {

    @PostMapping("/singup")
    public ModelAndView insCli(@ModelAttribute Cliente firstName, ModelMap model) {

        System.out.println(
                "part 1:" + firstName.getEmail() + " :: " + firstName.getNome() + " :: " + firstName.getSenha());
        connectBD.CreateTableX(firstName.getClass());

        System.out.println("part 2:");
        Cliente.InserirCBD(firstName);

        // System.out.println(firstName.getClass().equals(Object.class));
        // Cliente.Inserir(clinome, clisenha, cliemail);

        return new ModelAndView("redirect:/endereco", model);
    }

    @GetMapping("/singup/form")
    public String CliForm() {

        // System.out.println(cacheManager.getCacheNames());
        return "cliPages/singup";
    }

    @PostMapping("/login")
    public String logCli() {

        // Cliente.Deletar(Integer.parseInt(uid));

        return "cliPages/login";
    }
}
