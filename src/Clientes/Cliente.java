package Clientes;

import Produtos.Produto;

public abstract class Cliente {
    protected String id;
    protected String nome;
    protected String documento;
    protected String endereco;
    protected String telefone;
    protected String email;
    protected int diasLocacao;

    public Cliente(String id, String nome, String documento, String endereco, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e setters
    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getDocumento() { return documento; }
    public String getEndereco() { return endereco; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public int getDiasLocacao() { return diasLocacao; }
    public void setDiasLocacao(int diasLocacao) { this.diasLocacao = diasLocacao; }

    public abstract void exibirDetalhesAquisicao(Produto produto);
}
