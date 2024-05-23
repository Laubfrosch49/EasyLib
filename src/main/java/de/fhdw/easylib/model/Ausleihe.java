package de.fhdw.easylib.model;

import java.util.List;

public class Ausleihe implements IAusleihe {

    public Datum startDatum;
    public Datum endDatum;
    public AusleiheStatus status;
    public Kunde kunde;
    public List<IExemplar> ausgelieheneExemplare;

    public Ausleihe (Datum startDatum, Datum endDatum, AusleiheStatus status, Kunde kunde, List<IExemplar> ausgelieheneExemplare){
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.status = status;
        this.setKunde(kunde);
        this.ausgelieheneExemplare = ausgelieheneExemplare;
    }

    /**
     * Überträgt eine Ausleihe von einem Kunden auf einen anderen.
     *
     * @param neuerKunde Der jeweilige Kunde, zu dem die Ausleihe übertragen werden soll.
     */
    public void anKundenÜbertragen(Kunde neuerKunde) {

        if (this.kunde == null) { // Redundanter check, da bijektiv und theoretisch nie null.
            throw new IllegalCallerException("Die Ausleihe kann nicht übertragen werden, da momentan es momentan keinen Kunden gibt. " +
                    "Verwende stattdessen setKunde()");
        }

        this.kunde.ausleihen.remove(this); // Da Ausleihen von Kunden public. #InformationHiding
        neuerKunde.ausleihen.add(this); // Da Ausleihen von Kunden public. #InformationHiding

        this.kunde = neuerKunde; // Wird theoretisch schon von addAusleihe() behandelt (da bijektiv).
    }

    /**
     * Setzt für eine Ausleihe einen Kunden. (Wird schon durch den Konstruktor aufgerufen.
     * @param kunde Der jeweilige Kunde, dem die Ausleihe zugewiesen werden soll.
     */
    public void setKunde(Kunde kunde) {

        if (this.kunde != null) {
            throw new IllegalCallerException("Die Ausleihe kann nicht gesetzt werden, da bereits ein Kunde gesetzt ist. " +
                    "Verwende stattdessen anKundenÜbertragen().");
        }

        if (!kunde.ausleihen.contains(this)) {
            kunde.ausleihen.add(this); // Da Ausleihen von Kunden public. #InformationHiding
        }
        this.kunde = kunde;
    }

    @Override
    public Datum getStartDatum() {
        return this.startDatum;
    }

    @Override
    public Datum getEndDatum() {
        return this.endDatum;
    }

    @Override
    public AusleiheStatus getAusleiheStatus() {
        return this.status;
    }

    @Override
    public Kunde getKunde() {
        return this.kunde;
    }

    @Override
    public List<IExemplar> getAusgelieheneExemplare() {
        return this.ausgelieheneExemplare;
    }

    @Override
    public void verlängern(Datum neuesEndDatum) {
        this.endDatum = neuesEndDatum;
    }

    @Override
    public void exemplarHinzufügen(IExemplar exemplar) {
        this.ausgelieheneExemplare.add(exemplar);
    }

}
