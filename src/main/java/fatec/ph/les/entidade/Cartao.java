package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.Map;

import fatec.ph.les.servicos.connectBD;

public class Cartao {
    private int ncartao;
    private String bandeira;
    private String nomeCli;
    private int cli_id;
    private int cv;

    public Cartao() {
    }

    public Cartao(String ncartao, String bandeira, String nomeCli, String cv) {
        this.ncartao = Integer.parseInt(ncartao);
        this.bandeira = bandeira;
        this.nomeCli = nomeCli;
        this.cv = Integer.parseInt(cv);
    }

    public Cartao(Map<String, ?> param) {
        System.out.println("Cartao part 1:  ");
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

    public String getNomeCli() {
        return nomeCli;
    }

    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
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

    public String toString2() {
        return "Cartao [ncartao=" + ncartao + ", bandeira=" + bandeira + ", nomeCli=" + nomeCli + ", cli_id=" + cli_id
                + ", cv=" + cv + "]";
    }

}
