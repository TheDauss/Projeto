package faculdade.com.project;

import java.io.Console;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import faculdade.com.project.Classes.*;
import faculdade.com.project.Dao.*;

public class Main {

    static Console console = System.console();
    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = Integer.parseInt(console.readLine());
            switch (opcao) {
                case 1:
                    salvarDespesas();
                    break;
                case 2:
                    System.out.println("");
                    break;
                case 3:
                    observarDespesas();
                    break;
                case 4:
                    System.out.println("");
                    break;
                case 5:
                    System.out.println("");
                    break;
            }
        } while (opcao != 0);

    }

    public static void menu() {
        System.out.println("1. Criar Despesa");
        System.out.println("2. Deletar Despesa"); //
        System.out.println("3. Observar Despesas");
        System.out.println("4. Pagar Despesa");
        System.out.println("5. Dinheiro");// Ver saldo atual //Atualizar saldo atual
        System.out.println("0. Fechar programa");
    }

    public static void salvarDespesas() {
        System.out.println("\n### Criar Nova Despesa ###");

        System.out.println("Digite o nome da despesa");
        String nome = console.readLine();

        System.out.println("Digite o valor da despesa");
        // Passando para ler inteiro
        float valor = Float.parseFloat(console.readLine());

        System.out.println("Digite a data de vencimento da despesa");
        String vencimento = console.readLine();

        Despesas despesas = new Despesas(nome, valor, vencimento);
        DespesasDAO despesasDAO = new DespesasDAO();

        try {
            despesasDAO.salvar(despesas);
            System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
        } catch (Exception e) {
            // Fazer erro personalizado depois
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
    }

    public static void observarDespesas() {
        System.out.println("\n### Observar Despesa ###");
        DespesasDAO despesasDAO = new DespesasDAO();
    
        try {
            List<Despesas> despesas = despesasDAO.buscarTudo();
    
            if (despesas.isEmpty()) {
                System.out.println("Despesas não catalogadas.");
            } else {
                System.out.println("Despesas atuais:");
    
                for (Despesas despesa : despesas) {
                    System.out.println("Nome: " + despesa.getNome() + "\nValor " + despesa.getValor() + "R$ " + "\nData de Vencimento " + despesa.getVencimento());
                    System.out.println("---------------------");
                }
            }
        } catch (Exception e) {
            // Tratar a exceção de forma mais específica
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
    }
}
