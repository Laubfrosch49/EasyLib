package de.fhdw.easylib;

import de.fhdw.easylib.model.*;
import de.fhdw.easylib.model.test.InitTests;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AusleiheTest {

    @Test
    void anKundenÜbertragenTest() {

        InitTests Init = new InitTests();

        Kunde kunde1 = new Kunde ("Kunde1", "1", new ArrayList<IAusleihe>());
        Kunde kunde2 = new Kunde ("Kunde2", "2", new ArrayList<IAusleihe>());

        Ausleihe ausleihe1 = Init.ausleihe(kunde1, 4);
        Ausleihe ausleihe2 = Init.ausleihe(kunde2, 1);

        assertTrue(kunde1.ausleihen.contains(ausleihe1));
        assertFalse(kunde1.ausleihen.contains(ausleihe2));
        assertTrue(kunde2.ausleihen.contains(ausleihe2));
        assertFalse(kunde2.ausleihen.contains(ausleihe1));

        assertEquals(kunde1, ausleihe1.getKunde());
        assertEquals(kunde2, ausleihe2.getKunde());

        ausleihe1.anKundenÜbertragen(kunde2);

        assertFalse(kunde1.ausleihen.contains(ausleihe1));
        assertFalse(kunde1.ausleihen.contains(ausleihe2));
        assertTrue(kunde2.ausleihen.contains(ausleihe1));
        assertTrue(kunde2.ausleihen.contains(ausleihe2));

        assertEquals(kunde2, ausleihe1.getKunde());
        assertEquals(kunde2, ausleihe2.getKunde());
    }

    @Test
    void setKundeTest() {

        InitTests Init = new InitTests();

        Kunde kunde1 = new Kunde ("Kunde1", "1", new ArrayList<IAusleihe>());
        Kunde kunde2 = new Kunde ("Kunde2", "2", new ArrayList<IAusleihe>());

        Ausleihe ausleihe = Init.ausleihe(kunde1, 2);

        assertEquals(ausleihe.kunde, kunde1);
        assertTrue(kunde1.ausleihen.contains(ausleihe));

        assertThrows(IllegalCallerException.class, ()-> {ausleihe.setKunde(kunde2);});
    }

    @Test
    void getStartDatumTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Datum datum1 = new Datum (1, 1, 1);
        Datum datum2 = new Datum (2, 2, 2);

        List<IExemplar> liste = new ArrayList<>();
        Ausleihe ausleihe1 = new Ausleihe(datum1, datum2, AusleiheStatus.normal, kunde, liste);

        assertEquals(datum1, ausleihe1.getStartDatum());
    }

    @Test
    void getEndDatumTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Datum datum1 = new Datum (1, 1, 1);
        Datum datum2 = new Datum (2, 2, 2);

        List<IExemplar> liste = new ArrayList<>();
        Ausleihe ausleihe1 = new Ausleihe(datum1, datum2, AusleiheStatus.normal, kunde, liste);

        assertEquals(datum2, ausleihe1.getEndDatum());
    }

    @Test
    void getAusleiheStatusTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Ausleihe ausleihe = Init.ausleihe(kunde, 3);

        assertEquals(AusleiheStatus.normal, ausleihe.getAusleiheStatus());
    }

    @Test
    void getKundeTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Ausleihe ausleihe = Init.ausleihe(kunde, 1);

        assertEquals(kunde, ausleihe.getKunde());
    }

    @Test
    void getAusgelieheneExemplareTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();

        Datum startDatum = new Datum (23, 5, 2024);
        Datum endDatum = new Datum (24, 5, 2024);

        List<IExemplar> exemplare = new ArrayList<>();

        Ausleihe ausleihe = new Ausleihe(startDatum, endDatum, AusleiheStatus.normal, kunde, exemplare);

        Buch Buch1 = new PrintBuch("Laubfrosch", "123-0123456789", 49);
        Buch Buch2 = new PrintBuch("Die Antwort auf (fast) alles", "546-1536489512", 42);
        Buch Buch3 = new PrintBuch("Langsam gehen mir die Ideen aus", "016-1862030230", 69);
        Exemplar exemplar1 = new Exemplar ("1", "A-1", Buch1);
        Exemplar exemplar2 = new Exemplar ("2", "B-2", Buch2);
        Exemplar exemplar3 = new Exemplar ("3", "C-3", Buch3);

        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar1));
        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar2));
        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar3));

        ausleihe.exemplarHinzufügen(exemplar1);
        assertTrue(ausleihe.getAusgelieheneExemplare().contains(exemplar1));
        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar2));
        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar3));

        ausleihe.exemplarHinzufügen(exemplar2);
        ausleihe.exemplarHinzufügen(exemplar3);
        assertTrue(ausleihe.getAusgelieheneExemplare().contains(exemplar1));
        assertTrue(ausleihe.getAusgelieheneExemplare().contains(exemplar2));
        assertTrue(ausleihe.getAusgelieheneExemplare().contains(exemplar3));
    }

    @Test
    void verlängernTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();
        Ausleihe ausleihe = Init.ausleihe(kunde, 9);

        Datum newEndDatum = new Datum (1, 6, 2024);

        assertNotEquals(newEndDatum, ausleihe.getEndDatum());

        ausleihe.verlängern(newEndDatum);

        assertEquals(newEndDatum, ausleihe.getEndDatum());
    }

    @Test
    void exemplarHinzufügenTest() {

        InitTests Init = new InitTests();
        Kunde kunde = Init.kunde();
        Ausleihe ausleihe = Init.ausleihe(kunde, 4);

        Buch Buch = new AudioBuch("Zwergmaus", "321-0123456789", 49);
        Exemplar exemplar = new Exemplar ("z-1", "wald-zw", Buch);

        assertEquals(4, ausleihe.getAusgelieheneExemplare().size());
        assertFalse(ausleihe.getAusgelieheneExemplare().contains(exemplar));

        ausleihe.exemplarHinzufügen(exemplar);
        assertEquals(5, ausleihe.getAusgelieheneExemplare().size());
        assertTrue(ausleihe.getAusgelieheneExemplare().contains(exemplar));
    }
}