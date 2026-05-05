package Default;
import java.util.ArrayList;

public abstract class Zamestnanec {

    private static int counter = 1;

    protected int id;
    protected String jmeno;
    protected String prijmeni;
    protected int rok;

    protected ArrayList<Spoluprace> spoluprace = new ArrayList<>();

    public Zamestnanec(String j, String p, int r) {
        this.id = counter++;
        this.jmeno = j;
        this.prijmeni = p;
        this.rok = r;
    }

    public Zamestnanec(int id, String j, String p, int r) {
        this.id = id;
        this.jmeno = j;
        this.prijmeni = p;
        this.rok = r;

        if (id >= counter) counter = id + 1;
    }

    public int getId() {
        return id;
    }

    public String getCeleJmeno() {
        return jmeno + " " + prijmeni;
    }

    public void pridejSpolupraci(Zamestnanec z, String kvalita) {
        spoluprace.add(new Spoluprace(z, kvalita));
    }

    public ArrayList<Spoluprace> getSpoluprace() {
        return spoluprace;
    }

    // 🔥 abstraktní metoda
    public abstract void provedAnalyzu();

    public String toString() {
        return id + " | " + getCeleJmeno() + " | " + rok + " | " + getTyp();
    }

    public abstract String getTyp();
}