package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.*;
import com.sistemaMatricula.sistema.service.DisciplinaService;
import com.sistemaMatricula.sistema.service.PeriodoMatriculaService;
import com.sistemaMatricula.sistema.service.ProfessorService;
import com.sistemaMatricula.sistema.service.SecretariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu para o perfil de Secretaria
 */
@Component
public class MenuSecretaria implements Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Autowired
    private SecretariaService secretariaService;
    
    @Autowired
    private ProfessorService professorService;
    
    @Autowired
    private DisciplinaService disciplinaService;
    
    @Autowired
    private PeriodoMatriculaService periodoMatriculaService;
    
    @Override
    public void exibirMenu(Long usuarioId) {
        Optional<Secretaria> secretariaOpt = secretariaService.buscarPorId(usuarioId);
        
        if (secretariaOpt.isEmpty()) {
            System.out.println("Secretaria não encontrada.");
            return;
        }
        
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=== MENU SECRETARIA ===");
            System.out.println("1. Cadastrar curso");
            System.out.println("2. Cadastrar disciplina");
            System.out.println("3. Gerar currículo");
            System.out.println("4. Cancelar disciplina");
            System.out.println("5. Criar período de matrícula");
            System.out.println("6. Listar disciplinas");
            System.out.println("7. Listar cursos");
            System.out.println("8. Atualizar status das disciplinas");
            System.out.println("0. Sair");
            
            System.out.print("Opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    cadastrarCurso();
                    break;
                case "2":
                    cadastrarDisciplina();
                    break;
                case "3":
                    gerarCurriculo();
                    break;
                case "4":
                    cancelarDisciplina();
                    break;
                case "5":
                    criarPeriodoMatricula();
                    break;
                case "6":
                    listarDisciplinas();
                    break;
                case "7":
                    listarCursos();
                    break;
                case "8":
                    atualizarStatusDisciplinas();
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
     * Método para cadastrar um curso
     */
    private void cadastrarCurso() {
        System.out.println("\n=== CADASTRAR CURSO ===");
        
        System.out.print("Nome do curso: ");
        String nome = scanner.nextLine();
        
        int creditos = lerInteiro("Total de créditos: ");
        
        Curso curso = new Curso();
        curso.setNome(nome);
        curso.setTotalCreditos(creditos);
        
        Optional<Curso> cursoOpt = secretariaService.cadastrarCurso(curso);
        
        if (cursoOpt.isPresent()) {
            System.out.println("Curso cadastrado com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar o curso. Verifique se já existe um curso com este nome ou se os dados são válidos.");
        }
    }
    
    /**
     * Método para cadastrar uma disciplina
     */
    private void cadastrarDisciplina() {
        System.out.println("\n=== CADASTRAR DISCIPLINA ===");
        
        // Lista os cursos disponíveis
        List<Curso> cursos = secretariaService.listarCursos();
        
        if (cursos.isEmpty()) {
            System.out.println("Não há cursos cadastrados. Cadastre um curso primeiro.");
            return;
        }
        
        System.out.println("\n=== CURSOS DISPONÍVEIS ===");
        for (int i = 0; i < cursos.size(); i++) {
            Curso c = cursos.get(i);
            System.out.printf("%d. %s%n", i + 1, c.getNome());
        }
        
        int indiceCurso = lerInteiro("\nDigite o número do curso: ") - 1;
        
        if (indiceCurso < 0 || indiceCurso >= cursos.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Curso curso = cursos.get(indiceCurso);
        
        // Lista os professores disponíveis
        List<Professor> professores = professorService.listarTodos();
        
        if (professores.isEmpty()) {
            System.out.println("Não há professores cadastrados. Cadastre um professor primeiro.");
            return;
        }
        
        System.out.println("\n=== PROFESSORES DISPONÍVEIS ===");
        for (int i = 0; i < professores.size(); i++) {
            Professor p = professores.get(i);
            System.out.printf("%d. %s%n", i + 1, p.getNome());
        }
        
        int indiceProfessor = lerInteiro("\nDigite o número do professor: ") - 1;
        
        if (indiceProfessor < 0 || indiceProfessor >= professores.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Professor professor = professores.get(indiceProfessor);
        
        System.out.print("\nNome da disciplina: ");
        String nome = scanner.nextLine();
        
        System.out.println("\nTipo da disciplina:");
        System.out.println("1. Obrigatória");
        System.out.println("2. Optativa");
        int opcaoTipo = lerInteiro("Opção: ");
        
        ETipoDisciplina tipo;
        if (opcaoTipo == 1) {
            tipo = ETipoDisciplina.OBRIGATORIA;
        } else if (opcaoTipo == 2) {
            tipo = ETipoDisciplina.OPTATIVA;
        } else {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setTipo(tipo);
        
        Optional<Disciplina> disciplinaOpt = secretariaService.cadastrarDisciplina(disciplina, curso.getId(), professor.getId());
        
        if (disciplinaOpt.isPresent()) {
            System.out.println("Disciplina cadastrada com sucesso!");
        } else {
            System.out.println("Não foi possível cadastrar a disciplina.");
        }
    }
    
    /**
     * Método para gerar um currículo
     */
    private void gerarCurriculo() {
        System.out.println("\n=== GERAR CURRÍCULO ===");
        
        System.out.print("Nome do currículo: ");
        String nome = scanner.nextLine();
        
        Curriculo curriculo = new Curriculo();
        curriculo.setNome(nome);
        
        Optional<Curriculo> curriculoOpt = secretariaService.gerarCurriculo(curriculo);
        
        if (curriculoOpt.isPresent()) {
            System.out.println("Currículo gerado com sucesso!");
            
            // Adicionar disciplinas ao currículo
            boolean adicionandoDisciplinas = true;
            
            while (adicionandoDisciplinas) {
                // Lista as disciplinas disponíveis
                List<Disciplina> disciplinas = secretariaService.listarDisciplinas();
                
                if (disciplinas.isEmpty()) {
                    System.out.println("Não há disciplinas disponíveis.");
                    adicionandoDisciplinas = false;
                    continue;
                }
                
                System.out.println("\n=== DISCIPLINAS DISPONÍVEIS ===");
                for (int i = 0; i < disciplinas.size(); i++) {
                    Disciplina d = disciplinas.get(i);
                    System.out.printf("%d. %s (%s)%n", i + 1, d.getNome(), d.getTipo());
                }
                
                int indiceDisciplina = lerInteiro("\nDigite o número da disciplina para adicionar ao currículo (0 para finalizar): ") - 1;
                
                if (indiceDisciplina == -1) {
                    adicionandoDisciplinas = false;
                    continue;
                }
                
                if (indiceDisciplina < 0 || indiceDisciplina >= disciplinas.size()) {
                    System.out.println("Opção inválida.");
                    continue;
                }
                
                Disciplina disciplina = disciplinas.get(indiceDisciplina);
                
                boolean sucesso = secretariaService.adicionarDisciplinaAoCurriculo(curriculoOpt.get().getId(), disciplina.getId());
                
                if (sucesso) {
                    System.out.println("Disciplina adicionada ao currículo com sucesso!");
                } else {
                    System.out.println("Não foi possível adicionar a disciplina ao currículo.");
                }
            }
        } else {
            System.out.println("Não foi possível gerar o currículo. Verifique se já existe um currículo com este nome.");
        }
    }
    
    /**
     * Método para cancelar uma disciplina
     */
    private void cancelarDisciplina() {
        System.out.println("\n=== CANCELAR DISCIPLINA ===");
        
        // Lista as disciplinas disponíveis
        List<Disciplina> disciplinas = secretariaService.listarDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas disponíveis.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS DISPONÍVEIS ===");
        for (int i = 0; i < disciplinas.size(); i++) {
            Disciplina d = disciplinas.get(i);
            System.out.printf("%d. %s (%s) - Status: %s%n", i + 1, d.getNome(), d.getTipo(), d.getStatus());
        }
        
        int indice = lerInteiro("\nDigite o número da disciplina que deseja cancelar: ") - 1;
        
        if (indice < 0 || indice >= disciplinas.size()) {
            System.out.println("Opção inválida.");
            return;
        }
        
        Disciplina disciplina = disciplinas.get(indice);
        
        boolean sucesso = secretariaService.cancelarDisciplina(disciplina.getId());
        
        if (sucesso) {
            System.out.println("Disciplina cancelada com sucesso!");
        } else {
            System.out.println("Não foi possível cancelar a disciplina.");
        }
    }
    
    /**
     * Método para criar um período de matrícula
     */
    private void criarPeriodoMatricula() {
        System.out.println("\n=== CRIAR PERÍODO DE MATRÍCULA ===");
        
        System.out.print("Data de início (dd/MM/yyyy): ");
        String dataInicioStr = scanner.nextLine();
        
        System.out.print("Data de fim (dd/MM/yyyy): ");
        String dataFimStr = scanner.nextLine();
        
        try {
            LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
            LocalDate dataFim = LocalDate.parse(dataFimStr, formatter);
            
            PeriodoMatricula periodoMatricula = new PeriodoMatricula();
            periodoMatricula.setDataInicio(dataInicio);
            periodoMatricula.setDataFim(dataFim);
            
            Optional<PeriodoMatricula> periodoOpt = periodoMatriculaService.salvar(periodoMatricula);
            
            if (periodoOpt.isPresent()) {
                System.out.println("Período de matrícula criado com sucesso!");
            } else {
                System.out.println("Não foi possível criar o período de matrícula. Verifique se as datas são válidas.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Utilize o formato dd/MM/yyyy.");
        }
    }
    
    /**
     * Método para listar as disciplinas cadastradas
     */
    private void listarDisciplinas() {
        List<Disciplina> disciplinas = secretariaService.listarDisciplinas();
        
        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }
        
        System.out.println("\n=== DISCIPLINAS CADASTRADAS ===");
        for (Disciplina d : disciplinas) {
            System.out.printf("- %s (%s) - Status: %s - Professor: %s - Curso: %s%n", 
                    d.getNome(), d.getTipo(), d.getStatus(), 
                    d.getProfessorResponsavel().getNome(), d.getCurso().getNome());
        }
    }
    
    /**
     * Método para listar os cursos cadastrados
     */
    private void listarCursos() {
        List<Curso> cursos = secretariaService.listarCursos();
        
        if (cursos.isEmpty()) {
            System.out.println("Não há cursos cadastrados.");
            return;
        }
        
        System.out.println("\n=== CURSOS CADASTRADOS ===");
        for (Curso c : cursos) {
            System.out.printf("- %s (Créditos: %d)%n", c.getNome(), c.getTotalCreditos());
            
            if (!c.getDisciplinas().isEmpty()) {
                System.out.println("  Disciplinas:");
                for (Disciplina d : c.getDisciplinas()) {
                    System.out.printf("  - %s (%s)%n", d.getNome(), d.getTipo());
                }
            } else {
                System.out.println("  Sem disciplinas cadastradas.");
            }
        }
    }
    
    /**
     * Método para atualizar o status das disciplinas com base no número de alunos matriculados
     */
    private void atualizarStatusDisciplinas() {
        int atualizadas = disciplinaService.atualizarStatusDisciplinas();
        
        System.out.printf("\n%d disciplinas tiveram seu status atualizado.\n", atualizadas);
        
        if (atualizadas > 0) {
            System.out.println("Disciplinas com menos de 3 alunos foram CANCELADAS.");
            System.out.println("Disciplinas com 3 ou mais alunos foram ATIVADAS.");
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