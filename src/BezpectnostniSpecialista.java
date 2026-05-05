import java.util.HashMap;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class BezpectnostniSpecialista extends ZamestnanecBase {
    //rizikove skore (? 1/median spoluprace)
    //boolean analytik = false;
    int spolupracovnik = -1;

    public BezpectnostniSpecialista(int id, int rokNarozeni, String jmeno, String prijmeni) {
        super(id, rokNarozeni, jmeno, prijmeni);
    }

    /* public void setAnalytik(Boolean analytik) {
        this.analytik = false;
    }
 */

    public void setSpolupracovnik(int spolupracovnik) {
        this.spolupracovnik = spolupracovnik;
    }

    public int getSpolupracovnik() {
        return spolupracovnik;
    }

    public void pracuj(Databaze dat) {
        float ex;
        int n;
        HashMap<Integer, Databaze.UrovenSpoluprace> kolegove = dat.getSpoluprace().get(this.getID());
               
        System.out.println("Zadejte ID kolegy: ");
        Scanner scanner = new Scanner(System.in);
        int idKolegy = scanner.nextInt();

        ex = dat.prumernaSpoluprace(idKolegy);
        n = dat.spoluprace.get(idKolegy).size();

        float skore = ex * (float) n / (float) (n + 2);

        System.out.println("Rizikove skore: " + skore);
        }
}
