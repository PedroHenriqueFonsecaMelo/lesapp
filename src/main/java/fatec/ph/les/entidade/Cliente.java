package fatec.ph.les.entidade;

import java.lang.reflect.Field;

import fatec.ph.les.connect.connectBD;

public class Cliente {

    private String senha = "1";
    private String nome = "1";
    private String email = "1";

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

    public static void Inserir(String SenhaInserir, String NomeInserir, String EmailInserir) {
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

    public static void Deletar(int uid) {
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

        System.out.println(Inserir.toString());
        connectBD.EXEquery(Inserir.toString());
    }

}
