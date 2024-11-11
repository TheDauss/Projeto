package faculdade.com.project.Classes;

import faculdade.com.project.Enum.CategoriasDespesa;

public class Despesas extends RegistroFinanceiro {
    private String vencimento;
    private CategoriasDespesa categoria;

    public Despesas(String nome, float valor, String vencimento, CategoriasDespesa categoria) {
        super(nome, valor);
        this.vencimento = vencimento;
        this.categoria = categoria;
    }

    public Despesas(String nome){
        super(nome);
        this.nome = nome;
    }

    //Get and Set
    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public CategoriasDespesa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriasDespesa categoria) {
        this.categoria = categoria;
    }
}
