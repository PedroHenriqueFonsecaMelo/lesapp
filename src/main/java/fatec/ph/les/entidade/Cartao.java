package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

public class Cartao {
    public static ArrayList<Cartao> cartao(int uid, NavigableMap<String, String> args) {
        ArrayList<Cartao> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<cartao> cartao " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from cartao where cli_id = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from cartao");
        } else if (args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from cartao where ");

            for (Entry<String, String> i : args.entrySet()) {
                if (i.getKey().equals(lastEntry.getKey())) {
                    str.append(i.getKey() + " = " + i.getValue() + ";");
                } else {
                    str.append(i.getKey() + " = " + i.getValue() + " AND ");
                }
            }
        }
        System.out.println(str.toString());

        List<Map<String, Object>> rs = connectBD.EXE_Select(str.toString());

        for (Map<String, Object> map : rs) {
            Cartao cli = new Cartao();
            for (Entry<String, Object> map2 : map.entrySet()) {
                for (Field field : cli.getClass().getDeclaredFields()) {
                    if (field.getName().equalsIgnoreCase(map2.getKey())) {
                        try {
                            switch (field.getType().getSimpleName()) {
                                case "int":
                                    field.set(cli, Integer.parseInt(map2.getValue().toString()));
                                    break;

                                default:
                                    field.set(cli, map2.getValue().toString());
                                    break;
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            resulClientes.add(cli);
            System.out.println(cli.toString2() + "\n");
        }

        System.out.println(resulClientes.size());
        return resulClientes;
    }

    public static String cliUID(Cartao obj) {
        StringBuilder uid = new StringBuilder("select * from " + Cartao.class.getSimpleName() + " where ");
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

    public static ArrayList<Cartao> cartaoCLIUID(int uid) {
        ArrayList<Cartao> resulClientes = new ArrayList<>();

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<Cartao> cartaoCLIUID " + uid);

        if (uid != 0) {
            str.append("select * from cartao where cli_id = " + uid + ";");
        } else if (uid == 0) {
            str.append("select * from cartao");
        }

        System.out.println(str.toString());

        List<Map<String, Object>> rs = connectBD.EXE_Select(str.toString());

        for (Map<String, Object> map : rs) {
            Cartao cli = new Cartao();
            for (Entry<String, Object> map2 : map.entrySet()) {
                Field field;
                try {
                    if (!map2.getKey().equalsIgnoreCase("idCartao")) {
                        if (Cartao.class.getDeclaredField(map2.getKey().toLowerCase()) != null) {
                            field = Cartao.class.getDeclaredField(map2.getKey().toLowerCase());
                        } else {
                            field = Cartao.class.getDeclaredField(map2.getKey());
                        }

                        if (field != null) {
                            switch (field.getType().getSimpleName()) {
                                case "int":
                                    field.set(cli, map2.getValue());
                                    break;
                                default:
                                    field.set(cli, map2.getValue().toString());
                                    break;
                            }

                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            resulClientes.add(cli);
            System.out.println(cli.toString2());
        }
        System.out.println(resulClientes.size());
        return resulClientes;
    }

    public static ArrayList<Cartao> cartaoCLIUID(int uid, NavigableMap<String, String> args) {
        ArrayList<Cartao> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<Cartao> cartaoCLIUID " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from cartao where cli_id = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from cartao");
        } else if (args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from cartao where ");

            for (Entry<String, String> i : args.entrySet()) {
                if (i.getKey().equals(lastEntry.getKey())) {
                    str.append(i.getKey() + " = " + i.getValue() + ";");
                } else {
                    str.append(i.getKey() + " = " + i.getValue() + " AND ");
                }
            }
        }

        System.out.println(str.toString());

        List<Map<String, Object>> rs = connectBD.EXE_Select(str.toString());

        for (Map<String, Object> map : rs) {
            Cartao cli = new Cartao();
            for (Entry<String, Object> map2 : map.entrySet()) {
                Field field;
                try {
                    if (!map2.getKey().equalsIgnoreCase("idCartao")) {
                        if (Cartao.class.getDeclaredField(map2.getKey().toLowerCase()) != null) {
                            field = Cartao.class.getDeclaredField(map2.getKey().toLowerCase());
                        } else {
                            field = Cartao.class.getDeclaredField(map2.getKey());
                        }

                        if (field != null) {
                            switch (field.getType().getSimpleName()) {
                                case "int":
                                    field.set(cli, map2.getValue());
                                    break;
                                default:
                                    field.set(cli, map2.getValue().toString());
                                    break;
                            }

                        }
                    }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            resulClientes.add(cli);
            System.out.println(cli.toString2());
        }
        System.out.println(resulClientes.size());
        return resulClientes;
    }

    public static void CreateTable() {
        try {
            System.out.println("CreateTable no %s: Cartao");
            connectBD.CreateTableX(Cartao.class);
        } catch (Exception e) {
        }
    }

    public static void InserirCBD(Object obj) {
        System.out.println("inserir no %s: " + obj.getClass().getSimpleName());
        try {
            String queryIns = connectBD.InserirTableX(obj);
            connectBD.EXEquery(queryIns);
            System.out.println("sucesso inserir no %s: " + queryIns);
        } catch (Exception e) {
            System.out.println("erro inserir no %s: " + obj.getClass().getSimpleName());
        }
    }

    private int idCartao;

    private int ncartao;

    private String bandeira;

    private String nomecli;

    private int cli_id;

    private int cv;

    private int preferencial;

    public Cartao() {
    }

    public Cartao(String ncartao, String bandeira, String nomecli, String cv) {
        this.ncartao = Integer.parseInt(ncartao);
        this.bandeira = bandeira;
        this.nomecli = nomecli;
        this.cv = Integer.parseInt(cv);
    }

    public Cartao(Map<String, ?> param) {
        System.out.println("public Cartao(Map<String, ?> param)");
        for (Entry<String, ?> entry : param.entrySet()) {
            for (Field field : this.getClass().getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(entry.getKey())) {
                    try {
                        switch (field.getType().getSimpleName()) {
                            case "int":
                                field.set(this, Integer.parseInt(entry.getValue().toString()));
                                break;
                            default:
                                field.set(this, entry.getValue().toString());
                                break;
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void Deletar() {
        StringBuilder Inserir = new StringBuilder();
        Inserir.append("delete from " + Cartao.class.getSimpleName() + " where cli_id " + " = " + init.getUid());

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

    public static void Deletar(int uid) {
        StringBuilder Inserir = new StringBuilder();
        Inserir.append("delete from " + Cartao.class.getSimpleName() + " where idCartaos " + " = " + uid);

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

    public static void update(int uid, NavigableMap<String, String> args) {

        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static update cartao " + uid);
        str.append("UPDATE cartao SET ");

        lastEntry = args.lastEntry();
        for (Entry<String, String> i : args.entrySet()) {
            if (i.getKey().equals(lastEntry.getKey())) {
                switch (i.getValue().getClass().getSimpleName()) {
                    case "String":
                    case "Date":
                        str.append(i.getKey() + " = " + "'" + i.getValue() + "'");
                        break;
                    default:
                        str.append(i.getKey() + " = " + " " + i.getValue() + " ");
                        break;
                }
            } else {
                switch (i.getValue().getClass().getSimpleName()) {
                    case "String":
                    case "Date":
                        str.append(i.getKey() + " = " + "'" + i.getValue() + "' , ");
                        break;
                    default:
                        str.append(i.getKey() + " = " + i.getValue() + " , ");
                        break;
                }
            }
        }
        str.append(" where idCartao = " + uid + ";");

        System.out.println(str.toString());
        // connectBD.executeQuery(str.toString());
        connectBD.EXEquery("UPDATE cartao SET preferencial = " + "'" + "0" + "';");
        connectBD.EXEquery(str.toString());

    }

    public int getNcartao() {
        return ncartao;
    }

    public void setNcartao(int ncartao) {
        this.ncartao = ncartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNomecli() {
        return nomecli;
    }

    public void setNomecli(String nomecli) {
        this.nomecli = nomecli;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public int getCv() {
        return cv;
    }

    public void setCv(int cv) {
        this.cv = cv;
    }

    public int getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(int preferencial) {
        this.preferencial = preferencial;
    }

    public int getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(int idCartao) {
        this.idCartao = idCartao;
    }

    public String toString2() {
        return "Cartao [ncartao=" + ncartao + ", bandeira=" + bandeira + ", nomeCli=" + nomecli + ", cli_id=" + cli_id
                + ", cv=" + cv + ", preferencial=" + preferencial + ", idCartao=" + idCartao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idCartao;
        result = prime * result + ncartao;
        result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
        result = prime * result + ((nomecli == null) ? 0 : nomecli.hashCode());
        result = prime * result + cli_id;
        result = prime * result + cv;
        result = prime * result + preferencial;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cartao other = (Cartao) obj;
        if (idCartao != other.idCartao)
            return false;
        if (ncartao != other.ncartao)
            return false;
        if (bandeira == null) {
            if (other.bandeira != null)
                return false;
        } else if (!bandeira.equals(other.bandeira))
            return false;
        if (nomecli == null) {
            if (other.nomecli != null)
                return false;
        } else if (!nomecli.equals(other.nomecli))
            return false;
        if (cli_id != other.cli_id)
            return false;
        if (cv != other.cv)
            return false;
        if (preferencial != other.preferencial)
            return false;
        return true;
    }

}
