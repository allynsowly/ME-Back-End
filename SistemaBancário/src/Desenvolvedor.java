public class Desenvolvedor extends Funcionario implements Bonificavel {

    public Desenvolvedor(String nome, double salario) {
        super(nome, salario);
    }

    @Override
    public double calcularBonus() {
        return salario * 0.10;
    }

    @Override
    public double calcularSalarioFinal() {
        return salario + calcularBonus();
    }
}
