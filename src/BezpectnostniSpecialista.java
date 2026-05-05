public class BezpectnostniSpecialista extends ZamestnanecBase {
    //rizikove skore (? 1/median spoluprace)
    //boolean analytik = false;
    

    public BezpectnostniSpecialista(int id, int rokNarozeni, String jmeno, String prijmeni) {
        super(id, rokNarozeni, jmeno, prijmeni);
    }

    /* public void setAnalytik(Boolean analytik) {
        this.analytik = false;
    }
 */
    public void pracuj() {
        System.out.println("Bezpečnostní specialista " + getJmeno() + " " + getPrijmeni() + " pracuje na zabezpečení dat.");
    }
}
