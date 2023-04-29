package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import fatec.ph.les.servicos.connectBD;
import fatec.ph.les.servicos.init;

public class Cliente {

    public static String cliUID(Cliente obj) {
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

        fieldname = connectBD.EXE_Select_UID(uid.toString());

        return fieldname;
    }

    public static String cliUID(Cliente obj, Map<?, ?> param) {
        StringBuilder uid = new StringBuilder("select * from " + Cliente.class.getSimpleName() + " where ");

        String fieldname = "";

        for (Entry<?, ?> iterable_element : param.entrySet()) {
            uid.append(iterable_element.getKey() + " = " + "'" + iterable_element.getValue() + "'" + " AND ");
        }
        uid.replace(uid.length() - 4, uid.length(), ";");

        fieldname = connectBD.EXE_Select_UID(uid.toString());

        return fieldname;
    }

    public static ArrayList<Cliente> cliente(int uid, NavigableMap<String, String> args) {
        ArrayList<Cliente> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();

        if (uid != 0 & args == null) {
            str.append("select * from CLIENTE where idCliente = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from CLIENTE");
        } else if (args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from Cliente where ");

            for (Entry<String, String> i : args.entrySet()) {
                if (i.getKey().equals(lastEntry.getKey())) {
                    str.append(i.getKey() + " = " + i.getValue() + ";");
                } else {
                    str.append(i.getKey() + " = " + i.getValue() + " AND ");
                }
            }
        }

        List<Map<String, Object>> rs = connectBD.EXE_Select(str.toString());

        for (Map<String, Object> map : rs) {
            Cliente cli = new Cliente();
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

        }

        return resulClientes;
    }

    public static void CreateTable() {
        try {
            System.out.println("CreateTable no %s: Cliente");
            connectBD.CreateTableX(Cliente.class);
        } catch (Exception e) {
        }
    }

    public static void InserirCBD(Object obj) {

        try {

            String queryIns = connectBD.InserirTableX(obj);
            connectBD.EXEquery(queryIns);
            System.out.println("sucesso inserir no %s: " + queryIns);
        } catch (Exception e) {
            System.out.println("erro inserir no %s: " + obj.getClass().getSimpleName());
        }
    }

    public static void DeletarCBD(int uid, Class<?> cli) {

        try {
            String queryIns = connectBD.DeletarTableX(uid, cli);
            connectBD.EXEquery(queryIns);
            System.out.println("sucesso deletar no %s: " + cli.getClass().getSimpleName());
        } catch (Exception e) {
            System.out.println("erro deletar no %s: " + cli.getClass().getSimpleName());
        }
    }

    public static void Deletar(int uid) {
        StringBuilder Inserir = new StringBuilder();
        Inserir.append("delete from " + Cliente.class.getSimpleName() + " where id" + Cliente.class.getSimpleName()
                + " = " + uid);

        connectBD.EXEquery(Inserir.toString());
    }

    public static void update(int uid, NavigableMap<String, String> args) {

        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();

        str.append("UPDATE CLIENTE SET ");

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
        str.append(" where idCliente = " + init.getUid() + ";");

        connectBD.EXEquery(str.toString());

    }

    private int idCliente;

    private String senha;

    private String nome;

    private String datanasc;

    private String gen;

    private String email;

    public Cliente() {
    }

    public Cliente(String senha, String nome, String datanasc, String gen, String email, int idCliente) {

        this.senha = senha;
        this.nome = nome;
        this.datanasc = datanasc;
        this.gen = gen;
        this.email = email;
        this.idCliente = idCliente;
    }

    public Cliente(Map<String, ?> param) {

        for (Entry<String, ?> entry : param.entrySet()) {
            Field field;
            try {
                field = this.getClass().getDeclaredField(entry.getKey());
                if (field != null) {
                    field.set(this, entry.getValue());
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String toString2() {
        return "Cliente [senha=" + senha + ", nome=" + nome + ", datanasc=" + datanasc + ", gen=" + gen + ", email="
                + email + "]";
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((senha == null) ? 0 : senha.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((datanasc == null) ? 0 : datanasc.hashCode());
        result = prime * result + ((gen == null) ? 0 : gen.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        Cliente other = (Cliente) obj;
        if (senha == null) {
            if (other.senha != null)
                return false;
        } else if (!senha.equals(other.senha))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (datanasc == null) {
            if (other.datanasc != null)
                return false;
        } else if (!datanasc.equals(other.datanasc))
            return false;
        if (gen == null) {
            if (other.gen != null)
                return false;
        } else if (!gen.equals(other.gen))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

    public static Map<String, String> info() {
        Map<String, String> map = new HashMap<>();
        for (Field iterable_element : Cliente.class.getDeclaredFields()) {
            if (!iterable_element.getName().contains("id")) {
                String tipo = iterable_element.getType().getSimpleName();
                String name = iterable_element.getName();

                map.put(name, tipo);

            }
        }
        return map;
    }
}
