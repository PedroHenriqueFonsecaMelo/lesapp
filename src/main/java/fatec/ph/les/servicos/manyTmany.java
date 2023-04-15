package fatec.ph.les.servicos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

public class manyTmany {
    String str;
    static StringBuilder str2;

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
        String str3 = "select IDLIVRO, COUNT(IDLIVRO) from ORDDETAILS join LIVRO on IDLIVRO = LIVROID group by LIVROID";

        Map<String, ArrayList<String>> rs = connectBD.EXE_Map(str3);

        ArrayList<ArrayList<String>> array = new ArrayList<>();

        for (Entry<String, ArrayList<String>> iterable_element : rs.entrySet()) {
            for (int i = 0; i < iterable_element.getValue().size(); i++) {
                array.add(new ArrayList<String>(
                        Arrays.asList(iterable_element.getKey(), iterable_element.getValue().get(i))));
            }
        }

        for (int i = 0; i < array.size(); i++) {
            for (int j = 0; j < array.get(i).size(); j++) {

                if (array.get(i).get(0).equals("IDLIVRO")) {
                    array.get(i + (array.get(i).size() / 2)).set(0, array.get(i).get(0) + " " + array.get(i).get(1));
                }
            }
        }

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).get(0).equals("IDLIVRO")) {
                array.remove(i);
                i--;
            }
        }

        Gson gson = new Gson();
        // Type gsnoType = new TypeToken<HashMap>() { e();

        String gsoString = gson.toJson(array);
        System.out.println(array);

        return gsoString;
    }
}
