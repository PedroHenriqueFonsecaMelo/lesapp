package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.Map;

import fatec.ph.les.servicos.connectBD;

public class Cliente {

    private String senha;
    private String nome;
    private String datanasc;
    private String gen;
    private String email;

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

    public Cliente() {
    }

    public Cliente(String senha, String nome, String datanasc, String gen, String email) {

        this.senha = senha;
        this.nome = nome;
        this.datanasc = datanasc;
        this.gen = gen;
        this.email = email;
    }

    public String toString2() {
        return "Cliente [senha=" + senha + ", nome=" + nome + ", datanasc=" + datanasc + ", gen=" + gen + ", email="
                + email + "]";
    }

    public Cliente(Map<String, ?> param) {
        System.out.println("Cliente part 1:  ");
        for (Field field : this.getClass().getDeclaredFields()) {
            for (Map.Entry<String, ?> entry : param.entrySet()) {
                if (field.getName().equalsIgnoreCase(entry.getKey())) {
                    try {
                        switch (field.getType().getSimpleName()) {
                            case "int":
                                field.set(this, Integer.parseInt((String) entry.getValue()));
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

    public static String cliUID(Cliente obj) {
        StringBuilder uid = new StringBuilder("select * from " + Cliente.class.getSimpleName() + " where ");
        Field[] Fields = obj.getClass().getDeclaredFields();
        String fieldname = "";
        int i = 0;

        for (Field f : Fields) {
            i++;
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
        System.out.println(uid.toString());
        fieldname = connectBD.EXE_Select_UID(uid.toString());

        return fieldname;
    }

    public static void CreateTable() {
        try {
            System.out.println("CreateTable no %s: Cliente");
            connectBD.CreateTableX(Cliente.class);
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

    public static void DeletarCBD(int uid, Class cli) {
        System.out.println("deletar no %s: " + uid);
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
        Inserir.append("delete from" + Cliente.class.getSimpleName() + " where id" + Cliente.class.getSimpleName()
                + " = " + uid);

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

}
