package fatec.ph.les.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
    public String enderecoSingupForm(Model model, HttpServletRequest hRequest, ModelMap model2,
            @RequestParam String uid) {

        System.out.println("uid4:: " + uid);
        // System.out.println("uid5:: " + id2);

        model.addAttribute("uid", uid);

        return "endPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(HttpServletRequest request, @ModelAttribute Endereco endereco,
            @RequestParam Map<String, String> param) {

        // System.out.println(map.get("uid"));
        Map<String, String[]> parameterMap = request.getParameterMap();

        for (Map.Entry<String, String> entry : param.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }
        System.out.println("uid11:: " + endereco.getCep());
        System.out.println("uid12:: " + endereco.getCliuid());

        return new ModelAndView("redirect:/cartao");
    }

}
