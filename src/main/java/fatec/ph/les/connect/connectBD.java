package fatec.ph.les.connect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.management.Query;

public class connectBD {

    static private String url = "jdbc:h2:mem:testdb";
    static private String className = "org.h2.Driver";
    static private String user = "sa";
    static private String password = "password";
    static Connection con;
    static Statement stmt;

    public connectBD() {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String CreateTableX(Class x) {
        Field[] Fields = x.getDeclaredFields();
        StringBuilder table = new StringBuilder();
        String name, tipo;
        int i = 0;
        table.append("create table " + x.getSimpleName() + "(" + "id" + x.getSimpleName()
                + " INT PRIMARY KEY AUTO_INCREMENT ,");

        for (Field f : Fields) {
            // f.setAccessible(true);
            i++;
            name = f.getName();
            tipo = f.getType().getSimpleName();

            if (i <= Fields.length - 1) {
                table.append(extracted(name, tipo) + " , ");
            } else {
                table.append(extracted(name, tipo) + ");");
            }
        }

        EXEquery(table.toString());
        return table.toString() + " ";

    }

    private static String extracted(String name, String tipo) {
        switch (tipo) {
            case "String":
                return " " + name + " VARCHAR(100) ";
            case "int":
                return " " + name + " INT ";
            default:
                return "";
        }
    }

    public static void EXEquery(String BDquery) {

        try {

            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            stmt.execute(BDquery);
            con.close();
            System.out.println("sucesso");

        } catch (Exception e) {
            System.out.println("erro");
        }
    }

}
