package fatec.ph.les.entidade.Carrinho;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import fatec.ph.les.servicos.connectBD;

public class orderdetails {
    private int idorderdetails;
    private int orderid;
    private int livro_id;
    private int quant;

    public orderdetails() {
    }

    public orderdetails(int idorderdetails, int orderid, int livro_id, int quant) {
        this.idorderdetails = idorderdetails;
        this.orderid = orderid;
        this.livro_id = livro_id;
        this.quant = quant;
    }

    public orderdetails(Map<String, ?> param) {
        System.out.println("public orderdetails (Map<String, ?> param)");
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

    public int getIdorderdetails() {
        return idorderdetails;
    }

    public void setIdorderdetails(int idorderdetails) {
        this.idorderdetails = idorderdetails;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

}
