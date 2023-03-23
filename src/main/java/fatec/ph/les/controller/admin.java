package fatec.ph.les.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

@Controller
@RequestMapping("/admin")
public class admin {
    ArrayList<ArrayList<String>> row = new ArrayList<>();
    ArrayList<ArrayList<String>> orderArray = new ArrayList<>();
    ArrayList<ArrayList<String>> trocaArray = new ArrayList<>();
    ArrayList<Cliente> enderecos = new ArrayList<>();
    Map<String, ArrayList<String>> mapa = new HashMap<>();
    float valorDividido = 0;

    @GetMapping("/admin")
    public String data(ModelMap map) {
        clear();
        System.out.println("@GetMapping admin");
        if (connectBD.mrows("select * from cliente;") != null) {
            row.addAll(connectBD.mcolum("select * from cliente;"));
            row.addAll(connectBD.mrows("select * from cliente;"));

            enderecos.addAll(Cliente.cliente(Integer.parseInt(init.getUid()), null));
        }
        if (connectBD.mrows("select * from ordem;") != null) {
            orderArray.addAll(connectBD.mcolum("select * from ordem;"));
            orderArray.addAll(connectBD.mrows("select * from ordem;"));
        } else {
            orderArray.add(null);
            orderArray.add(null);
        }

        if (connectBD.mrows("SELECT * FROM ORDEM  where CLI_ID = " + Integer.parseInt(init.getUid())) != null) {
            for (int i = 1; i < orderArray.get(1).size(); i++) {
                if (orderArray.get(0).get(i).equalsIgnoreCase("cli_id")
                        && orderArray.get(1).get(i).getClass().getSimpleName().equalsIgnoreCase("int")) {
                    for (Cliente arrayList : enderecos) {
                        if (arrayList.getIdCliente() == Integer.parseInt(orderArray.get(1).get(i))) {
                            row.get(1).set(i, arrayList.getNome());
                        }
                    }
                }
            }
        }

        if (connectBD.mrows("SELECT * FROM TROCA") != null) {
            trocaArray.addAll(connectBD.mcolum("SELECT * FROM TROCA"));
            trocaArray.addAll(connectBD.mrows("SELECT * FROM TROCA"));

        } else {
            trocaArray.add(null);
            trocaArray.add(null);
        }

        map.addAttribute("Clientes", row);
        map.addAttribute("Ordem", orderArray);
        map.addAttribute("Troca", trocaArray);

        return "bookStore/admin";
    }

    private void clear() {
        row.clear();
        orderArray.clear();
        trocaArray.clear();
        mapa.clear();
        enderecos.clear();
    }

