package Clientes;

import Produtos.Produto;

public class PessoaFisica extends Cliente {
    public PessoaFisica(String id, String nome, String cpf, String endereco, String telefone, String email) {
        super(id, nome, cpf, endereco, telefone, email);
    }

    @Override
    public void exibirDetalhesAquisicao(Produto produto) {
        System.out.println("Pessoa Física " + this.getNome() + " (CPF: " + this.getDocumento() + ") adquiriu o produto " + produto.getModelo());
    }
}
