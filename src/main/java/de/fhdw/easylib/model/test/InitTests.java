package de.fhdw.easylib.model.test;

import de.fhdw.easylib.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class InitTests {

    public Kunde kunde() {
        return new Kunde ("Greenyer", UUID.randomUUID().toString(), new ArrayList<IAusleihe>());
    }

    public Ausleihe ausleihe(Kunde kunde, int exemplare) {

        Datum startDatum = new Datum (23, 5, 2024);
        Datum endDatum = new Datum (24, 5, 2024);

        List<IExemplar> exemplarList = this.exemplar(exemplare);

        return new Ausleihe(startDatum, endDatum, AusleiheStatus.normal, kunde, exemplarList);
    }

    public List<IExemplar> exemplar(int count) {

        Random rand = new Random(0);
        List<IExemplar> exemplarList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            Buch buch = null;

            if (rand.nextInt(2) == 0) {
                buch = new AudioBuch("Vorlesung Greenyer", "000-0000000000", rand.nextInt(75,120));

            } else {
                buch = new PrintBuch("Folien Greenyer", "000-5468532864", rand.nextInt(10,40));

            }

            Exemplar exemplar = new Exemplar(UUID.randomUUID().toString(), "003-045", buch);
            exemplarList.add(exemplar);
        }

        return exemplarList;
    }
}
