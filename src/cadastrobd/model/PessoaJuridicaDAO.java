package cadastrobd.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class PessoaJuridicaDAO {

    // Método para obter uma pessoa física a partir do seu id
    public PessoaJuridica getPessoa(int id) throws SQLException {
        PessoaJuridica pj = null;
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.pessoa_id WHERE p.id = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pj = new PessoaJuridica();
                pj.setId(rs.getInt("id"));
                pj.setNome(rs.getString("nome"));
                pj.setLogradouro(rs.getString("logradouro"));
                pj.setCidade(rs.getString("cidade"));
                pj.setEstado(rs.getString("estado"));
                pj.setTelefone(rs.getString("telefone"));
                pj.setEmail(rs.getString("email"));
                pj.setCnpj(rs.getString("cnpj"));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
        if (pj != null){
            pj.exibir();
        }else{
            System.out.println("Este ID não existe!");
        }
        return pj;
    }

    // Método para obter todas as pessoas físicas do banco de dados
    public List<PessoaJuridica> getPessoas() throws SQLException {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Pessoa p INNER JOIN PessoaJuridica pj ON p.id = pj.pessoa_id");
            rs = ps.executeQuery();
            while (rs.next()) {
                PessoaJuridica pj = new PessoaJuridica();
                pj.setId(rs.getInt("id"));
                pj.setNome(rs.getString("nome"));
                pj.setLogradouro(rs.getString("logradouro"));
                pj.setCidade(rs.getString("cidade"));
                pj.setEstado(rs.getString("estado"));
                pj.setTelefone(rs.getString("telefone"));
                pj.setEmail(rs.getString("email"));
                pj.setCnpj(rs.getString("cnpj"));
                pessoas.add(pj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
        return pessoas;
    }

    // Método para incluir uma pessoa física nas tabelas Pessoa e PessoaJuridica
    public void incluir(PessoaJuridica pj) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
            // Inserir na tabela Pessoa
            ps = conn.prepareStatement("INSERT INTO Pessoa (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pj.getNome());
            ps.setString(2, pj.getLogradouro());
            ps.setString(3, pj.getCidade());
            ps.setString(4, pj.getEstado());
            ps.setString(5, pj.getTelefone());
            ps.setString(6, pj.getEmail());
    
            int rows = ps.executeUpdate(); 

            // Verificar se a inserção foi bem sucedida 
            if (rows > 0) {
                // Obter o ResultSet com o id gerado pelo banco 
                ResultSet rs = ps.getGeneratedKeys(); 

                // Verificar se o ResultSet tem algum resultado
                if (rs.next()) { 
                    // Obter o id da pessoa a partir da primeira coluna do ResultSet 
                    int id = rs.getInt(1);

                    // Usar o mesmo objeto PreparedStatement para inserir os dados na tabela PessoaJurídica 
                    ps = conn.prepareStatement("INSERT INTO PessoaJuridica (cnpj, pessoa_id) VALUES (?, ?)");
                    ps.setString(1, pj.getCnpj()); 
                    ps.setInt(2, id);
                    rows = ps.executeUpdate(); 
    
                    if (rows > 0) { 
                        System.out.println("Pessoa jurídica inserida com sucesso!"); 
                    } else { 
                        System.out.println("Falha ao inserir a pessoa jurídica!"); 
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
    public void alterar(PessoaJuridica pj) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
             // Alterar na tabela Pessoa
             ps = conn.prepareStatement("UPDATE Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?");
             ps.setString(1, pj.getNome());
             ps.setString(2, pj.getLogradouro());
             ps.setString(3, pj.getCidade());
             ps.setString(4, pj.getEstado());
             ps.setString(5, pj.getTelefone());
             ps.setString(6, pj.getEmail());
             ps.setInt(7, pj.getId());
             ps.executeUpdate();

            // Alterar na tabela PessoaJuridica
            ps = conn.prepareStatement("UPDATE PessoaJuridica SET cnpj = ? WHERE pessoa_id = ?");
            ps.setString(1, pj.getCnpj());
            ps.setInt(2, pj.getId());
            ps.executeUpdate();
            System.out.println("Pessoa jurídica alterada com sucesso!"); 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(conn);
        }
    }

    // Método para remover uma pessoa física das tabelas Pessoa e PessoaJuridica
    public void excluir(int id) throws SQLException {
        Connection conn = ConectorBD.getConnection();
        PreparedStatement ps = null;
        try {
            // Excluir da tabela PessoaJuridica
            ps = conn.prepareStatement("DELETE FROM PessoaJuridica WHERE pessoa_id = ?");
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