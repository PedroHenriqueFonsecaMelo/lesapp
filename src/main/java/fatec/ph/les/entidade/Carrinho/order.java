package fatec.ph.les.entidade.Carrinho;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

public class order {
    private int idorder;
    private int cli_id;
    private float valorTotal;
    private String status;
    private String enderecoentrega;

    public order() {
    }

    public order(int idorder, int cli_id, float valorTotal, String status, String enderecoentrega) {
        this.idorder = idorder;
        this.cli_id = cli_id;
        this.valorTotal = valorTotal;
        this.status = status;
        this.enderecoentrega = enderecoentrega;
    }

    public order(Map<String, ?> param) {
        System.out.println("public order (Map<String, ?> param)");
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

    public int getIdorder() {
        return idorder;
    }

    public void setIdorder(int idorder) {
        this.idorder = idorder;
    }

    public int getCli_id() {
        return cli_id;
    }

    public void setCli_id(int cli_id) {
        this.cli_id = cli_id;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnderecoentrega() {
        return enderecoentrega;
    }

    public void setEnderecoentrega(String enderecoentrega) {
        this.enderecoentrega = enderecoentrega;
    }

}
