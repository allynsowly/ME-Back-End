import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class SistemaBancarioMain {

    private static Conta[] contas = new Conta[100];
    private static int quantidadeContas = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n=== Sistema Bancário ===");
            System.out.println("1. Cadastrar nova conta");
            System.out.println("2. Listar todas as contas");
            System.out.println("3. Buscar conta por número (Não implementado)");
            System.out.println("4. Remover uma conta (Não implementado)");
            System.out.println("5. Depositar em uma conta (Não implementado)");
            System.out.println("6. Sacar de uma conta (Não implementado)");
            System.out.println("7. Encerrar conta (Não implementado)");
            System.out.println("8. Sair do sistema");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarConta();
                    break;
                case 2:
                    listarContas();
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    if (opcao >= 3 && opcao <= 7) {
                        System.out.println("Opção em desenvolvimento");
                    } else {
                        System.out.println("Opção inválida.");
                    }
            }
        } while (opcao != 8);
    }

    // 3.1 Cadastrar nova conta
    private static void cadastrarConta() {
        if (quantidadeContas >= contas.length) {
            System.out.println("Erro: A capacidade máxima do vetor de contas foi atingida.");
            return;
        }

        System.out.println("\n--- Cadastrar Nova Conta ---");
        System.out.print("Número da Conta: ");
        int numero = scanner.nextInt();
        System.out.print("Agência: ");
        int agencia = scanner.nextInt();
        scanner.nextLine();

        // Adicionar titulares
        System.out.print("Quantos titulares terá esta conta? ");
        int qtdTitulares = scanner.nextInt();
        scanner.nextLine();

        Titular[] titulares = new Titular[qtdTitulares];
        for (int i = 0; i < qtdTitulares; i++) {
            System.out.print("Nome do titular " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.print("CPF do titular " + (i + 1) + ": ");
            String cpf = scanner.nextLine();
            titulares[i] = new Titular(nome, cpf);
        }

        System.out.print("Saldo Inicial: ");
        BigDecimal saldo = scanner.nextBigDecimal();
        scanner.nextLine();

        LocalDate dataAbertura = LocalDate.now();
        boolean ativa = true;

        System.out.println("Tipo de Conta (1 - Conta Especial | 2 - Conta Poupança): ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Limite Especial: ");
            BigDecimal limite = scanner.nextBigDecimal();
            scanner.nextLine();
            contas[quantidadeContas] = new ContaEspecial(numero, agencia, titulares, saldo, dataAbertura, ativa, limite);
            quantidadeContas++;
            System.out.println("Conta Especial cadastrada com sucesso!");

        } else if (tipo == 2) {
            System.out.print("Dia de Aniversário (Rendimento): ");
            int dia = scanner.nextInt();
            scanner.nextLine();

            contas[quantidadeContas] = new ContaPoupanca(numero, agencia, titulares, saldo, dataAbertura, ativa, dia);
            quantidadeContas++;
            System.out.println("Conta Poupança cadastrada com sucesso!");

        } else {
            System.out.println("Tipo de conta inválido. Registo cancelado.");
        }
    }

    // 3.2 Listar todas as contas
    private static void listarContas() {
        System.out.println("\n--- Lista de Contas Ativas ---");
        boolean encontrouAtiva = false;

        // Mostra os dados de todas as contas ativas no vetor
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i] != null && contas[i].isAtiva()) {
                encontrouAtiva = true;
                System.out.println("\nNúmero: " + contas[i].getNumeroConta() + " | Agência: " + contas[i].getAgencia());

                System.out.print("Titular(es): ");
                for (Titular t : contas[i].getTitulares()) {
                    System.out.print(t.getNome() + " (CPF: " + t.getCpf() + ") ");
                }
                System.out.println("\nSaldo: " + contas[i].getSaldo());
                System.out.println("Data de Abertura: " + contas[i].getDataAbertura());

                if (contas[i] instanceof ContaEspecial) {
                    ContaEspecial ce = (ContaEspecial) contas[i];
                    System.out.println("Tipo: Conta Especial | Limite: " + ce.getLimiteEspecial());
                } else if (contas[i] instanceof ContaPoupanca) {
                    ContaPoupanca cp = (ContaPoupanca) contas[i];
                    System.out.println("Tipo: Conta Poupança | Dia Aniversário: " + cp.getDiaDeAniversario());
                }
                System.out.println("---------------------------------");
            }
        }

        if (!encontrouAtiva) {
            System.out.println("Não existem contas ativas para apresentar.");
        }
    }
}
