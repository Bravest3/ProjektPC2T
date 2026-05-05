package Default;
public class DatovyAnalytik extends Zamestnanec {

    public DatovyAnalytik(String j, String p, int r) {
        super(j, p, r);
    }

    public DatovyAnalytik(int id, String j, String p, int r) {
        super(id, j, p, r);
    }

    @Override
    public void provedAnalyzu() {

        int max = 0;
        Zamestnanec nej = null;

        for (Spoluprace s : spoluprace) {
            Zamestnanec k = s.getKolega();
            int count = 0;

            for (Spoluprace s1 : spoluprace) {
                for (Spoluprace s2 : k.getSpoluprace()) {
                    if (s1.getKolega() == s2.getKolega())
                        count++;
                }
            }

            if (count > max) {
                max = count;
                nej = k;
            }
        }

        if (nej != null)
            System.out.println("Nejvíce společných spolupracovníků má s: " + nej.getCeleJmeno());
        else
            System.out.println("Nelze vyhodnotit.");
    }

    @Override
    public String getTyp() {
        return "Datový analytik";
    }
}