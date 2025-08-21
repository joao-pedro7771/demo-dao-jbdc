Sistema de Vendedores e Departamentos
Descrição
Este projeto é uma aplicação Java que realiza o gerenciamento de vendedores e seus respectivos departamentos, utilizando JDBC para acesso ao banco de dados relacional, modelo DAO (Data Access Object) para abstrair a persistência dos dados e tratamento de exceções personalizado, garantindo maior controle e clareza nos erros de execução.

O projeto segue boas práticas de programação orientada a objetos (POO) e arquitetura em camadas.
 Estrutura do Projeto

src/
 ├── application/
 │    └── Program.java          # Classe principal (execução do sistema)
 │
 ├── db/
 │    ├── DB.java               # Conexão com o banco de dados
 │    ├── DbException.java      # Exceção personalizada para erros gerais de DB
 │    └── DbIntegrityException.java  # Exceção personalizada para integridade
 │
 ├── model/
 │    ├── entities/
 │    │    ├── Department.java  # Classe de domínio para Departamento
 │    │    └── Seller.java      # Classe de domínio para Vendedor
 │    │
 │    ├── dao/
 │    │    ├── DepartmentDao.java  # Interface DAO para Departamento
 │    │    ├── SellerDao.java     # Interface DAO para Vendedor
 │    │    └── DaoFactory.java    # Factory para instanciar os DAOs
 │    │
 │    └── dao/impl/
 │         ├── DepartmentDaoImpl.java # Implementação JDBC do DAO de Departamento
 │         └── SellerDaoImpl.java     # Implementação JDBC do DAO de Vendedor
 │
 └── resources/
      └── db.properties          # Configurações de conexão com o banco

 Modelagem
Entidades
- Seller (Vendedor)
  - id
  - name
  - email
  - birthDate
  - baseSalary
  - department (associação com Department)

- Department (Departamento)
  - id
  - name

Relacionamento
- Um departamento pode ter vários vendedores.
- Um vendedor pertence a apenas um departamento.

Tecnologias Utilizadas
- Java SE 21+
- JDBC (Driver MySQL)
- MySQL
- DAO Pattern
- Tratamento de Exceções Personalizado

Configuração do Banco de Dados
Criação do schema:

CREATE DATABASE coursejdbc;

USE coursejdbc;

CREATE TABLE department (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(60) NOT NULL
);

CREATE TABLE seller (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    BirthDate DATE NOT NULL,
    BaseSalary DOUBLE NOT NULL,
    DepartmentId INT NOT NULL,
    FOREIGN KEY (DepartmentId) REFERENCES department(Id)
);

Como Executar
1. Clone o repositório:
   git clone https://github.com/seu-usuario/sistema-vendedores.git

2. Configure o banco de dados conforme as instruções acima.
3. Ajuste o arquivo db.properties com suas credenciais.
4. Compile e execute:
   javac -d bin src/**/*.java
   java -cp bin application.Program

 Funcionalidades
- Inserir novo vendedor/departamento
- Atualizar dados existentes
- Deletar por ID
- Buscar vendedor por ID
- Buscar todos vendedores
- Buscar vendedores por departamento
- Listar todos os departamentos

 Tratamento de Exceções
O sistema utiliza exceções personalizadas para melhorar a clareza:
- DbException → erros gerais de conexão e consulta ao banco.
- DbIntegrityException → violação de integridade referencial (ex: deletar um departamento com vendedores vinculados).
