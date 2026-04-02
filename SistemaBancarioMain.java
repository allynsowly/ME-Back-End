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
            System.out.println("3. Buscar conta por número");
            System.out.println("4. Remover uma conta");
            System.out.println("5. Depositar em uma conta");
            System.out.println("6. Sacar de uma conta");
            System.out.println("7. Encerrar conta");
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
                case 3:
                    buscarConta();
                    break;
                case 4:
                    removerConta();
                    break;
                case 5:
                    depositarEmConta();
                    break;
                case 6:
                    sacarDeConta();
                    break;
                case 7:
                    encerrarConta();
                    break;
                case 8:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 8);
    }

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

        if (buscarContaPorNumero(numero) != null) {
            System.out.println("Já existe uma conta com esse número.");
            return;
        }

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
            System.out.println("Tipo de conta inválido. Registro cancelado.");
        }
    }

    private static void listarContas() {
        System.out.println("\n--- Lista de Contas Ativas ---");
        boolean encontrouAtiva = false;

        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i] != null && contas[i].isAtiva()) {
                encontrouAtiva = true;
                exibirConta(contas[i]);
                System.out.println("---------------------------------");
            }
        }

        if (!encontrouAtiva) {
            System.out.println("Não existem contas ativas para apresentar.");
        }
    }

    private static void buscarConta() {
        System.out.print("\nDigite o número da conta que deseja buscar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = buscarContaPorNumero(numero);

        if (conta != null) {
            System.out.println("\n--- Conta encontrada ---");
            exibirConta(conta);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    private static void removerConta() {
        System.out.print("\nDigite o número da conta que deseja remover: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i] != null && contas[i].getNumeroConta() == numero) {
                if (contas[i].getSaldo().compareTo(BigDecimal.ZERO) == 0) {
                    for (int j = i; j < quantidadeContas - 1; j++) {
                        contas[j] = contas[j + 1];
                    }
                    contas[quantidadeContas - 1] = null;
                    quantidadeContas--;
                    System.out.println("Conta removida com sucesso.");
                } else {
                    System.out.println("Não foi possível remover. A conta precisa estar com saldo zero.");
                }
                return;
            }
        }

        System.out.println("Conta não encontrada.");
    }

    private static void depositarEmConta() {
        System.out.print("\nDigite o número da conta para depósito: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (!conta.isAtiva()) {
            System.out.println("A conta está inativa.");
            return;
        }

        System.out.print("Valor do depósito: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor inválido.");
            return;
        }

        conta.setSaldo(conta.getSaldo().add(valor));
        System.out.println("Depósito realizado com sucesso. Saldo atual: " + conta.getSaldo());
    }

    private static void sacarDeConta() {
        System.out.print("\nDigite o número da conta para saque: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (!conta.isAtiva()) {
            System.out.println("A conta está inativa.");
            return;
        }

        System.out.print("Valor do saque: ");
        BigDecimal valor = scanner.nextBigDecimal();
        scanner.nextLine();

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Valor inválido.");
            return;
        }

        if (conta.getSaldo().compareTo(valor) < 0) {
            System.out.println("Saldo insuficiente.");
            return;
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        System.out.println("Saque realizado com sucesso. Saldo atual: " + conta.getSaldo());
    }

    private static void encerrarConta() {
        System.out.print("\nDigite o número da conta que deseja encerrar: ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        Conta conta = buscarContaPorNumero(numero);

        if (conta == null) {
            System.out.println("Conta não encontrada.");
            return;
        }

        if (conta.getSaldo().compareTo(BigDecimal.ZERO) != 0) {
            System.out.println("A conta só pode ser encerrada com saldo zero.");
            return;
        }

        if (!conta.isAtiva()) {
            System.out.println("A conta já está inativa.");
            return;
        }

        conta.setAtiva(false);
        System.out.println("Conta encerrada com sucesso.");
    }

    private static Conta buscarContaPorNumero(int numero) {
        for (int i = 0; i < quantidadeContas; i++) {
            if (contas[i] != null && contas[i].getNumeroConta() == numero) {
                return contas[i];
            }
        }
        return null;
    }

    private static void exibirConta(Conta conta) {
        System.out.println("Número: " + conta.getNumeroConta());
        System.out.println("Agência: " + conta.getAgencia());

        System.out.print("Titular(es): ");
        for (Titular t : conta.getTitulares()) {
            System.out.print(t.getNome() + " (CPF: " + t.getCpf() + ") ");
        }

        System.out.println("\nSaldo: " + conta.getSaldo());
        System.out.println("Data de Abertura: " + conta.getDataAbertura());
        System.out.println("Ativa: " + (conta.isAtiva() ? "Sim" : "Não"));

        if (conta instanceof ContaEspecial) {
            ContaEspecial ce = (ContaEspecial) conta;
            System.out.println("Tipo: Conta Especial | Limite: " + ce.getLimiteEspecial());
        } else if (conta instanceof ContaPoupanca) {
            ContaPoupanca cp = (ContaPoupanca) conta;
            System.out.println("Tipo: Conta Poupança | Dia Aniversário: " + cp.getDiaDeAniversario());
        }
    }
}