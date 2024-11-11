package faculdade.com.project.Dao;

import faculdade.com.project.Classes.Saldo;
import faculdade.com.project.Conexao.Conexao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaldoDAO {

    //Busca o saldo atual que está guardado na primary key 'Saldos'.
    public Saldo buscarSaldoAtual() throws SQLException {
        String sql = "SELECT saldoAtual FROM saldo WHERE nome = 'Saldos'";
        // Método para iniciar conexão com o banco.
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Retorna o objeto Saldo com o valor de saldoAtual
                return new Saldo(
                    "Saldos",  
                    rs.getFloat("saldoAtual")
                );
            }
        }
        return null; 
    }
    
    // Subtrai o saldo de acordo com o valor no parâmetro do método.
    public void subtrairSaldo(Saldo saldo) throws SQLException {
        String sql = "UPDATE saldo SET saldoAtual = ? WHERE nome = 'Saldos'";
        // Método para iniciar conexão com o banco.
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            
            // Define o valor subtraído e guarda no banco.
            stmt.setFloat(1, saldo.getValor());
            stmt.executeUpdate();
        }
    }    

    // Coloca 'Saldos' na coluna nome da tabela.
    public void criarSaldo() throws SQLException {
        String sqlCheck = "SELECT COUNT(*) FROM saldo WHERE nome = 'Saldos'"; // Verifica se já existe uma entrada com 'Saldos'
        
        // Método para iniciar conexão com o banco.    
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sqlCheck)) {
            ResultSet rs = stmt.executeQuery();

            // Se não houver uma célula na coluna nome, realiza o INSERT para criar a conta.
            if (rs.next() && rs.getInt(1) == 0) { 
                String sql = "INSERT INTO saldo (nome, saldoAtual) VALUES ('Saldos', 0)";
                try (var stmtInsert = conexao.prepareStatement(sql)) {
                    stmtInsert.executeUpdate();
                    System.out.println("Conta criada com sucesso, agora atualize seu saldo.");
                }
            } else {
                System.out.println("Você já possui uma conta.");
            }
        }
    }
    
    // Atualiza o saldo através do parâmetro.
    public void atualizarSaldo(float novoSaldo) throws SQLException {
        String sql = "UPDATE saldo SET saldoAtual = ? WHERE nome = 'Saldos'"; // Atualiza o saldo baseado no nome 'Saldos'.
    
        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {
            
            // Define o novo valor pro saldoAtual e guarda no banco.
            stmt.setFloat(1, novoSaldo);
            stmt.executeUpdate();
            System.out.println("Saldo atualizado com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o saldo: " + e.getMessage());
        }
    }
}
