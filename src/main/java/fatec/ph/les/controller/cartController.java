package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/cart")
public class cartController {
    private ArrayList<Integer> lista = new ArrayList<>();
    private ArrayList<Livro> ls = new ArrayList<>();
    ArrayList<Cartao> arrayCartao = new ArrayList<>();
    ArrayList<Cartao> arrayCartao2 = new ArrayList<>();
    private NavigableMap<String, String> map = new TreeMap<>();
    private boolean mudanca = false;
    private String uid;

    @GetMapping("/cartAdd/{id}")
    public ModelAndView cartAdd(@PathVariable(value = "id") int id, ModelMap model) {
        System.out.println("/cartAdd/{id} " + id);
        System.out.println("init.getUid() " + init.getUid());
        uid = init.getUid();
        lista.add(id);
        mudanca = true;
        return new ModelAndView("redirect:/shop", model);
    }

    @GetMapping("/cartTotal")
    public String cartTotal(ModelMap model) {
        if (mudanca) {
            for (int i = 0; i <= lista.size() - 1; i++) {
                ls.addAll(Livro.livroCLIUID(lista.get(i), 0));
            }

            if (arrayCartao.isEmpty()) {
                map.put("cli_id", uid);
                map.put("preferencial", "1");

                arrayCartao = Cartao.cartao(Integer.valueOf(init.getUid()), map);
                arrayCartao2 = Cartao.cartaoCLIUID(Integer.valueOf(init.getUid()));

            } else {
                map.clear();
                arrayCartao.addAll(Cartao.cartaoCLIUID(Integer.valueOf(init.getUid())));
            }
            mudanca = false;

        }
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);

        return "cartPages/cartTotal";
    }

    @GetMapping("/addCartaoCart/{id}")
    public String addCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        map.clear();
        map.put("ncartao", ncard);

        arrayCartao.addAll(Cartao.cartao(Integer.valueOf(init.getUid()), map));

        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        return "cartPages/cartTotal";
    }

    @GetMapping("/removeCartaoCart/{id}")
    public String removeCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        for (int i = 0; i <= arrayCartao.size() - 1; i++) {
            if (arrayCartao.get(i).getNcartao() == Integer.parseInt(ncard)) {
                arrayCartao.remove(i);
            }
        }
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        return "cartPages/cartTotal";
    }

    @GetMapping("/removeLivroCart/{id}")
    public String removeLivroCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        for (int i = 0; i <= lista.size() - 1; i++) {
            if (lista.get(i) == Integer.parseInt(ncard)) {
                arrayCartao.remove(i);
            }
        }

        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        return "cartPages/cartTotal";
    }

}
