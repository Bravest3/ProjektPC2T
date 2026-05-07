package Default;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseSQL {

    private static final String URL = "jdbc:sqlite:data.db";

    public static void init() {
        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {

            // tabulka zaměstnanců
            s.execute("CREATE TABLE IF NOT EXISTS zamestnanec (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "jmeno TEXT," +
                    "prijmeni TEXT," +
                    "rok INTEGER," +
                    "skupina TEXT)");

            // 
            s.execute("CREATE TABLE IF NOT EXISTS spoluprace (" +
                    "id1 INTEGER," +
                    "id2 INTEGER," +
                    "kvalita TEXT)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pridej(String j, String p, int r, String typ) {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO zamestnanec(jmeno, prijmeni, rok, skupina) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, j);
            ps.setString(2, p);
            ps.setInt(3, r);
            ps.setString(4, typ);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void smaz(int id) {
        try (Connection c = DriverManager.getConnection(URL)) {

            PreparedStatement ps1 = c.prepareStatement(
                    "DELETE FROM zamestnanec WHERE id = ?");
            ps1.setInt(1, id);
            ps1.executeUpdate();

            // 
            PreparedStatement ps2 = c.prepareStatement(
                    "DELETE FROM spoluprace WHERE id1 = ? OR id2 = ?");
            ps2.setInt(1, id);
            ps2.setInt(2, id);
            ps2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public static void pridejSpolupraci(int id1, int id2, String kvalita) {
        try (Connection c = DriverManager.getConnection(URL);
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO spoluprace(id1, id2, kvalita) VALUES (?, ?, ?)")) {

            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.setString(3, kvalita);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 
    public static ArrayList<Zamestnanec> nacti() {

        ArrayList<Zamestnanec> list = new ArrayList<>();
        HashMap<Integer, Zamestnanec> mapa = new HashMap<>();

        try (Connection c = DriverManager.getConnection(URL);
             Statement s = c.createStatement()) {

            // 
            ResultSet rs = s.executeQuery("SELECT * FROM zamestnanec");

            while (rs.next()) {

                int id = rs.getInt("id");
                String j = rs.getString("jmeno");
                String p = rs.getString("prijmeni");
                int r = rs.getInt("rok");
                String typ = rs.getString("skupina");

                Zamestnanec z;

                if (typ.equals("Datový analytik"))
                    z = new DatovyAnalytik(id, j, p, r);
                else
                    z = new BezpecnostniSpecialista(id, j, p, r);

                list.add(z);
                mapa.put(id, z);
            }

            // 
            ResultSet rs2 = s.executeQuery("SELECT * FROM spoluprace");

            while (rs2.next()) {

                int id1 = rs2.getInt("id1");
                int id2 = rs2.getInt("id2");
                String kvalita = rs2.getString("kvalita");

                Zamestnanec z1 = mapa.get(id1);
                Zamestnanec z2 = mapa.get(id2);

                if (z1 != null && z2 != null) {
                    z1.pridejSpolupraci(z2, kvalita);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
