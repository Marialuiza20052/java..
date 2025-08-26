public class LoggingService
public void logAccountCreation (String username) €
// Lógica para registrar a criação da conta (ex: em um arquivo, no console, etc.)
System.out printin("[LOG] Conta criada para o usuário: " + username);
// Responsabilidade: Enviar e-mails.
public class EmailService & public void sendEmailConfirmation (String email) €
// Lógica para enviar um e-mail de confirmação.
System.out println("Enviando e-mail de confirmação para: " + email);
// Responsabilidade: Orquestrar a criação de uma conta de usuário.
public class AccountService &
// Dependências de outros serviços private final EmailService emailService; private final LoggingService loggingService;
// O ideal é receber as dependências no construtor (Injeção de Dependência) public AccountService(EmailService emailService, LoggingService loggingService) €
this.emailService = emailService;
this.loggingService = loggingService;
public void createAccount(String username, String email) €
// 1. Lógica principal para criar uma conta (ex: salvar no banco de dados)
System.out printin("Salvando usuário '"+ username + "' no banco de dados...") ;
// 2. Delega a responsabilidade de enviar o e-mail
emailService.sendEmailConfirmation(email);
// 3. Delega a responsabilidade de registrar o 10g
loggingService. logAccountCreation(username);
System.out.println("Processo de criação de conta finalizado!");
