package Default;
public class BezpecnostniSpecialista extends Zamestnanec {

    public BezpecnostniSpecialista(String j, String p, int r) {
        super(j, p, r);
    }

    public BezpecnostniSpecialista(int id, String j, String p, int r) {
        super(id, j, p, r);
    }

    @Override
    public void provedAnalyzu() {

        double score = 0;

        for (Spoluprace s : spoluprace) {
            switch (s.getKvalita()) {
                case "špatná" -> score += 3;
                case "průměrná" -> score += 2;
                case "dobrá" -> score += 1;
            }
        }

        score *= spoluprace.size();

        System.out.println("Rizikové skóre: " + score);
    }

    @Override
    public String getTyp() {
        return "Bezpečnostní specialista";
    }
}