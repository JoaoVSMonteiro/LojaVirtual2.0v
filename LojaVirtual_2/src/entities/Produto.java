package entities;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private int quantidadeEstoque;
    private String nomeVendedor;
    private String cpfVendedor;

    public Produto() {
    }

    public Produto(int codigo, String nome, double preco, int quantidadeEstoque, String nomeVendedor, String cpfVendedor) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.nomeVendedor = nomeVendedor;
        this.cpfVendedor = cpfVendedor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(String cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Código: " + codigo + " | Preço: R$ " + String.format("%.2f", preco) +
                " | Estoque: " + quantidadeEstoque +
                " | Nome Vendedor: " + nomeVendedor +
                " | CPF Vendedor: " + cpfVendedor;
    }
}