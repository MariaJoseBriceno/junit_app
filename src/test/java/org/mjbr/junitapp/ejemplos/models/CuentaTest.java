package org.mjbr.junitapp.ejemplos.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mjbr.junitapp.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    private Cuenta cuenta;

    private String real;

    @BeforeEach
    void setUp() {
         this.cuenta = new Cuenta("Andres", new BigDecimal("10000.12345"));
         this.real = cuenta.getPersona();

    }

    @Test
    @DisplayName("Nombre de cuenta correcto")
    void testNombreCuentaOk() {
        String esperado = "Andres";
        assertAll(
                () ->  assertEquals(esperado, real),
                () -> assertNotNull(real),
                () -> assertTrue(real.equals(esperado))); // Otra forma de probar assertEquals
    }

    @Test
    @DisplayName("Nombre de cuenta diferente")
    void testNombreCuentaNoOk() {
        String esperado = "Juan";
        assertAll(
                () -> assertNotEquals(esperado, real),
                () -> assertNotNull(real, () -> "El nombre de la cuenta es un valor nulo"),
                () -> assertFalse(real.equals(esperado)) // otra forma de probar
        );
    }

    @Test
    @DisplayName("Nombre de cuenta no asignado")
    void testNombreCuentaVacio() {
        Cuenta cuenta1 = new Cuenta("", new BigDecimal("10000.12345"));
        String real1 = cuenta1.getPersona();
        System.out.println(real1);
        String esperado = "";
        assertAll(
                () -> assertEquals(esperado, real1),
                () -> assertNotNull(real1),
                () -> assertTrue(real1.equals(esperado))
    );
    }

    @Test
    @DisplayName("Chequeo de saldo mayor que cero")
    void testSaldoCuenta() {
        BigDecimal esperado = new BigDecimal("10000.12345");
        assertAll(
                () -> assertNotNull(cuenta.getSaldo()),
                () -> assertEquals(esperado.doubleValue(), cuenta.getSaldo().doubleValue()),
                () -> assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0), // el saldo tiene que ser  mayor que cero
                () -> assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0)
        );
    }

    @Test
    @DisplayName("Chequeo de saldo igual a cero")
    void testSaldoCuentaVacío() {
        Cuenta cuenta1 = new Cuenta("", new BigDecimal("0"));
        BigDecimal esperado = new BigDecimal("0");
        assertAll(
                () -> assertNotNull(cuenta1.getSaldo()),
                () -> assertEquals(esperado.doubleValue(), cuenta1.getSaldo().doubleValue())
        );
    }


    @Test
    @DisplayName("Referencia a cuenta")
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta1, cuenta2);
    }

    @Test
    @DisplayName("Descuento en cuenta o debito cuenta")
    void testDebitoCuenta() {
        Cuenta cuenta1 = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta1.debito(new BigDecimal(100));
        assertAll (
                () -> assertNotNull(cuenta1.getSaldo()),
                () -> assertEquals(900,cuenta1.getSaldo().intValue()),
                () -> assertEquals("900.12345",cuenta1.getSaldo().toPlainString())
        );
    }

    @Test
    @DisplayName("Ingreso de dinero a cuenta o crédito cuenta")
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertAll (
                () -> assertNotNull(cuenta.getSaldo()),
                () -> assertEquals(1100, cuenta.getSaldo().intValue()),
                () -> assertEquals("1100.12345", cuenta.getSaldo().toPlainString())
        );

    }

    @Test
    @DisplayName("Probando la excepción cuando no hay suficiente dinero")
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }

    @Test
    @DisplayName("Transferencia de dinero entre cuentas")
    void transferirDineroCuentas() {
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }

    @Test
    @DisplayName("Relación entre cuentas")
    void  testRelacionCuentas() {
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
        assertEquals(2, banco.getCuentas().size());

        assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());
        assertEquals("Andres", banco.getCuentas().stream().filter(
                c -> c.getPersona().equals("Andres")).findFirst().get().getPersona());

        assertTrue(banco.getCuentas().stream().filter( c ->
                c.getPersona().equals("Andres")).findFirst().isPresent());

        // la línea 99 se puede reemplazar con anyMatch
        assertTrue(banco.getCuentas().stream().anyMatch(c ->
                c.getPersona().equals("Andres")));
    }


    @Test
    @DisplayName("Relacion entre cuentas con funciones anónimas")
    void  testRelacionCuentasConFuncionesAnonimas() {
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal("500"));

        assertAll(() -> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString()),
                () -> assertEquals("1000.8989", cuenta2.getSaldo().toPlainString()),
                () -> assertEquals("3000", cuenta1.getSaldo().toPlainString()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("Banco del Estado", cuenta1.getBanco().getNombre()),
                () -> assertEquals("Andres", banco.getCuentas().stream().filter(
                        c -> c.getPersona().equals("Andres")).findFirst().get().getPersona()),
                () -> assertTrue(banco.getCuentas().stream().filter( c ->
                        c.getPersona().equals("Andres")).findFirst().isPresent()),
                () -> // la línea 126 se puede reemplazar con anyMatch
                        assertTrue(banco.getCuentas().stream().anyMatch(c ->
                                c.getPersona().equals("Andres")))
                );

    }

}