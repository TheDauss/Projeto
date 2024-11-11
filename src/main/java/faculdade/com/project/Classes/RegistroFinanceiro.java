package faculdade.com.project.Classes;

public abstract class RegistroFinanceiro {
    protected String nome;
    protected Float valor;

    public RegistroFinanceiro(String nome, float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public RegistroFinanceiro(String nome) {
        this.nome = nome;
        this.valor = 0.0f; 
    }
    
    public RegistroFinanceiro(float valor) {
        this.valor = 0.0f; 
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
}
