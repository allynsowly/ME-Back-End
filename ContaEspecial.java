import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaEspecial extends Conta {
    private BigDecimal limiteEspecial;

    public ContaEspecial(int numeroConta, int agencia, Titular[] titulares, BigDecimal saldo, LocalDate dataAbertura, boolean ativa, BigDecimal limiteEspecial) {
        super(numeroConta, agencia, titulares, saldo, dataAbertura, ativa);
        this.limiteEspecial = limiteEspecial;
    }

    public BigDecimal getLimiteEspecial() { return limiteEspecial; }
    public void setLimiteEspecial(BigDecimal limiteEspecial) { this.limiteEspecial = limiteEspecial; }
}
