package cadastrobd.model;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import cadastrobd.model.ConectorBD.SequenceManager;

public class PessoaFisicaDAO {

    // Método para obter uma pessoa física a partir do seu id
    public PessoaFisica getPessoa(int id) throws SQLException {
        PessoaFisica pf = null;
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.pessoa_id WHERE p.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pf = new PessoaFisica();
                pf.setId(rs.getInt("id"));
                pf.setNome(rs.getString("nome"));
                pf.setLogradouro(rs.getString("logradouro"));
                pf.setCidade(rs.getString("cidade"));
                pf.setEstado(rs.getString("estado"));
                pf.setTelefone(rs.getString("telefone"));
                pf.setEmail(rs.getString("email"));
                pf.setCpf(rs.getString("cpf"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
        if (pf != null){
            pf.exibir();
        }else{
            System.out.println("Este ID não existe!");
        }
        
        return pf;
    }

    // Método para obter todas as pessoas físicas do banco de dados
    public List<PessoaFisica> getPessoas() throws SQLException {
        List<PessoaFisica> pessoas = new ArrayList<>();
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Pessoa p INNER JOIN PessoaFisica pf ON p.id = pf.pessoa_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                PessoaFisica pf = new PessoaFisica();
                pf.setId(rs.getInt("id"));
                pf.setNome(rs.getString("nome"));
                pf.setLogradouro(rs.getString("logradouro"));
                pf.setCidade(rs.getString("cidade"));
                pf.setEstado(rs.getString("estado"));
                pf.setTelefone(rs.getString("telefone"));
                pf.setEmail(rs.getString("email"));
                pf.setCpf(rs.getString("cpf"));
                pessoas.add(pf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
        return pessoas;
    }

    // Método para incluir uma pessoa física nas tabelas Pessoa e PessoaFisica
    public void incluir(PessoaFisica pf) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
            // Inserir na tabela Pessoa
            ps = conn.prepareStatement("INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pf.getNome());
            ps.setString(2, pf.getLogradouro());
            ps.setString(3, pf.getCidade());
            ps.setString(4, pf.getEstado());
            ps.setString(5, pf.getTelefone());
            ps.setString(6, pf.getEmail());
    
            int rows = ps.executeUpdate(); 

            // Verificar se a inserção foi bem sucedida 
            if (rows > 0) {
                // Obter o ResultSet com o id gerado pelo banco 
                ResultSet rs = ps.getGeneratedKeys(); 

                // Verificar se o ResultSet tem algum resultado
                if (rs.next()) { 
                    // Obter o id da pessoa a partir da primeira coluna do ResultSet 
                    int id = rs.getInt(1);

                    // Usar o mesmo objeto PreparedStatement para inserir os dados na tabela PessoaFisica 
                    ps = conn.prepareStatement("INSERT INTO PessoaFisica (cpf, pessoa_id) VALUES (?, ?)");
                    ps.setString(1, pf.getCpf()); 
                    ps.setInt(2, id);
                    rows = ps.executeUpdate(); 
    
                    if (rows > 0) { 
                        System.out.println("Pessoa física inserida com sucesso!"); 
                    } else { 
                        System.out.println("Falha ao inserir a pessoa física!"); 
                    } 
                } else { 
                    System.out.println("Falha ao obter o id da pessoa!"); 
                } 
            } else { 
                System.out.println("Falha ao inserir a pessoa!"); 
            }  
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
    }

    // Método para alterar os dados de uma pessoa física
    public void alterar(PessoaFisica pf) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
            // Alterar na tabela Pessoa
            ps = conn.prepareStatement("UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?");
            ps.setString(1, pf.getNome());
            ps.setString(2, pf.getLogradouro());
            ps.setString(3, pf.getCidade());
            ps.setString(4, pf.getEstado());
            ps.setString(5, pf.getTelefone());
            ps.setString(6, pf.getEmail());
            ps.setInt(7, pf.getId());
            ps.executeUpdate();

            // Alterar na tabela PessoaFisica
            ps = conn.prepareStatement("UPDATE PessoaFisica SET cpf = ? WHERE pessoa_id = ?");
            ps.setString(1, pf.getCpf());
            ps.setInt(2, pf.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
    }

    // Método para remover uma pessoa física das tabelas Pessoa e PessoaFisica
    public void excluir(int id) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
            // Excluir da tabela PessoaFisica
            ps = conn.prepareStatement("DELETE FROM PessoaFisica WHERE pessoa_id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();

            // Excluir da tabela Pessoa
            ps = conn.prepareStatement("DELETE FROM Pessoa WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
    }
}