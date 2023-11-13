import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TreeReader {
    private DBConnection dbConnection;

    public TreeReader(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public List<Tree> readTrees() throws SQLException {
        List<Tree> trees = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM TREES")) {
            while (resultSet.next()) {
                int nodeId = resultSet.getInt("id");
                int parentId = resultSet.getInt("parent_id");
                Tree tree = findTree(trees, parentId);
                Node node = new Node(nodeId);
                Node parent = findNode(tree != null ? tree.getRoot() : null, parentId);
                if (parent == null) {
                    tree = new Tree();
                    tree.setRoot(node);
                    trees.add(tree);
                } else {
                    parent.addChild(node);
                }
            }
        }
        return trees;
    }

    private static Tree findTree(List<Tree> trees, int parentId) {
        for (Tree tree : trees) {
            Node root = tree.getRoot();
            if (root != null && root.getId() == parentId) {
                return tree;
            }
        }
        return null;
    }

    private static Node findNode(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (node.getId() == id) {
            return node;
        }
        for (Node child : node.getChildNodes()) {
            Node result = findNode(child, id);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}