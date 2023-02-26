package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.rmi.server.UID;

import fatec.ph.les.connect.connectBD;

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

    public String CreateClienteTable() {

        System.out.println("aqui");
        StringBuilder table = new StringBuilder();

        Field[] clienFields = this.getClass().getDeclaredFields();
        System.out.println("aqui" + clienFields.length);
        int i = 0;

        for (Field f : clienFields) {
            i = i + 1;

            String name = f.getName();
            System.out.println("field:: " + f.getType().getSimpleName());

            System.out.println("field:: " + f.getType().getSimpleName().equals("String"));
            System.out.println("field:: int " + f.getType().getSimpleName().equals("int"));
            System.out.println("field:: Integer " + f.getType().getSimpleName().equals("Integer"));

            if (i <= clienFields.length - 1) {
                table.append(name + " , ");
            } else {
                table.append(" " + name);
            }
            System.out.println(table.toString() + " " + i);

        }

        return table.toString();

    }

    public static void Inserir(String NomeInserir, String SenhaInserir, String EmailInserir) {
        StringBuilder Inserir = new StringBuilder();
        String name;
        int i = 0;
        Field[] Fields = Cliente.class.getDeclaredFields();
        Inserir.append("insert into " + Cliente.class.getSimpleName() + " (");

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
        Inserir.append(
                "'" + SenhaInserir + "'" + " , " + "'" + NomeInserir + "'" + " , " + "'" + EmailInserir + "'" + " );");
        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
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
