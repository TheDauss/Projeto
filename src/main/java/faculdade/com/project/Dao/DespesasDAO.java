package faculdade.com.project.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import faculdade.com.project.Classes.Despesas;
import faculdade.com.project.Enum.CategoriasDespesa;
import faculdade.com.project.Conexao.Conexao;

public class DespesasDAO {
    
    // Salva uma nova despesa no banco de dados usando os dados fornecidos.  
    public void salvar(Despesas despesas) throws Exception {
        String sql = "INSERT INTO despesas(nome, valor, vencimento, categoria) VALUES(?, ?, ?, ?)";

        // Método para iniciar conexão com o banco.
        try (var conexao = Conexao.obterConexao(); 
             var stmt = conexao.prepareStatement(sql)) {    
            // Define os parâmetros de acordo com as informações do objeto Despesas.
            stmt.setString(1, despesas.getNome());
            stmt.setFloat(2, despesas.getValor());
            stmt.setString(3, despesas.getVencimento());
            stmt.setString(4, despesas.getCategoria().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    
    // Busca todas as despesas no banco de dados e retorna uma lista de objetos Despesas.
    public List<Despesas> buscarTudo() throws Exception {
        var sql = "SELECT * FROM despesas";
        List<Despesas> despesas = new ArrayList<>();
        // Método para iniciar conexão com o banco.

        try (var conexao = Conexao.obterConexao();
             var stmt = conexao.prepareStatement(sql)) {            
            // 'executeQuery()' usado para executar consulta SELECT.
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CategoriasDespesa categoria = CategoriasDespesa.valueOf(rs.getString("categoria"));

                    // Cria um novo objeto 'Despesas' usando os valores extraídos das colunas.
                    Despesas despesa = new Despesas(
                            rs.getString("nome"),
                            rs.getFloat("valor"),
                            rs.getString("vencimento"),
                            categoria
                    );
                    despesas.add(despesa);
                }
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
        return despesas;
    }

    // Exclui uma coluna cujo nome seja igual ao parâmetro.
    public void excluir(String nome) throws Exception {
       var sql = "DELETE FROM despesas WHERE nome = ?"; 
       
       // Método para iniciar conexão com o banco.
       try (var conexao = Conexao.obterConexao();
            var stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
       } catch (SQLException e) {
           throw new Exception(e);
       }
    }
}
