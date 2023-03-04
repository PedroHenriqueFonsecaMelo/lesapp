package fatec.ph.les.controller;

import java.util.Map;
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

import fatec.ph.les.entidade.Endereco;

@Controller
@RequestMapping("/endereco")
public class enderecoController {
    private static String aux1;
    private String aux2;

    @GetMapping("/singup/form")
    public String enderecoSingupForm(HttpServletRequest request, ModelMap map,
            RedirectAttributes redirectAttributes) {
        String emailId2 = "";

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            emailId2 = (String) flashMap.get("flash_uid");
        } else
            emailId2 = " ";

        // System.out.println("flashMap /singup/form " + emailId2);
        // System.out.println("request: " +
        // request.getSession().getAttribute("uidcli"));

        // request.getSession().setAttribute("emailId2", emailId2);
        redirectAttributes.addFlashAttribute("flash_uid", emailId2);

        aux1 = emailId2;
        aux2 = emailId2;

        return "endPages/singup";
    }

    @GetMapping("/singup/form2")
    public String enderecoSingupForm2(HttpServletRequest hRequest, ModelMap map) {
        String emailId2 = "";

        // System.out.println("flashMap /singup/form " + emailId2);

        // hRequest.getSession().setAttribute("emailId2", emailId2);
        // redirectAttributes.addFlashAttribute("flash_uid", emailId2);

        aux1 = emailId2;
        aux2 = emailId2;

        return "endPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        // System.out.println("flashMap /singup " + str);
        /*
         * for (Entry<String, ?> iterable_element : param.entrySet()) {
         * System.out.println(iterable_element.getKey() + " / " +
         * iterable_element.getValue() + " / "
         * + iterable_element.getValue().getClass().getSimpleName());
         * }
         */
        Endereco.CreateTable();
        Endereco endereco = new Endereco(param);
        endereco.setCliuid(Integer.parseInt(aux1));
        Endereco.InserirCBD(endereco);
        redirectAttributes.addFlashAttribute("flash_uid", aux1);

        return new ModelAndView("redirect:/cartao/singup/form", model);
    }

}
