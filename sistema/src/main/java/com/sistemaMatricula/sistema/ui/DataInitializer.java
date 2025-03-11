package com.sistemaMatricula.sistema.ui;

import com.sistemaMatricula.sistema.enums.EStatusDisciplina;
import com.sistemaMatricula.sistema.enums.ETipoDisciplina;
import com.sistemaMatricula.sistema.model.*;
import com.sistemaMatricula.sistema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe responsável por inicializar dados para teste do sistema
 */
@Component
public class DataInitializer {

    @Autowired
    private AlunoRepository alunoRepository;
    
    @Autowired
    private ProfessorRepository professorRepository;
    
    @Autowired
    private SecretariaRepository secretariaRepository;
    
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    
    @Autowired
    private PeriodoMatriculaRepository periodoMatriculaRepository;
    
    /**
     * Inicializa dados para teste do sistema
     */
    @Transactional
    public void init() {
        // Verifica se já existem dados
        if (administradorRepository.count() > 0) {
            return;
        }
        
        System.out.println("Inicializando dados para teste...");
        
        // Cria usuários
        criarUsuarios();
        
        // Cria cursos e disciplinas
        criarCursosEDisciplinas();
        
        // Cria período de matrícula
        criarPeriodoMatricula();
        
        System.out.println("Dados inicializados com sucesso!");
    }
    
    /**
     * Cria usuários para teste
     */
    private void criarUsuarios() {
        // Cria administrador
        Administrador admin = new Administrador();
        admin.setNome("Administrador");
        admin.setLogin("admin");
        admin.setSenha("admin");
        administradorRepository.save(admin);
        
        // Cria secretaria
        Secretaria secretaria = new Secretaria();
        secretaria.setNome("Secretaria");
        secretaria.setLogin("secretaria");
        secretaria.setSenha("secretaria");
        secretariaRepository.save(secretaria);
        
        // Cria professores
        Professor prof1 = new Professor();
        prof1.setNome("Professor 1");
        prof1.setLogin("prof1");
        prof1.setSenha("prof1");
        professorRepository.save(prof1);
        
        Professor prof2 = new Professor();
        prof2.setNome("Professor 2");
        prof2.setLogin("prof2");
        prof2.setSenha("prof2");
        professorRepository.save(prof2);
        
        // Cria alunos
        Aluno aluno1 = new Aluno();
        aluno1.setNome("Aluno 1");
        aluno1.setLogin("aluno1");
        aluno1.setSenha("aluno1");
        alunoRepository.save(aluno1);
        
        Aluno aluno2 = new Aluno();
        aluno2.setNome("Aluno 2");
        aluno2.setLogin("aluno2");
        aluno2.setSenha("aluno2");
        alunoRepository.save(aluno2);
        
        Aluno aluno3 = new Aluno();
        aluno3.setNome("Aluno 3");
        aluno3.setLogin("aluno3");
        aluno3.setSenha("aluno3");
        alunoRepository.save(aluno3);
    }
    
    /**
     * Cria cursos e disciplinas para teste
     */
    private void criarCursosEDisciplinas() {
        // Busca professores
        List<Professor> professores = professorRepository.findAll();
        
        // Cria cursos
        Curso engenhariaComputacao = new Curso();
        engenhariaComputacao.setNome("Engenharia de Computação");
        engenhariaComputacao.setTotalCreditos(240);
        cursoRepository.save(engenhariaComputacao);
        
        Curso cienciaComputacao = new Curso();
        cienciaComputacao.setNome("Ciência da Computação");
        cienciaComputacao.setTotalCreditos(220);
        cursoRepository.save(cienciaComputacao);
        
        // Cria disciplinas para Engenharia de Computação
        Disciplina prog1 = new Disciplina();
        prog1.setNome("Programação 1");
        prog1.setTipo(ETipoDisciplina.OBRIGATORIA);
        prog1.setStatus(EStatusDisciplina.ATIVA);
        prog1.setCurso(engenhariaComputacao);
        prog1.setProfessorResponsavel(professores.get(0));
        disciplinaRepository.save(prog1);
        
        Disciplina prog2 = new Disciplina();
        prog2.setNome("Programação 2");
        prog2.setTipo(ETipoDisciplina.OBRIGATORIA);
        prog2.setStatus(EStatusDisciplina.ATIVA);
        prog2.setCurso(engenhariaComputacao);
        prog2.setProfessorResponsavel(professores.get(0));
        disciplinaRepository.save(prog2);
        
        Disciplina circuitos = new Disciplina();
        circuitos.setNome("Circuitos Digitais");
        circuitos.setTipo(ETipoDisciplina.OBRIGATORIA);
        circuitos.setStatus(EStatusDisciplina.ATIVA);
        circuitos.setCurso(engenhariaComputacao);
        circuitos.setProfessorResponsavel(professores.get(1));
        disciplinaRepository.save(circuitos);
        
        Disciplina robotica = new Disciplina();
        robotica.setNome("Robótica");
        robotica.setTipo(ETipoDisciplina.OPTATIVA);
        robotica.setStatus(EStatusDisciplina.ATIVA);
        robotica.setCurso(engenhariaComputacao);
        robotica.setProfessorResponsavel(professores.get(1));
        disciplinaRepository.save(robotica);
        
        // Cria disciplinas para Ciência da Computação
        Disciplina estruturaDados = new Disciplina();
        estruturaDados.setNome("Estrutura de Dados");
        estruturaDados.setTipo(ETipoDisciplina.OBRIGATORIA);
        estruturaDados.setStatus(EStatusDisciplina.ATIVA);
        estruturaDados.setCurso(cienciaComputacao);
        estruturaDados.setProfessorResponsavel(professores.get(0));
        disciplinaRepository.save(estruturaDados);
        
        Disciplina ia = new Disciplina();
        ia.setNome("Inteligência Artificial");
        ia.setTipo(ETipoDisciplina.OPTATIVA);
        ia.setStatus(EStatusDisciplina.ATIVA);
        ia.setCurso(cienciaComputacao);
        ia.setProfessorResponsavel(professores.get(1));
        disciplinaRepository.save(ia);
    }
    
    /**
     * Cria período de matrícula para teste
     */
    private void criarPeriodoMatricula() {
        // Cria um período de matrícula atual (começa 1 semana atrás e termina 1 semana à frente)
        PeriodoMatricula periodo = new PeriodoMatricula();
        periodo.setDataInicio(LocalDate.now().minusWeeks(1));
        periodo.setDataFim(LocalDate.now().plusWeeks(1));
        periodoMatriculaRepository.save(periodo);
    }
} 