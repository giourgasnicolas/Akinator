// Source code is decompiled from a .class file using FernFlower decompiler.
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

public class BinaryTree {
   private InteractFile source;
   private Node root;

   BinaryTree(InteractFile source) {
      this.source = source;
      this.root = new Node((String)null);
   }

   public void ConstructTree() {
      Vector<String> Info = this.source.getInfo();
      Vector<Integer> indexY = this.source.getIndexY();
      Vector<Integer> indexN = this.source.getIndexN();
      this.root.setCurrent((String)Info.get(1));
      Node NextN = new Node((String)Info.get((Integer)indexN.get(1)));
      Node NextY = new Node((String)Info.get((Integer)indexY.get(1)));
      this.root.setNextN(NextN);
      this.root.setNextY(NextY);

      for(int i = 2; i < Info.size(); ++i) {
         Node current = new Node((String)Info.get(i));
         NextN = null;
         NextY = null;
         if (this.IsInTheTree(i, indexY, indexN)) {
            Vector<String> path = this.getPath(indexY, indexN, Info, (String)Info.get(i));
            current = this.getNode(path);
         }

         if ((Integer)indexN.get(i) != -1 && (Integer)indexY.get(i) != -1) {
            NextN = new Node((String)Info.get((Integer)indexN.get(i)));
            NextY = new Node((String)Info.get((Integer)indexY.get(i)));
         }

         current.setNextN(NextN);
         current.setNextY(NextY);
      }

   }

   public boolean IsInTheTree(int index, Vector<Integer> indexY, Vector<Integer> indexN) {
      for(int i = 1; i <= index; ++i) {
         if ((Integer)indexN.get(i) == index) {
            return true;
         }

         if ((Integer)indexY.get(i) == index) {
            return true;
         }
      }

      return false;
   }

   public Vector<String> getPath(Vector<Integer> indexY, Vector<Integer> indexN, Vector<String> Info, String contenu) {
      Vector<String> path = new Vector();
      String current = contenu;
      int index = 0;

      while(!current.equals(Info.get(1))) {
         int i;
         for(i = 1; i < Info.size(); ++i) {
            if (((String)Info.get(i)).equals(current)) {
               index = i;
            }
         }

         for(i = 1; i < indexY.size(); ++i) {
            if ((Integer)indexY.get(i) == index) {
               path.add("Y");
               current = (String)Info.get(i);
            }

            if ((Integer)indexN.get(i) == index) {
               path.add("N");
               current = (String)Info.get(i);
            }
         }
      }

      return path;
   }

   public Node getNode(Vector<String> path) {
      Collections.reverse(path);
      Node ToReturn = this.root;
      Iterator var3 = path.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         if (s.equals("Y")) {
            ToReturn = ToReturn.getNextY();
         }

         if (s.equals("N")) {
            ToReturn = ToReturn.getNextN();
         }
      }

      return ToReturn;
   }

   public Node getRoot() {
      return this.root;
   }
}
