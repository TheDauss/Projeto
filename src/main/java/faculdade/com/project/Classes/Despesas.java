package faculdade.com.project.Classes;

public class Despesas{
    protected String nome;
    protected Float valor;
    protected String vencimento;

    public Despesas(String nome, Float valor, String vencimento){
        this.nome = nome;
        this.valor = valor;
        this.vencimento = vencimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    
}
