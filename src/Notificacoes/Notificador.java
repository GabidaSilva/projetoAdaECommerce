package Notificacoes;

import Clientes.Cliente;

public interface Notificador {
    void notificar(String mensagem, Cliente cliente) throws IllegalArgumentException;
    default boolean isDisponivel() { return true; }
    String getTipoNotificador();
}
