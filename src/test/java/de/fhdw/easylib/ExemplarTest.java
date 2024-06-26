package de.fhdw.easylib;

import org.junit.jupiter.api.Test;
import de.fhdw.easylib.model.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExemplarTest {

    @Test
    void getIdTest() {

        String uniqueID = UUID.randomUUID().toString();

        Exemplar testExemplar = new Exemplar (uniqueID, "FHDW Hannover",
                new PrintBuch ("Das Leben von Tristan", "0703-2001-00", 23));

        assertEquals(uniqueID, testExemplar.getID());
    }

    @Test
    void getRegalplatzTest() {
        Exemplar testExemplar = new Exemplar (UUID.randomUUID().toString(), "FHDW Hannover",
                new PrintBuch ("Das Leben von Tristan II", "0703-2001-01", 23));

        assertEquals ("FHDW Hannover", testExemplar.getRegalplatz());
    }
}