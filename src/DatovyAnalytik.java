public class DatovyAnalytik extends ZamestnanecBase{
    // dovednost najít nejblizsiho spolupracovnika (graf nebo matice
    

    public DatovyAnalytik(int id, int rokNarozeni, String jmeno, String prijmeni) {
        super(id, rokNarozeni, jmeno, prijmeni);
    }

    /* public void setAnalytik(Boolean analytik) {
        this.analytik = true;
    } */

    public void pracuj() {
        System.out.println("Datový analytik " + getJmeno() + " " + getPrijmeni() + " pracuje na analýze dat.");
    }
}
