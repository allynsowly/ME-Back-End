import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaPoupanca extends Conta {
    private int diaDeAniversario;

    public ContaPoupanca(int numeroConta, int agencia, Titular[] titulares, BigDecimal saldo, LocalDate dataAbertura, boolean ativa, int diaDeAniversario) {
        super(numeroConta, agencia, titulares, saldo, dataAbertura, ativa);
        this.diaDeAniversario = diaDeAniversario;
    }

    public int getDiaDeAniversario() { return diaDeAniversario; }
    public void setDiaDeAniversario(int diaDeAniversario) { this.diaDeAniversario = diaDeAniversario; }
}
