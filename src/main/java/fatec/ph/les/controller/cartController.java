package fatec.ph.les.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.entidade.Cartao;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/cart")
public class cartController {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ArrayList<Integer> lista = new ArrayList<>();
    private Map<Livro, Integer> ls = new HashMap<>();
    private Map<Cartao, Integer> arrayCartao = new HashMap<>();
    private ArrayList<Cartao> arrayCartao2 = new ArrayList<>();
    private NavigableMap<String, String> map = new TreeMap<>();
    private ArrayList<Endereco> arrayEndereco = new ArrayList<>();
    private boolean mudanca = false;
    private String uid;
    private float total = 0;

    @GetMapping("/cartAdd/{id}/{quant}")
    public ModelAndView cartAdd(@PathVariable(value = "id") int id, @PathVariable(value = "quant") int quant,
            ModelMap model) {

        uid = init.getUid();

        if (!ls.isEmpty()) {

            for (Entry<Livro, Integer> iterable_element : ls.entrySet()) {
                if (iterable_element.getKey().equals(Livro.livroCLIUID(id, 0).get(0))) {

                    ls.replace(iterable_element.getKey(), iterable_element.getValue() + quant);

                    total = total + (quant * Livro.livroCLIUID(id, 0).get(0).getPrecificacao());

                }
            }
        } else {

            ls.put(Livro.livroCLIUID(id, 0).get(0), quant);
            total = total + (quant * Livro.livroCLIUID(id, 0).get(0).getPrecificacao());
        }

        mudanca = true;
        System.out.println("<===========================================>");
        for (Entry<Livro, Integer> iterable_element : ls.entrySet()) {
            System.out.println(iterable_element.getKey() + " / " +
                    iterable_element.getValue() + " / "
                    + iterable_element.getValue().getClass().getSimpleName());
        }

        System.out.println("<===========================================>");
        return new ModelAndView("redirect:/shop", model);
    }

    @GetMapping("/cartTotal")
    public String cartTotal(ModelMap model) {
        if (mudanca) {

            if (arrayCartao.isEmpty()) {
                map.put("cli_id", uid);
                map.put("preferencial", "1");

                arrayCartao.put(Cartao.cartao(Integer.valueOf(init.getUid()), map).get(0), 1);

                map.clear();

                map.put("cli_id", uid);
                map.put("preferencial", "0");
                arrayCartao2 = Cartao.cartaoCLIUID(Integer.valueOf(init.getUid()), map);

                arrayEndereco.addAll(Endereco.endereco(Integer.valueOf(init.getUid()), null));

            } else {
                map.clear();
            }

            mudanca = false;

        }

        model.addAttribute("Total", df.format(total));
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        model.addAttribute("cartoes2", arrayCartao2);

        return "cartPages/cartTotal";
    }

    @GetMapping("/addCartaoCart/{id}")
    public String addCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        map.clear();
        map.put("ncartao", ncard);

        for (Cartao cartao : arrayCartao2) {
            if (cartao.getNcartao() == Integer.parseInt(ncard)) {
                arrayCartao.put(cartao, 0);
                arrayCartao2.remove(cartao);
            }
        }

        model.addAttribute("Total", total);
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        model.addAttribute("cartoes2", arrayCartao2);
        return "cartPages/cartTotal";
    }

    @GetMapping("/removeCartaoCart/{id}")
    public String removeCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {
        map.clear();
        map.put("ncartao", ncard);

        System.out.println(ncard);
        System.out.println(arrayCartao.get(Cartao.cartao(0, map).get(0)));
        arrayCartao.remove(Cartao.cartao(0, map).get(0));

        model.addAttribute("Total", total);
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        model.addAttribute("cartoes2", arrayCartao2);
        return "cartPages/cartTotal";
    }

    @GetMapping("/removeLivroCart/{id}")
    public String removeLivroCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        for (int i = 0; i <= lista.size() - 1; i++) {
            if (lista.get(i) == Integer.parseInt(ncard)) {
                ls.remove(Livro.livroCLIUID(i, 0).get(0));
            }
        }

        model.addAttribute("Total", total);
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        model.addAttribute("cartoes2", arrayCartao2);
        return "cartPages/cartTotal";
    }

    @PostMapping("/order")
    public String order(@RequestParam Map<String, ?> param) {
        // String query = "create table ORDER ()";
        for (Entry<String, ?> iterable_element : param.entrySet()) {
            System.out.println(iterable_element.getKey() + " / " +
                    iterable_element.getValue() + " / "
                    + iterable_element.getValue().getClass().getSimpleName());
        }
        return null;
    }

}
