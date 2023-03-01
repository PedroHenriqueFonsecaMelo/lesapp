package fatec.ph.les.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.entidade.Endereco;

@Controller
@RequestMapping("/endereco")
public class enderecoController {

    @GetMapping("/singup/form")
    public String enderecoSingupForm(Model model, HttpServletRequest hRequest,
            @RequestParam(name = "uidcli", required = false) String uid) {

        System.out.println("uid4:: " + uid);
        System.out.println("uid5:: " + hRequest.getSession().getAttribute("uidcli"));

        model.addAttribute("uid", uid);

        return "endPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(HttpServletRequest request,
            @RequestParam Map<String, ?> param) {


        Endereco endereco2 = new Endereco(param);
        Endereco.InserirCBD(endereco2);
        System.out.println(endereco2.getCep());

        return new ModelAndView("redirect:/cartao");
    }

}
