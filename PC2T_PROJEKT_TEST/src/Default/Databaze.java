package Default;
import java.util.ArrayList;
import java.util.Comparator;

public class Databaze {

    private ArrayList<Zamestnanec> list = new ArrayList<>();

    public void pridej(Zamestnanec z) {
        list.add(z);
    }

    public void nastavData(ArrayList<Zamestnanec> nova) {
        list = nova;
    }

    public Zamestnanec najdi(int id) {
        for (Zamestnanec z : list)
            if (z.getId() == id)
                return z;
        return null;
    }

    public void smaz(int id) {
        Zamestnanec z = najdi(id);
        if (z != null) list.remove(z);
    }

    public void vypis() {
        for (Zamestnanec z : list)
            System.out.println(z);
    }

    // 
    public void vypisPodleTypu() {

        System.out.println("\n=== DATOVÍ ANALYTICI ===");

        list.stream()
            .filter(z -> z.getTyp().equals("Datový analytik"))
            .sorted(Comparator.comparing(z -> z.prijmeni))
            .forEach(System.out::println);

        System.out.println("\n=== BEZPEČNOSTNÍ SPECIALISTÉ ===");

        list.stream()
            .filter(z -> z.getTyp().equals("Bezpečnostní specialista"))
            .sorted(Comparator.comparing(z -> z.prijmeni))
            .forEach(System.out::println);
    }

    public void statistika() {

        int spatna = 0;
        int prumerna = 0;
        int dobra = 0;

        Zamestnanec maxZam = null;
        int max = 0;

        for (Zamestnanec z : list) {

            int pocet = z.getSpoluprace().size();

            if (pocet > max) {
                max = pocet;
                maxZam = z;
            }

            for (Spoluprace s : z.getSpoluprace()) {
                switch (s.getKvalita()) {
                    case "špatná" -> spatna++;
                    case "průměrná" -> prumerna++;
                    case "dobrá" -> dobra++;
                }
            }
        }

        System.out.println("\n=== STATISTIKA FIRMY ===");

        if (dobra >= prumerna && dobra >= spatna)
            System.out.println("Převažující kvalita: DOBRÁ");
        else if (prumerna >= dobra && prumerna >= spatna)
            System.out.println("Převažující kvalita: PRŮMĚRNÁ");
        else
            System.out.println("Převažující kvalita: ŠPATNÁ");

        if (maxZam != null)
            System.out.println("Nejvíce spoluprací má: " + maxZam.getCeleJmeno() + " (" + max + ")");
    }
}
