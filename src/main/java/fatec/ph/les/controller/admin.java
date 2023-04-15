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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fatec.ph.les.entidade.Cliente;
import fatec.ph.les.entidade.Endereco;
import fatec.ph.les.entidade.Livro;
import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.manyTmany;

@Controller
@RequestMapping("/admin")
public class admin {
    ArrayList<ArrayList<String>> row = new ArrayList<>();
    ArrayList<ArrayList<String>> orderArray = new ArrayList<>();
    ArrayList<ArrayList<String>> trocaArray = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    Map<String, ArrayList<String>> mapa = new HashMap<>();
    float valorDividido = 0;

    @GetMapping("/admin")
    public String data(ModelMap map) {

        clear();
        ModelAddCli();
        ModelAddOderm();
        ModelAddTroca();

        map.addAttribute("Clientes", row);
        AdminBaseModel(map);

        return "bookStore/admin";
    }

    private void AdminBaseModel(ModelMap map) {

        map.addAttribute("Ordem", orderArray);

        map.addAttribute("Troca", trocaArray);

        map.addAttribute("livros", Livro.info());

        map.addAttribute("grafico", manyTmany.SelectOneLivro());
    }

    private void ModelAddTroca() {
        if (connectBD.mrows("SELECT * FROM TROCA") != null) {
            trocaArray.addAll(connectBD.mcolum("SELECT * FROM TROCA"));
            trocaArray.addAll(connectBD.mrows("SELECT * FROM TROCA"));

        } else {
            trocaArray.add(null);
            trocaArray.add(null);
        }
    }

    private void ModelAddOderm() {
        if (!connectBD.mrows("select * from ordem;").isEmpty()) {

            orderArray.addAll(connectBD.mcolum("select * from ordem;"));

            orderArray.addAll(connectBD.mrows(
                    "select ORDEM_ID, CLI_ID, TOTAL, STATUS, RUA from ordem join Endereco on endereco = IDENDERECO;"));

        } else {
            orderArray.add(null);
            orderArray.add(null);
        }

    }

    private void ModelAddCli() {
        if (connectBD.mrows("select * from cliente;") != null) {
            row.addAll(connectBD.mcolum("select * from cliente;"));
            row.addAll(connectBD.mrows("select * from cliente;"));

            System.out.println(connectBD.EXE_Map("select * from cliente;"));

            Map<String, ArrayList<String>> smap = connectBD.EXE_Map("select IDCLIENTE from cliente;");

            for (Entry<String, ArrayList<String>> cliente : smap.entrySet()) {
                for (String cliente2 : cliente.getValue()) {
                    clientes.addAll(Cliente.cliente(Integer.parseInt(cliente2), null));
                }
            }
        }
    }

    private void clear() {
        row.clear();
        orderArray.clear();
        trocaArray.clear();
        mapa.clear();
        clientes.clear();
    }

    @GetMapping("/cliTrocar/{id}")
    public ModelAndView trocar(ModelMap model, @PathVariable(value = "id") String ncard) {
        String updateDetails = "";
        String updateORDEM = "";
        ArrayList<String> updatePay = new ArrayList<>();

        DadoPreparoMapa(ncard);

        updateDetails = updateDetails(updateDetails);
        updateORDEM = updateORDEM(updateORDEM);
        updatePay(updatePay);

        connectBD.EXEquery("delete from troca where ordem_id = " + mapa.get("ORDEM_ID").get(0) + ";");

        execUpdate(updateDetails, updateORDEM, updatePay);

        return new ModelAndView("redirect:/admin/admin", model);
    }

    private void execUpdate(String updateDetails, String updateORDEM, ArrayList<String> updatePay) {
        connectBD.EXEquery(updateORDEM);
        connectBD.EXEquery(updateDetails);
        for (String string : updatePay) {
            connectBD.EXEquery(string);
        }
    }

