public abstract class Funcionario {
    protected String nome;
    protected double salario;

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public abstract double calcularSalarioFinal();

    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Salário Base: " + salario);
    }
}
