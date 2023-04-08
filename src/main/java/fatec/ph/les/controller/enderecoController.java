package fatec.ph.les.controller;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/singup/form")
    public String enderecoSingupForm(HttpServletRequest request, ModelMap map,
            RedirectAttributes redirectAttributes) {
        String emailId2 = "";

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            emailId2 = (String) flashMap.get("flash_uid");
        } else
            emailId2 = " ";

        // System.out.println("request: " +
        // request.getSession().getAttribute("uidcli"));

        // request.getSession().setAttribute("emailId2", emailId2);
        redirectAttributes.addFlashAttribute("flash_uid", emailId2);

        aux1 = emailId2;
        return "endPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        Endereco endereco = new Endereco(param);
        endereco.setCliuid(Integer.parseInt(aux1));
        // Endereco.cliUID(endereco);
        Endereco.InserirCBD(endereco);
        redirectAttributes.addFlashAttribute("flash_uid", aux1);

        return new ModelAndView("redirect:/cartao/singup/form", model);
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") String ncard, ModelMap model,
            @RequestParam Map<String, String> param) {
        TreeMap<String, String> ls = new TreeMap<>();
        ls.putAll(param);

        Endereco.update(Integer.parseInt(ncard), (NavigableMap<String, String>) ls);
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView del(@PathVariable(value = "id") String ncard, ModelMap model,
            @RequestParam Map<String, String> param) {

        Endereco.Deletar(Integer.parseInt(ncard));
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

}
