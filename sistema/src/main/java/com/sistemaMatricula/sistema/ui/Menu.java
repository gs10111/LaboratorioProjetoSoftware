package com.sistemaMatricula.sistema.ui;

/**
 * Interface base para todos os menus do sistema
 */
public interface Menu {
    /**
     * Exibe o menu para o usuário
     * @param usuarioId O ID do usuário que está utilizando o menu
     */
    void exibirMenu(Long usuarioId);
} 