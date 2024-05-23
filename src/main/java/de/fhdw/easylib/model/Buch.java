package de.fhdw.easylib.model;

public abstract class Buch {

    public String title;
    public String isbn;

    public Buch(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }
}
