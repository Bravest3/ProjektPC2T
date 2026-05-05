import java.util.HashMap;

public class DatovyAnalytik extends ZamestnanecBase{
    // dovednost najít nejblizsiho spolupracovnika (graf nebo matice
    

    public DatovyAnalytik(int id, int rokNarozeni, String jmeno, String prijmeni) {
        super(id, rokNarozeni, jmeno, prijmeni);
    }

    /* public void setAnalytik(Boolean analytik) {
        this.analytik = true;
    } */

    public void pracuj(Databaze dat) {
        HashMap<Integer, Databaze.UrovenSpoluprace> kolegove = dat.getSpoluprace().get(this.getID());
        int kolega = -1, maxSpoluprace = -1, pocet = -1;
        
        for ( int i : kolegove.keySet()) {
            pocet = dat.pocetSpolupraci(this.getID(), i);
            if ( pocet > maxSpoluprace ){
                maxSpoluprace = pocet;
                kolega = i;
            }
       }
        if (kolega != -1) {
            System.out.println("Datový analytik " + getJmeno() + " " + getPrijmeni() + " má " + maxSpoluprace + " spoluprací s kolegou " + dat.getSeznamZamestnancu().get(kolega).getJmeno() + " " + dat.getSeznamZamestnancu().get(kolega).getPrijmeni() + ".");
        } else {
            System.out.println("Datový analytik " + getJmeno() + " " + getPrijmeni() + " nemá žádné spolupracovníky.");
        }
    }
}
