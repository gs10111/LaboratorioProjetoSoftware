package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.model.Aluno;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.model.Professor;
import com.sistemaMatricula.sistema.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu para o perfil de Professor
 */
@Component
public class MenuProfessor implements Menu {

    private static final Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private ProfessorService professorService;
    
    @Override
    public void exibirMenu(Long usuarioId) {
        Optional<Professor> professorOpt = professorService.buscarPorId(usuarioId);
        
        if (professorOpt.isEmpty()) {
            System.out.println("Professor não encontrado.");
            return;
        }
        
        Professor professor = professorOpt.get();
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=== MENU PROFESSOR ===");
            System.out.println("1. Listar disciplinas ministradas");
            System.out.println("2. Listar alunos por disciplina");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    listarDisciplinasMinistrando(professor.getId());
                    break;
                case "2":
                    listarAlunosPorDisciplina(professor.getId());
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
     * Método para listar as disciplinas ministradas pelo professor
     * @param professorId O ID do professor
     */
    private void listarDisciplinasMinistrando(Long professorId) {
        List<Disciplina> disciplinas = professorService.listarDisciplinasMinistrando(professorId);
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está ministrando nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS MINISTRADAS ===");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s (%s) - Status: %s%n", i + 1, d.getNome(), d.getTipo(), d.getStatus());
        }
    }
    
    /**
     * Método para listar os alunos matriculados em uma disciplina ministrada pelo professor
     * @param professorId O ID do professor
     */
    private void listarAlunosPorDisciplina(Long professorId) {
        List<Disciplina> disciplinas = professorService.listarDisciplinasMinistrando(professorId);
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está ministrando nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== SELECIONE UMA DISCIPLINA ===");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s%n", i + 1, d.getNome());
        }
        
        int indice = lerInteiro("Digite o número da disciplina: ");
        
        if (indice < 1 || indice > disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinas.get(indice - 1);
        
        List<Aluno> alunos = professorService.listarAlunosPorDisciplina(professorId, disciplina.getId());
        
        if (alunos.isEmpty()) {
            System.out.println("Não há alunos matriculados nesta disciplina.");
            return;
        }
        
        System.out.printf("\n=== ALUNOS MATRICULADOS EM %s ===\n", disciplina.getNome());
        for (Aluno a : alunos) {
            System.out.printf("- %s (Login: %s)%n", a.getNome(), a.getLogin());
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