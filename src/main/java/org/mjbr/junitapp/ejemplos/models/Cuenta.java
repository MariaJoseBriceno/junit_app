package org.mjbr.junitapp.ejemplos.models;

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

    @Override
    public boolean equals(Object obj) {
        if (obj ==null)
            return false;
        Cuenta c = (Cuenta) obj;

        
        return super.equals(obj);
    }
}
