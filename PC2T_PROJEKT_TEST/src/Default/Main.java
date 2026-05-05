package Default;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Databaze db = new Databaze();
    static boolean pouzitSQL = false;

    public static void main(String[] args) {

        System.out.print("Používat SQL? (ano/ne): ");
        String odp = sc.nextLine();

        if (odp.equalsIgnoreCase("ano")) {
            DatabaseSQL.init();
            pouzitSQL = true;
            db.nastavData(DatabaseSQL.nacti());
        }

        int volba;

        do {
            menu();
            volba = nactiInt("Zadej volbu: ");

            switch (volba) {
                case 1 -> pridatZamestnance();
                case 2 -> pridatSpolupraci();
                case 3 -> smazat();
                case 4 -> vyhledat();
                case 5 -> db.vypis();
                case 6 -> db.vypisPodleTypu();
                case 7 -> db.statistika();
                case 0 -> System.out.println("Konec");
                default -> System.out.println("Neplatná volba");
            }

            if (volba != 0) pauza();

        } while (volba != 0);
    }

    static void menu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Přidat zaměstnance");
        System.out.println("2 - Přidat spolupráci");
        System.out.println("3 - Smazat");
        System.out.println("4 - Vyhledat (detail + analýza)");
        System.out.println("5 - Výpis");
        System.out.println("6 - Výpis podle skupin (A-Z)");
        System.out.println("7 - Statistika firmy");
        System.out.println("0 - Konec");
    }

    static void pridatZamestnance() {

        System.out.println("1 - Datový analytik");
        System.out.println("2 - Bezpečnostní specialista");

        int typVolba = nactiInt("Typ: ");

        System.out.print("Jméno: ");
        String j = sc.nextLine();

        System.out.print("Příjmení: ");
        String p = sc.nextLine();

        int rok = nactiInt("Rok: ");

        Zamestnanec z;

        if (typVolba == 1)
            z = new DatovyAnalytik(j, p, rok);
        else
            z = new BezpecnostniSpecialista(j, p, rok);

        db.pridej(z);

        if (pouzitSQL)
            DatabaseSQL.pridej(j, p, rok, z.getTyp());

        System.out.println("Přidán.");
    }

    static void pridatSpolupraci() {
        int id1 = nactiInt("ID 1: ");
        int id2 = nactiInt("ID 2: ");

        Zamestnanec z1 = db.najdi(id1);
        Zamestnanec z2 = db.najdi(id2);

        System.out.print("Kvalita (špatná/průměrná/dobrá): ");
        String k = sc.nextLine();

        if (z1 != null && z2 != null) {
            z1.pridejSpolupraci(z2, k);
            z2.pridejSpolupraci(z1, k);

            // 🔥 TADY JE TA JEDINÁ NOVÁ VĚC
            if (pouzitSQL)
                DatabaseSQL.pridejSpolupraci(id1, id2, k);

            System.out.println("OK");
        } else {
            System.out.println("Nenalezen");
        }
    }

    static void smazat() {
        int id = nactiInt("ID: ");

        db.smaz(id);

        if (pouzitSQL)
            DatabaseSQL.smaz(id);

        System.out.println("Smazáno.");
    }

    static void vyhledat() {

        int id = nactiInt("ID: ");
        Zamestnanec z = db.najdi(id);

        if (z == null) {
            System.out.println("Nenalezen");
            return;
        }

        System.out.println("\n=== ZAMĚSTNANEC ===");
        System.out.println(z);

        int spatna = 0;
        int prumerna = 0;
        int dobra = 0;

        System.out.println("\n=== SPOLUPRÁCE ===");

        for (Spoluprace s : z.getSpoluprace()) {
            System.out.println("- " + s.getKolega().getCeleJmeno() + " (" + s.getKvalita() + ")");

            switch (s.getKvalita()) {
                case "špatná" -> spatna++;
                case "průměrná" -> prumerna++;
                case "dobrá" -> dobra++;
            }
        }

        System.out.println("\n=== STATISTIKA ===");
        System.out.println("Dobrá: " + dobra);
        System.out.println("Průměrná: " + prumerna);
        System.out.println("Špatná: " + spatna);

        System.out.println("\n=== ANALÝZA ===");
        z.provedAnalyzu();
    }

    static int nactiInt(String t) {
        while (true) {
            try {
                System.out.print(t);
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Zadej číslo");
            }
        }
    }

    static void pauza() {
        System.out.println("ENTER...");
        sc.nextLine();
    }
}