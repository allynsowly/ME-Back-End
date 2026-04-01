import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Conta {
    protected int numeroConta;
    protected int agencia;
    protected Titular[] titulares; 
    protected BigDecimal saldo;
    protected LocalDate dataAbertura;
    protected boolean ativa;

    public Conta(int numeroConta, int agencia, Titular[] titulares, BigDecimal saldo, LocalDate dataAbertura, boolean ativa) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.titulares = titulares;
        this.saldo = saldo;
        this.dataAbertura = dataAbertura;
        this.ativa = ativa;
    }

    public int getNumeroConta() { return numeroConta; }
    public void setNumeroConta(int numeroConta) { this.numeroConta = numeroConta; }

    public int getAgencia() { return agencia; }
    public void setAgencia(int agencia) { this.agencia = agencia; }

    public Titular[] getTitulares() { return titulares; }
    public void setTitulares(Titular[] titulares) { this.titulares = titulares; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
}
