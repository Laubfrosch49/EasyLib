package de.fhdw.easylib;

import de.fhdw.easylib.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AusleiheverwaltungTest {

    @Test
    void exemplarAusleihenTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();

        List<IExemplar> exemplarList = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, ()-> {ausleiheverwaltung.exemplarAusleihen(exemplarList, kunde);});

        exemplarList.add(new Exemplar("HFI423", "H-B302", new AudioBuch("Objektorientierte Softwaretechnik 1 - B-Übung - Gruppe 4", "0945-1115", 90)));
        exemplarList.add(new Exemplar("HFI423", "H-B211", new AudioBuch("Technische Grundlagen 2 - A-Übung", "1130-1300", 90)));
        exemplarList.add(new Exemplar("HFI423", "H-B113", new AudioBuch("Mathematik 2", "1400-1530", 90)));


        assertEquals(0, kunde.ausleihen.size());
        assertEquals(0, ausleiheverwaltung.ausleihen.size());

        IAusleihe ausleihe;

        for (int i = 1; i < 3; i++) {
            ausleihe = ausleiheverwaltung.exemplarAusleihen(exemplarList, kunde);

            assertEquals(i, kunde.ausleihen.size());
            assertTrue(kunde.ausleihen.contains(ausleihe));
            assertEquals(i, ausleiheverwaltung.ausleihen.size());
            assertTrue(ausleiheverwaltung.ausleihen.contains(ausleihe));
            assertSame(ausleihe.getKunde(), kunde);
        }
    }

    @Test
    void alleAusleihenTest() {

        InitTests Init = new InitTests();

        Kunde kunde1 = Init.kunde();
        Kunde kunde2 = Init.kunde();

        var kundenArray = new Kunde[] {kunde1, kunde2} ;

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();
        Random rand = new Random();

        assertEquals(0, ausleiheverwaltung.alleAusleihen().size());

        int count = 10;
        for (int i = 1; i <= count; i++) {

            List<IExemplar> exemplare = Init.exemplar(rand.nextInt(1,10));
            ausleiheverwaltung.exemplarAusleihen(exemplare, kundenArray[rand.nextInt(kundenArray.length)]);

            assertEquals(i, ausleiheverwaltung.alleAusleihen().size());
        }

        ausleiheverwaltung.ausleiheLöschen(ausleiheverwaltung.alleAusleihen().getFirst());
        assertEquals(count-1, ausleiheverwaltung.alleAusleihen().size());

    }

    @Test
    void ausleiheLöschenTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();
        List<IExemplar> exemplare1 = Init.exemplar(4);
        List<IExemplar> exemplare2 = Init.exemplar(2);

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();

        IAusleihe ausleihe1 = ausleiheverwaltung.exemplarAusleihen(exemplare1, kunde);
        IAusleihe ausleihe2 = ausleiheverwaltung.exemplarAusleihen(exemplare2, kunde);

        assertTrue(ausleiheverwaltung.alleAusleihen().contains(ausleihe1));
        assertTrue(ausleiheverwaltung.alleAusleihen().contains(ausleihe2));

        ausleiheverwaltung.ausleiheLöschen(ausleihe1);

        assertFalse(ausleiheverwaltung.alleAusleihen().contains(ausleihe1));
        assertTrue(ausleiheverwaltung.alleAusleihen().contains(ausleihe2));

        assertThrows(IllegalArgumentException.class, ()-> {ausleiheverwaltung.ausleiheLöschen(ausleihe1);});
    }

    @Test
    void alleAusleihenVonKundenTest() {

        InitTests Init = new InitTests();
        Kunde kunde1 = Init.kunde();
        Kunde kunde2 = Init.kunde();

        List<IExemplar> exemplare1 = Init.exemplar(1);
        List<IExemplar> exemplare2 = Init.exemplar(2);
        List<IExemplar> exemplare3 = Init.exemplar(3);

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();

        IAusleihe ausleihe1 = ausleiheverwaltung.exemplarAusleihen(exemplare1, kunde1);
        IAusleihe ausleihe2 = ausleiheverwaltung.exemplarAusleihen(exemplare2, kunde2);
        IAusleihe ausleihe3 = ausleiheverwaltung.exemplarAusleihen(exemplare3, kunde1);

        assertTrue(ausleiheverwaltung.alleAusleihenVonKunden(kunde1).contains(ausleihe1));
        assertTrue(ausleiheverwaltung.alleAusleihenVonKunden(kunde1).contains(ausleihe3));
        assertTrue(ausleiheverwaltung.alleAusleihenVonKunden(kunde2).contains(ausleihe2));

        assertEquals(2, ausleiheverwaltung.alleAusleihenVonKunden(kunde1).size());
        assertEquals(1, ausleiheverwaltung.alleAusleihenVonKunden(kunde2).size());
    }

    @Test
    void verspäteteAusleihenTest() {

        InitTests Init = new InitTests();
        Kunde kunde1 = Init.kunde();
        Kunde kunde2 = Init.kunde();
        Kunde kunde3 = Init.kunde();

        List<IExemplar> exemplare1 = Init.exemplar(1);
        List<IExemplar> exemplare2 = Init.exemplar(2);
        List<IExemplar> exemplare3 = Init.exemplar(3);
        List<IExemplar> exemplare4 = Init.exemplar(4);

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();

        IAusleihe ausleihe1 = ausleiheverwaltung.exemplarAusleihen(exemplare1, kunde1);
        IAusleihe ausleihe2 = ausleiheverwaltung.exemplarAusleihen(exemplare2, kunde2);
        IAusleihe ausleihe3 = ausleiheverwaltung.exemplarAusleihen(exemplare3, kunde3);
        IAusleihe ausleihe4 = ausleiheverwaltung.exemplarAusleihen(exemplare4, kunde3);

        ((Ausleihe)ausleihe1).status = AusleiheStatus.verspätet;
        ((Ausleihe)ausleihe2).status = AusleiheStatus.verspätet;
        ((Ausleihe)ausleihe3).status = AusleiheStatus.gemahnt;
        ((Ausleihe)ausleihe4).status = AusleiheStatus.verspätet;

        List<IAusleihe> verspäteteAusleihen = ausleiheverwaltung.verspäteteAusleihen();

        assertTrue(verspäteteAusleihen.contains(ausleihe1));
        assertTrue(verspäteteAusleihen.contains(ausleihe2));
        assertTrue(verspäteteAusleihen.contains(ausleihe4));
        assertEquals(3, verspäteteAusleihen.size());
    }

    @Test
    void ausleiheAnKundenÜbertragenTest() {

        InitTests Init = new InitTests();

        Kunde kunde1 = Init.kunde();
        Kunde kunde2 = Init.kunde();

        List<IExemplar> exemplare1 = Init.exemplar(4);
        List<IExemplar> exemplare2 = Init.exemplar(1);

        Ausleiheverwaltung ausleiheverwaltung = new Ausleiheverwaltung();

        IAusleihe ausleihe1 = ausleiheverwaltung.exemplarAusleihen(exemplare1, kunde1);
        IAusleihe ausleihe2 = ausleiheverwaltung.exemplarAusleihen(exemplare2, kunde2);

        assertTrue(kunde1.ausleihen.contains(ausleihe1));
        assertFalse(kunde1.ausleihen.contains(ausleihe2));
        assertTrue(kunde2.ausleihen.contains(ausleihe2));
        assertFalse(kunde2.ausleihen.contains(ausleihe1));

        assertEquals(kunde1, ausleihe1.getKunde());
        assertEquals(kunde2, ausleihe2.getKunde());

        ausleiheverwaltung.ausleiheAnKundenÜbertragen(kunde2, ausleihe1);

        assertFalse(kunde1.ausleihen.contains(ausleihe1));
        assertFalse(kunde1.ausleihen.contains(ausleihe2));
        assertTrue(kunde2.ausleihen.contains(ausleihe1));
        assertTrue(kunde2.ausleihen.contains(ausleihe2));

        assertEquals(kunde2, ausleihe1.getKunde());
        assertEquals(kunde2, ausleihe2.getKunde());
    }
}