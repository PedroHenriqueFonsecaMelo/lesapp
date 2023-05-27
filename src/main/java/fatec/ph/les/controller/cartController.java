package fatec.ph.les.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    private float total = 0;
    private double frete = 0;

    ArrayList<ArrayList<String>> cupon = new ArrayList<>();

    private ArrayList<Integer> lista = new ArrayList<>();
    ArrayList<Endereco> enderecos2 = new ArrayList<>();
    private ArrayList<Cartao> arrayCartao2 = new ArrayList<>();
    ArrayList<Endereco> enderecos = new ArrayList<>();
    private ArrayList<Endereco> arrayEndereco = new ArrayList<>();

    @GetMapping("/cartAdd/{id}/{quant}")
    public ModelAndView cartAdd(@PathVariable(value = "id") int id, @PathVariable(value = "quant") int quant,
            ModelMap model) {

        if (!ls.isEmpty()) {

            for (Entry<Livro, Integer> iterable_element : ls.entrySet()) {
                if (Livro.livroCLIUID(id, 0).get(0).equals(iterable_element.getKey())) {

                    ls.replace(iterable_element.getKey(), iterable_element.getValue() + quant);

                    total = total + (quant * Livro.livroCLIUID(id, 0).get(0).getPrecificacao());
                    frete = 0.05 * total;

                } else {
                    ls.put(Livro.livroCLIUID(id, 0).get(0), quant);
                    total = total + (quant * Livro.livroCLIUID(id, 0).get(0).getPrecificacao());
                    frete = 0.05 * total;
                }
            }
        } else {
            ls.put(Livro.livroCLIUID(id, 0).get(0), quant);
            total = total + (quant * Livro.livroCLIUID(id, 0).get(0).getPrecificacao());
            frete = 0.05 * total;
        }
        mudanca = true;

        return new ModelAndView("redirect:/shop", model);
    }

    @GetMapping("/cartTotal")
    public String cartTotal(ModelMap model) {
        if (mudanca) {

            if (arrayCartao.isEmpty()) {
                map.put("cli_id", init.getUid());
                map.put("preferencial", "1");

                arrayCartao.put(Cartao.cartao(0, map).get(0), 1);

                map.clear();

                map.put("cli_id", init.getUid());
                map.put("preferencial", "0");

                arrayCartao2 = Cartao.cartaoCLIUID(0, map);

                if (arrayEndereco.isEmpty()) {
                    arrayEndereco.addAll(Endereco.endereco(Integer.valueOf(init.getUid()), null));
                } else {
                    arrayEndereco.clear();
                    arrayEndereco.addAll(Endereco.endereco(Integer.valueOf(init.getUid()), null));
                }

            } else {
                map.clear();

            }

            mudanca = false;

        }

        enderecos.clear();
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

        model.addAttribute("Total", df.format(total + frete));

        model.addAttribute("TotalPorCartao", df.format((total + frete) / arrayCartao.size()).replace(",", "."));

        model.addAttribute("livros", ls);

        model.addAttribute("cartoes", arrayCartao);

        model.addAttribute("cartoes2", arrayCartao2);

        StringBuilder cuponBuilder = new StringBuilder();
        StringBuilder cuponBuilder2 = new StringBuilder();

        List<ArrayList<String>> cupon1metade = cupon.subList(1, (int) Math.ceil(cupon.size() / 2) + 1);
        List<ArrayList<String>> cupon2metade = cupon.subList((int) Math.ceil(cupon.size() / 2.0), cupon.size());

        for (int i = 0; i < cupon1metade.size(); i++) {
            cuponBuilder.append("<option value='");
            cuponBuilder.append(cupon1metade.get(i).get(2) + "'>");
            cuponBuilder.append(cupon1metade.get(i).get(2) + "</option>");

            cuponBuilder2.append("<option value='");
            cuponBuilder2.append(cupon2metade.get(i).get(2) + "'>");
            cuponBuilder2.append(cupon2metade.get(i).get(2) + "</option>");

        }

        model.addAttribute("cupon", cuponBuilder.toString());
        model.addAttribute("cupon2", cuponBuilder2.toString());

        model.addAttribute("cuponSize", cupon1metade.size());
        model.addAttribute("cuponSize2", cupon2metade.size());

        model.addAttribute("frete", df.format(frete));

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
    public ModelAndView removeLivroCart(@PathVariable(value = "id") String ncard, ModelMap model) {

        for (Entry<Livro, Integer> cartao : ls.entrySet()) {

            if (cartao.getKey().getIdlivro() == Integer.parseInt(ncard)) {
                ls.remove(Livro.livroCLIUID(Integer.parseInt(ncard), 0).get(0));
                total = total - (cartao.getKey().getPrecificacao() * cartao.getValue());
            }

        }

        return new ModelAndView("redirect:/cart/cartTotal", model);
    }

    @PostMapping("/order")
    public ModelAndView order(@RequestParam Map<String, ?> param, ModelMap model) {
        float totalCart = 0;

        for (Entry<String, ?> cartao : param.entrySet()) {

            if (cartao.getKey().contains("in")) {
                totalCart = totalCart + Float.parseFloat(cartao.getValue().toString());
            }
            if (cartao.getKey().equalsIgnoreCase("cupon") || cartao.getKey().equalsIgnoreCase("cupon2")) {
                for (ArrayList<String> cartao2 : cupon) {
                    for (int i = 0; i < cartao2.size(); i++) {
                        if (cartao2.get(i).toString().equalsIgnoreCase(cartao.getValue().toString())) {

                            // totalCart = totalCart - Float.parseFloat(cartao.getValue().toString());
                            // total = total - Float.parseFloat(cartao.getValue().toString());

                            connectBD.EXEquery("delete from cupons where CUPONS_ID = " + cartao2.get(0) + ";");

                        }
                    }
                }

            }

        }

        double d = total + frete;
        double abs = Math.abs(totalCart - d);

        if (-abs <= 0 && totalCart <= d && abs <= 0.01) {
            execCart(param);
            clear();
        } else {
            return new ModelAndView("redirect:/cart/cartTotal", model);
        }

        return new ModelAndView("redirect:/shop", model);
    }

    private void execCart(Map<String, ?> param) {

        if (param.containsKey("cupon")) {
            if (param.get("cupon").toString() != "" && param.get("cupon") != null) {
                total = total - Float.parseFloat(param.get("cupon").toString());
                total = Float.parseFloat(df.format(total).replace(",", "."));
            }
        }
        if (param.containsKey("cupon")) {
            if (param.get("cupon2").toString() != "" && param.get("cupon2") != null) {
                total = total - Float.parseFloat(param.get("cupon2").toString());
                total = Float.parseFloat(df.format(total).replace(",", "."));
            }
        }

        String insertOrder = "insert into ordem ( cli_id, total, status, endereco, data_pedido) values ("
                + init.getUid()
                + ", " + total + ", 'EM PROCESSAMENTO' , '" + param.get("endereco") + "', '"
                + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "');";

        connectBD.EXEquery(insertOrder);

        String orderID = String
                .valueOf(connectBD.mrows("select * from ordem where cli_id = " + init.getUid()).size());

        insertDetails(orderID);

        insertpay(param, orderID);
    }

    private void insertDetails(String orderID) {
        for (Entry<Livro, Integer> iterable_element : ls.entrySet()) {
            String insertDetails = "insert into ordDetails ( cli_id, ordem_id, livroid, quant) values ("
                    + Integer.parseInt(init.getUid()) + ", " + orderID + ", "
                    + iterable_element.getKey().getIdlivro() + ", " + iterable_element.getValue()
                    + ");";
            Map<String, ArrayList<String>> map = connectBD
                    .EXE_Map("Select quant from LIVRO where IDLIVRO = " + iterable_element.getKey().getIdlivro());
            Entry<String, ArrayList<String>> entry = map.entrySet().iterator().next();

            String updateLivro = "update LIVRO set quant = "
                    + (Integer.parseInt(entry.getValue().get(0)) - iterable_element.getValue()) + " where IDLIVRO =  "
                    + iterable_element.getKey().getIdlivro() + ");";

            connectBD.EXEquery(updateLivro);
            connectBD.EXEquery(insertDetails);

        }
    }

    private void insertpay(Map<String, ?> param, String orderID) {

        for (Entry<Cartao, Integer> cartao : arrayCartao.entrySet()) {
            String insertPay = "";
            float cupon = 0;
            float cupon2 = 0;

            if (param.containsKey("cupon") && param.get("cupon") != ""
                    && param.containsKey("cupon2") && param.get("cupon2") != "") {
                cupon = Float.parseFloat(param.get("cupon").toString());
                cupon2 = Float.parseFloat(param.get("cupon2").toString());

                insertPay = "insert into ordPay (cli_id , ordem_id, cartaoid, valor) values ("
                        + Integer.parseInt(init.getUid()) + ", " + Integer.parseInt(orderID) + ", " +
                        cartao.getKey().getNcartao()
                        + ", " +
                        Float.parseFloat(df.format(
                                Float.parseFloat(param.get("in" + cartao.getKey().getNcartao()).toString())
                                        - ((cupon + cupon2) / arrayCartao.size()))
                                .replace(",", "."))

                        + ");";

            } else if (param.containsKey("cupon") && param.get("cupon") != "") {
                cupon = Float.parseFloat(param.get("cupon").toString());

                insertPay = "insert into ordPay (cli_id , ordem_id, cartaoid, valor) values ("
                        + Integer.parseInt(init.getUid()) + ", " + Integer.parseInt(orderID) + ", " +
                        cartao.getKey().getNcartao()
                        + ", " +
                        Float.parseFloat(df.format(
                                Float.parseFloat(param.get("in" + cartao.getKey().getNcartao()).toString())
                                        - (cupon / arrayCartao.size()))
                                .replace(",", "."))

                        + ");";
            } else if (param.containsKey("cupon") && param.get("cupon") != "") {
                cupon2 = Float.parseFloat(param.get("cupon2").toString());

                insertPay = "insert into ordPay (cli_id , ordem_id, cartaoid, valor) values ("
                        + Integer.parseInt(init.getUid()) + ", " + Integer.parseInt(orderID) + ", " +
                        cartao.getKey().getNcartao()
                        + ", " +
                        Float.parseFloat(df.format(
                                Float.parseFloat(param.get("in" + cartao.getKey().getNcartao()).toString())
                                        - (cupon2 / arrayCartao.size()))
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

    @GetMapping("/getCartaoADD")
    public String getCartaoADD() {
        return "cartPages/cartaoCart";
    }

    @GetMapping("/getEnderecoADD")
    public String getEnderecoADD() {

        return "cartPages/enderecoCart";
    }

    @PostMapping("/postCartaoADD")
    public ModelAndView postCartaoADD(ModelMap model, @RequestParam Map<String, ?> param) {
        Cartao cartao = new Cartao(param);

        cartao.setCli_id(Integer.parseInt(init.getUid()));

        if (connectBD.EXE_Map("select * from cartao where CLI_ID = " + init.getUid()).isEmpty()) {
            cartao.setPreferencial(1);
        } else
            cartao.setPreferencial(0);

        Cartao.InserirCBD(cartao);

        arrayCartao2.add(cartao);

        mudanca = true;
        return new ModelAndView("redirect:/cart/cartTotal", model);
    }

    @PostMapping("/postEnderecoADD")
    public ModelAndView postEnderecoADD(ModelMap model, @RequestParam Map<String, ?> param) {

        Endereco endereco = new Endereco(param);
        endereco.setCliuid(Integer.parseInt(init.getUid()));
        Endereco.InserirCBD(endereco);

        mudanca = true;
        return new ModelAndView("redirect:/cart/cartTotal", model);
    }

}
