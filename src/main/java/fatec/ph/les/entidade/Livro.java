package fatec.ph.les.entidade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import fatec.ph.les.servicos.connectBD;

public class Livro {
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

    public static ArrayList<Livro> livroCLIUID(int uid, int cart) {
        ArrayList<Livro> resulClientes = new ArrayList<>();

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<Cartao> livroCLIUID " + uid);

        if (cart != 0 & uid == 0) {
            str.append("select * from livro where categorias = " + uid + ";");
        } else if (cart == 0 & uid != 0) {
            str.append("select * from livro where idlivro = " + uid + ";");
        } else if (uid == 0 & cart == 0) {
            str.append("select * from livro");
        }

        System.out.println(str.toString());

        List<Map<String, Object>> rs = connectBD.EXE_Select(str.toString());

        for (Map<String, Object> map : rs) {
            Livro cli = new Livro();
            for (Entry<String, Object> map2 : map.entrySet()) {
                Field field;
                try {
                    // if (!map2.getKey().equalsIgnoreCase("idLivro")) {
                    field = Livro.class.getDeclaredField(map2.getKey().toLowerCase());
                    if (field != null) {
                        switch (field.getType().getSimpleName()) {
                            case "int":
                                field.set(cli, Integer.parseInt(map2.getValue().toString()));
                                break;
                            case "float":
                                field.set(cli, Float.parseFloat(map2.getValue().toString()));
                                break;
                            default:
                                field.set(cli, map2.getValue().toString());
                                break;
                        }
                    }
                    // }
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

    public static ArrayList<Livro> livro(int uid, NavigableMap<String, String> args) {
        ArrayList<Livro> resulClientes = new ArrayList<>();
        Entry<String, String> lastEntry;

        StringBuilder str = new StringBuilder();
        System.out.println(" public static ArrayList<cartao> livro " + uid);

        if (uid != 0 & args == null) {
            str.append("select * from cartao where idlivro = " + uid + ";");
        } else if (uid == 0 & args == null) {
            str.append("select * from livro");
        } else if (args != null) {
            lastEntry = args.lastEntry();
            str.append("select * from livro where ");

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
            Livro cli = new Livro();
            for (Entry<String, Object> map2 : map.entrySet()) {
                Field field;
                try {
                    // if (!map2.getKey().equalsIgnoreCase("idlivro")) {
                    field = Livro.class.getDeclaredField(map2.getKey().toLowerCase());
                    if (field != null) {
                        switch (field.getType().getSimpleName()) {
                            case "int":
                                field.set(cli, map2.getValue());
                                break;
                            case "float":
                                field.set(cli, Float.parseFloat(map2.getValue().toString()));
                                break;
                            default:
                                field.set(cli, map2.getValue().toString());
                                break;
                        }
                        resulClientes.add(cli);
                    }
                    // }
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                resulClientes.add(cli);
                System.out.println(cli.toString2());
            }

            resulClientes.add(cli);
            System.out.println(cli.toString2());
        }

        System.out.println(resulClientes.size());
        return resulClientes;
    }

    public static void CreateTable() {
        try {
            System.out.println("CreateTable no %s: Livro");
            connectBD.CreateTableX(Livro.class);
        } catch (Exception e) {
        }
    }

    private int idlivro;
    private String autor;
    private int ano;
    private String titulo;
    private String editora;
    private int edicao;
    private int isbn;
    private int npaginas;
    private String sinopse;
    private float altura;
    private float largura;
    private String categorias;

    private float peso;

    private float profundidade;

    private float precificacao;

    private String barras;

    public Livro() {
    }

    public Livro(Map<String, ?> param) {
        System.out.println("public Cartao(Map<String, ?> param)");
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getEdicao() {
        return edicao;
    }

    public void setEdicao(int edicao) {
        this.edicao = edicao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getNpaginas() {
        return npaginas;
    }

    public void setNpaginas(int npaginas) {
        this.npaginas = npaginas;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getLargura() {
        return largura;
    }

    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(float profundidade) {
        this.profundidade = profundidade;
    }

    public float getPrecificacao() {
        return precificacao;
    }

    public void setPrecificacao(float precificacao) {
        this.precificacao = precificacao;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public int getIdlivro() {
        return idlivro;
    }

    public void setIdlivro(int idlivro) {
        this.idlivro = idlivro;
    }

    public String toString2() {
        return "Livro [idlivro=" + idlivro + ", autor=" + autor + ", ano=" + ano + ", titulo=" + titulo + ", editora="
                + editora + ", edicao=" + edicao + ", isbn=" + isbn + ", npaginas=" + npaginas + ", sinopse=" + sinopse
                + ", altura=" + altura + ", largura=" + largura + ", categorias=" + categorias + ", peso=" + peso
                + ", profundidade=" + profundidade + ", precificacao=" + precificacao + ", barras=" + barras + "]";
    }

}
