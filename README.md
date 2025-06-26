# 👨‍🏫 Site do Professor — Back-End

Este é o sistema back-end da aplicação **"Site do Professor"**, desenvolvido em **Java + Spring Boot**, com foco em segurança, estrutura modular e integração com autenticação via **JWT** e **Google OAuth2**.

---

## 📚 Funcionalidades

- Cadastro e edição dos dados do professor
- CRUD de projetos, publicações, hobbies e áreas de pesquisa
- Envio de mensagens de contato
- Autenticação com JWT e múltiplos perfis
- Login via conta Google (OAuth2)
- Controle de acesso por perfil: ADMINISTRADOR, AUXILIAR, VISUALIZADOR
- API RESTful com estrutura MVC

---

## 🧱 Tecnologias

- Java 17
- Spring Boot
- Spring Security + JWT
- OAuth2 (Google)
- MySQL
- Maven
- JPA (Hibernate)
- Lombok
- Postman / Insomnia para testes
- JUnit 5 + Mockito + MockMvc

---

## 🧪 Instruções para Execução

### 1️⃣ Pré-requisitos

- [Java 17 ou 21](https://www.oracle.com/java/technologies/javase/jdk-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)
- Git
- Postman ou Insomnia (opcional para testes)

---

### 2️⃣ Clonar o Repositório

```bash
git clone https://github.com/seu_usuario/seu_repositorio.git
cd seu_repositorio
git checkout entrega-2
 
3️⃣ Configurar o Banco de Dados
Crie o banco:

CREATE DATABASE siteprofessor DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
  
4️⃣ Configurar o arquivo application.properties
Abra o arquivo:

src/main/resources/application.properties
 
spring.datasource.url=jdbc:mysql://localhost:3306/siteprofessor
spring.datasource.username=root
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

app.jwt.secret=segredo123456789
app.jwt.expiration=7200000
 
5️⃣ Executar os scripts SQL
No terminal:

mysql -u root -p siteprofessor < backend/database/schema.sql
mysql -u root -p siteprofessor < backend/database/seed.sql
 
6️⃣ Rodar o projeto
Na raiz do projeto (pasta backend/), execute:

./mvnw spring-boot:run 

A aplicação vai rodar em:

http://localhost:8080