package repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import model.Produto;
import model.Venda;

public class Repositorio {
    Scanner scan = new Scanner(System.in);
    private List<Produto> listaDeProdutos = new ArrayList<>();
    private List<Venda> listaDeVendas = new ArrayList<>();

    LocalDate dataInicial = LocalDate.of(1000, 1, 1);
    LocalDate dataFinal = LocalDate.of(1000, 1, 1);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void incluirProduto(){
        limpa();
        System.out.print("Insira um código para o produto: ");
        String codigo = scan.nextLine();
        for (Produto p : listaDeProdutos) {
            if (p.getCodigo().equals(codigo)){
                throw new InputMismatchException("Esse código já existe.");
            }
        }
        System.out.print("Insira o nome do produto: ");
        String nome = scan.nextLine();
        System.out.print("Insira o valor: ");
        Double valor = scan.nextDouble();
        scan.nextLine();
        System.out.print("Insira a quantidade em estoque: ");
        int qtdEstoque = scan.nextInt();
        scan.nextLine();
        listaDeProdutos.add(new Produto(codigo, nome, valor, qtdEstoque));
        System.out.print("Produto incluido com sucesso. Aperte [ENTER] para continuar.");
        scan.nextLine();
    }

    public void consultarProduto(String codigo){
        limpa();
        verificarProdutosExistentes();
        for (Produto p : listaDeProdutos) {
            if (p.getCodigo().equals(codigo)) {
                System.out.println(p);
            }
        }
        System.out.print("Aperte [ENTER] para continuar.");
        scan.nextLine();
    }

    public void listagemProdutos(){
        limpa();
        verificarProdutosExistentes();
        System.out.println("- Listagem de Produtos -\n");
        listaDeProdutos.forEach(System.out::println);
        System.out.println("--------------------------------------");
        DoubleSummaryStatistics valor = listaDeProdutos.stream().
        collect(Collectors.summarizingDouble((Produto::getValor)));
        System.out.printf("[Valor médio: %.2f / Valor máximo: %.2f / Valor mínimo: %.2f]",
        valor.getAverage(), valor.getMax(), valor.getMin());
        System.out.print("\nAperte [ENTER] para continuar.");
        scan.nextLine();
    }

    public void realizarVenda(){
        limpa();
        verificarProdutosExistentes();
        Venda venda = new Venda();
        System.out.print("Insira o código do produto: ");
        String codigo = scan.nextLine();
        for (Produto p : listaDeProdutos) {
            if (p.getCodigo().equals(codigo)){
                venda.setProdutoVendido(p);
            }
        }
        System.out.print("Insira a data da venda (DD/MM/AAAA): ");
        String dataVenda = scan.nextLine();
        venda.setDataDaVenda(dataVenda);
        System.out.print("Digite a quantidade que será vendida: ");
        int qtdVenda = scan.nextInt();
        scan.nextLine();
        venda.setQtdVendida(qtdVenda);
        if (qtdVenda > venda.getProdutoVendido().getQtdEstoque()){
            System.out.println("Você não tem uma quantidade suficiente desse produto em estoque.");
        } else {
            listaDeVendas.add(venda);
            venda.getProdutoVendido().setQtdEstoque(venda.getProdutoVendido().getQtdEstoque() - venda.getQtdVendida());
        }
        System.out.print("\nAperte [ENTER] para continuar.");
        scan.nextLine();
    }

    public void vendasPorPeriodo(){
        limpa();
        String data1 = "";
        String data2 = "";

        try {
            System.out.print("Digite a data mínima: ");
            data1 = scan.nextLine();
            dataInicial = LocalDate.parse(data1, dtf);
            System.out.print("Digite a data máxima: ");
            data2 = scan.nextLine();
            dataFinal = LocalDate.parse(data2, dtf);
        } catch (DateTimeParseException e) {
            System.out.println("Data não inserida corretamente.");
        }

        System.out.println("* Lista de vendas entre " + dataInicial.format(dtf) + " e " + dataFinal.format(dtf) + " *\n");

        listaDeVendas.stream().
        filter(p -> p.getDataDaVenda().compareTo(dataInicial) >= 0 && p.getDataDaVenda().compareTo(dataFinal) <= 0).
        forEach(System.out::println);
        System.out.println("--------------------------------------");
        DoubleSummaryStatistics valor = listaDeVendas.stream().
        filter(p -> p.getDataDaVenda().compareTo(dataInicial) >= 0 && p.getDataDaVenda().compareTo(dataFinal) <= 0).
        collect(Collectors.summarizingDouble((p -> {return p.getProdutoVendido().getValor();})));
        System.out.println("Valor médio: " + valor.getAverage());
        System.out.print("\nAperte [ENTER] para continuar.");
        scan.nextLine();
    }

    private void verificarProdutosExistentes(){
        if (listaDeProdutos.isEmpty()){
            throw new NullPointerException("Não foi incluido um produto ainda.");
        }
    }

    public static void limpa(){
        System.out.print("Everything on the console will cleared");
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
