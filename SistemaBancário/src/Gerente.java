public class Gerente extends Funcionario implements Bonificavel{

    public Gerente(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public double calcularBonus() {
        return salario * 0.20;
    }

    @Override
    public double calcularSalarioFinal() {
        return salario + calcularBonus();
    }
}