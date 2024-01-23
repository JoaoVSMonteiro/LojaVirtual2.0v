package entities;

public class Estoque {
    public void adicionarEstoque(Produto produto, int quantidade) {
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
    }

    public void removerEstoque(Produto produto) {
        produto.setQuantidadeEstoque(0);
    }

    public boolean venderProduto(Produto produto, int quantidade) {
        if (produto.getQuantidadeEstoque() >= quantidade) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
            return true;
        } else {
            return false;
        }
    }
}