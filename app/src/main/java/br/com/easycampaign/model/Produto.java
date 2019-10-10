package br.com.easycampaign.model;

public class Produto {

    private String produtoId;
    private String produtoNome;
    private String produtoDesc;
    private String produtoQtd;

    //Construtor vazio
    public Produto() {
    }

    //Construtor Cheio
    public Produto(String produtoId, String produtoNome, String produtoDesc, String produtoQtd) {
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.produtoDesc = produtoDesc;
        this.produtoQtd = produtoQtd;
    }


    //Getter`s and Setter`s

    public String getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(String produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public String getProdutoDesc() {
        return produtoDesc;
    }

    public void setProdutoDesc(String produtoDesc) {
        this.produtoDesc = produtoDesc;
    }

    public String getProdutoQtd() {
        return produtoQtd;
    }

    public void setProdutoQtd(String produtoQtd) {
        this.produtoQtd = produtoQtd;
    }
}
