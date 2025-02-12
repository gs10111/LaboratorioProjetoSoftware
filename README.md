# LaboratorioProjetoSoftware
Repositório para os projetos do laboratório de projeto de software 2025

# Link para diagrama de useCase interativo (Incompleto)
https://excalidraw.com/#room=a4f39f7e53e0b57c2954,kSKdkJ54TfgL7RJtP53nog

# Entidades e principais funcionalidades
Entidades Principais

1. Curso
- id: int
- nome: String
- numeroCreditos: int
- disciplinas: List<Disciplina>

2. Disciplina
- id: int
- nome: String
- codigo: String
- cargaHoraria: int
- professor: Professor
- alunosMatriculados: List<Aluno>
- maxAlunos: int (default = 60)
- minAlunos: int (default = 3)
- status: Enum (ATIVA, CANCELADA)
- tipo: Enum (OBRIGATORIA, OPTATIVA)

3. Aluno
- id: int
- nome: String
- matricula: String
- senha: String
- curso: Curso
- disciplinasMatriculadas: List<Disciplina>
- disciplinasOptativas: List<Disciplina>
- statusFinanceiro: boolean (true = pago, false = pendente)

4. Professor
- id: int
- nome: String
- departamento: String
- disciplinasMinistradas: List<Disciplina>
- senha: String

5. Secretaria
- id: int
- nome: String
- senha: String
- cursos: List<Curso>
- disciplinas: List<Disciplina>

6. Sistema de Matrículas
- alunos: List<Aluno>
- professores: List<Professor>
- disciplinas: List<Disciplina>
- cursos: List<Curso>
- secretarios: List<Secretaria>

7. Sistema de Cobranças
- id: int
- aluno: Aluno
- disciplinasCobradas: List<Disciplina>
- valorTotal: double
- statusPagamento: boolean

Principais Funcionalidades

1. Gerenciamento de Cursos
- Criar novo curso
- Atualizar curso
- Excluir curso
- Listar cursos

2. Gerenciamento de Disciplinas
- Criar nova disciplina
- Definir professor responsável
- Definir carga horária e código
- Listar disciplinas disponíveis
- Cancelar disciplinas com menos de 3 alunos
- Controlar limite de 60 alunos por disciplina

3. Gerenciamento de Matrículas
- Aluno faz login e acessa o sistema
- Aluno se inscreve em disciplinas obrigatórias e optativas
- Aluno cancela matrícula em disciplina
- Aluno consulta suas disciplinas matriculadas
- Sistema verifica número mínimo/máximo de alunos
- Notificação do Sistema de Cobranças ao confirmar matrícula

4. Gerenciamento de Professores
- Cadastro e atualização de professores
- Listagem de professores
- Consulta de alunos matriculados nas disciplinas ministradas

5. Gerenciamento de Alunos
- Cadastro e atualização de alunos
- Consulta de disciplinas matriculadas
- Verificação do status financeiro

6. Autenticação e Segurança
- Login e senha para alunos, professores e secretários
- Controle de acesso baseado no tipo de usuário

7. Sistema de Cobrança
- Envio de cobrança ao aluno após a matrícula
- Atualização do status de pagamento
- Notificação ao aluno sobre pendências financeiras
