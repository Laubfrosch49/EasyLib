package de.fhdw.easylib.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ausleiheverwaltung implements IAusleiheverwaltung {

    public List<IAusleihe> ausleihen = new ArrayList<>();

    @Override
    public IAusleihe exemplarAusleihen(@NotNull List<IExemplar> exemplare, IKunde kunde) {

        if (exemplare.isEmpty()) {
            throw new IllegalArgumentException("Es kann nicht nichts ausgeliehen werden! Bitte scannen Sie alle Bücher über das Lesegerät ein.");
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = currentDate.plusMonths(1);

        Datum startDatum = new Datum(currentDate.getDayOfYear(), currentDate.getMonthValue(), currentDate.getYear());
        Datum endDatum = new Datum(returnDate.getDayOfYear(), returnDate.getMonthValue(), returnDate.getYear());

        Ausleihe ausleihe = new Ausleihe(startDatum, endDatum, AusleiheStatus.normal, (Kunde)kunde, exemplare);
        this.ausleihen.add(ausleihe);

        return ausleihe;
    }

    @Override
    public List<IAusleihe> alleAusleihen() {
        return ausleihen;
    }

    @Override
    public void ausleiheLöschen(@NotNull IAusleihe ausleihe) {

        if (!this.ausleihen.contains(ausleihe)) {
            throw new IllegalArgumentException("Ausleihe konnte nicht gefunden werden bzw. wurde bereits entfernt. ");
        }

        ((Kunde) ausleihe.getKunde()).ausleihen.remove(ausleihe);
        this.ausleihen.remove(ausleihe);

        //garbage collecting ausleihe?
    }

    @Override
    public List<IAusleihe> alleAusleihenVonKunden(IKunde kunde) {
        return ((Kunde)kunde).ausleihen;
    }

    @Override
    public List<IAusleihe> verspäteteAusleihen() {
        ArrayList<IAusleihe> verspäteteAusleihenListe = new ArrayList<>();

        for (IAusleihe currentIAusleihe : this.ausleihen) {
            if (currentIAusleihe.getAusleiheStatus() == AusleiheStatus.verspätet) {
                verspäteteAusleihenListe.add(currentIAusleihe);
            }
        }
        return verspäteteAusleihenListe;
    }

    @Override
    public IAusleihe ausleiheAnKundenÜbertragen(IKunde kunde, IAusleihe ausleihe) {
        ((Ausleihe) ausleihe).anKundenÜbertragen((Kunde) kunde);
        return ausleihe;
    }


}
