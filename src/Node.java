// Source code is decompiled from a .class file using FernFlower decompiler.
public class Node {
    private String Current;
    private Node NextN;
    private Node NextY;
 
    public Node(String contenu) {
       this.Current = contenu;
       this.NextN = null;
       this.NextY = null;
    }
 
    public void setNextN(Node b) {
       this.NextN = b;
    }
 
    public void setCurrent(String b) {
       this.Current = b;
    }
 
    public void setNextY(Node b) {
       this.NextY = b;
    }
 
    public Node getNextN() {
       return this.NextN;
    }
 
    public Node getNextY() {
       return this.NextY;
    }
 
    public String getData() {
       return this.Current;
    }
 }
 