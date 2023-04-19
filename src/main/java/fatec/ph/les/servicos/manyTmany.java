package fatec.ph.les.servicos;

import java.util.ArrayList;
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
}
