package Default;
public class Spoluprace {

    private Zamestnanec kolega;
    private String kvalita;

    public Spoluprace(Zamestnanec k, String kvalita) {
        this.kolega = k;
        this.kvalita = kvalita;
    }

    public Zamestnanec getKolega() {
        return kolega;
    }

    public String getKvalita() {
        return kvalita;
    }
}