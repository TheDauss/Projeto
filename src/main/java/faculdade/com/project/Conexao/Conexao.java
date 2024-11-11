package faculdade.com.project.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // Declaração das constantes de conexão que armazenam a URL do banco de dados.
    private static final String URL = "jdbc:mysql://localhost:3306/dbgerenciamento";
    private static final String USUARIO = "root";
    private static final String SENHA = "ggizi1234";
 
    // Utiliza o DriverManager para iniciar a conexão com o banco.     
    public static final Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
