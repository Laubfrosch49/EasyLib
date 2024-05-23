package de.fhdw.easylib.model;

import java.util.List;

public class Kunde implements IKunde {

    public String name;
    public String id;

    public List<IAusleihe> ausleihen;

    public Kunde (String name, String id, List<IAusleihe> ausleihen) {
        this.name = name;
        this.id = id;
        this.ausleihen = ausleihen;
    }

    /**
     * @throws IllegalCallerException <b>Implementierung nicht zufriedenstellend gemäß Klassendiagramm möglich,
     * da aufgrund der bijektiven Zuordnung keine Ausleihe ohne Kunde existieren kann. Die Ausleihe hat also
     * bereits einen Kunden (this). Somit ist die Methode redundant.</b>
     */
    public void addAusleihe(Ausleihe ausleihe) {
        throw new IllegalCallerException();
    }

    /**
     * @throws IllegalCallerException <b>Implementierung nicht zufriedenstellend gemäß Klassendiagramm möglich,
     * da aufgrund der bijektiven Zuordnung keine Ausleihe ohne Kunde existieren kann.
     * Eine Löschung der Ausleihe ist nicht möglich, da Ausleihe auch in Ausleiheverwaltung existiert
     * und diese nicht explizit angesprochen werden kann</b>
     */
    public void removeAusleihe(Ausleihe ausleihe) {
        throw new IllegalCallerException();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getID() {
        return this.id;
    }
}
