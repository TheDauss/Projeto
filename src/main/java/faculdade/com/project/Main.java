package faculdade.com.project;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;

import faculdade.com.project.Classes.*;
import faculdade.com.project.Dao.*;
import faculdade.com.project.Enum.CategoriasDespesa;

public class Main {

    static Console console = System.console();

    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = Integer.parseInt(console.readLine());
            switch (opcao) {
                case 1:
                    criarConta();
                    break;
                case 2:
                    atualizarConta();
                    break;
                case 3:
                    salvarDespesas();
                    break;
                case 4:
                    excluirDespesas();
                    break;
                case 5:
                    observarDespesas();
                    break;
                case 6:
                    pagarDespesas();
                    break;
            }
        } while (opcao != 0);
    }

    public static void menu() {
        System.out.println("\n========================");
        System.out.println("Obrigatório: Financeiro");
        System.out.println("1. Criar Conta"); 
        System.out.println("2. Atualizar Saldo");
        System.out.println("\nOpcional: Sistema de Despesas");
        System.out.println("3. Criar Despesa");
        System.out.println("4. Deletar Despesa");
        System.out.println("5. Observar Despesas"); 
        System.out.println("6. Pagar Despesas"); 
        
        System.out.println("\n0. Fechar programa");
        System.out.println("========================");
    }

    public static void salvarDespesas() {
        System.out.println("\n### Criar Nova Despesa ###");

        System.out.println("Digite o nome da despesa");
        String nome = console.readLine();

        System.out.println("Digite o valor da despesa (ex: 5.00)");
        // Passando para ler inteiro
        float valor = Float.parseFloat(console.readLine());

        System.out.println("Digite a data de vencimento da despesa");
        String vencimento = console.readLine();

        System.out.println("Digite o número da categoria da sua despesa");
        System.out.println("\n1.Alimentação\n2.Transporte\n3.Lazer\n4.Educação\n5.Saúde\n6.Outros\nOutro número.Voltar ao menu");
        System.out.println("---------------------------------");

        int categoria;
        categoria = Integer.parseInt(console.readLine());
        DespesasDAO despesasDAO = new DespesasDAO();

        //Switch que recebe o valor de categoria e define a mesma na despesa.
        switch (categoria) {
            case 1:
                Despesas despesas1 = new Despesas(nome, valor, vencimento, CategoriasDespesa.alimentação);

                try {
                    despesasDAO.salvar(despesas1);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            case 2:
                Despesas despesas2 = new Despesas(nome, valor, vencimento, CategoriasDespesa.transporte);

                try {
                    despesasDAO.salvar(despesas2);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            case 3:
                Despesas despesas3 = new Despesas(nome, valor, vencimento, CategoriasDespesa.lazer);

                try {
                    despesasDAO.salvar(despesas3);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            case 4:
                Despesas despesas4 = new Despesas(nome, valor, vencimento, CategoriasDespesa.educação);

                try {
                    despesasDAO.salvar(despesas4);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            case 5:
                Despesas despesas5 = new Despesas(nome, valor, vencimento, CategoriasDespesa.saúde);

                try {
                    despesasDAO.salvar(despesas5);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            case 6:
                Despesas despesas6 = new Despesas(nome, valor, vencimento, CategoriasDespesa.outros);

                try {
                    despesasDAO.salvar(despesas6);
                    System.out.println("Despesa salva com sucesso. Consulte em 'Observar Despesas'.");
                } catch (Exception e) {
                    System.err.println("Erro ao salvar despesa: " + e.getMessage());
                }
                break;
            default:
                System.out.println("Voltando ao menu...");
                break;
        }
    }

    public static void observarDespesas() {
        System.out.println("\n### Lista de Despesas ###\n");
        DespesasDAO despesasDAO = new DespesasDAO();
        int count = 0;

        try {
            List<Despesas> despesas = despesasDAO.buscarTudo();

            // Verifica se a tabela despesas está vazia.
            if (despesas.isEmpty()) {
                System.out.println("Despesas não catalogadas.");
            } else {
                for (Despesas despesa : despesas) {
                    if (count > 0) {
                        System.out.println("\n+----------------+\n");
                    }

                    // Interface da defesa.
                    System.out.println("Nome: " + despesa.getNome() + "\nValor: " + "R$" + despesa.getValor() +
                            "\nData de Vencimento " + despesa.getVencimento() + "\nCategoria: "
                            + despesa.getCategoria());
                    count++;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }
    }

    public static void excluirDespesas() {
        System.out.println("\n### Excluir Despesa ###");
        DespesasDAO despesasDAO = new DespesasDAO();

        try {
            List<Despesas> despesas = despesasDAO.buscarTudo();

            // Verifica se a tabela despesas está vazia.
            if (despesas.isEmpty()) {
                System.out.println("Nenhuma despesa catalogada.");
                return;
            } else {
                observarDespesas();
            }
        } catch (Exception e) {
            System.err.println("Erro ao consultar despesas: " + e.getMessage());
        }

        System.out.println("\n+----------------+\n");
        System.out.println("Digite o nome da despesa que deseja excluir.");
        
        //Faz o parâmetro e coloca ele no método excluir.
        String nome = console.readLine();
        if (nome != null) {
            try {
                despesasDAO.excluir(nome);
            } catch (Exception e) {
                System.err.println("O nome que você inseriu não pôde ser lido." + e.getMessage());
                return;
            }
            System.out.println("Opção para excluir despesa " + nome + " executada.");
        }
    }

    public static void pagarDespesas() {
        System.out.println("\n### Pagar Despesa ###");
        SaldoDAO saldoDAO = new SaldoDAO();
        DespesasDAO despesasDAO = new DespesasDAO();

        try {
            // Exibir o saldo atual.
            Saldo saldo = saldoDAO.buscarSaldoAtual();
            if (saldo == null) {
                System.out.println("Saldo não encontrado, atualize-o.");
                return;
            }
            System.out.println("Saldo Atual: " + saldo.getValor());

            try {
                List<Despesas> despesas = despesasDAO.buscarTudo();
                if (despesas.isEmpty()) {
                    System.out.println("Nenhuma despesa catalogada.");
                    return;

                } else {
                    System.out.println("Despesas disponíveis para pagamento:");
                    for (Despesas despesa : despesas) {
                        System.out.println("Nome: " + despesa.getNome() + " | Valor: " + despesa.getValor());
                    }

                    // Solicita ao cliente que escolha uma despesa pelo nome.
                    System.out.print("Digite o nome da despesa que deseja pagar: ");
                    String nomeDespesaEscolhida = console.readLine().trim();
                    Despesas despesaEscolhida = null;

                    // Procurar a despesa pelo nome fornecido.
                    for (Despesas despesa : despesas) {
                        if (despesa.getNome().equalsIgnoreCase(nomeDespesaEscolhida)) {
                            despesaEscolhida = despesa;
                            break;
                        }
                    }

                    if (despesaEscolhida == null) {
                        System.out.println("Despesa não encontrada.");
                        return;
                    }

                    // Atualizar o saldo subtraindo o valor da despesa.
                    saldo.setValor(saldo.getValor() - despesaEscolhida.getValor());
                    saldoDAO.subtrairSaldo(saldo);

                    System.out.println("Novo Saldo Atual: " + saldo.getValor());

                    // Excluir a despesa do banco de dados
                    String despesaEscolhidaString = despesaEscolhida.getNome();
                    despesasDAO.excluir(despesaEscolhidaString);
                    System.out.println("Despesa '" + despesaEscolhida.getNome() + "' foi paga e removida do sistema.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao consultar despesas: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Erro ao processar a operação: " + e.getMessage());
        }
    }

    public static void criarConta() {
        System.out.println("\n### Criar Conta ###");
        SaldoDAO saldoDAO = new SaldoDAO(); 
        
        // Chamando o método para verificar e criar a conta
        try {
            saldoDAO.criarSaldo(); 
        } catch (SQLException e) {
            System.err.println("Erro ao criar ou verificar a conta: " + e.getMessage());
        }
    }

    public static void atualizarConta() {
        System.out.println("\n### Atualizar Saldo ###");
        SaldoDAO saldoDAO = new SaldoDAO();
        System.out.println("Qual o saldo atual da sua conta?");

        // Console para atualizar o saldo atual.
        float saldoAtual = Float.parseFloat(console.readLine());
        try {
            saldoDAO.atualizarSaldo(saldoAtual);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: " + e.getMessage());
        }
    }
}
