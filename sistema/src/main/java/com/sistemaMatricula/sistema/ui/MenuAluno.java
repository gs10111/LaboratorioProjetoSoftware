package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.Aluno;
import com.sistemaMatricula.sistema.model.Disciplina;
import com.sistemaMatricula.sistema.service.AlunoService;
import com.sistemaMatricula.sistema.service.DisciplinaService;
import com.sistemaMatricula.sistema.service.PeriodoMatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu para o perfil de Aluno
 */
@Component
public class MenuAluno implements Menu {

    private static final Scanner scanner = new Scanner(System.in);
    
    @Autowired
    private AlunoService alunoService;
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @Autowired
    private PeriodoMatriculaService periodoMatriculaService;
    
    @Override
    public void exibirMenu(Long usuarioId) {
        Optional<Aluno> alunoOpt = alunoService.buscarPorId(usuarioId);
        
        if (alunoOpt.isEmpty()) {
            System.out.println("Aluno não encontrado.");
            return;
        }
        
        Aluno aluno = alunoOpt.get();
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=== MENU ALUNO ===");
            System.out.println("1. Matricular em disciplina obrigatória");
            System.out.println("2. Matricular em disciplina optativa");
            System.out.println("3. Cancelar matrícula em disciplina");
            System.out.println("4. Listar disciplinas matriculadas");
            System.out.println("5. Listar disciplinas disponíveis");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    matricularDisciplina(aluno.getId(), ETipoDisciplina.OBRIGATORIA);
                    break;
                case "2":
                    matricularDisciplina(aluno.getId(), ETipoDisciplina.OPTATIVA);
                    break;
                case "3":
                    cancelarMatricula(aluno.getId());
                    break;
                case "4":
                    listarDisciplinasMatriculadas(aluno.getId());
                    break;
                case "5":
                    listarDisciplinasDisponiveis();
                    break;
                case "0":
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
    
    /**
     * Método para matricular o aluno em uma disciplina
     * @param alunoId O ID do aluno
     * @param tipo O tipo da disciplina (obrigatória ou optativa)
     */
    private void matricularDisciplina(Long alunoId, ETipoDisciplina tipo) {
        // Verifica se existe período de matrícula aberto
        if (!periodoMatriculaService.existePeriodoAberto()) {
            System.out.println("Não há período de matrícula aberto no momento.");
            return;
        }
        
        // Lista disciplinas disponíveis
        List<Disciplina> disciplinas = disciplinaService.listarAtivas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis para matrícula.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS DISPONÍVEIS ===");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s (%s)%n", i + 1, d.getNome(), d.getTipo());
        }
        
        int indice = lerInteiro("\nDigite o número da disciplina que deseja se matricular: ") - 1;
        
        if (indice < 0 || indice >= disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinas.get(indice);
        
        // Verifica se a disciplina selecionada é do tipo especificado
        if (!disciplina.getTipo().equals(tipo)) {
            System.out.println("Você selecionou uma disciplina do tipo " + disciplina.getTipo() + 
                    ", mas está tentando matricular em uma disciplina do tipo " + tipo + ".");
            return;
        }
        
        boolean sucesso = alunoService.matricular(alunoId, disciplina.getId(), tipo);
        
        if (sucesso) {
            System.out.println("Matrícula realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a matrícula. Verifique se você já atingiu o limite de disciplinas deste tipo ou se a disciplina está lotada.");
        }
    }
    
    /**
     * Método para cancelar a matrícula do aluno em uma disciplina
     * @param alunoId O ID do aluno
     */
    private void cancelarMatricula(Long alunoId) {
        // Verifica se existe período de matrícula aberto
        if (!periodoMatriculaService.existePeriodoAberto()) {
            System.out.println("Não há período de matrícula aberto no momento.");
            return;
        }
        
        // Lista disciplinas matriculadas
        List<Disciplina> disciplinas = alunoService.listarDisciplinasMatriculadas(alunoId);
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS MATRICULADAS ===");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s (%s)%n", i + 1, d.getNome(), d.getTipo());
        }
        
        int indice = lerInteiro("\nDigite o número da disciplina que deseja cancelar: ") - 1;
        
        if (indice < 0 || indice >= disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinas.get(indice);
        
        boolean sucesso = alunoService.cancelarMatricula(alunoId, disciplina.getId());
        
        if (sucesso) {
            System.out.println("Matrícula cancelada com sucesso!");
        } else {
            System.out.println("Não foi possível cancelar a matrícula.");
        }
    }
    
    /**
     * Método para listar as disciplinas em que o aluno está matriculado
     * @param alunoId O ID do aluno
     */
    private void listarDisciplinasMatriculadas(Long alunoId) {
        List<Disciplina> disciplinas = alunoService.listarDisciplinasMatriculadas(alunoId);
        
        if (disciplinas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS MATRICULADAS ===");
        for (Disciplina d : disciplinas) {
            System.out.printf("- %s (%s)%n", d.getNome(), d.getTipo());
        }
    }
    
    /**
     * Método para listar as disciplinas disponíveis para matrícula
     */
    private void listarDisciplinasDisponiveis() {
        List<Disciplina> disciplinas = disciplinaService.listarAtivas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis para matrícula.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS DISPONÍVEIS ===");
        for (Disciplina d : disciplinas) {
            boolean lotada = disciplinaService.verificarDisciplinaLotada(d.getId());
            String status = lotada ? "(LOTADA)" : "";
            System.out.printf("- %s (%s) %s%n", d.getNome(), d.getTipo(), status);
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