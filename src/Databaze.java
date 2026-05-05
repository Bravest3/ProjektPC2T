import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.random.Random;

public class Databaze {

    public HashMap<Integer, ZamestnanecBase> seznamZamestnancu = new HashMap<>();
    int citac = 1;
    //spoluprace

    public void setSeznamZamestnancu(HashMap<Integer, ZamestnanecBase> seznamZamestnancu) {
        this.seznamZamestnancu = seznamZamestnancu;
    }

    public HashMap<Integer, ZamestnanecBase> getSeznamZamestnancu() {
        return seznamZamestnancu;
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
        }
    
    
        /* seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1990, "Jan", "Novak"));
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1985, "Petr", "Svoboda"));
        citac++;
        System.out.println("Přidáno 2 zaměstnance do databáze."); */
    } 

    public void inicializace() {
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1990, "Jan", "Novak"));
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1985, "Petr", "Svoboda"));
        citac++;
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1992, "Eva", "Kralova"));
        citac++;
        seznamZamestnancu.put(citac, new BezpectnostniSpecialista(citac, 1988, "Martin", "Dvorak"));
        citac++;
        seznamZamestnancu.put(citac, new DatovyAnalytik(citac, 1995, "Lucie", "Horakova"));
        citac++;
        System.out.println("Přidáno 5 zaměstnanců do databáze.");
    }

    public void vypisZamestnance() {
        System.out.println("Zadejte ID: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        if (seznamZamestnancu.containsKey(id)) {
            ZamestnanecBase zamestnanec = seznamZamestnancu.get(id);
            System.out.println("ID: " + zamestnanec.getID());
            System.out.println("Jméno: " + zamestnanec.getJmeno());
            System.out.println("Příjmení: " + zamestnanec.getPrijmeni());
            System.out.println("Rok narození: " + zamestnanec.getRokNarozeni());
        }
        else {
            System.out.println("Zaměstnanec s ID " + id + " nenalezen.");
        }
    }


}
