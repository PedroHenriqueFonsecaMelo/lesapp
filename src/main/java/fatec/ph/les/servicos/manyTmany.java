package fatec.ph.les.servicos;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class manyTmany {
    String str;
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
        String str3 = "select CONCAT('IDLIVRO: ', IDLIVRO) as CONCATENADO, COUNT(IDLIVRO) as CONTAGEM from ORDDETAILS join LIVRO on IDLIVRO = LIVROID group by LIVROID";

        ArrayList<ArrayList<String>> rc = connectBD.mcolum(str3);
        ArrayList<ArrayList<String>> rr = connectBD.mrows(str3);

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        array.addAll(rc);
        array.addAll(rr);

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(array);

        return gsoString;
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
}
