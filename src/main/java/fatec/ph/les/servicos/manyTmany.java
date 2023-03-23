package fatec.ph.les.servicos;

import java.util.ArrayList;

public class manyTmany {
    String str;
    StringBuilder str2;

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
}
