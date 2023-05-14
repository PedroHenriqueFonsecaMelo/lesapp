package fatec.ph.les.controller;

import java.util.ArrayList;
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

    @PostMapping("/pedidostatus")
    public ModelAndView pedidostatus(ModelMap model, @RequestParam Map<String, ?> param) {

        ArrayList<ArrayList<String>> ordetailsLivro = connectBD
                .mrows("select LIVROID, QUANT from ORDDETAILS where ORDEM_ID  =  " + param.get("index"));

        ArrayList<ArrayList<String>> livroQuant = new ArrayList<>();

        for (ArrayList<String> iterable_element : ordetailsLivro) {
            livroQuant.addAll(connectBD.mrows("select quant from LIVRO where IDLIVRO = " + iterable_element.get(0)));
        }
        for (int i = 0; i < livroQuant.size(); i++) {
            int ogQuant = Integer.parseInt(livroQuant.get(i).get(0));
            int pedQuant = Integer.parseInt(ordetailsLivro.get(i).get(1));

            String upadeteLivro = "UPDATE LIVRO set quant = "
                    + (ogQuant - pedQuant)
                    + " where IDLIVRO = "
                    + ordetailsLivro.get(i).get(0) + ";";

            connectBD.EXEquery(upadeteLivro);
        }

        String updateOrderm = "UPDATE ORDEM set status = '" + param.get("pedidoStatus").toString()
                + "' where ORDEM_ID = "
                + param.get("index").toString() + ";";

        connectBD.EXEquery(updateOrderm);

        return new ModelAndView("redirect:/admin/admin", model);
    }

    @PostMapping("/trocastatus")
    public ModelAndView trocaStatus(ModelMap model, @RequestParam Map<String, ?> param) {
        String updateDetails = "";
        String updateORDEM = "";
        ArrayList<String> updatePay = new ArrayList<>();

        DadoPreparoMapa(param.get("index").toString());

        updateDetails = updateDetails(updateDetails);
        updateORDEM = updateORDEMparam(updateORDEM, param.get("trocaStatus").toString());

        updatePay(updatePay, param.get("index").toString());
        execUpdate(updateDetails, updateORDEM, updatePay);

        connectBD.EXEquery("delete from troca where TROCA_ID = " + mapa.get("TROCA_ID").get(0) + ";");

        return new ModelAndView("redirect:/admin/admin", model);
    }

    @GetMapping("/cliPedido/{id}")
    public ModelAndView aceito(ModelMap model, @PathVariable(value = "id") String ncard) {
        mapa.putAll(
                connectBD.EXE_Map("SELECT TOTAL FROM ORDEM where ORDEM_ID = " + ncard + ";"));
        String updateOrderm = "UPDATE ORDEM set status = 'APROVADO' where ORDEM_ID = " + ncard + ";";

        connectBD.EXEquery(updateOrderm);

        return new ModelAndView("redirect:/admin/admin", model);
    }

    @PostMapping("/livro")
    public ModelAndView name(ModelMap model, @RequestParam Map<String, ?> param) {

        Livro li = new Livro(param);
        Livro.InserirCBD(li);
        return new ModelAndView("redirect:/admin/admin", model);
    }

    @PostMapping("/pesquisa")
    public String pesquisa(ModelMap map, @RequestParam Map<String, ?> param) {

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

        row.addAll(connectBD.mrows(str.toString()));

        ModelAddOderm();
        ModelAddTroca();
        AdminBaseModel(map);

        map.addAttribute("Clientes", row);

        return "bookStore/admin";
    }

    private void AdminBaseModel(ModelMap map) {

        map.addAttribute("Ordem", orderArray);

        map.addAttribute("Troca", trocaArray);

        map.addAttribute("livros", Livro.info());

        map.addAttribute("grafico", manyTmany.SelectOneLivro());

        map.addAttribute("LivrosCadastrados", LivroTable());
    }

    private String LivroTable() {

        ArrayList<ArrayList<String>> rowToTable = new ArrayList<>();

        String query = "select * from LIVRO";

        rowToTable.addAll(connectBD.mcolum(query));
        rowToTable.addAll(connectBD.mrows(query));

        return manyTmany.GenericSimpleTable(rowToTable);

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
                    "select ORDEM_ID, CLI_ID, TOTAL, STATUS, DATA_PEDIDO, RUA from ordem join Endereco on endereco = IDENDERECO;"));

        } else {
            orderArray.add(null);
            orderArray.add(null);
        }

    }

    private void ModelAddCli() {
        if (connectBD.mrows("select * from cliente;") != null) {
            row.addAll(connectBD.mcolum("select * from cliente;"));
            row.addAll(connectBD.mrows("select * from cliente;"));

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

    private void execUpdate(String updateDetails, String updateORDEM, ArrayList<String> updatePay) {
        connectBD.EXEquery(updateORDEM);
        connectBD.EXEquery(updateDetails);
        for (String string : updatePay) {
            connectBD.EXEquery(string);
        }
    }

    private void updatePay(ArrayList<String> updatePay, String ncard) {

        ArrayList<ArrayList<String>> ordpay = connectBD.mrows("select * from ORDPAY where ORDEM_ID = " + ncard);

        float PRECIFICACAO = Float.parseFloat(mapa.get("PRECIFICACAO").get(0));

        float QUANTIDADE_TROCA = Float.parseFloat(mapa.get("QUANTIDADE_TROCA").get(0));
        float QUANTIDADE_OG = Float.parseFloat(mapa.get("QUANT").get(0));

        valorDividido = (PRECIFICACAO * (QUANTIDADE_OG - QUANTIDADE_TROCA)) / ordpay.size();

        for (int i = 0; i < ordpay.size(); i++) {
            int indexPay = Integer.parseInt(ordpay.get(i).get(0));

            if (valorDividido >= 10) {
                update(updatePay, indexPay);

            } else if (valorDividido <= 0) {

                update(updatePay, indexPay);
                delete(updatePay, indexPay);

            }
        }
    }

    private String updateORDEMparam(String updateORDEM, String status) {

        float novototal = Float.parseFloat(mapa.get("TOTAL").get(0))
                - (Float.parseFloat(mapa.get("PRECIFICACAO").get(0))
                        * Float.parseFloat(mapa.get("QUANTIDADE_TROCA").get(0)));
        System.out.println(novototal);

        if (novototal >= 10) {
            updateORDEM = "UPDATE ORDEM set TOTAL = " + novototal + ", status = '" + status
                    + "' where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";

            connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                    + Integer.parseInt(mapa.get("CLI_ID").get(0)) + ", "
                    + (novototal / 2) + ");");
        } else {
            updateORDEM = "delete from ORDEM where ORDEM_ID = " + mapa.get("ORDEM_ID").get(0) + ";";

            connectBD.EXEquery("insert into cupons (cli_id, desconto) values ("
                    + Integer.parseInt(mapa.get("CLI_ID").get(0)) + ", "
                    + (novototal / 2) + ");");
        }

        return updateORDEM;
    }

    private String updateDetails(String updateDetails) {

        for (int i = 0; i < mapa.get("QUANTIDADE_TROCA").size(); i++) {
            int QUANTIDADE_TROCA = Integer.parseInt(mapa.get("QUANTIDADE_TROCA").get(i));
            int QUANTIDADE_DETAILS = Integer.parseInt(mapa.get("QUANT").get(i));

            if ((QUANTIDADE_DETAILS - QUANTIDADE_TROCA) == 0) {
                updateDetails = "delete from ORDDETAILS where ORDEM_ID = " + mapa.get("ORDEM_ID").get(i);
            } else
                updateDetails = "UPDATE ORDDETAILS set QUANT = "
                        + (QUANTIDADE_DETAILS - QUANTIDADE_TROCA)
                        + " where ORDEM_ID = " + mapa.get("ORDEM_ID").get(i) + ";";
        }

        return updateDetails;
    }

    private void DadoPreparoMapa(String ncard) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT ORDEM.ORDEM_ID, TROCA_ID, DETAILS_ID, ORDEM.CLI_ID, LIVROID, ");
        query.append("PRECIFICACAO,ORDDETAILS.QUANT ,TROCA.QUANTIDADE_TROCA , VALORTROCA, TOTAL ");
        query.append("FROM ORDDETAILS join ORDEM on ORDDETAILS.ORDEM_ID = ORDEM.ORDEM_ID ");
        query.append("join LIVRO on LIVRO.idlivro = ORDDETAILS.LIVROID ");
        query.append("join TROCA on TROCA.ORDEM_ID = ORDEM.ORDEM_ID ");

        query.append("where TROCA_ID = " + Integer.parseInt(ncard) + ";");

        System.out.println(query.toString());

        mapa.putAll(connectBD.EXE_Map(query.toString()));

    }

    private void delete(ArrayList<String> updatePay, int i) {
        updatePay.add("delete from ORDPAY where PAY_ID = " + i + ";");
    }

    private void update(ArrayList<String> updatePay, int i) {
        updatePay.add("UPDATE ORDPAY set VALOR  = " + valorDividido + " where PAY_ID = " + i + ";");
    }
}
