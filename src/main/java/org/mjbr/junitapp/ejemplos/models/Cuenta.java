package org.mjbr.junitapp.ejemplos.models;

import org.mjbr.junitapp.ejemplos.exceptions.DineroInsuficienteException;

import java.math.BigDecimal;

public class Cuenta {

    private String persona;

    private BigDecimal saldo;

    public Cuenta(String persona, BigDecimal saldo) {
        this.saldo = saldo;
        this.persona = persona;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto) {
       BigDecimal nuevoSaldo = this.saldo.subtract(monto);
       if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
           throw new DineroInsuficienteException("Dinero Insuficiente");
       }
       this.saldo = nuevoSaldo;

    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cuenta)) //instanceof: mala practica pero para efectos del curso, se está usando
            return false;
        Cuenta c = (Cuenta) obj;
        if (this.persona == null || this.saldo ==null) {
            return false;
        }
        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }
}
