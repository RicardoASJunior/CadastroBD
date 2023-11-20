import java.sql.SQLException;
import java.util.List;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Instanciar uma pessoa física e persis4tir no banco de dados
        PessoaFisica pf = new PessoaFisica(17, "João", "Rua A", "São Paulo",
        "SP", "1111-1111", "teste@teste.com",
        "123.456.789-00");
        PessoaFisicaDAO pfDAO = new PessoaFisicaDAO();
        pfDAO.getPessoa(1);

        pfDAO.incluir(pf);

        // // // Alterar os dados da pessoa física no banco
        pf.setNome("João Pedro Paulo");
        pf.setTelefone("2222-2222");
        pf.setCpf("234.456.456-12");
        pfDAO.alterar(pf);
        System.out.println("Pessoa física alterada com sucesso!");

        // Consultar todas as pessoas físicas do banco de dados e listar no console
        List<PessoaFisica> pessoasFisicas = pfDAO.getPessoas();
        System.out.println("Pessoas físicas cadastradas:");
        for (PessoaFisica p : pessoasFisicas) {
        p.exibir();
        }

        // // //Excluir a pessoa física criada anteriormente no banco
        pfDAO.excluir(pf.getId());
        System.out.println("Pessoa física excluída com sucesso!");

        // Instanciar uma pessoa jurídica e persistir no banco de dados
        PessoaJuridica pj = new PessoaJuridica(2, "Empresa X", "Rua B", "Rio de Janeiro",
                                                "RJ", "3333-3333", "teste@teste.com",
                                                "12.345.6780001-90");
        PessoaJuridicaDAO pjDAO = new PessoaJuridicaDAO();
        pjDAO.getPessoa(3);
        pjDAO.incluir(pj);
        System.out.println("Pessoa jurídica incluída com sucesso!");

        // Alterar os dados da pessoa jurídica no banco
        pj.setNome("Empresa Y");
        pjDAO.alterar(pj);
        System.out.println("Pessoa jurídica alterada com sucesso!");

        // Consultar todas as pessoas jurídicas do banco e listar no console
        List<PessoaJuridica> pessoasJuridicas = pjDAO.getPessoas();
        System.out.println("Pessoas jurídicas cadastradas:");
        for (PessoaJuridica p : pessoasJuridicas) {
        p.exibir();
        }

        // Excluir a pessoa jurídica criada anteriormente no banco
        pjDAO.excluir(pj.getId());
        System.out.println("Pessoa jurídica excluída com sucesso!");
    }
}
