package com.example.amarildo.masterchef;

/**
 * Created by amarildo on 17-12-27.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {

    public static HashMap<String, List<String>> getData() {

        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> tipo = new ArrayList<String>();
        tipo.add("BOX DI PROVA");
        tipo.add("ABBONAMENTO");

        /*List<String> nrDiRicette = new ArrayList<String>();
        nrDiRicette.add("RESET");
        nrDiRicette.add("1 RICETTA");
        nrDiRicette.add("2 RICETTE");
        nrDiRicette.add("3 RICETTE");
        nrDiRicette.add("4 RICETTE");
        nrDiRicette.add("5 RICETTE");*/

        /*List<String> nrDiPersone = new ArrayList<String>();
        nrDiPersone.add("RESET");
        nrDiPersone.add("2 PERSONE");
        nrDiPersone.add("4 PERSONE");
        nrDiPersone.add("6 PERSONE");
        */

        expandableListDetail.put("TIPO DI BOX", tipo);
        //expandableListDetail.put("NUMERO DI RICETTE", nrDiRicette);
        // expandableListDetail.put("NUMERO DI PERSONE", nrDiPersone);

        return expandableListDetail;
    }
}
