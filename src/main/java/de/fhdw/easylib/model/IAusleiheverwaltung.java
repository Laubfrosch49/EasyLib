package de.fhdw.easylib.model;

import java.util.List;

public interface IAusleiheverwaltung {

    IAusleihe exemplarAusleihen(List<IExemplar> exemplar, IKunde kunde);

    List<IAusleihe> alleAusleihen();

    void ausleiheLöschen(IAusleihe ausleihe);

    List<IAusleihe> alleAusleihenVonKunden(IKunde kunde);

    List<IAusleihe> verspäteteAusleihen();

    IAusleihe ausleiheAnKundenÜbertragen(IKunde kunde, IAusleihe ausleihe);
}
