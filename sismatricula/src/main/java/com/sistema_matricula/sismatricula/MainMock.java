package com.sistema_matricula.sismatricula;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sistema_matricula.sismatricula.Models.Aluno;
import com.sistema_matricula.sismatricula.Models.Curriculo;
import com.sistema_matricula.sismatricula.Models.Disciplina;
import com.sistema_matricula.sismatricula.Models.EEstadoDisciplina;
import com.sistema_matricula.sismatricula.Models.EPerfilUsuario;
import com.sistema_matricula.sismatricula.Models.ETipoDisciplina;
import com.sistema_matricula.sismatricula.Models.Professor;

public class MainMock {

    // Listas para armazenar as entidades criadas
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Disciplina> disciplinas = new ArrayList<>();
    private static List<Curriculo> curriculos = new ArrayList<>();

    public static void main(String[] args) {
        // Inicializando dados
        inicializarDados();

        // Menu de interação
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Listar alunos");
            System.out.println("2. Listar professores");
            System.out.println("3. Listar disciplinas");
            System.out.println("4. Listar currículos");
            System.out.println("5. Exibir detalhes da disciplina");
            System.out.println("6. Adicionar aluno à disciplina");
            System.out.println("7. Remover aluno da disciplina");
            System.out.println("8. Cancelar disciplina");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar buffer

            switch (opcao) {
                case 1:
                    listarAlunos();
                    break;
                case 2:
                    listarProfessores();
                    break;
                case 3:
                    listarDisciplinas();
                    break;
                case 4:
                    listarCurriculos();
                    break;
                case 5:
                    exibirDetalhesDisciplina(scanner);
                    break;
                case 6:
                    adicionarAlunoADisciplina(scanner);
                    break;
                case 7:
                    removerAlunoDaDisciplina(scanner);
                    break;
                case 8:
                    cancelarDisciplina(scanner);
                    break;
                case 9:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void inicializarDados() {
        // Criando professores
        Professor professor1 = new Professor(1L, "Prof. João", "joao@university.com", "senha123", "joao123", 
        EPerfilUsuario.PROFESSOR, "123456", new ArrayList<>()); // SIAPE preenchido e lista de disciplinas
        Professor professor2 = new Professor(2L, "Prof. Maria", "maria@university.com", "senha456", "maria123", 
        EPerfilUsuario.PROFESSOR, "654321", new ArrayList<>()); // SIAPE preenchido e lista de disciplinas

        // Adicionando professores à lista
        professores.add(professor1);
        professores.add(professor2);

        // Criando disciplinas
        Disciplina disciplina1 = new Disciplina(1L, "Matemática", ETipoDisciplina.OBRIGATORIA, EEstadoDisciplina.ATIVA, professor1);
        Disciplina disciplina2 = new Disciplina(2L, "Física", ETipoDisciplina.OBRIGATORIA, EEstadoDisciplina.ATIVA, professor2);

        // Adicionando disciplinas à lista
        disciplinas.add(disciplina1);
        disciplinas.add(disciplina2);

        // Criando alunos
        Aluno aluno1 = new Aluno(1L, "Pedro Silva", "pedro@aluno.com", "senha123", "pedro123", 
        EPerfilUsuario.ALUNO, new ArrayList<>()); // Lista de matrículas vazia
        Aluno aluno2 = new Aluno(2L, "Ana Souza", "ana@aluno.com", "senha456", "ana123", 
        EPerfilUsuario.ALUNO, new ArrayList<>()); // Lista de matrículas vazia

        // Adicionando alunos à lista
        alunos.add(aluno1);
        alunos.add(aluno2);
    }

    private static void listarAlunos() {
        System.out.println("\n--- Lista de Alunos ---");
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getId() + ", Nome: " + aluno.getDescricao());
        }
    }

    private static void listarProfessores() {
        System.out.println("\n--- Lista de Professores ---");
        for (Professor professor : professores) {
            System.out.println("ID: " + professor.getId() + ", Nome: " + professor.getDescricao());
        }
    }

