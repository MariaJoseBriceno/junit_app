package org.mjbr.junitapp.ejemplos.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("10000.12345"));
        //cuenta.setPersona("Andres");
        String esperado = "Andres";
        String real = cuenta.getPersona();
        assertEquals(esperado, real);
        assertTrue(real.equals("Andres"));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("10000.12345"));
        assertEquals(10000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0); // el saldo tiene que ser  mayor que cero
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }


    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("John Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta, cuenta2);

    }
}