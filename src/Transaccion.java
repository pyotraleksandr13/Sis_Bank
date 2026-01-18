import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaccion {
    private final String tipo;
    private final BigDecimal monto;
    private final BigDecimal saldoDespues;

    public Transaccion(String tipo, BigDecimal monto, BigDecimal saldoDespues) {
        this.tipo = tipo;
        this.monto = monto;
        this.saldoDespues = saldoDespues;
    }

    public String getTipo() {
        return tipo;
    }

    public BigDecimal getMonto() {
        return monto.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getSaldoDespues() {
        return saldoDespues.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format("%s: %s | Saldo: %s", tipo,
            monto.setScale(2, RoundingMode.HALF_UP).toPlainString(),
            saldoDespues.setScale(2, RoundingMode.HALF_UP).toPlainString());
    }
}