    private static void listarDisciplinas() {
        System.out.println("\n--- Lista de Disciplinas ---");
        for (Disciplina disciplina : disciplinas) {
            System.out.println("ID: " + disciplina.getId() + ", Nome: " + disciplina.getNome());
        }
    }

    private static void listarCurriculos() {
        System.out.println("\n--- Lista de Currículos ---");
        for (Curriculo curriculo : curriculos) {
            System.out.println("ID: " + curriculo.getId() + ", Nome: " + curriculo.getNome());
        }
    }

    private static void exibirDetalhesDisciplina(Scanner scanner) {
        System.out.print("\nInforme o ID da disciplina para exibir os detalhes: ");
        Long idDisciplina = scanner.nextLong();
        scanner.nextLine();  // Limpar buffer

        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getId().equals(idDisciplina)) {
                System.out.println("\n--- Detalhes da Disciplina ---");
                System.out.println("Nome: " + disciplina.getNome());
                System.out.println("Tipo: " + disciplina.getTipo());
                System.out.println("Status: " + disciplina.getStatus());
                System.out.println("Número de alunos matriculados: " + disciplina.getNumeroAlunosMatriculados());
                System.out.println("Professor responsável: " + disciplina.getProfessorResponsavel().getDescricao());
                return;
            }
        }
        System.out.println("Disciplina não encontrada.");
    }

    private static void adicionarAlunoADisciplina(Scanner scanner) {
        System.out.print("\nInforme o ID do aluno para adicionar à disciplina: ");
        Long idAluno = scanner.nextLong();
        System.out.print("Informe o ID da disciplina para adicionar o aluno: ");
        Long idDisciplina = scanner.nextLong();
        scanner.nextLine();  // Limpar buffer

        Aluno aluno = null;
        Disciplina disciplina = null;

        for (Aluno a : alunos) {
            if (a.getId().equals(idAluno)) {
                aluno = a;
                break;
            }
        }

        for (Disciplina d : disciplinas) {
            if (d.getId().equals(idDisciplina)) {
                disciplina = d;
                break;
            }
        }

        if (aluno != null && disciplina != null) {
            disciplina.adicionarAluno(aluno);
            System.out.println("Aluno " + aluno.getDescricao() + " adicionado à disciplina " + disciplina.getNome());
        } else {
            System.out.println("Aluno ou Disciplina não encontrados.");
        }
    }

    private static void removerAlunoDaDisciplina(Scanner scanner) {
        System.out.print("\nInforme o ID do aluno para remover da disciplina: ");
        Long idAluno = scanner.nextLong();
        System.out.print("Informe o ID da disciplina para remover o aluno: ");
        Long idDisciplina = scanner.nextLong();
        scanner.nextLine();  // Limpar buffer

        Aluno aluno = null;
        Disciplina disciplina = null;

        for (Aluno a : alunos) {
            if (a.getId().equals(idAluno)) {
                aluno = a;
                break;
            }
        }

        for (Disciplina d : disciplinas) {
            if (d.getId().equals(idDisciplina)) {
                disciplina = d;
                break;
            }
        }

        if (aluno != null && disciplina != null) {
            disciplina.removerAluno(aluno);
            System.out.println("Aluno " + aluno.getDescricao() + " removido da disciplina " + disciplina.getNome());
        } else {
            System.out.println("Aluno ou Disciplina não encontrados.");
        }
    }

    private static void cancelarDisciplina(Scanner scanner) {
        System.out.print("\nInforme o ID da disciplina para cancelar: ");
        Long idDisciplina = scanner.nextLong();
        scanner.nextLine();  // Limpar buffer

        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getId().equals(idDisciplina)) {
                disciplina.cancelarDisciplina();
                System.out.println("Disciplina " + disciplina.getNome() + " cancelada.");
                return;
            }
        }
        System.out.println("Disciplina não encontrada.");
    }
}
