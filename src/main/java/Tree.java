import java.util.ArrayList;
import java.util.List;

public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = new ArrayList<>();
        addNodes(root, nodes);
        return nodes;
    }

    private void addNodes(Node node, List<Node> nodes) {
        nodes.add(node);
        for (Node child : node.getChildNodes()) {
            addNodes(child, nodes);
        }
    }

    public List<Node> getAllLeaves() {
        List<Node> leaves = new ArrayList<>();
        for (Node node : getAllNodes()) {
            if (node.isLeaf()) {
                leaves.add(node);
            }
        }
        return leaves;
    }

    public void setRoot(Node node) {
        this.root = node;
    }
}
