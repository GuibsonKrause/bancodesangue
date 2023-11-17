# Sistema Doador de Sangue

## Descrição
O Sistema Doador de Sangue é uma aplicação Spring Boot projetada para gerenciar doações de sangue. Este sistema permite o registro de candidatos a doadores, gerencia doações e fornece funcionalidades relacionadas à autenticação de usuários e geração de relatórios em PDF.

## Tecnologias Utilizadas
- Spring Boot
- Spring Security para autenticação e autorização
- JPA/Hibernate para persistência de dados
- MySQL como sistema de gerenciamento de banco de dados
- Apache PDFBox para geração de relatórios em PDF

## Configuração e Instalação
Para configurar e rodar o projeto, siga os passos abaixo:

### Pré-requisitos
- JDK 11 ou superior
- Maven
- MySQL

### Passos para Configuração
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/GuibsonKrause/bancodesangue
Configure o MySQL:

Crie um banco de dados para a aplicação.
Atualize o arquivo application.properties com as credenciais do seu banco de dados.

mvn clean install
mvn spring-boot:run

Estrutura do Projeto
CandidatoController: Controlador para gerenciar candidatos.
CandidatoService: Serviço para lógica de negócios relacionada a candidatos.
Candidato: Entidade representando um candidato no banco de dados.
WebSecurityConfig: Configuração de segurança do Spring Security.
