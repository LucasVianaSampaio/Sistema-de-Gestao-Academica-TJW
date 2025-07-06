# 📚 Sistema de Gerenciamento Acadêmico

Este é um sistema CRUD completo desenvolvido em Java com **Spring Boot**, que permite o gerenciamento de entidades acadêmicas como Usuários, Professores, Disciplinas, Turmas, Alunos e Matrículas. O projeto utiliza banco de dados em memória H2 e segue boas práticas como soft delete e separação em camadas (Entity, Repository, Service, Controller).

---

## 🚀 Funcionalidades

### ✅ Usuários
- Cadastro, atualização, listagem e inativação de usuários.
- Filtro por nome, email ou status (ativo/inativo).

### 🧑‍🏫 Professores
- Cadastro de professores com CPF único.
- Atualização e inativação (soft delete).
- Filtros por nome e área de atuação.

### 📘 Disciplinas
- CRUD completo com validação de código único.
- Visualização da ementa e carga horária.

### 🏫 Turmas
- Cadastro de turmas com vínculo a professores e disciplinas.
- Cálculo automático de vagas disponíveis.
- Filtros por semestre, ano, professor e disciplina.

### 👨‍🎓 Alunos
- Cadastro e listagem de alunos com filtros por nome e CPF.

### 📝 Matrículas
- Realizar matrícula de aluno em uma turma.
- Alterar turma de uma matrícula existente.
- Cancelar matrícula (com validação de status).
- Visualizar todas as matrículas por aluno ou por turma.

---

## 🛠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **Banco de dados H2 (in-memory)**
- **Lombok**
- **Maven**
- **Postman (para testes de API)**

---

## 🔧 Como Executar o Projeto

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

2. Compile e rode o projeto com sua IDE ou pelo terminal:

```bash
./mvnw spring-boot:run
```

3. Acesse o console H2 para visualizar o banco de dados:

```
http://localhost:8080/h2-console
```

**JDBC URL:** `jdbc:h2:mem:academico`  
**User:** `sa`  
**Password:** *(deixe em branco)*

---

## 📮 Endpoints de Exemplo

### 🔹 Usuários
```
GET     /usuarios
POST    /usuarios
PUT     /usuarios/{id}
DELETE  /usuarios/{id}
```

### 🔹 Professores
```
GET     /professores
POST    /professores
PUT     /professores/{id}
DELETE  /professores/{id}
```

### 🔹 Disciplinas
```
GET     /disciplinas
POST    /disciplinas
PUT     /disciplinas/{id}
DELETE  /disciplinas/{id}
```

### 🔹 Turmas
```
GET     /turmas
POST    /turmas
PUT     /turmas/{id}
DELETE  /turmas/{id}
```

### 🔹 Matrículas
```
POST    /matriculas
PUT     /matriculas/{id}/alterar-turma
DELETE  /matriculas/{id}
GET     /matriculas/aluno/{id}
GET     /matriculas/turma/{id}
```

---

## 📌 Observações

- Todas as exclusões no sistema são do tipo *soft delete*, ou seja, os dados permanecem no banco com o campo `ativo = false`.
- O sistema foi modelado com validações de integridade e integridade referencial (ex: um professor não pode ser removido se houver turmas atreladas a ele, a menos que o relacionamento seja quebrado).
- As respostas seguem o padrão HTTP e retornam erros adequados (400, 404, etc).
