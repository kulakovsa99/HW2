import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private List<Node> childNodes = new ArrayList<>();
    private Node parentNode;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public boolean isLeaf() {
        return childNodes.isEmpty();
    }

    public boolean isRoot() {
        return parentNode == null;
    }

    public void addChild(Node child) {
        child.setParent(this);
        this.childNodes.add(child);
    }

    private void setParent(Node parent) {
        this.parentNode = parent;
    }
}
