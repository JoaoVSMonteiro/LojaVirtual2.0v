package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.Estoque;
import entities.Produto;

public class Main {

    public static void main(String[] args) {
        List<Produto> listaProdutos = new ArrayList<Produto>();
        Estoque estoque = new Estoque();

        int opcao;
        int codigo = 1;

        do {
            System.out.println("\nGERENCIAMENTO DE LOJA VIRTUAL\n" 	   
                   + "\nSELECIONE UMA OPÇÃO: \n\n" 
                   + "1) Listar todos os produtos \n" 
                   + "2) Cadastrar um novo produto \n" 
                   + "3) Adicionar estoque de um produto \n" 
                   + "4) Remover produto \n" 
                   + "5) Vender produto \n" 
                   + "6) Listar os produtos cadastrados por um determinado vendedor \n" 
                   + "7) Contar quantos produtos foram cadastrados por um determinado vendedor \n" 
                   + "8) Somar os valores dos produtos cadastrados por um determinado vendedor \n" 
                   + "9) Sair \n");

            Scanner sc = new Scanner(System.in);
            opcao = sc.nextInt();

            if (opcao == 1) {
                if (listaProdutos.isEmpty()) {
                    System.out.println("Não há produtos no estoque!");
                } else {
                    System.out.println("Lista de Produtos:\n");
                    for (Produto produto : listaProdutos) {
                        System.out.println(produto);
                    }
                }
            } else if (opcao == 2) {
                Produto produto = new Produto();
                System.out.println("Digite o nome do novo produto:");
                sc.nextLine();
                produto.setNome(sc.nextLine());

                System.out.println("Digite o nome do vendedor:");
                String nomeVendedor = sc.nextLine();
                produto.setNomeVendedor(nomeVendedor);

                System.out.println("Digite o CPF do vendedor:");
                String cpfVendedor = sc.nextLine();
                produto.setCpfVendedor(cpfVendedor);

                System.out.println("Digite o preço do produto:");
                double preco = sc.nextDouble();
                produto.setPreco(preco);

                produto.setCodigo(codigo++);

                listaProdutos.add(produto);

                System.out.println("Deseja adicionar estoque para este produto? (S/N):");
                String resposta = sc.next();
                if (resposta.equalsIgnoreCase("S")) {
                    System.out.println("Digite a quantidade a ser adicionada ao estoque:");
                    int quantidade = sc.nextInt();
                    estoque.adicionarEstoque(produto, quantidade);

                    double valorAdicionado = quantidade * produto.getPreco();
                    System.out.println("Valor em reais adicionado no estoque: R$ " + String.format("%.2f", valorAdicionado));
                }

                System.out.println(produto.getNome() + " cadastrado com sucesso! \n" +
                        "Código: " + produto.getCodigo() +
                        " | Preço: R$ " + String.format("%.2f", produto.getPreco()) +
                        " | Estoque: " + produto.getQuantidadeEstoque() +
                        " | Nome Vendedor: " + produto.getNomeVendedor() +
                        " | CPF Vendedor: " + produto.getCpfVendedor());
                
            } else if (opcao == 3) {
                System.out.println("Digite o código do produto para adicionar estoque:");
                int codigoProduto = sc.nextInt();

                Produto produto = encontrarProdutoPorCodigo(listaProdutos, codigoProduto);

                if (produto != null) {
                    System.out.println("Digite a quantidade a ser adicionada ao estoque:");
                    int quantidade = sc.nextInt();
                    estoque.adicionarEstoque(produto, quantidade);

                    double valorAdicionado = quantidade * produto.getPreco();
                    System.out.println("Valor em reais adicionado no estoque: R$ " + String.format("%.2f", valorAdicionado));

                    System.out.println("Estoque cadastrado com sucesso!");
                } else {
                    System.out.println("Nenhum produto cadastrado no sistema com o código informado.");
                }
                
            } else if (opcao == 4) {
                System.out.println("Digite o código do produto para remover:");
                int codigoProduto = sc.nextInt();

                Produto produto = encontrarProdutoPorCodigo(listaProdutos, codigoProduto);

                if (produto != null) {
                    System.out.println("Tem certeza que deseja remover o produto " + produto.getNome() + "? (S/N):");
                    String confirmacao = sc.next();

                    if (confirmacao.equalsIgnoreCase("S")) {
                        listaProdutos.remove(produto);
                        estoque.removerEstoque(produto);
                        System.out.println("Produto removido com sucesso.");
                    } else {
                        System.out.println("Remoção cancelada.");
                    }
                } else {
                    System.out.println("Nenhum produto cadastrado no sistema com o código informado.");
                }
                
            } else if (opcao == 5) {
                System.out.println("Digite o código do produto para vender:");
                int codigoProduto = sc.nextInt();

                Produto produto = encontrarProdutoPorCodigo(listaProdutos, codigoProduto);

                if (produto != null) {
                    System.out.println("Digite a quantidade a ser vendida:");
                    int quantidade = sc.nextInt();

                    if (estoque.venderProduto(produto, quantidade)) {
                        double valorDecrescido = quantidade * produto.getPreco();
                        System.out.println("Valor em reais decrescido do estoque: R$ " + String.format("%.2f", valorDecrescido));
                        System.out.println("Venda realizada com sucesso.");
                    } else {
                        System.out.println("A quantidade desejada é insuficiente no estoque.");
                    }
                } else {
                    System.out.println("Nenhum produto cadastrado no sistema com o código informado.");
                }
                
            } else if (opcao == 6) {
                System.out.println("Digite o CPF do vendedor para listar os produtos cadastrados:");
                String cpfVendedor = sc.next();

                System.out.println("Produtos cadastrados pelo vendedor com CPF " + cpfVendedor + ":");

                for (Produto produto : listaProdutos) {
                    if (produto.getCpfVendedor().equals(cpfVendedor)) {
                        System.out.println(produto);
                    }
                }
                
            } else if (opcao == 7) {
                System.out.println("Digite o CPF do vendedor para contar os produtos cadastrados:");
                String cpfVendedor = sc.next();

                int totalProdutos = 0;
                for (Produto produto : listaProdutos) {
                    if (produto.getCpfVendedor().equals(cpfVendedor)) {
                        totalProdutos++;
                    }
                }

                System.out.println("Total de produtos cadastrados pelo vendedor com CPF " + cpfVendedor + ": " + totalProdutos);
                
            } else if (opcao == 8) {
                System.out.println("Digite o CPF do vendedor para somar os valores dos produtos cadastrados:");
                String cpfVendedor = sc.next();

                double totalValorProdutos = 0;
                for (Produto produto : listaProdutos) {
                    if (produto.getCpfVendedor().equals(cpfVendedor)) {
                        totalValorProdutos += (produto.getPreco() * produto.getQuantidadeEstoque());
                    }
                }

                System.out.println("Total de valor dos produtos cadastrados pelo vendedor com CPF " + cpfVendedor + ": R$ " + String.format("%.2f", totalValorProdutos));
            } else if (opcao == 9) {
                System.out.println("Finalizando o programa...");
                break;
            }
            sc.close();
        } while (opcao != 9);

    }
    

    private static Produto encontrarProdutoPorCodigo(List<Produto> listaProdutos, int codigo) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
            
        }
        return null;
        
    }
    
}