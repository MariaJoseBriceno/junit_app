package org.mjbr.junitapp.ejemplos.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import java.util.Properties;

public class CondicionalesTest {


    @Test
    @EnabledOnOs(OS.WINDOWS)
     void testSoloWindows() {
        System.out.println(" Test se ejecuta sólo en Windows");
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testSoloLinuxyMac() {
        System.out.println(" Test se ejecuta tanto en Linux como en Mac");
    }

    @Test
    @DisabledOnOs(OS.MAC)
    void testSeDeshabilitaEnMac() {
        System.out.println(" Test deshabilitado en OS Mac");
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void testSoloEnJdk8() {

    }

    @Test
    @EnabledOnJre(JRE.OTHER)
    void testOtroJdk() {

    }

    @Test
    @DisabledOnJre(JRE.JAVA_15)
    void testNoJdk15() {
        System.out.println("Desactivado para JDK 15");
    }

    @Test
    void getProperties() {
        Properties properties = System.getProperties();
        properties.forEach((k,v) -> System.out.println( k + " : " + v));
    }


    @Test
    @EnabledIfSystemProperty(named = "java.runtime.version", matches = "23.0.1")
    void javaVersion() {
    }

    @Test
    @EnabledIfSystemProperty(named = "java.runtime.version", matches = "*.23.*")
    void javaVersion2() {
    }
}
