import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.SQLException;
import java.io.IOException;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        String url = "jdbc:h2:~/treeDB";
        String user = "userTree";
        String password = "pass";
        Class.forName("org.h2.Driver");

        DBConnection dbConnection = new H2Connection("jdbc:h2:~/treeDB", "userTree", "pass");
        TreeReader treeReader = new TreeReader(dbConnection);
        List<Tree> trees = treeReader.readTrees();

        int totalLeaves = 0;
        for (Tree tree : trees) {
            totalLeaves += tree.getAllLeaves().size();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.csv"))) {
            writer.write(String.valueOf(totalLeaves));
        }
    }
}
