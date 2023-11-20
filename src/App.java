import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

public class App {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        int opcao = 1;
        int id;
        String nome;
        String logradouro;
        String cidade;
        String estado;
        String telefone;
        String email;
        String cpf;
        String cnpj;
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

        while (opcao != 0) {
            System.out.println("Insira a opção desejada:");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar execução");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1: // código para incluir PessoaJuridica
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    String tipo = scanner.next().toUpperCase();

                    if (tipo.charAt(0) == 'F') {
                        PessoaFisica pessoaFisica = new PessoaFisica();
                        boolean valido = false;

                        id = 1;
                        scanner.nextLine();

                        System.out.println("Digite o Nome: ");
                        nome = scanner.nextLine();
                        pessoaFisica.setNome(nome);

                        System.out.println("Digite o Logradouro: ");
                        logradouro = scanner.nextLine();
                        pessoaFisica.setLogradouro(logradouro);

                        System.out.println("Digite a Cidade: ");
                        cidade = scanner.nextLine();
                        pessoaFisica.setCidade(cidade);

                        System.out.println("Digite o Estado: ");
                        estado = scanner.nextLine();
                        pessoaFisica.setEstado(estado);

                        System.out.println("Digite o Telefone: ");
                        telefone = scanner.nextLine();
                        pessoaFisica.setTelefone(telefone);

                        System.out.println("Digite o Email: ");
                        email = scanner.nextLine();
                        pessoaFisica.setEmail(email);

                        System.out.println("Digite o CPF: ");
                        cpf = scanner.next();
                        pessoaFisica.setCpf(cpf);

                        pessoaFisicaDAO.incluir(pessoaFisica);

                    } else if (tipo.charAt(0) == 'J') {
                        PessoaJuridica pessoaJuridica = new PessoaJuridica();

                        boolean valido = false;

                        id = 1;
                        scanner.nextLine();

                        System.out.println("Digite o Nome: ");
                        nome = scanner.nextLine();
                        pessoaJuridica.setNome(nome);

                        System.out.println("Digite o Logradouro: ");
                        logradouro = scanner.nextLine();
                        pessoaJuridica.setLogradouro(logradouro);

                        System.out.println("Digite a Cidade: ");
                        cidade = scanner.nextLine();
                        pessoaJuridica.setCidade(cidade);

                        System.out.println("Digite o Estado: ");
                        estado = scanner.nextLine();
                        pessoaJuridica.setEstado(estado);

                        System.out.println("Digite o Telefone: ");
                        telefone = scanner.nextLine();
                        pessoaJuridica.setTelefone(telefone);

                        System.out.println("Digite o Email: ");
                        email = scanner.nextLine();
                        pessoaJuridica.setEmail(email);

                        System.out.println("Digite o CNPJ: ");
                        cnpj = scanner.next();
                        pessoaJuridica.setCnpj(cnpj);

                        pessoaJuridicaDAO.incluir(pessoaJuridica);
                    } else {
                        System.out.println("Tipo inválido");
                    }
                    break;

                // -----------------------------------------------------------------------
                case 2: // código para alterar
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    tipo = scanner.next().toUpperCase();

                    if (tipo.charAt(0) == 'F') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja alterar: ");
                                id = scanner.nextInt();
                                System.out.println("Pessoa que irá ser alterada: ");

                                PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(id);
                                if (pessoaFisica != null) {
                                    scanner.nextLine();
                                    System.out.println("Digite o Nome: ");
                                    nome = scanner.nextLine();
                                    pessoaFisica.setNome(nome);

                                    System.out.println("Digite o Logradouro: ");
                                    logradouro = scanner.nextLine();
                                    pessoaFisica.setLogradouro(logradouro);

                                    System.out.println("Digite a Cidade: ");
                                    cidade = scanner.nextLine();
                                    pessoaFisica.setCidade(cidade);

                                    System.out.println("Digite o Estado: ");
                                    estado = scanner.nextLine();
                                    pessoaFisica.setEstado(estado);

                                    System.out.println("Digite o Telefone: ");
                                    telefone = scanner.nextLine();
                                    pessoaFisica.setTelefone(telefone);

                                    System.out.println("Digite o Email: ");
                                    email = scanner.nextLine();
                                    pessoaFisica.setEmail(email);

                                    System.out.println("Digite o CPF: ");
                                    cpf = scanner.next();
                                    pessoaFisica.setCpf(cpf);

                                    pessoaFisicaDAO.alterar(pessoaFisica);
                                }

                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }

                    } else if (tipo.charAt(0) == 'J') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja alterar: ");
                                id = scanner.nextInt();
                                System.out.println("Pessoa que irá ser alterada: ");
                                PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(id);

                                if (pessoaJuridica != null) {
                                    scanner.nextLine();
                                    System.out.println("Digite o Nome: ");
                                    nome = scanner.nextLine();
                                    pessoaJuridica.setNome(nome);

                                    System.out.println("Digite o Logradouro: ");
                                    logradouro = scanner.nextLine();
                                    pessoaJuridica.setLogradouro(logradouro);

                                    System.out.println("Digite a Cidade: ");
                                    cidade = scanner.nextLine();
                                    pessoaJuridica.setCidade(cidade);

                                    System.out.println("Digite o Estado: ");
                                    estado = scanner.nextLine();
                                    pessoaJuridica.setEstado(estado);

                                    System.out.println("Digite o Telefone: ");
                                    telefone = scanner.nextLine();
                                    pessoaJuridica.setTelefone(telefone);

                                    System.out.println("Digite o Email: ");
                                    email = scanner.nextLine();
                                    pessoaJuridica.setEmail(email);

                                    System.out.println("Digite o CNPJ: ");
                                    cnpj = scanner.next();
                                    pessoaJuridica.setCnpj(cnpj);

                                    pessoaJuridicaDAO.alterar(pessoaJuridica);
                                }

                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }
                    } else {
                        System.out.println("Tipo inválido");
                    }
                    break;

                // -----------------------------------------------------------------------
                case 3: // código para excluir
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    tipo = scanner.next().toUpperCase();

                    if (tipo.charAt(0) == 'F') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja excluir: ");
                                id = scanner.nextInt();
                                pessoaFisicaDAO.excluir(id);
                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }

                    } else if (tipo.charAt(0) == 'J') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja excluir: ");
                                id = scanner.nextInt();
                                pessoaJuridicaDAO.excluir(id);
                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }
                    } else {
                        System.out.println("Tipo inválido");
                    }
                    break;

                // -----------------------------------------------------------------------
                case 4: // código para obter pelo ID
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    tipo = scanner.next().toUpperCase();

                    if (tipo.charAt(0) == 'F') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja obter: ");
                                id = scanner.nextInt();
                                pessoaFisicaDAO.getPessoa(id);

                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }

                    } else if (tipo.charAt(0) == 'J') {
                        boolean valido = false;
                        while (!valido) {
                            try {
                                System.out.println("Digite o ID que deseja obter: ");
                                id = scanner.nextInt();
                                pessoaJuridicaDAO.getPessoa(id);

                                valido = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Erro: você deve digitar um inteiro.");
                                scanner.nextLine();
                            }
                        }
                    } else {
                        System.out.println("Tipo inválido");
                    }
                    break;

                // -----------------------------------------------------------------------
                case 5: // código para obter todos
                    System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
                    tipo = scanner.next().toUpperCase();

                    if (tipo.charAt(0) == 'F') {
                        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
                        System.out.println("Pessoas físicas cadastradas:");
                        for (PessoaFisica p : pessoasFisicas) {
                            p.exibir();
                        }
                    } else if (tipo.charAt(0) == 'J') {
                        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
                        System.out.println("Pessoas jurídicas cadastradas:");
                        for (PessoaJuridica p : pessoasJuridicas) {
                            p.exibir();
                        }
                    } else {
                        System.out.println("Tipo inválido");
                    }
                    break;

                // -----------------------------------------------------------------------
                case 0:
                    System.out.println("Finalizando execução...");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }
        scanner.close();
        
    }
}
