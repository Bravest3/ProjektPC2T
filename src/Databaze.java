import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.random.Random;

public class Databaze {

    public HashMap<Integer, ZamestnanecBase> seznamZamestnancu = new HashMap<>();

    public enum UrovenSpoluprace {
        NIZKA,
        STREDNI,
        VYSOKA
    }

    public HashMap<Integer, HashMap<Integer, UrovenSpoluprace>> spoluprace = new HashMap<>();
    int citac = 1;
    //spoluprace

    public void setSeznamZamestnancu(HashMap<Integer, ZamestnanecBase> seznamZamestnancu) {
        this.seznamZamestnancu = seznamZamestnancu;
    }

    public HashMap<Integer, ZamestnanecBase> getSeznamZamestnancu() {
        return seznamZamestnancu;
    }

    public void setSpoluprace(HashMap<Integer, HashMap<Integer, UrovenSpoluprace>> spoluprace) {
        this.spoluprace = spoluprace;
    }

    public HashMap<Integer, HashMap<Integer, UrovenSpoluprace>> getSpoluprace() {
        return spoluprace;
    }

    public void pridejZamestnance(ZamestnanecBase zamestnanec) {
        while (true) {
            int id = citac, rokNarozeni;
            String jmeno, prijmeni;
            boolean analytik;
            Scanner volbaSc = new Scanner(System.in);
            citac++;
            
            System.out.println("Zadejte křestni jméno:");
            jmeno = volbaSc.next();
            
            System.out.println("Zadejte příjmení:");
            prijmeni = volbaSc.next();
            
            System.out.println("Zadejte rok narození:");
            rokNarozeni = volbaSc.nextInt();

            System.out.println("Zvolte 1 pro bezpečnostního specialistu, 2 pro datového analytika:");
            int volbaZamestnance = volbaSc.nextInt();

            if (volbaZamestnance == 1) {
                seznamZamestnancu.put(id, new BezpectnostniSpecialista(id, rokNarozeni, jmeno, prijmeni));
                System.out.println("Vytvořen bezpečnostní specialista: " + seznamZamestnancu.get(id).getJmeno() + " " + seznamZamestnancu.get(id).getPrijmeni() + "\n ID: " + seznamZamestnancu.get(id).getID());
                break;
            }
            else if (volbaZamestnance == 2) {
                seznamZamestnancu.put(id, new DatovyAnalytik(id, rokNarozeni, jmeno, prijmeni));
                System.out.println("Vytvořen datový analytik: " + jmeno + " " + prijmeni +"\n ID: " + id);
                break;
            }
            else {
                System.out.println("Neplatná volba zaměstnance. Zadejte znovu.");
                continue;
            }

            spoluprace.put(id, new HashMap<>());
        }
    
    
        /* seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1990, "Jan", "Novak"));
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1985, "Petr", "Svoboda"));
        citac++;
        System.out.println("Přidáno 2 zaměstnance do databáze."); */
    } 

    public void inicializace() {
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1990, "Jan", "Novak"));
        spoluprace.put(citac, new HashMap<>());
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1985, "Petr", "Svoboda"));
        spoluprace.put(citac, new HashMap<>());
        citac++;
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1992, "Eva", "Kralova"));
        spoluprace.put(citac, new HashMap<>());
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1988, "Martin", "Dvorak"));
        spoluprace.put(citac, new HashMap<>());
        citac++;
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1995, "Lucie", "Horakova"));
        spoluprace.put(citac, new HashMap<>());

        citac++;
        novaSpoluprace(1, 2, UrovenSpoluprace.VYSOKA);
        novaSpoluprace(1, 3, UrovenSpoluprace.NIZKA);
        novaSpoluprace(2, 4, UrovenSpoluprace.STREDNI);
        novaSpoluprace(3, 5, UrovenSpoluprace.VYSOKA);

        System.out.println("Přidáno 5 zaměstnanců do databáze.");
    }

    public void vypisZamestnance(int id) {
        
        if (seznamZamestnancu.containsKey(id)) {
            ZamestnanecBase zamestnanec = seznamZamestnancu.get(id);
            System.out.println("ID: " + zamestnanec.getID());
            System.out.println("Jméno: " + zamestnanec.getJmeno());
            System.out.println("Příjmení: " + zamestnanec.getPrijmeni());
            System.out.println("Rok narození: " + zamestnanec.getRokNarozeni());
            vypisSpoluprace(id);
        }
        else {
            System.out.println("Zaměstnanec s ID " + id + " nenalezen.");
        }
    }

    public void vypis(boolean analytik) {
        for (Map.Entry<Integer, ZamestnanecBase> entry : seznamZamestnancu.entrySet()) {
            if (analytik && entry.getValue() instanceof DatovyAnalytik) {
                vypisZamestnance(entry.getValue().getID());
            }
            else if (!analytik && entry.getValue() instanceof BezpectnostniSpecialista) {
                vypisZamestnance(entry.getValue().getID());
            }
        }
    }

    public void odeberZamestnance(int id) {
        if (seznamZamestnancu.containsKey(id)) {
            seznamZamestnancu.remove(id);
            spoluprace.remove(id);

            for (HashMap<Integer, UrovenSpoluprace> spolupraceZamestnance : spoluprace.values()) {
                spolupraceZamestnance.remove(id);
            }

            System.out.println("Zaměstnanec s ID " + id + " byl odebrán.");
        }
        else {
            System.out.println("Zaměstnanec s ID " + id + " nenalezen.");
        }
    }

    public void novaSpoluprace(int id1, int id2, UrovenSpoluprace uroven) {
        if (seznamZamestnancu.containsKey(id1) && seznamZamestnancu.containsKey(id2)) {
            spoluprace.get(id1).put(id2, uroven);
            spoluprace.get(id2).put(id1, uroven);
            System.out.println("Spolupráce mezi zaměstnanci " + id1 + " a " + id2 + " byla nastavena na úroveň: " + uroven);
        }
        else {
            System.out.println("Jeden nebo oba zaměstnanci nebyli nalezeni.");
        }
    }

    public void vypisSpoluprace(int id) {
        if (seznamZamestnancu.containsKey(id)) {
            System.out.println("Spolupráce:");
            HashMap<Integer, UrovenSpoluprace> spolupraceZamestnance = spoluprace.get(id);
            for (Map.Entry<Integer, UrovenSpoluprace> entry : spolupraceZamestnance.entrySet()) {
                System.out.println("Spolupráce s" + entry.getKey() + ": " + entry.getValue());
            }
            System.out.println("\n");
        }
        else {
            System.out.println("Zaměstnanec s ID " + id + " nenalezen.");
        }
    }

    public void pocetZamestnancu() {
        System.out.println("Počet zaměstnanců: " + seznamZamestnancu.size());
    }
}
