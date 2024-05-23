package de.fhdw.easylib;

import de.fhdw.easylib.model.*;
import de.fhdw.easylib.model.test.InitTests;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class InitTest {

    @Test
    void initAusleiheTest() {

        InitTests Init = new InitTests();

        Random rand = new Random();
        int exemplare = rand.nextInt(10);

        Kunde kunde = Init.kunde();
        Ausleihe ausleihe = Init.ausleihe(kunde, exemplare);

        assertEquals(ausleihe.ausgelieheneExemplare.size(), exemplare);
        assertEquals(ausleihe.kunde, kunde);
    }
}