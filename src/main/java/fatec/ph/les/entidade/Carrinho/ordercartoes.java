package fatec.ph.les.entidade.Carrinho;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import fatec.ph.les.servicos.connectBD;

public class ordercartoes {
    private int idordercartoes;
    private int orderid;
    private int cartao_id;
    private int vezes;

    public ordercartoes() {
    }

    public ordercartoes(int idordercartoes, int orderid, int cartao_id, int vezes) {
        this.idordercartoes = idordercartoes;
        this.orderid = orderid;
        this.cartao_id = cartao_id;
        this.vezes = vezes;
    }

    public ordercartoes(Map<String, ?> param) {
        System.out.println("public ordercartoes (Map<String, ?> param)");
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

    public int getIdordercartoes() {
        return idordercartoes;
    }

    public void setIdordercartoes(int idordercartoes) {
        this.idordercartoes = idordercartoes;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCartao_id() {
        return cartao_id;
    }

    public void setCartao_id(int cartao_id) {
        this.cartao_id = cartao_id;
    }

    public int getVezes() {
        return vezes;
    }

    public void setVezes(int vezes) {
        this.vezes = vezes;
    }

}
