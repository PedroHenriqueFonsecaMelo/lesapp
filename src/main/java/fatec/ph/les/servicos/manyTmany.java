package fatec.ph.les.servicos;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        SortedMap<String, Integer> toJson = new TreeMap<>();
        // ArrayList<String[]> array = new ArrayList<>();

        for (Entry<String, ArrayList<String>> iterable_element : rs.entrySet()) {
            /*
             * String[] arraStrings = { "'" + iterable_element.getKey() + "'",
             * iterable_element.getValue().get(0) };
             * array.add(arraStrings);
             */
            toJson.put(iterable_element.getKey(), Integer.parseInt(iterable_element.getValue().get(0)));
        }
        Gson gson = new Gson();
        Type gsnoType = new TypeToken<HashMap>() {
        }.getType();
        String gsoString = gson.toJson(toJson, gsnoType);

        return gsoString;
    }
}
