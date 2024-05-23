package de.fhdw.easylib.model;

import java.util.List;

public interface IAusleihe {

    Datum getStartDatum();

    Datum getEndDatum();

    AusleiheStatus getAusleiheStatus();

    IKunde getKunde();

    List<IExemplar> getAusgelieheneExemplare();

    void verlängern(Datum neuesEndDatum);

    void exemplarHinzufügen(IExemplar exemplar);
}
