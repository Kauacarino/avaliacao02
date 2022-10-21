package model;

public class Produto {
    private String codigo;
    private String nome;
    private Double valor;
    private int qtdEstoque;
    
    public Produto(String codigo, String nome, Double valor, int qtdEstoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public int getQtdEstoque() {
        return qtdEstoque;
    }
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    @Override
    public String toString() {
        return String.format("[Código: %s / Nome: %s / Valor: %.2f / Quantidade em Estoque: %d]",
        getCodigo(), getNome(), getValor(), getQtdEstoque());
    }
}