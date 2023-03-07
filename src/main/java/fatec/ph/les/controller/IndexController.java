package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import fatec.ph.les.entidade.Livro;

@Controller
public class IndexController {
    private String userid;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            userid = (String) flashMap.get("flash_uid");
        } else
            userid = " ";

        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model, HttpServletRequest request) {
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (flashMap != null) {
            userid = (String) flashMap.get("flash_uid");
        } else
            userid = " ";

        ArrayList<Livro> ls = Livro.livroCLIUID(0, 0);

        ArrayList<Livro> top = new ArrayList<>();
        top.addAll(ls.subList(0, (ls.size() / 2)));

        ArrayList<Livro> bottom = new ArrayList<>();
        bottom.addAll(ls.subList((ls.size() / 2), ls.size()));

        model.addAttribute("livros", top);
        model.addAttribute("livros2", bottom);
        return "shopPages/shop";
    }
}