    @GetMapping("/cliTrocar/{id}")
    public ModelAndView trocar(ModelMap model, @PathVariable(value = "id") String ncard) {

        System.out.println(connectBD.EXE_Map("SELECT ORDEM_ID, LIVROID, QUANT, CLI_ID FROM ORDDETAILS where "
                + " ORDEM_ID = " + Integer.parseInt(ncard)) + ";");

        mapa.putAll(connectBD.EXE_Map("SELECT ORDEM_ID, LIVROID, QUANT, CLI_ID FROM ORDDETAILS where "
                + " ORDEM_ID = " + Integer.parseInt(ncard) + ";"));

        mapa.putAll(
                connectBD
                        .EXE_Map("SELECT PRECIFICACAO FROM LIVRO where idlivro = " + mapa.get("LIVROID").get(0) + ";"));
        mapa.putAll(
                connectBD
                        .EXE_Map("SELECT TOTAL FROM ORDEM where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";"));
        mapa.putAll(
                connectBD
                        .EXE_Map("SELECT VALOR, PAY_ID, CARTAOID FROM ORDPAY where ORDEM_ID = "
                                + mapa.get("ORDEM_ID").get(0) + ";"));

        for (Entry<String, ArrayList<String>> endereco : mapa.entrySet()) {
            System.out.println(endereco.getKey() + " !! " + endereco.getValue());
        }

        String updateDetails = "";
        String updateORDEM = "";
        ArrayList<String> updatePay = new ArrayList<>();
        if (Integer.parseInt(mapa.get("QUANT").get(0)) > 1) {
            updateDetails = "UPDATE ORDDETAILS set QUANT = "
                    + (Integer.parseInt(mapa.get("QUANT").get(0)) - 1)
                    + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";
        }
        if ((Float.parseFloat(mapa.get("TOTAL").get(0)) - Float.parseFloat(mapa.get("PRECIFICACAO").get(0))) > 1) {
            updateORDEM = "UPDATE ORDEM set TOTAL = "
                    + ((Float.parseFloat(mapa.get("TOTAL").get(0))
                            - Float.parseFloat(mapa.get("PRECIFICACAO").get(0))))
                    + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";
        }

        for (int i = 0; i < mapa.get("CARTAOID").size(); i++) {
            if (mapa.get("CARTAOID").size() > 1) {
                valorDividido = Float.parseFloat(mapa.get("VALOR").get(i))
                        - (Float.parseFloat(mapa.get("TOTAL").get(0)) / mapa.get("CARTAOID").size());
            } else {
                valorDividido = Float.parseFloat(mapa.get("TOTAL").get(i))
                        - (Float.parseFloat(mapa.get("PRECIFICACAO").get(i)) / 2);
            }

            System.out.println("valorDividido " + valorDividido);

            if (valorDividido >= 10) {
                System.out.println("valorDividido >= 10 && primeiroCard");
                update(updatePay, i);

                connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                        + Integer.parseInt(mapa.get("CLI_ID").get(i)) + ", "
                        + (valorDividido - Float.parseFloat(mapa.get("PRECIFICACAO").get(i))) + ");");

            } else if (valorDividido <= 0) {
                System.out.println("valorDividido <= 0");
                update(updatePay, i);
                delete(updatePay, i);

            } else {
                System.out.println("else {");
                ArrayList<Float> parse = new ArrayList<>();
                for (String string : mapa.get("VALOR")) {
                    parse.add(Float.parseFloat(string));
                }
                Float min = Collections.min(parse);
                Float max = Collections.max(parse);

                updatePay.add("delete from ORDPAY where VALOR = " + min + " where ORDEM_ID = "
                        + mapa.get("ORDEM_ID").get(0) + " AND CARTAOID = "
                        + mapa.get("CARTAOID").get(i) + ";");

                updatePay.add("UPDATE ORDPAY set VALOR = "
                        + (max / connectBD.EXE_Map("SELECT VALOR FROM ORDPAY where ORDEM_ID = "
                                + mapa.get("ORDEM_ID").get(0) + ";").get(i).size())
                        + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";");

                connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                        + Integer.parseInt(mapa.get("CLI_ID").get(i)) + ", "
                        + (valorDividido - Float.parseFloat(mapa.get("PRECIFICACAO").get(i))) + ");");
            }
        }

        System.out.println(updateDetails);
        System.out.println(updateORDEM);
        System.out.println(updatePay);

        connectBD.EXEquery("delete from troca where ordem_id = " + mapa.get("ORDEM_ID").get(0) + ";");

        connectBD.EXEquery(updateORDEM);
        connectBD.EXEquery(updateDetails);
        for (String string : updatePay) {
            connectBD.EXEquery(string);
        }

        return new ModelAndView("redirect:/admin/admin", model);
    }

    private void delete(ArrayList<String> updatePay, int i) {
        updatePay.add("delete from ORDPAY where VALOR = " + valorDividido + " AND ORDEM_ID = "
                + mapa.get("ORDEM_ID").get(0) + " AND CARTAOID = "
                + mapa.get("CARTAOID").get(i) + ";");
    }

    private void update(ArrayList<String> updatePay, int i) {
        updatePay.add("UPDATE ORDPAY set VALOR  = " + valorDividido + " where ORDEM_ID = "
                + mapa.get("ORDEM_ID").get(0)
                + " AND PAY_ID = " + mapa.get("PAY_ID").get(i) + ";");
    }

    @GetMapping("/admin/cliPedido/{id}")
    public ModelAndView name(ModelMap model, @PathVariable(value = "id") String ncard) {
        mapa.putAll(
                connectBD.EXE_Map("SELECT TOTAL FROM ORDEM where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";"));
        return new ModelAndView("redirect:/admin/admin", model);
    }

}
