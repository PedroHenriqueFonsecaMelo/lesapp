package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.Map;

import fatec.ph.les.servicos.connectBD;

public class Cliente {

    private String senha;
    private String nome;
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

    public Cliente() {
    }

    public Cliente(String senha, String nome, String email) {
        this.senha = senha;
        this.nome = nome;
        this.email = email;
    }

    public Cliente(Map<String, ?> param) {
        System.out.println("endereco part 1:  ");
        for (Field field : this.getClass().getDeclaredFields()) {
            for (Map.Entry<String, ?> entry : param.entrySet()) {
                if (field.getName().equals(entry.getKey())
                        & field.getType().equals(entry.getValue().getClass())) {
                    try {
                        field.set(this, entry.getValue());
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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

    public static String cliUID(Cliente obj) {
        StringBuilder uid = new StringBuilder("select * from " + Cliente.class.getSimpleName() + " where ");
        Field[] Fields = obj.getClass().getDeclaredFields();
        String fieldname = "";
        int i = 0;

        for (Field f : Fields) {
            i++;
            switch (f.getType().getSimpleName()) {
                case "String":
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
        fieldname = connectBD.EXE_Select(uid.toString());

        return fieldname;
    }

    public static void CreateTable() {
        try {
            connectBD.CreateTableX(Cliente.class);
        } catch (Exception e) {
            // TODO: handle exception
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
