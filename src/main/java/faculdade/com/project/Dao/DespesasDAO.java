package faculdade.com.project.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import faculdade.com.project.Classes.Despesas;
import faculdade.com.project.Conexao.Conexao;

public class DespesasDAO{
    
    public void salvar(Despesas despesas) throws Exception {
        String sql = "INSERT INTO despesas(nome, valor, vencimento) VALUES(?, ?, ?)";

        try (var conexao = Conexao.obterConexao(); 
             var stmt = conexao.prepareStatement(sql)) {
            
            stmt.setString(1, despesas.getNome());
            stmt.setFloat(2, despesas.getValor());
            stmt.setString(3, despesas.getVencimento());
            
            stmt.executeUpdate();
            System.out.println("Conta de salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar conta de energia: " + e.getMessage());
            throw e;
        }
    }

    public List<Despesas> buscarTudo() throws Exception{
        var sql = "select * from despesas";
        
        List<Despesas> despesas = new ArrayList<>();

        //Buscar conexão
        try (var conexao = Conexao.obterConexao();
            var stmt = conexao.prepareStatement(sql)) {
            
                try(ResultSet rs = stmt.executeQuery()){
                    while (rs.next()) {
                        //Colocar o nome das colunas
                        Despesas despesa = new Despesas(rs.getString("nome"), rs.getFloat("valor"), rs.getString("vencimento"));
                        despesas.add(despesa);
                    }
                }
        } catch (SQLException e){
            throw new Exception(e);
        }   
        return despesas;
    }

    public void excluir(String nome) throws Exception{
        // insert sem valor definido
       var sql = "delete from despesas where name = ?"; 
       
       //Buscar conexão
       try (var conexao = Conexao.obterConexao();
            var stmt = conexao.prepareStatement(sql)) {
                //Usando o prepare stmt para arrumar os parâmetros de forma segura
                stmt.setString(1, nome);
                stmt.executeUpdate();
       } catch (SQLException e) {
           //Criar exception personalizada depois
           throw new Exception(e);
       }
    }
}
