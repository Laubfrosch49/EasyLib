package de.fhdw.easylib.model;

public class PrintBuch extends Buch {

    public Integer seitenzahl;

    public PrintBuch(String title, String isbn, Integer seitenzahl) {
        super(title, isbn);
        this.seitenzahl = seitenzahl;
    }
}
