package fatec.ph.les.servicos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class connectBD {

    static private String url = "jdbc:h2:mem:testdb";
    static private String className = "org.h2.Driver";
    static private String user = "sa";
    static private String password = "password";
    static Connection con;
    static Statement stmt;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public connectBD() {
        try {
            Class.forName(className);
            con = DriverManager.getConnection(url, user, password);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void CreateTableX(Class x) {
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
            if (name.contains("data")) {
                tipo = "Date";
            }

            if (i <= Fields.length - 1) {
                table.append(extracted(name, tipo) + " , ");
            } else {
                table.append(extracted(name, tipo) + ");");
            }
        }
        System.out.println(table.toString());
        EXEquery(table.toString());
        // return table.toString() + " ";

    }

    public static String InserirTableX(Object obj) {
        StringBuilder Inserir = new StringBuilder();
        String name;
        int i = 0;
        Field[] Fields = obj.getClass().getDeclaredFields();

        Inserir.append("insert into " + obj.getClass().getSimpleName() + " (");

        for (Field f : Fields) {
            // f.setAccessible(true);
            i++;
            name = f.getName();
            if (i <= Fields.length - 1) {
                Inserir.append(name + " , ");
            } else {
                Inserir.append(name + ") VALUES  (");
            }
        }
        i = 0;
        for (Field f : Fields) {
            i++;
            switch (f.getType().getSimpleName()) {
                case "String":
                case "Date":
                    Inserir.append("'" + runGetter(f, obj) + "'");
                    break;
                default:
                    Inserir.append(" " + runGetter(f, obj) + " ");
                    break;
            }
            if (i <= Fields.length - 1) {
                Inserir.append(" , ");
            } else {
                Inserir.append(" ); ");
            }

        }

        System.out.println("resultado::: " + Inserir.toString());

        return Inserir.toString();
    }

    public static String DeletarTableX(int uid, Class obj) {
        StringBuilder queryDel = new StringBuilder();
        queryDel.append("delete from " + obj.getClass().getSimpleName() + " where id" + obj.getClass().getSimpleName()
                + " = " + uid + ";");
        return queryDel.toString();
    }

    private static String extracted(String name, String tipo) {
        switch (tipo) {
            case "String":
                return " " + name + " VARCHAR(100) ";
            case "int":
                return " " + name + " INT ";
            case "Date":
                return " " + name + " DATE ";
            default:
                return "";
        }
    }

    public static void EXEquery(String BDquery) {

        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            stmt.execute(BDquery);
            con.close();
            System.out.println("sucesso EXEquery");

        } catch (Exception e) {
            System.out.println("erro EXEquery");
        }
    }

    public static String EXE_Select_UID(String BDquery) {
        ResultSet rs;
        String resultQuery = "";

        try {
            con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(BDquery);
            while (rs.next()) { // Position the cursor 3
                resultQuery = rs.getString(1); // Retrieve only the first column value
                // Print the column value
            }
            con.close();
            rs.close();
            System.out.println("sucesso");
            return resultQuery;

        } catch (Exception e) {
            System.out.println("erro");
            return "";
        }
    }

    public static String runGetter(Field field, Object o) {
        // MZ: Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3))) {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())) {
                    // MZ: Method found, run it
                    try {
                        return method.invoke(o).toString();
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        System.out.println("Could not determine method: " + method.getName());
                    }
                }
            }
        }

        return null;
    }

    public static ResultSet EXE_Select(Class classe, int uid, ArrayList<String> args) {
        StringBuilder str = new StringBuilder();

        if (args == null) {
            str.append("select * from " + classe.getSimpleName() + " where id" + classe.getSimpleName() + " = " + uid);
        } else {
            str.append("select ");
            for (int i = 0; i < args.size(); i++) {
                if (i == args.size() - 1) {
                    str.append(args.get(i));
                } else
                    str.append(args.get(i) + " , ");
            }
            str.append(" where id" + classe.getSimpleName() + " = " + uid);
        }
        System.out.println(str.toString());
        return null;

    }

}
