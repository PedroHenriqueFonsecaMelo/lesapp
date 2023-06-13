package fatec.ph.les.servicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class manyTmany {
    String str;
    static String datasOption = "";
    static StringBuilder str2;
    static Gson tGson;

    public void manyTOmany(Class<?> R, Class<?> L, int iR, int iL) {
        str = R.getSimpleName() + "_" + L.getSimpleName() + "( iR INT PRIMARY KEY, iL INT PRIMARY KEY);";
        connectBD.EXEquery(str);
    }

    public ArrayList<?> Select(Class<?> R, Class<?> L, int paramR, int paramL) {
        str2.append("select * from " + R.getSimpleName() + " JOIN " + R.getSimpleName() + "_" + L.getSimpleName()
                + " on " + paramR + " = " + " iR "
                + " JOIN " + L.getSimpleName()
                + " on " + paramL + " = " + " iL ");

        // List<Map<String, Object>> rs = connectBD.EXE_Select(str2.toString());

        return null;
    }

    public static String SelectOneLivro() {
        String str3 = "select data_pedido, sum(ordDetails.quant) as Livro, titulo from ORDDETAILS join LIVRO on IDLIVRO = LIVROID join ORDEM on ORDEM.ordem_id = ORDDETAILS.ordem_id group by titulo, data_pedido order by data_pedido ASC;";

        ArrayList<ArrayList<String>> rr = connectBD.mrows(str3);
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<String>> novasLinhas = new ArrayList<>();
        Set<String> novasColunas = new HashSet<>();
        Set<String> setaux = new HashSet<>();
        String data = "0";

        for (int i = 0; i < rr.size(); i++) {
            if (rr.get(i).get(0) == data) {
                novasColunas.add(rr.get(i).get(2));
            } else {
                novasColunas.add("data_pedido");
                novasColunas.add(rr.get(i).get(2));
                data = rr.get(i).get(0);

            }

        }

        ArrayList<String> novasColunasArray = new ArrayList<>(novasColunas);

        for (int i = 0; i < rr.size(); i++) {
            setaux.add(rr.get(i).get(0));
        }

        ArrayList<String> setauxArrat = new ArrayList<>(setaux);

        for (String string : setauxArrat) {
            ArrayList<String> aux = new ArrayList<>();
            for (int i = 0; i < novasColunas.size(); i++) {
                aux.add("0");
            }
            aux(rr, aux, string, novasColunasArray, novasColunas, novasLinhas);

        }

        array.add(new ArrayList<String>(novasColunas));
        array.addAll(novasLinhas);

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(array);

        return gsoString;
    }

    public static String SelectOneLivroGeral() {
        String str3 = "select YEAR(data_pedido), sum(ordDetails.quant) as Livro, titulo from ORDDETAILS join LIVRO on IDLIVRO = LIVROID join ORDEM on ORDEM.ordem_id = ORDDETAILS.ordem_id group by titulo, data_pedido order by data_pedido ASC;";

        ArrayList<ArrayList<String>> rr = connectBD.mrows(str3);
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<String>> novasLinhas = new ArrayList<>();
        Set<String> novasColunas = new HashSet<>();
        Set<String> setaux = new HashSet<>();
        String data = "0";

        for (int i = 0; i < rr.size(); i++) {
            if (rr.get(i).get(0) == data) {
                novasColunas.add(rr.get(i).get(2));
            } else {
                novasColunas.add("data_pedido");
                novasColunas.add(rr.get(i).get(2));
                data = rr.get(i).get(0);

            }

        }

        ArrayList<String> novasColunasArray = new ArrayList<>(novasColunas);

        for (int i = 0; i < rr.size(); i++) {
            setaux.add(rr.get(i).get(0));
        }

        ArrayList<String> setauxArrat = new ArrayList<>(setaux);

        for (String string : setauxArrat) {
            ArrayList<String> aux = new ArrayList<>();
            for (int i = 0; i < novasColunas.size(); i++) {
                aux.add("0");
            }
            aux(rr, aux, string, novasColunasArray, novasColunas, novasLinhas);

        }

        array.add(new ArrayList<String>(novasColunas));
        array.addAll(novasLinhas);

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(array);

        return gsoString;
    }

    private static String aux(ArrayList<ArrayList<String>> rr, ArrayList<String> aux, String data,
            ArrayList<String> novasColunasArray, Set<String> novasColunas, ArrayList<ArrayList<String>> novasLinhas) {

        for (ArrayList<String> arrayList : rr) {

            aux.set(0, data);

            for (String string : novasColunas) {

                if (data.equalsIgnoreCase(arrayList.get(0)) &&
                        arrayList.get(2).contains(string.substring(1, string.length() - 1))) {

                    aux.set(novasColunasArray.indexOf(string),
                            arrayList.subList(1, arrayList.size() - 1).get(0));

                }
                if (!aux.isEmpty() && !novasLinhas.contains(aux)) {
                    novasLinhas.add(aux);

                }
                if (!datasOption.contains(data.substring(0, 4))) {
                    datasOption += "<option value=" + data.substring(0, 4) + ">"
                            + data.substring(0, 4) + "</option>";
                }

            }

        }

        return "data";
    }

    public static String GenericTable(ArrayList<ArrayList<String>> toTable, int l) {
        ArrayList<ArrayList<String>> mapss = toTable;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tbody>");
        stringBuilder.append("<tr><td class='tg-0lax'>"
                + "<button type='button' onclick='changeTable("
                + 0 + ")' class='cart-btn'>Voltar</button>"
                + "</td></tr>");

        for (int i = 0; i < mapss.size(); i++) {
            stringBuilder.append("<tr>");
            if (i == 0) {
                for (int j = 0; j < mapss.get(0).size(); j++) {
                    stringBuilder.append("<td class='tg-0lax'>" + mapss.get(0).get(j) + "</td>");
                }
            } else {
                for (int k = 0; k < mapss.get(i).size(); k++) {
                    stringBuilder.append("<td class='tg-0lax'>" + mapss.get(i).get(k) + "</td>");
                    if (k == mapss.get(i).size() - 1 && l != 0) {

                        stringBuilder.append("<td class='tg-0lax'>"
                                + "<form action='/cliHome/TrocaForm' method='post'>  <div id='dv'><nobr>"
                                + "<input type='number' id='trocaQuant' name='trocaQuant' min='0' max="
                                + mapss.get(i).get(4) + ">"
                                + "<input type='hidden' id='ordem_id' name='ordem_id' value=" + mapss.get(i).get(0)
                                + ">"
                                + "<input type='hidden' id='livroid' name='livroid' value=" + mapss.get(i).get(1)
                                + ">"
                                + "<button type='submit' id='save-btn'>Cadastrar</button>"
                                + "</nobr></div></form></td>");

                    } else if (k == mapss.get(i).size() - 1) {
                        stringBuilder.append("<td class='tg-0lax'>"
                                + "<button type='button' onclick='changeTable("
                                + mapss.get(i).get(0) + ")' id='cliDetails"
                                + mapss.get(i).get(0) + "' class='cart-btn'>Add to cart</button>"
                                + "</td>");
                    }
                }
            }
            stringBuilder.append("</tr>");

        }
        stringBuilder.append("</tbody>");

        return stringBuilder.toString();
    }

    public static String GenericSimpleTable(ArrayList<ArrayList<String>> toTable) {
        ArrayList<ArrayList<String>> mapss = toTable;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tbody>");

        for (int i = 0; i < mapss.size(); i++) {
            stringBuilder.append("<tr>");
            if (i == 0) {
                for (int j = 0; j < mapss.get(0).size(); j++) {
                    stringBuilder.append("<td class='tg-0lax'>" + mapss.get(0).get(j) + "</td>");
                }
            } else {
                for (int k = 0; k < mapss.get(i).size(); k++) {
                    stringBuilder.append("<td class='tg-0lax'>" + mapss.get(i).get(k) + "</td>");

                }
            }
            stringBuilder.append("</tr>");

        }
        stringBuilder.append("</tbody>");

        return stringBuilder.toString();
    }

    public static String GenericJson(String toJson) {
        Map<String, ArrayList<String>> mapss = connectBD.EXE_Map(toJson);

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        for (Entry<String, ArrayList<String>> value1 : mapss.entrySet()) {

            ArrayList<ArrayList<String>> rc2 = new ArrayList<>();

            ArrayList<String> args = new ArrayList<>();

            args.add(value1.getKey());
            for (String value2 : value1.getValue()) {
                args.add(value2);
            }
            rc2.add(args);
            array.addAll(rc2);
        }

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(array);

        return gsoString;
    }

    public static String ArrayListToJson(ArrayList<ArrayList<String>> tables) {

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(tables);
        return gsoString;

    }

    public static String getDatasOption() {
        return datasOption;
    }

    public static void setDatasOption(String datasOption) {
        manyTmany.datasOption = datasOption;
    }

}
