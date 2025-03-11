package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.model.*;
import com.sistemaMatricula.sistema.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu para o perfil de Administrador
 */
@Component
public class MenuAdministrador implements Menu {

    private static final Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private AdministradorService administradorService;
    
    @Override
    public void exibirMenu(Long usuarioId) {
        Optional<Administrador> administradorOpt = administradorService.buscarPorId(usuarioId);
        
        if (administradorOpt.isEmpty()) {
            System.out.println("Administrador não encontrado.");
            return;
        }
        
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Criar usuário");
            System.out.println("2. Remover usuário");
            System.out.println("3. Listar usuários");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    criarUsuario();
                    break;
                case "2":
                    removerUsuario();
                    break;
                case "3":
                    listarUsuarios();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    /**
     * Método para criar um usuário
     */
    private void criarUsuario() {
        System.out.println("\n=== CRIAR USUÁRIO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Login: ");
        String login = scanner.nextLine();
        
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.println("\nTipo de usuário:");
        System.out.println("1. Aluno");
        System.out.println("2. Professor");
        System.out.println("3. Secretaria");
        System.out.println("4. Administrador");
        
        System.out.print("Opção: ");
        int opcaoTipo = Integer.parseInt(scanner.nextLine());
        
        UsuarioBase usuario;
        
        switch (opcaoTipo) {
            case 1:
                usuario = new Aluno();
                break;
            case 2:
                usuario = new Professor();
                break;
            case 3:
                usuario = new Secretaria();
                break;
            case 4:
                usuario = new Administrador();
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        
        Optional<UsuarioBase> usuarioCriado = administradorService.criarUsuario(usuario);
        
        if (usuarioCriado.isPresent()) {
            System.out.println("Usuário criado com sucesso!");
        } else {
            System.out.println("Não foi possível criar o usuário. Verifique se o login já existe.");
        }
    }
    
    /**
     * Método para remover um usuário
     */
    private void removerUsuario() {
        System.out.println("\n=== REMOVER USUÁRIO ===");
        
        List<UsuarioBase> usuarios = administradorService.listarUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
            return;
        }
        
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
        for (int i = 0; i < usuarios.size(); i++) {
            UsuarioBase u = usuarios.get(i);
            System.out.printf("%d. %s (Login: %s, Perfil: %s)%n", i + 1, u.getNome(), u.getLogin(), u.getTipo());
        }
        
        int indice = lerInteiro("\nDigite o número do usuário que deseja remover: ") - 1;
        
        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        UsuarioBase usuario = usuarios.get(indice);
        
        boolean sucesso = administradorService.removerUsuario(usuario.getId());
        
        if (sucesso) {
            System.out.println("Usuário removido com sucesso!");
        } else {
            System.out.println("Não foi possível remover o usuário.");
        }
    }
    
    /**
     * Método para listar os usuários cadastrados
     */
    private void listarUsuarios() {
        List<UsuarioBase> usuarios = administradorService.listarUsuarios();
        
        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados.");
            return;
        }
        
        System.out.println("\n=== USUÁRIOS CADASTRADOS ===");
        for (UsuarioBase u : usuarios) {
            System.out.printf("- %s (Login: %s, Perfil: %s)%n", u.getNome(), u.getLogin(), u.getTipo());
        }
    }

    /**
     * Método utilitário para ler um número inteiro com tratamento de exceção
     * @param mensagem A mensagem a ser exibida para o usuário
     * @return O número inteiro lido
     */
    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite apenas números.");
            }
        }
    }
} 