package Notificacoes;

import Clientes.Cliente;

public class EmailNotificador implements Notificador {
    @Override
    public void notificar(String mensagem, Cliente cliente) throws IllegalArgumentException {
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O cliente não possui um endereço de e-mail válido.");
        }
        System.out.println("Enviando e-mail para " + cliente.getEmail() + ": " + mensagem);
    }

    @Override
    public String getTipoNotificador() {
        return "E-mail";
    }
}
