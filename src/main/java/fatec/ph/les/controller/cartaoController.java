package fatec.ph.les.controller;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import fatec.ph.les.entidade.Cartao;

@Controller
@RequestMapping("/cartao")
public class cartaoController {
    private static String aux1;
    private String aux2;

    @GetMapping("/singup/form")
    public String cartaoSingupForm(HttpServletRequest hRequest, ModelMap map,
            RedirectAttributes redirectAttributes) {
        String emailId2 = "";

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(hRequest);

        emailId2 = (String) flashMap.get("flash_uid");

        System.out.println("flashMap /singup/form " + emailId2);

        hRequest.getSession().setAttribute("emailId2", emailId2);
        redirectAttributes.addFlashAttribute("flash_uid", emailId2);

        aux1 = emailId2;
        aux2 = emailId2;

        return "cartaoPages/singup";
    }

    @GetMapping("/singup/form2")
    public String cartaoSingupForm2(HttpServletRequest hRequest, ModelMap map) {

        aux1 = "0";
        aux2 = "0";

        return "cartaoPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        for (Entry<String, ?> iterable_element : param.entrySet()) {
            System.out.println(iterable_element.getKey() + " / " +
                    iterable_element.getValue() + " / "
                    + iterable_element.getValue().getClass().getSimpleName());
        }

        Cartao.CreateTable();
        Cartao cartao = new Cartao(param);

        cartao.setCli_id(Integer.parseInt(aux1));
        System.out.println(cartao.toString2());
        // Cartao.InserirCBD(cartao);
        redirectAttributes.addFlashAttribute("flash_uid", aux1);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }
}
