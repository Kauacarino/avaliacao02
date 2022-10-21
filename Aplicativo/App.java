package Aplicativo;

import java.util.Scanner;

import repository.Repositorio;

public class App {
    public static void main(String[] args) {
        int option = -1;
        Scanner scan = new Scanner(System.in);
        Repositorio repositorio = new Repositorio();

        do {
            limpa();
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Realizar venda");
            System.out.println("5 - Vendas por período");
            System.out.println("0 - Sair");
            System.out.print("Digite aqui: ");
            
            option = scan.nextInt();
            scan.nextLine();

            switch (option) {
                case 1:
                    repositorio.incluirProduto();
                    break;
                case 2:
                    System.out.print("Digite o código do produto: ");
                    repositorio.consultarProduto(scan.nextLine());
                    break;
                case 3:
                    repositorio.listagemProdutos();
                    break;
                case 4:
                    repositorio.realizarVenda();
                    break;
                case 5:
                    repositorio.vendasPorPeriodo();
                    break;
                default:
                    break;
            }
        } while(option !=0);

        scan.close();
        }

        public static void limpa(){
            System.out.print("Everything on the console will cleared");
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
}
