package fatec.ph.les.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

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
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/cart")
public class cartController {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private Map<Livro, Integer> ls = new HashMap<>();
    private Map<Cartao, Integer> arrayCartao = new HashMap<>();
    private NavigableMap<String, String> map = new TreeMap<>();

    private boolean mudanca = false;
    private String uid;
    private float total = 0;

    ArrayList<ArrayList<String>> cupon = new ArrayList<>();
    private ArrayList<Integer> lista = new ArrayList<>();
    ArrayList<Endereco> enderecos2 = new ArrayList<>();
    private ArrayList<Cartao> arrayCartao2 = new ArrayList<>();
    ArrayList<Endereco> enderecos = new ArrayList<>();
    private ArrayList<Endereco> arrayEndereco = new ArrayList<>();

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

        enderecos.addAll(Endereco.endereco(Integer.parseInt(init.getUid()), null));
        for (Endereco cartao : enderecos) {
            if (!cartao.equals(enderecos.get(0))) {
                enderecos2.add(cartao);
            }
        }
        if (connectBD.mrows("select * from cupons where CLI_ID   = " + init.getUid()) != null) {
            cupon.clear();
            cupon.addAll(connectBD.mcolum("select * from cupons where CLI_ID   = " + init.getUid()));
            cupon.addAll(connectBD.mrows("select * from cupons where CLI_ID   = " + init.getUid()));

        }

        modelagem(model);

