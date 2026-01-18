import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Sis_Bank {

    private static BigDecimal leerMonto(Scanner input, String texto){
        while (true) {
            System.out.print(texto + " (o C para cancelar): ");
            String token = input.nextLine().trim();
            if (token.isEmpty()) {
                continue;
            }
            if (token.equalsIgnoreCase("c")) {
                return null;
            }
            try {
                BigDecimal monto = new BigDecimal(token).setScale(2, RoundingMode.HALF_UP);
                if (monto.compareTo(BigDecimal.ZERO) <= 0) {
                    System.out.println("CANTIDAD INVALIDA");
                    continue;
                }
                return monto;
            } catch (NumberFormatException e) {
                System.out.println("ENTRADA INVALIDA, INTENTE DE NUEVO");
            }
        }
    }

    public static void main(String[] args){
        BancoOperaciones operaciones = new Operacciones();
        boolean salida = false;
        Scanner eleccion = new Scanner(System.in);

        do {
            System.out.println("\n****** BIENVENIDO A SIS_BANK ******");
            System.out.println("1. CONSULTAR SALDO");
            System.out.println("2. RETIRAR DINERO");
            System.out.println("3. DEPOSITAR DINERO");
            System.out.println("4. MOSTRAR ULTIMOS MOVIMIENTOS");
            System.out.println("5. SALIR");
            System.out.print("POR FAVOR INGRESE SU ELECCION: ");
            String line = eleccion.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("OPCION NO VALIDA, INTENTE DE NUEVO.");
                continue;
            }
            int option;
            try {
                option = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("OPCION NO VALIDA, INTENTE DE NUEVO.");
                continue;
            }

            switch (option) {
                case 1:
                    operaciones.consultarSaldo();
                    break;
                case 2:
                    BigDecimal retiro = leerMonto(eleccion, "INGRESE EL MONTO A RETIRAR");
                    if (retiro != null) operaciones.retirarDinero(retiro);
                    break;
                case 3:
                    BigDecimal deposito = leerMonto(eleccion, "INGRESE EL MONTO A DEPOSITAR");
                    if (deposito != null) operaciones.depositarDinero(deposito);
                    break;
                case 4:
                    operaciones.mostrarMovimientos();
                    break;
                case 5:
                    salida = true;
                    System.out.println("GRACIAS POR USAR SIS_BANK. HASTA LUEGO!");
                    break;
                default:
                    System.out.println("OPCION NO VALIDA, INTENTE DE NUEVO.");
                    break;
            }
        } while(!salida);
        eleccion.close();
    }
}