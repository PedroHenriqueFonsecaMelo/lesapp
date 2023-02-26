package fatec.ph.les.connect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

            if (i <= Fields.length - 1) {
                table.append(extracted(name, tipo) + " , ");
            } else {
                table.append(extracted(name, tipo) + ");");
            }
        }

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

}
