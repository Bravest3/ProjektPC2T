import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.Class;

public class SqlManager extends Databaze{

    public SqlManager() {
        super();
    }

    public void start() {
        try {
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:databaze.db");
            //String cesta = System.getProperty("user.dir") + "\\databaze.db";
            //Connection conn = DriverManager.getConnection("jdbc:sqlite:" + cesta);  
            //String url = "jdbc:mysql://localhost:3306/databaze";
            String user = "root";
            String password = "root";

            //Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databazePC2T", user, password);
            System.out.println("Připojeno k databázi databaze.db");

            conn.createStatement().execute("""
                CREATE TABLE IF NOT EXISTS zamestnanci (
                id INTEGER PRIMARY KEY,
                rokNarozeni YEAR,
                jmeno TEXT,
                prijmeni TEXT,
                analytik BOOLEAN
            )""");
            //conn.createStatement().execute("""CREATE TABLE IF NOT EXISTS spoluprace (...)""");
       
             conn.createStatement().execute("""
                INSERT INTO zamestnanci (id, rokNarozeni, jmeno, prijmeni, analytik) VALUES (1, 1990, 'Jan', 'Novak', 0);
             """);
            conn.createStatement().execute("""
                INSERT INTO zamestnanci (id, rokNarozeni, jmeno, prijmeni, analytik) VALUES (2, 1996, 'Jana', 'Novotná', 1);
            """);
       
            conn.close();
            System.out.println("Odpojeno od databáze.");

        } catch (SQLException e) {
            System.out.println("Chyba: " + e.getMessage());
        }
    }

    


}
