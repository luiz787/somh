package br.cefetmg.inf.somh.domain;

public class OS {
    private Long id;
    private Cliente cliente;
    private Equipamento equipamento;
    private String txtReclamacao;
    private String txtObservacaoAcessorios;
    private Double vlrDesconto;
    private Double perDesconto;
    private Double vlrFrete;

    public OS() {
    }

    public OS(Long id, Cliente cliente, Equipamento equipamento, String txtReclamacao, String txtObservacaoAcessorios, Double vlrDesconto, Double perDesconto, Double vlrFrete) {
        this.id = id;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.txtReclamacao = txtReclamacao;
        this.txtObservacaoAcessorios = txtObservacaoAcessorios;
        this.vlrDesconto = vlrDesconto;
        this.perDesconto = perDesconto;
        this.vlrFrete = vlrFrete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public String getTxtReclamacao() {
        return txtReclamacao;
    }

    public void setTxtReclamacao(String txtReclamacao) {
        this.txtReclamacao = txtReclamacao;
    }

    public String getTxtObservacaoAcessorios() {
        return txtObservacaoAcessorios;
    }

    public void setTxtObservacaoAcessorios(String txtObservacaoAcessorios) {
        this.txtObservacaoAcessorios = txtObservacaoAcessorios;
    }

    public Double getVlrDesconto() {
        return vlrDesconto;
    }

    public void setVlrDesconto(Double vlrDesconto) {
        this.vlrDesconto = vlrDesconto;
    }

    public Double getPerDesconto() {
        return perDesconto;
    }

    public void setPerDesconto(Double perDesconto) {
        this.perDesconto = perDesconto;
    }

    public Double getVlrFrete() {
        return vlrFrete;
    }

    public void setVlrFrete(Double vlrFrete) {
        this.vlrFrete = vlrFrete;
    }
}
