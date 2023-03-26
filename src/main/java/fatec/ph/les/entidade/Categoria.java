package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.NavigableMap;
import java.util.Map.Entry;

import fatec.ph.les.servicos.connectBD;

public class Categoria {

    public static void CreateTable() {
        try {
            System.out.println("CreateTable no %s: Categoria");
            connectBD.CreateTableX(Categoria.class);
        } catch (Exception e) {
        }
    }

    public static int categoriaId(int uid, NavigableMap<String, String> args) {

        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<Categoria> Categoria " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from Categoria where idCategoria = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from Categoria");
        } else if (uid == 0 && args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from Categoria where ");

            for (Entry<String, String> i : args.entrySet()) {
                if (i.getKey().equals(lastEntry.getKey())) {
                    str.append(i.getKey() + " = " + i.getValue() + ";");
                } else {
                    str.append(i.getKey() + " = " + i.getValue() + " AND ");
                }
            }
        }
        System.out.println(str.toString());

        int i = Integer.parseInt(connectBD.EXE_Select_UID(str.toString()));

        return i;
    }

    public static String cliUID(Categoria obj) {
        StringBuilder uid = new StringBuilder("select * from " + Cliente.class.getSimpleName() + " where ");
        Field[] Fields = obj.getClass().getDeclaredFields();
        String fieldname = "";
        int i = 0;

        for (Field f : Fields) {
            i++;
            if (!f.getName().contains("id" + obj.getClass().getSimpleName())) {
                switch (f.getType().getSimpleName()) {
                    case "String":
                    case "Date":
                        if (i <= Fields.length - 1) {
                            uid.append(f.getName() + " = " + "'" + connectBD.runGetter(f, obj) + "'" + " AND ");
                        } else {
                            uid.append(f.getName() + " = " + "'" + connectBD.runGetter(f, obj) + "'" + ";");
                        }
                        break;
                    default:
                        if (i <= Fields.length - 1) {
                            uid.append(f.getName() + " = " + connectBD.runGetter(f, obj) + " AND ");
                        } else {
                            uid.append(f.getName() + " = " + connectBD.runGetter(f, obj) + ";");
                        }
                        break;
                }
            }

        }
        System.out.println(uid.toString());
        fieldname = connectBD.EXE_Select_UID(uid.toString());

        return fieldname;
    }

    private int idCategoria;

    private String categorias;

    public Categoria() {
    }

    public Categoria(String categorias) {
        this.categorias = categorias;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
