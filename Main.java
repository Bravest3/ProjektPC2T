import java.util.Scanner;
import java.util.random.Random;

public class Main {

    
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Scanner volbaSc = new Scanner(System.in);
        

        while (true) {
            //tady bude hlavni menu
            String volba = volbaSc.nextLine();
            if (volba.equals("0")) {
                System.out.println("Ukončuji program...");
                break;
            }
            else if (volba.equals("1")) {
                int id = (int) (Math.random() * 101), rokNarozeni;
                String jmeno, prijmeni;
                System.out.println("Zadejte rok narození:");
                rokNarozeni = volbaSc.nextInt();

                System.out.println("Zadejte křestni jméno:");
                jmeno = volbaSc.next();
                System.out.println("Zadejte příjmení:");
                prijmeni = volbaSc.next();

                System.out.println("Zvolte 1 pro bezpečnostního specialistu, 2 pro datového analytika:");
                int volbaZamestnance = volbaSc.nextInt();

                if (volbaZamestnance == 1) {
                    BezpectnostniSpecialista bezpecnostniSpecialista = new BezpectnostniSpecialista(id, rokNarozeni, jmeno, prijmeni);
                    System.out.println("Vytvořen bezpečnostní specialista: " + bezpecnostniSpecialista.getJmeno() + " " + bezpecnostniSpecialista.getPrijmeni() + "\n ID: " + bezpecnostniSpecialista.getID());
                    continue;
                }
                else if (volbaZamestnance == 2) {
                    DatovyAnalytik datovyAnalytik = new DatovyAnalytik(id, rokNarozeni, jmeno, prijmeni);
                    System.out.println("Vytvořen datový analytik: " + datovyAnalytik.getJmeno() + " " + datovyAnalytik.getPrijmeni() +"\n ID: " + datovyAnalytik.getID());
                    continue;
                }
                else {
                    System.out.println("Neplatná volba zaměstnance.");
                }
            }

        }

    }

}
