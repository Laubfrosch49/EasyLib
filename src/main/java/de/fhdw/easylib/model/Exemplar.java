package de.fhdw.easylib.model;

public class Exemplar implements IExemplar {

    public String id;
    public String regalplatz;
    public Buch buch;

    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getRegalplatz() {
        return this.regalplatz;
    }

    public Exemplar(String id, String regalplatz, Buch buch) {
        this.id = id;
        this.regalplatz = regalplatz;
        this.buch = buch;
    }

    public Buch getBuch() {
        return buch;
    }
}
