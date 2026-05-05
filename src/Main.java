import java.util.Scanner;

public class Main {

    
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Scanner volbaSc = new Scanner(System.in);
        Databaze dat = new Databaze();
        String volba;
        
        String menu = """
        \n----------------
        \nZadejte 0 pro ukončení programu
        1 pro přidání zaměstnance
        2 pro přidání spolupráce
        3 pro odebrání zaměstnance
        4 pro vyhledání zaměstnance
        5 pro spuštění dovednosti
        6 pro výpis zaměstnanců podle role
        7 statistiky
        8 pro výpis počtu zaměstnanců
        11 pro inicializaci databáze:""";
        

        while (true) {
            System.out.println(menu);
            
            volba = volbaSc.nextLine();
            
            if (volba.equals("0")) {
                System.out.println("Ukončuji program...");
                break;
            }
            else if (volba.equals("1")) { //pridani zamestnance
                dat.pridejZamestnance(null);
            }
            else if (volba.equals("2")) { //pridani spoluprace
                try{
                    int id1, id2, sp;
                    System.out.println("Zadejte ID prvního zaměstnance: ");
                    id1 = Integer.parseInt(volbaSc.nextLine());
                    System.out.println("Zadejte ID druhého zaměstnance: ");
                    id2 = Integer.parseInt(volbaSc.nextLine());
                    System.out.println("Zvolte úroveň spolupráce (1 - nízká, 2 - střední, 3 - vysoká): ");
                    sp = Integer.parseInt(volbaSc.nextLine());
                    Databaze.UrovenSpoluprace uroven = null;
                    switch (sp) {   
                        case 1 -> uroven = Databaze.UrovenSpoluprace.NIZKA;
                        case 2 -> uroven = Databaze.UrovenSpoluprace.STREDNI;
                        case 3 -> uroven = Databaze.UrovenSpoluprace.VYSOKA;
                        default -> System.out.println("Neplatná volba úrovně spolupráce.");
                    }
                    dat.novaSpoluprace(id1, id2, uroven);
                }catch(NumberFormatException e) {
                    System.out.println("Neplatný vstup.");
                }
            }
            else if (volba.equals("3")) { //odebrani
                System.out.println("Zadejte ID zaměstnance, kterého chcete smazat: ");
                int id = Integer.parseInt(volbaSc.nextLine());
                dat.odeberZamestnance(id);
            }
            else if (volba.equals("4")) { //vyhledani                              
                System.out.println("Zadejte ID: ");
                int id = Integer.parseInt(volbaSc.nextLine());
                dat.vypisZamestnance(id);
            }
            else if (volba.equals("5")) { //spusteni dovednosti
                System.out.println("Zadejte ID zaměstnance, jehož dovednost chcete spustit: ");
                int id = Integer.parseInt(volbaSc.nextLine());
                dat.seznamZamestnancu.get(id).pracuj(dat);
            }  
            else if (volba.equals("6")) { //vypis,(ma byt abecedne)
                System.out.println("Zvolte 1 pro zobrazení datových analytiků, 2 pro zobrazení bezpečnostních specialistů: ");
                int volbaAnalytik = Integer.parseInt(volbaSc.nextLine());
                dat.vypis(volbaAnalytik == 1);
            }
            else if (volba.equals("7")) { //statistika, (neni)
                }
            else if (volba.equals("8")) {//vypis poctu (ma byt rozdeleny)
                dat.pocetZamestnancu();
                }
            else if (volba.equals("9")) { //
            }
            else if (volba.equals("11")) {
                dat.inicializace();
            }
            else {
                System.out.println("Neplatná volba. Zadejte znovu.");
            }
            
        }
        volbaSc.close();

    }

}
