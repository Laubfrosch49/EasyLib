package de.fhdw.easylib;

import de.fhdw.easylib.model.*;
import de.fhdw.easylib.model.test.InitTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class KundeTest {

    @Test
    void addAusleiheTest() {

        InitTests Init = new InitTests();

        Kunde kunde = Init.kunde();

        Datum startDatum = new Datum (23, 5, 2024);
        Datum endDatum = new Datum (24, 5, 2024);

        Exemplar exemplar = new Exemplar("String", "Informatik", new PrintBuch("Clean Code", "978-0132350884", 454));
        List<IExemplar> exemplarList = new ArrayList<>();
        exemplarList.add(exemplar);

        Ausleihe ausleihe = new Ausleihe(startDatum, endDatum, AusleiheStatus.normal, kunde, exemplarList);

        assertThrows(IllegalCallerException.class, ()-> {kunde.addAusleihe(ausleihe);});
    }

    @Test
    void removeAusleiheTest() {

        InitTests Init = new InitTests();

        Kunde kunde = Init.kunde();
        Ausleihe ausleihe = Init.ausleihe(kunde,2);

        assertThrows(IllegalCallerException.class, ()-> {kunde.removeAusleihe(ausleihe);});
    }

    @Test
    void getNameTest() {

        String name = "Tristan";
        Kunde kunde = new Kunde (name, UUID.randomUUID().toString(), new ArrayList<IAusleihe>());

        assertEquals (name, kunde.getName());
    }

    @Test
    void getIDTest() {

        String id = UUID.randomUUID().toString();
        Kunde kunde = new Kunde ("Tristan", id, new ArrayList<IAusleihe>());

        assertEquals (id, kunde.getID());
    }
}