// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game {
   public Game() {
   }

   public static void main(String[] args) throws IOException {
      InteractFile test = new InteractFile(args[0]);

      try {
         test.ExtractInformation();
      } catch (FileNotFoundException var6) {
         System.out.println("File not found");
      }

      System.out.println(test.getInfo());
      System.out.println(test.getIndexN());
      System.out.println(test.getIndexY());
      BinaryTree test2 = new BinaryTree(test);
      test2.ConstructTree();
      InteractWithUser test3 = new InteractWithUser(test2, test);

      try {
         test3.Play();
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }
   }
}
