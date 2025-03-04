package Clientes;

import Produtos.Produto;

public class PessoaJuridica extends Cliente {
    public PessoaJuridica(String id, String nome, String cnpj, String endereco, String telefone, String email) {
        super(id, nome, cnpj, endereco, telefone, email);
    }

    @Override
    public void exibirDetalhesAquisicao(Produto produto) {
        System.out.println("Pessoa Jurídica " + this.getNome() + " (CNPJ: " + this.getDocumento() + ") adquiriu o produto " + produto.getModelo());
    }
}

