public abstract class ZamestnanecBase  implements Zamestnanec {
    int id, rokNarozeni;
    String jmeno, prijmeni;
    /* Boolean analytik; */
    //seznam spolupracovniku

    public ZamestnanecBase(int id, int rokNarozeni, String jmeno, String prijmeni) {
        this.id = id;
        this.rokNarozeni = rokNarozeni;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        /* this.analytik = analytik; */
    }

    public void setID(int id) {
        this.id = id;
    }

    public public void setRokNarozeni(int rokNarozeni) {
        this.rokNarozeni = rokNarozeni;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    /* public void setAnalytik(Boolean analytik) {
        this.analytik = analytik;
    } */

    public int getID() {
        return id;
    }

    public int getRokNarozeni() {
        return rokNarozeni;
    }

    public String getJmeno() {
        return jmeno;
    }

     public String getPrijmeni() {
        return prijmeni;
    }

    /* public Boolean getAnalytik() {
        return analytik;
    } */

    public abstract void pracuj();

}
