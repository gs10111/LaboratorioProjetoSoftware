package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.model.UsuarioBase;
import com.sistemaMatricula.sistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

/**
 * Interface de linha de comando para o Sistema de Matrículas
 */
@Component
public class SistemaMatriculaUI implements CommandLineRunner {

    private static final Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private MenuAluno menuAluno;
    
    @Autowired
    private MenuProfessor menuProfessor;
    
    @Autowired
    private MenuSecretaria menuSecretaria;
    
    @Autowired
    private MenuAdministrador menuAdministrador;
    
    @Autowired
    private DataInitializer dataInitializer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=============================================================");
        System.out.println("                SISTEMA DE MATRÍCULAS");
        System.out.println("=============================================================");
        
        // Inicializa dados para teste
        dataInitializer.init();
        
        boolean executando = true;
        
        while (executando) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Login");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    realizarLogin();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Sistema finalizado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    /**
     * Método para realizar o login no sistema
     */
    private void realizarLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        Optional<UsuarioBase> usuarioOpt = usuarioService.autenticar(login, senha);
        
        if (usuarioOpt.isPresent()) {
            UsuarioBase usuario = usuarioOpt.get();
            System.out.println("\nBem-vindo, " + usuario.getNome() + "!");
            
            // Direciona para o menu de acordo com o perfil do usuário
            switch (usuario.getTipo()) {
                case ALUNO:
                    menuAluno.exibirMenu(usuario.getId());
                    break;
                case PROFESSOR:
                    menuProfessor.exibirMenu(usuario.getId());
                    break;
                case SECRETARIA:
                    menuSecretaria.exibirMenu(usuario.getId());
                    break;
                case ADMINISTRADOR:
                    menuAdministrador.exibirMenu(usuario.getId());
                    break;
                default:
                    System.out.println("Perfil de usuário não reconhecido.");
            }
        } else {
            System.out.println("Login ou senha incorretos. Tente novamente.");
        }
    }
} 