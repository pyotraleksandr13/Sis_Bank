import java.math.BigDecimal;
import java.util.List;

public interface BancoOperaciones {
    void consultarSaldo();
    boolean retirarDinero(BigDecimal retiro);
    boolean depositarDinero(BigDecimal deposito);
    void mostrarMovimientos();
    BigDecimal getSaldo();
    List<Transaccion> getMovimientos();
}
