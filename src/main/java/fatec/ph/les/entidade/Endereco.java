package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

public class Endereco {
    public static void CreateTable() {
        try {
            connectBD.CreateTableX(Endereco.class);
        } catch (Exception e) {
        }
    }

    public static String cliUID(Endereco obj) {
        StringBuilder uid = new StringBuilder("select * from " + Endereco.class.getSimpleName() + " where ");
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

    public static void InserirCBD(Object obj) {
        System.out.println("inserir no %s: " + obj.getClass().getSimpleName());
        try {
            String queryIns = connectBD.InserirTableX(obj);
            connectBD.EXEquery(queryIns);
            System.out.println("sucesso inserir no %s: " + obj.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("erro inserir no %s: " + obj.getClass().getSimpleName());
        }
    }

    public static ArrayList<Endereco> endereco(int uid, NavigableMap<String, String> args) {
        ArrayList<Endereco> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<Endereco> Endereco " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from Endereco where cliuid = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from Endereco");
        } else if (args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from Endereco where ");

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
            Endereco cli = new Endereco();
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
            System.out.println(cli.toString2());
        }

        System.out.println(resulClientes.size());
        return resulClientes;
    }

    private int idEndereco;
    private int cliuid;
    private String pais;
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private String bairro;
    private int numero;
    private String complemento;
    private String tiporesidencia;

    public Endereco(int uid, String cep, String estado, String cidade, String rua, String bairro, String numero,
            String complemento) {
        this.cliuid = uid;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = Integer.parseInt(numero);
        this.complemento = complemento;
    }

    public Endereco(String pais, String cep, String estado, String cidade, String rua, String bairro, String numero,
            String complemento, String tiporesidencia) {
        this.pais = pais;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = Integer.parseInt(numero);
        this.complemento = complemento;
        this.tiporesidencia = tiporesidencia;
    }

    public Endereco() {
    }

    public Endereco(Map<String, ?> param) {
        System.out.println("public Endereco(Map<String, ?> param)");
        for (Entry<String, ?> entry : param.entrySet()) {

            Field field;
            try {
                if (this.getClass().getDeclaredField(entry.getKey()) != null) {
                    field = this.getClass().getDeclaredField(entry.getKey());
                } else {
                    field = this.getClass().getDeclaredField(entry.getKey().toLowerCase());
                }

                if (field != null) {
                    switch (field.getType().getSimpleName()) {
                        case "String":
                            field.set(this, entry.getValue());
                            break;
                        case "int":
                            field.set(this, Integer.parseInt(entry.getValue().toString()));
                            break;
                        default:
                            break;
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static void update(int uid, NavigableMap<String, String> args) {

        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static update endereco " + uid);
        str.append("UPDATE endereco SET ");

        lastEntry = args.lastEntry();
        for (Entry<String, String> i : args.entrySet()) {
            if (i.getKey().equals(lastEntry.getKey())) {
                argsParse(str, i);
            } else {
                argsParse2(str, i);
            }
        }
        str.append(" where idEndereco = " + uid + ";");

        System.out.println(str.toString());
        // connectBD.executeQuery(str.toString());
        connectBD.EXEquery(str.toString());

    }

    private static void argsParse2(StringBuilder str, Entry<String, String> i) {
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

    private static void argsParse(StringBuilder str, Entry<String, String> i) {
        switch (i.getValue().getClass().getSimpleName()) {
            case "String":
            case "Date":
                str.append(i.getKey() + " = " + "'" + i.getValue() + "'");
                break;
            case "int":
                str.append(i.getKey() + " = " + " " + i.getValue() + " ");
                break;
        }
    }

    public static void Deletar() {
        StringBuilder Inserir = new StringBuilder();
        Inserir.append("delete from " + Endereco.class.getSimpleName() + " where cliuid " + " = " + init.getUid());

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

    public static void Deletar(int uid) {
        StringBuilder Inserir = new StringBuilder();
        Inserir.append("delete from " + Endereco.class.getSimpleName() + " where idEndereco " + " = " + uid);

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCliuid() {
        return cliuid;
    }

    public void setCliuid(int cliuid) {
        this.cliuid = cliuid;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = Integer.parseInt(numero);
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTiporesidencia() {
        return tiporesidencia;
    }

    public void setTiporesidencia(String tiporesidencia) {
        this.tiporesidencia = tiporesidencia;
    }

    public String toString2() {
        return "Endereco [cliuid=" + cliuid + ", pais=" + pais + ", cep=" + cep + ", estado=" + estado + ", cidade="
                + cidade + ", rua=" + rua + ", bairro=" + bairro + ", numero=" + numero + ", complemento=" + complemento
                + ", tiporesidencia=" + tiporesidencia + "]";
    }

}
