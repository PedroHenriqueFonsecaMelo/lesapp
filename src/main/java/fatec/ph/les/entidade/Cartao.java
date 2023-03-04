package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

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

    public static ArrayList<Cartao> cartao(int uid, NavigableMap<String, String> args) {
        ArrayList<Cartao> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<cartao> cartao " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from cartao where idcartao = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from cartao");
        } else {
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
                switch (map2.getKey()) {
                    case "Bandeira":
                        cli.setBandeira(map2.getValue().toString());
                        break;
                    case "Cv":
                        cli.setCv(Integer.parseInt((String) map2.getValue()));
                        break;
                    case "NomeCli":
                        cli.setNomeCli(map2.getValue().toString());
                        break;
                    case "Ncartao":
                        cli.setNcartao(Integer.parseInt((String) map2.getValue()));
                        break;
                    case "Cli_id":
                        cli.setCli_id(Integer.parseInt((String) map2.getValue()));
                        break;
                    default:
                        break;
                }
            }

            resulClientes.add(cli);
            System.out.println(cli.toString2());
        }

        System.out.println(resulClientes.size());
        return resulClientes;
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
