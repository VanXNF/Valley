package com.vanxnf.photovalley.utils;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataUtil {

    public static List<String> getImageUri(int startId, int endId) {
        return getImageUri(startId, endId, -1);
    }

    @Nullable
    public static List<String> getImageUri(int startId, int endId, int skipId) {
        if (startId > endId) {
            return null;
        }
        List<String> items = new ArrayList<>();
        String item;
        for (int start = startId; start <= endId; start++) {
            if (start == skipId) {
                continue;
            }
            item = uris[start];
            items.add(item);
        }
        return items;
    }

    public static List<String> getImageUriFromUnsplash(int startId, int endId) {
        return getImageUriFromUnsplash(startId, endId, -1);
    }

    public static List<String> getImageUriFromUnsplash(int startId, int endId, int skipId) {
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

    public static final String[] uris = {

            "https://s1.ax1x.com/2018/04/02/CStfmD.jpg",
            "https://s1.ax1x.com/2018/04/02/CStROO.jpg",
            "https://s1.ax1x.com/2018/04/02/CSt26K.jpg",
            "https://s1.ax1x.com/2018/04/02/CStgl6.jpg",
            "https://s1.ax1x.com/2018/04/02/CStsYR.jpg",
            "https://s1.ax1x.com/2018/04/02/CStrk9.jpg",
            "https://s1.ax1x.com/2018/04/02/CStBTJ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSt0w4.jpg",
            "https://s1.ax1x.com/2018/04/02/CStwmF.jpg",
            "https://s1.ax1x.com/2018/04/02/CStaOU.jpg",
            "https://s1.ax1x.com/2018/04/02/CStUyT.jpg",
            "https://s1.ax1x.com/2018/04/02/CStNlV.jpg",
            "https://s1.ax1x.com/2018/04/02/CSttS0.jpg",
            "https://s1.ax1x.com/2018/04/02/CStJWq.jpg",
            "https://s1.ax1x.com/2018/04/02/CStGYn.jpg",
            "https://s1.ax1x.com/2018/04/02/CSt8Fs.jpg",
            "https://s1.ax1x.com/2018/04/02/CSt1oj.jpg",
            "https://s1.ax1x.com/2018/04/02/CStlwQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CStKOS.jpg",
            "https://s1.ax1x.com/2018/04/02/CStuy8.jpg",
            "https://s1.ax1x.com/2018/04/02/CStnQf.jpg",
            "https://s1.ax1x.com/2018/04/02/CStmSP.jpg",
            "https://s1.ax1x.com/2018/04/02/CStZWt.jpg",
            "https://s1.ax1x.com/2018/04/02/CStVJI.jpg",
            "https://s1.ax1x.com/2018/04/02/CStEFA.jpg",
            "https://s1.ax1x.com/2018/04/02/CStkod.jpg",
            "https://s1.ax1x.com/2018/04/02/CStFdH.jpg",
            "https://s1.ax1x.com/2018/04/02/CStiee.jpg",
            "https://s1.ax1x.com/2018/04/02/CStCLD.jpg",
            "https://s1.ax1x.com/2018/04/02/CSt9sO.jpg",
            "https://s1.ax1x.com/2018/04/02/CStpQK.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYzz6.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYxRx.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYvJ1.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYjiR.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYOo9.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYLdJ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYqZ4.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYHLF.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY7sU.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYTMT.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYIzV.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY5R0.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY4Gq.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYhin.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYWIs.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYRaj.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY2ZQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYcqg.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY6sS.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYyM8.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYrxf.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYDRP.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYBGt.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY0PI.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYdIA.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYaad.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYUVH.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYtqe.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYYrD.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYJKO.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY8xK.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY326.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY18x.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYlP1.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYM5R.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYKa9.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYuVJ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYmb4.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYerF.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYZKU.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYEvT.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYA2V.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYk80.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYFCq.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYP5n.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYCUs.jpg",
            "https://s1.ax1x.com/2018/04/02/CSY9Ej.jpg",
            "https://s1.ax1x.com/2018/04/02/CSYSbQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJzDg.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJxKS.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJjv8.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJXgf.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJO8P.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJLCt.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJb4I.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJHUA.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ7Ed.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJoHH.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ5uD.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJhjO.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJfgK.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJW36.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJR9x.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJg41.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJcNR.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ6E9.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJsHJ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJrB4.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJDuF.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ0jU.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJwcT.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJd3V.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJa90.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJNhq.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJtNn.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJYAs.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJG7j.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ8BQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJ3ng.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJljS.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJQc8.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJK9P.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJnht.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJmtI.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJeAA.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJV7d.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJE0H.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJAne.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJFXD.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJi6O.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJP1K.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJCp6.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJpfx.jpg",
            "https://s1.ax1x.com/2018/04/02/CSJSt1.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGzkR.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGv79.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGj0J.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGXm4.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGLXF.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGq6U.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGblT.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGHpV.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGTf0.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGoYq.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGIkn.jpg",
            "https://s1.ax1x.com/2018/04/02/CSG4Ts.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGhwj.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGfmQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGROg.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGgl8.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGcSf.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGyfP.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGsYt.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGrFI.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGBTA.jpg",
            "https://s1.ax1x.com/2018/04/02/CSG0wd.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGweH.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGaOe.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGUyD.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGNQO.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGlw9.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGQeJ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGKL4.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGuyF.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGnQU.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGezT.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGZWV.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGVJ0.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGEiq.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGkon.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGFds.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGiZj.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGCLQ.jpg",
            "https://s1.ax1x.com/2018/04/02/CSG9sg.jpg",
            "https://s1.ax1x.com/2018/04/02/CSGpQS.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8zz8.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8xRf.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8vJP.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8jit.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8OII.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8LdA.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8qZd.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8HqH.jpg",
            "https://s1.ax1x.com/2018/04/02/CS87se.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8TMD.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8IxO.jpg",
            "https://s1.ax1x.com/2018/04/02/CS85RK.jpg",
            "https://s1.ax1x.com/2018/04/02/CS84G6.jpg",
            "https://s1.ax1x.com/2018/04/02/CS8hPx.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzMDI.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzKKA.jpg",
            "https://s1.ax1x.com/2018/03/30/9vznvd.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzmgH.jpg",
            "https://s1.ax1x.com/2018/03/30/9vze8e.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzZCD.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzE4O.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzAUK.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzkE6.jpg",
            "https://s1.ax1x.com/2018/03/30/9vziHx.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzPD1.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzCuR.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzpv9.jpg",
            "https://s1.ax1x.com/2018/03/30/9vzSgJ.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxz34.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxxCF.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxj4U.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxXNT.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxOEV.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxqH0.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxbBq.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxHun.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxTjs.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxocj.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxI3Q.jpg",
            "https://s1.ax1x.com/2018/03/30/9vx59g.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxh4S.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxfN8.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxWAf.jpg",
            "https://s1.ax1x.com/2018/03/30/9vx2HP.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxgBt.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxcnI.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxyjA.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxscd.jpg",
            "https://s1.ax1x.com/2018/03/30/9vxr1H.jpg",
    };

}