package de.fhdw.easylib.model;

public class AudioBuch extends Buch {

    public int spieldauer;

    public AudioBuch (String title, String isbn, int spieldauer) {
        super(title, isbn);
        this.spieldauer = spieldauer;
    }
}
