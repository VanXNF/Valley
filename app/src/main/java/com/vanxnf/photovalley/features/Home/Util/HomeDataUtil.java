package com.vanxnf.photovalley.features.Home.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by VanXN on 2018/3/29.
 */

public class HomeDataUtil {


    public static String getRandomName() {
        return names[new Random().nextInt(names.length)];
    }

    public static final String[] names = {"Andrea", "Luca", "Marco", "Francesco", "Matteo",
            "Alessandro", "Davide", "Simone", "Federico", "Lorenzo", "Mattia", "Stefano",
            "Giuseppe", "Riccardo", "Daniele", "Michele", "Alessio", "Antonio", "Giovanni",
            "Nicola", "Gabriele", "Fabio", "Alberto", "Giacomo", "Giulio", "Filippo",
            "Gianluca", "Paolo", "Roberto", "Salvatore", "Emanuele", "Edoardo", "Enrico",
            "Vincenzo", "Nicolò", "Leonardo", "Jacopo", "Manuel", "Mirko", "Tommaso", "Pietro",
            "Luigi", "Giorgio", "Angelo", "Dario", "Valerio", "Domenico", "Claudio", "Alex",
            "Christian", "Giulia", "Chiara", "Francesca", "Federica", "Sara", "Martina",
            "Valentina", "Alessia", "Silvia", "Elisa", "Ilaria", "Eleonora", "Giorgia", "Elena",
            "Laura", "Alice", "Alessandra", "Jessica", "Arianna", "Marta", "Veronica", "Roberta",
            "Anna", "Giada", "Claudia", "Beatrice", "Valeria", "Michela", "Serena", "Camilla",
            "Irene", "Cristina", "Simona", "Maria", "Noemi", "Stefania", "Erika", "Sofia", "Lucia",
            "Vanessa", "Greta", "Debora", "Nicole", "Angela", "Paola", "Caterina", "Monica",
            "Erica", "Lisa", "Gaia"
    };


    public static List<String> getImageUri(int startId, int endId) {
        return getImageUri(startId, endId, -1);
    }

    public static List<String> getImageUri(int startId, int endId, int skipId) {
        List<String> items = new ArrayList<>();
        String uri = new String("https://picsum.photos/800/600/?image=");
        String item;
        for (int i = startId; i <= endId; i++) {
            if (i == skipId) continue;
            item = uri + i;
            items.add(item);
        }
        return items;
    }
}
