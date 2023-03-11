package fatec.ph.les.controller;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

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

import fatec.ph.les.entidade.Cartao;

@Controller
@RequestMapping("/cartao")
public class cartaoController {
    private static String aux1;
    private int aux2 = 0;

    @GetMapping("/singup/form")
    public String cartaoSingupForm(HttpServletRequest hRequest, ModelMap map,
            RedirectAttributes redirectAttributes) {
        String emailId2 = "";

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(hRequest);
        if (flashMap != null) {
            emailId2 = (String) flashMap.get("flash_uid");
        } else
            emailId2 = " ";

        // System.out.println("flashMap /singup/form " + emailId2);

        hRequest.getSession().setAttribute("emailId2", emailId2);
        redirectAttributes.addFlashAttribute("flash_uid", emailId2);

        aux1 = emailId2;

        return "cartaoPages/singup";
    }

    @PostMapping("/singup")
    public ModelAndView enderecoSingup(@RequestParam Map<String, ?> param,
            ModelMap model, RedirectAttributes redirectAttributes) {

        /*
         * for (Entry<String, ?> iterable_element : param.entrySet()) {
         * System.out.println(iterable_element.getKey() + " / " +
         * iterable_element.getValue() + " / "
         * + iterable_element.getValue().getClass().getSimpleName());
         * }
         */

        Cartao cartao = new Cartao(param);

        cartao.setCli_id(Integer.parseInt(aux1));
        if (aux2 == 0) {
            cartao.setPreferencial(1);
            aux2 = aux2 + 1;
        } else
            cartao.setPreferencial(0);

        // System.out.println(cartao.toString2());
        Cartao.InserirCBD(cartao);

        redirectAttributes.addFlashAttribute("flash_uid", aux1);

        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable(value = "id") String ncard, ModelMap model,
            @RequestParam Map<String, String> param) {
        TreeMap<String, String> ls = new TreeMap<>();

        for (Entry<String, String> iterable_element : param.entrySet()) {
            System.out.println(iterable_element.getKey() + " ZZ " + iterable_element.getValue());

        }
        if (!param.containsKey("preferencial")) {
            param.put("preferencial", "0");
        }
        ls.putAll(param);

        Cartao.update(Integer.parseInt(ncard), (NavigableMap<String, String>) ls);
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView del(@PathVariable(value = "id") String ncard, ModelMap model,
            @RequestParam Map<String, String> param) {

        Cartao.Deletar(Integer.parseInt(ncard));
        return new ModelAndView("redirect:/cliHome/cliProfile", model);
    }
}