        return "cartPages/cartTotal";
    }

    private void modelagem(ModelMap model) {
        model.addAttribute("enderecoPrincipal", enderecos.get(0));
        model.addAttribute("outrosEnderecos", enderecos2);
        model.addAttribute("Total", df.format(total));
        model.addAttribute("TotalPorCartao", total / arrayCartao.size());
        model.addAttribute("livros", ls);
        model.addAttribute("cartoes", arrayCartao);
        model.addAttribute("cartoes2", arrayCartao2);
        model.addAttribute("cupon", cupon);
    }

    @GetMapping("/addCartaoCart/{id}")
    public String addCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        map.clear();
        map.put("ncartao", ncard);
        Cartao aux = new Cartao();

        for (Cartao cartao : arrayCartao2) {
            if (cartao.getNcartao() == Integer.parseInt(ncard)) {
                arrayCartao.put(cartao, 0);
                aux = cartao;
            }
        }

        arrayCartao2.remove(aux);

        modelagem(model);

        return "cartPages/cartTotal";
    }

    @GetMapping("/removeCartaoCart/{id}")
    public String removeCartaoCart(@PathVariable(value = "id") String ncard, ModelMap model) {
        map.clear();

        Cartao aux = new Cartao();

        for (Entry<Cartao, Integer> cartao : arrayCartao.entrySet()) {
            if (cartao.getKey().getNcartao() == Integer.valueOf(ncard)) {
                aux = cartao.getKey();
            }
        }
        arrayCartao2.add(aux);
        arrayCartao.remove(aux);

        modelagem(model);
        return "cartPages/cartTotal";
    }

    @GetMapping("/removeLivroCart/{id}")
    public String removeLivroCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        for (int i = 0; i <= lista.size() - 1; i++) {
            if (lista.get(i) == Integer.parseInt(ncard)) {
                ls.remove(Livro.livroCLIUID(i, 0).get(0));
            }
        }

        modelagem(model);

        return "cartPages/cartTotal";
    }

    @PostMapping("/order")
    public ModelAndView order(@RequestParam Map<String, ?> param, ModelMap model) {
        float totalCart = 0;
        System.out.println(param);
        for (Entry<String, ?> cartao : param.entrySet()) {

            if (cartao.getKey().contains("in")) {
                totalCart = totalCart + Float.parseFloat(cartao.getValue().toString());
            }
            if (cartao.getKey().equalsIgnoreCase("cupon")) {
                for (ArrayList<String> cartao2 : cupon) {
                    for (int i = 0; i < cartao2.size(); i++) {
                        if (cartao2.get(i).toString().equalsIgnoreCase(cartao.getValue().toString())) {
                            totalCart = totalCart - Float.parseFloat(cartao.getValue().toString());
                            total = total - Float.parseFloat(cartao.getValue().toString());
                        }
                    }
                }

            }
            if (cartao.getKey().contains("in") && totalCart == total) {
                execCart(param);
            }
        }

        clear();

        return new ModelAndView("redirect:/shop", model);
    }

    private void execCart(Map<String, ?> param) {
        String query = "create table ordem (ordem_id int primary key AUTO_INCREMENT, cli_id int, total NUMERIC(20,2), status VARCHAR(100), endereco VARCHAR(100));";
        connectBD.EXEquery(query);

        String query2 = "create table ordDetails (details_id int primary key AUTO_INCREMENT, cli_id int, ordem_id int, livroid int, quant int);";
        connectBD.EXEquery(query2);

        String query3 = "create table ordPay (pay_id int primary key AUTO_INCREMENT, cli_id int, ordem_id int, cartaoid int, valor NUMERIC(20, 2));";
        connectBD.EXEquery(query3);

        if (param.containsKey("cupon")) {
            if (param.get("cupon").toString() != "" && param.get("cupon") != null) {
                total = total - Float.parseFloat(param.get("cupon").toString());
                total = Float.parseFloat(df.format(total).replace(",", "."));
            }
        }

        String insertOrder = "insert into ordem ( cli_id, total, status, endereco) values (" + init.getUid()
                + ", " + total + ", 'EM PROCESSAMENTO' , '" + param.get("endereco") + "');";

        connectBD.EXEquery(insertOrder);

        String orderID = connectBD.EXE_Select_UID("select * from ordem where cli_id = " + init.getUid()
                + " AND total = " + total + " AND endereco = " + param.get("endereco"));

        insertDetails(orderID);

        insertpay(param, orderID);
    }

    private void insertDetails(String orderID) {
        for (Entry<Livro, Integer> iterable_element : ls.entrySet()) {
            String insertDetails = "insert into ordDetails ( cli_id, ordem_id, livroid, quant) values ("
                    + Integer.parseInt(init.getUid()) + ", " + orderID + ", "
                    + iterable_element.getKey().getIdlivro() + ", " + iterable_element.getValue()
                    + ");";
            connectBD.EXEquery(insertDetails);

        }
    }

    private void insertpay(Map<String, ?> param, String orderID) {
        for (Entry<Cartao, Integer> cartao : arrayCartao.entrySet()) {
            String insertPay = "";
            if (param.containsKey("cupon") && param.get("cupon") != "") {
                insertPay = "insert into ordPay (cli_id , ordem_id, cartaoid, valor) values ("
                        + Integer.parseInt(init.getUid()) + ", " + Integer.parseInt(orderID) + ", " +
                        cartao.getKey().getNcartao()
                        + ", " +
                        Float.parseFloat(df.format(
                                Float.parseFloat(param.get("in" + cartao.getKey().getNcartao()).toString())
                                        - (Float.parseFloat(param.get("cupon").toString()) / arrayCartao.size()))
                                .replace(",", "."))

                        + ");";
            } else {
                insertPay = "insert into ordPay (cli_id , ordem_id, cartaoid, valor) values ("
                        + Integer.parseInt(init.getUid()) + ", " + Integer.parseInt(orderID) + ", " +
                        cartao.getKey().getNcartao()
                        + ", " + param.get("in" + cartao.getKey().getNcartao()) + ");";
            }
            connectBD.EXEquery(insertPay);
        }
    }

    private void clear() {
        ls.clear();
        arrayCartao.clear();
        arrayCartao2.clear();
        total = 0;
        enderecos2.clear();
        enderecos.clear();
        arrayEndereco.clear();
        lista.clear();
        map.clear();
        cupon.clear();
    }

}
