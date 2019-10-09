package br.com.easycampaign.model;

public class Campanha {

    private String campanhaId;
    private String campanhaNome;
    private String campanhaProduto;
    private String campanhaDataInicio;
    private String campanhaDataFim;


    // Constructor vazio
    public Campanha(){

    }

    //Constructor cheio
    public Campanha(String campanhaId, String campanhaNome, String campanhaProduto, String campanhaDataInicio, String campanhaDataFim) {
        this.campanhaId = campanhaId;
        this.campanhaNome = campanhaNome;
        this.campanhaProduto = campanhaProduto;
        this.campanhaDataInicio = campanhaDataInicio;
        this.campanhaDataFim = campanhaDataFim;
    }

    //Construtor personalizado
    public Campanha(String campanhaId, String campanhaNome, String campanhaDataInicio, String campanhaDataFim) {
        this.campanhaId = campanhaId;
        this.campanhaNome = campanhaNome;
        this.campanhaProduto = campanhaProduto;
        this.campanhaDataInicio = campanhaDataInicio;
        this.campanhaDataFim = campanhaDataFim;
    }

    //Getter`s and SEtter`s
    public String getCampanhaId() {
        return campanhaId;
    }

    public void setCampanhaId(String campanhaId) {
        this.campanhaId = campanhaId;
    }

    public String getCampanhaNome() {
        return campanhaNome;
    }

    public void setCampanhaNome(String campanhaNome) {
        this.campanhaNome = campanhaNome;
    }

    public String getCampanhaProduto() {
        return campanhaProduto;
    }

    public void setCampanhaProduto(String campanhaProduto) {
        this.campanhaProduto = campanhaProduto;
    }

    public String getCampanhaDataInicio() {
        return campanhaDataInicio;
    }

    public void setCampanhaDataInicio(String campanhaDataInicio) {
        this.campanhaDataInicio = campanhaDataInicio;
    }

    public String getCampanhaDataFim() {
        return campanhaDataFim;
    }

    public void setCampanhaDataFim(String campanhaDataFim) {
        this.campanhaDataFim = campanhaDataFim;
    }
}
