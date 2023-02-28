package fatec.ph.les.entidade;

import fatec.ph.les.servicos.connectBD;

public class Endereco {
    private int cliuid;
    private String pais;
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private String bairro;
    private String numero;
    private String complemento;
    private String tipoResidencia;

    public Endereco(int cliuid, String cep, String estado, String cidade, String rua, String bairro, String numero,
            String complemento) {
        this.cliuid = cliuid;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public Endereco(String cep, String estado, String cidade, String rua, String bairro, String numero,
            String complemento) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public static void CreateTable() {
        try {
            connectBD.CreateTableX(Endereco.class);
        } catch (Exception e) {
            // TODO: handle exception
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

    public int getCliuid() {
        return cliuid;
    }

    public void setCliuid(int cliuid) {
        this.cliuid = cliuid;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(String tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

}
