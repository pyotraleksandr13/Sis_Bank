import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operaciones implements BancoOperaciones {
    private BigDecimal saldo;
    private final List<Transaccion> movimientos = new ArrayList<>();

    public void consultarSaldo(){
        System.out.printf("SU SALDO ES: %s BS%n", saldo.setScale(2, java.math.RoundingMode.HALF_UP).toPlainString());
    }

    public boolean retirarDinero(BigDecimal retiro){
        if (retiro.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("CANTIDAD INVALIDA");
            return false;
        } else if (retiro.compareTo(saldo) > 0) {
            System.out.println("SALDO INSUFICIENTE");
            return false;
        } else {
            saldo = saldo.subtract(retiro);
            System.out.println("RETIRO EXITOSO");
            movimientos.add(new Transaccion("RETIRO", retiro, saldo));
            return true;
        }
    }

    public boolean depositarDinero(BigDecimal deposito){
        if (deposito.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("CANTIDAD INVALIDAD");
            return false;
        } else {
            saldo = saldo.add(deposito);
            System.out.println("DEPOSITO EXITOSO");
            movimientos.add(new Transaccion("DEPOSITO", deposito, saldo));
            return true;
        }
    }

    public void mostrarMovimientos(){
        if (movimientos.isEmpty()){
            System.out.println("NO HAY MOVIMIENTOS PARA MOSTRAR");
            return;
        }
        System.out.println("\n****** ULTIMOS MOVIMIENTOS ******");
        int inicio = Math.max(movimientos.size() - 5, 0);
        for (int i = inicio; i < movimientos.size(); i++){
            System.out.println(movimientos.get(i).toString());
        }
    }

    public BigDecimal getSaldo() {
        return saldo.setScale(2, RoundingMode.HALF_UP);
    }

    public List<Transaccion> getMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }

    public Operaciones() {
        this.saldo = BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_UP);
    }

    public Operaciones(BigDecimal initialSaldo) {
        if (initialSaldo == null || initialSaldo.compareTo(BigDecimal.ZERO) < 0) {
            this.saldo = BigDecimal.valueOf(100.00).setScale(2, RoundingMode.HALF_UP);
        } else {
            this.saldo = initialSaldo.setScale(2, RoundingMode.HALF_UP);
        }
    }
}