    private void updatePay(ArrayList<String> updatePay) {
        for (int i = 0; i < mapa.get("CARTAOID").size(); i++) {
            if (mapa.get("CARTAOID").size() > 1) {
                valorDividido = Float.parseFloat(mapa.get("VALOR").get(i))
                        - (Float.parseFloat(mapa.get("TOTAL").get(0)) / mapa.get("CARTAOID").size());
            } else {
                valorDividido = Float.parseFloat(mapa.get("TOTAL").get(i))
                        - (Float.parseFloat(mapa.get("PRECIFICACAO").get(i)) / 2);
            }

            if (valorDividido >= 10) {
                update(updatePay, i);

                connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                        + Integer.parseInt(mapa.get("CLI_ID").get(i)) + ", "
                        + (Float.parseFloat(mapa.get("PRECIFICACAO").get(i)) - valorDividido) + ");");

            } else if (valorDividido <= 0) {

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

                System.out.println(
                        "private void updatePay" + connectBD.EXE_Map("SELECT VALOR FROM ORDPAY where ORDEM_ID = "
                                + mapa.get("ORDEM_ID").get(0) + ";"));
                System.out.println(
                        "private void updatePay" + connectBD.EXE_Map("SELECT VALOR FROM ORDPAY where ORDEM_ID = "
                                + mapa.get("ORDEM_ID").get(0) + ";").get(i));

                connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                        + Integer.parseInt(mapa.get("CLI_ID").get(i)) + ", "
                        + (valorDividido - Float.parseFloat(mapa.get("PRECIFICACAO").get(i))) + ");");
            }
        }
    }

    private String updateORDEM(String updateORDEM) {
        if ((Float.parseFloat(mapa.get("TOTAL").get(0)) - Float.parseFloat(mapa.get("PRECIFICACAO").get(0))) > 1) {
            updateORDEM = "UPDATE ORDEM set TOTAL = "
                    + ((Float.parseFloat(mapa.get("TOTAL").get(0))
                            - Float.parseFloat(mapa.get("PRECIFICACAO").get(0))))
                    + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";
        } else {
            updateORDEM = "delete from ORDEM where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";
        }
        System.out.println("private String updateORDEM  ::" + updateORDEM);
        return updateORDEM;
    }

    private String updateDetails(String updateDetails) {
        if (Integer.parseInt(mapa.get("QUANT").get(0)) > 1) {
            updateDetails = "UPDATE ORDDETAILS set QUANT = "
                    + (Integer.parseInt(mapa.get("QUANT").get(0)) - 1)
                    + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";
        }
        return updateDetails;
    }

    private void DadoPreparoMapa(String ncard) {
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

    @GetMapping("/cliPedido/{id}")
    public ModelAndView aceito(ModelMap model, @PathVariable(value = "id") String ncard) {
        mapa.putAll(
                connectBD.EXE_Map("SELECT TOTAL FROM ORDEM where ORDEM_ID = " + ncard + ";"));
        String updateOrderm = "UPDATE ORDEM set status = 'APROVADO' where ORDEM_ID = " + ncard + ";";

        connectBD.EXEquery(updateOrderm);

        return new ModelAndView("redirect:/admin/admin", model);
    }

    @GetMapping("/cliPedido/{id}/0")
    public ModelAndView recusado(ModelMap model, @PathVariable(value = "id") String ncard) {

        Map<String, ArrayList<String>> Livroid = connectBD
                .EXE_Map("select livroid, quant from ORDDETAILS where ordem_id = " + ncard);

        Map<String, ArrayList<String>> map;

        for (int index = 0; index < Livroid.get("livroid").size(); index++) {

            int livroi = Integer.parseInt(Livroid.get("livroid").get(index));
            map = connectBD.EXE_Map("select livroid, quant from LIVRO where LIVROID = " + livroi + ";");

            for (int j = 0; j < map.get("quant").size(); j++) {

                int detailLivro = Integer.parseInt(map.get("livroid").get(j));
                if (livroi == detailLivro) {
                    int quantidade = Integer.parseInt(map.get("quant").get(j))
                            + Integer.parseInt(map.get("quant").get(j));
                    String updateLivro = "update LIVRO set quant = " + quantidade + "where ordem_id = " + ncard;
                    connectBD.EXEquery(updateLivro);
                }

            }
        }

        mapa.putAll(
                connectBD.EXE_Map("SELECT TOTAL FROM ORDEM where ORDEM_ID = " + ncard + ";"));
        String updateOrderm = "delete from ORDEM where ORDEM_ID = " + ncard + ";";
        connectBD.EXEquery(updateOrderm);

        return new ModelAndView("redirect:/admin/admin", model);
    }

    @PostMapping("/livro")
    public ModelAndView name(ModelMap model, @RequestParam Map<String, ?> param) {
        for (Entry<String, ?> cliente : param.entrySet()) {
            System.out.println(cliente.getKey() + "|| " + cliente.getValue() + " || "
                    + cliente.getValue().getClass().getSimpleName());
        }
        Livro li = new Livro(param);
        Livro.InserirCBD(li);
        return new ModelAndView("redirect:/admin/admin", model);
    }

    @PostMapping("/pesquisa")
    public String pesquisa(ModelMap map, @RequestParam Map<String, ?> param) {
        for (Entry<String, ?> cliente : param.entrySet()) {
            System.out.println(cliente.getKey() + "|| " + cliente.getValue() + " || "
                    + cliente.getValue().getClass().getSimpleName());
        }

        row.clear();

        row.addAll(connectBD.mcolum("select * from cliente;"));
        StringBuilder str = new StringBuilder("select * from cliente where ");
        String tipo = "";

        Map<String, String> cliInfo = Cliente.info();
        for (Entry<String, String> cliente : cliInfo.entrySet()) {
            if (cliente.getKey().equalsIgnoreCase(param.get("pesquisaCli").toString())) {
                tipo = cliente.getValue();
            }
        }

        switch (tipo) {
            case "String":
                str.append(param.get("pesquisaCli").toString() + " LIKE ");
                str.append("'%" + param.get("pesquisaCliValue") + "%';");
                break;
            default:
                str.append(param.get("pesquisaCli").toString() + " = ");
                str.append(param.get("pesquisaCliValue") + ";");
                break;
        }

        System.out.println(str.toString());
        row.addAll(connectBD.mrows(str.toString()));

        ModelAddOderm();
        ModelAddTroca();
        AdminBaseModel(map);

        map.addAttribute("Clientes", row);

        return "bookStore/admin";
    }

    private boolean IsIteger(String tri) {
        try {
            Integer.parseInt(tri);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
