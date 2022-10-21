package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {
    private LocalDate dataDaVenda;
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Produto produtoVendido;
    private int qtdVendida;

    public Venda() {
        
    }

    public Venda(String dataDaVenda, Produto produtoVendido, int qtdVendida) {
        setDataDaVenda(dataDaVenda);
        this.produtoVendido = produtoVendido;
        this.qtdVendida = qtdVendida;
    }

    public LocalDate getDataDaVenda() {
        return dataDaVenda;
    }
    public void setDataDaVenda(String dataDaVenda) {
        this.dataDaVenda = LocalDate.parse(dataDaVenda, dtf);
    }
    public Produto getProdutoVendido() {
        return produtoVendido;
    }
    public void setProdutoVendido(Produto produtoVendido) {
        this.produtoVendido = produtoVendido;
    }
    public int getQtdVendida() {
        return qtdVendida;
    }
    public void setQtdVendida(int qtdVendida) {
        this.qtdVendida = qtdVendida;
    }

    @Override
    public String toString() {
        return String.format("[Data da Venda: %s - Produto: %s - Quantidade Vendida: %s - Valor Unitário: %.2f - Valor Total: %.2f]", 
        getDataDaVenda().format(dtf), getProdutoVendido().getNome(), getQtdVendida(), 
        getProdutoVendido().getValor(), (getProdutoVendido().getValor()) * getQtdVendida());
    }
}
