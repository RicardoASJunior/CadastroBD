package cadastrobd.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



   public class ConectorBD { 
    // Constantes para armazenar os dados de conexão com o banco de dados 
    // Você pode alterar esses valores de acordo com a sua configuração 
    
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Loja;user=loja;password=loja;encrypt=true;trustServerCertificate=true;";
   
    
    public static Connection getConnection() throws SQLException { 
        try { 
            // Carregar o driver JDBC do SQL Server 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        
        } catch (ClassNotFoundException e) {
             // Tratar a exceção se o driver não for encontrado 
             e.printStackTrace(); 
        }
        return DriverManager.getConnection(URL); 
    }
    // Criar o pacote cadastrobd.model.util package cadastrobd.model.util; 
    //Método getPrepared, para retornar um objeto do tipo PreparedStatement a partir de um SQL fornecido com parâmetro 
    public static PreparedStatement getPrepared(String sql) throws SQLException { 
        // Obter uma conexão com o banco de dados 
        Connection con = getConnection(); 
        // Criar e retornar um objeto do tipo PreparedStatement usando o SQL fornecido 
        return con.prepareStatement(sql); 
    }

    // Método getSelect, para retornar o ResultSet relacionado a uma consulta 
    public static ResultSet getSelect(String sql) throws SQLException { 
        // Obter um objeto do tipo PreparedStatement usando o método getPrepared 
        PreparedStatement ps = getPrepared(sql);
        // Executar a consulta e retornar o ResultSet resultante 
        return ps.executeQuery(); 
    }

    // Método close sobrecarregado para Statement, para garantir o fechamento do objeto 
    public static void close(Statement st) { 
        // Verificar se o objeto não é nulo 
        if (st != null) { 
            try { 
                // Fechar o objeto 
                st.close(); 
            } catch (SQLException e) { 
                // Imprimir a mensagem de erro 
                e.printStackTrace(); 
            } 
        } 
    } 

    // Método close sobrecarregado para ResultSet, para garantir o fechamento do objeto 
    public static void close(ResultSet rs) { 
        // Verificar se o objeto não é nulo 
        if (rs != null) { 
            try { 
                // Fechar o objeto 
                rs.close(); 
            } catch (SQLException e) { 
                // Imprimir a mensagem de erro 
                e.printStackTrace(); 
            } 
        } 
    }
     
    // Método close sobrecarregado para Connection, para garantir o fechamento do objeto 
    public static void close(Connection con) { 
        // Verificar se o objeto não é nulo 
        if (con != null) { 
            try { 
                // Fechar o objeto 
                con.close(); 
            } catch (SQLException e) { 
                // Imprimir a mensagem de erro 
                e.printStackTrace(); 
            } 
        } 
    } 
    // Classe SequenceManager 
    public class SequenceManager {
        // Método getValue, recebendo o nome da sequência como parâmetro e retornando o próximo valor 
        public static int getValue(String sequence) throws SQLException { 
            // Inicializar uma variável para armazenar o valor da sequência 
            int value = 0; 
            // Criar uma string com o SQL para obter o valor atual da sequência 
            String sql = "SELECT NEXT VALUE FOR " + sequence ; 
            // Obter o ResultSet relacionado à consulta usando o método getSelect da classe ConectorBD 
            ResultSet rs = ConectorBD.getSelect(sql); 
            // Verificar se o ResultSet tem algum resultado 
            if (rs.next()) { 
                // Obter o valor da sequência a partir da primeira coluna do ResultSet 
                value = rs.getInt(1); 
            } 
            // Fechar o ResultSet usando o método close da classe ConectorBD 
            ConectorBD.close(rs); 
            // Retornar o valor da sequência 
            return value; 
        } 
    }
}
   