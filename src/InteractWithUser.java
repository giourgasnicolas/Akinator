// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Vector;

public class InteractWithUser {
   private BinaryTree Tree;
   private InteractFile source;

   InteractWithUser(BinaryTree Tree, InteractFile source) {
      this.Tree = Tree;
      this.source = source;
   }

   public void Play() throws IOException {
      PrintStream var10000 = System.out;
      Vector var10001 = this.source.getInfo();
      var10000.println((String)var10001.get(0) + ", and then press <return>.");
      Scanner keyboard = new Scanner(System.in);
      String answer = keyboard.nextLine();
      Node current = this.Tree.getRoot();
      if (answer.equals("")) {
         System.out.println(current.getData() + " (Y/N)?");
         boolean loop = true;

         while(true) {
            do {
               String BadAnswer;
               String NewAnswer;
               String NewQuestion;
               do {
                  do {
                     if (!loop) {
                        return;
                     }

                     answer = keyboard.nextLine();
                     if (answer.equals("Y") && current.getNextY() != null) {
                        current = current.getNextY();
                        if (current.getNextY() == null || current.getNextN() == null) {
                           System.out.println("Is it " + current.getData() + " (Y/N)?");
                           answer = keyboard.nextLine();
                           if (answer.equals("Y")) {
                              System.out.println("I have won!");
                              loop = false;
                           }

                           if (answer.equals("N")) {
                              BadAnswer = current.getData();
                              System.out.println("I am unable to guess; you have won! What did you choose?");
                              answer = keyboard.nextLine();
                              NewAnswer = answer;
                              System.out.println("What question could I ask to distinguish " + answer + " from " + BadAnswer);
                              answer = keyboard.nextLine();
                              NewQuestion = answer;
                              System.out.println("For " + NewAnswer + ", would you answer yes or no to this question (Y/N)?");
                              answer = keyboard.nextLine();
                              System.out.println("Thank you !");
                              this.source.WriteInformation(BadAnswer, NewAnswer, NewQuestion, answer);
                              loop = false;
                           }
                        }

                        if (current.getNextY() != null || current.getNextN() != null) {
                           System.out.println(current.getData() + " (Y/N)?");
                        }
                     }
                  } while(!answer.equals("N"));
               } while(current.getNextN() == null);

               current = current.getNextN();
               if (current.getNextY() == null || current.getNextN() == null) {
                  System.out.println("Is it " + current.getData() + " (Y/N)?");
                  answer = keyboard.nextLine();
                  if (answer.equals("Y")) {
                     System.out.println("I have won!");
                     loop = false;
                  }

                  if (answer.equals("N")) {
                     BadAnswer = current.getData();
                     System.out.println("I am unable to guess; you have won! What did you choose?");
                     answer = keyboard.nextLine();
                     NewAnswer = answer;
                     System.out.println("What question could I ask to distinguish " + answer + " from " + BadAnswer);
                     answer = keyboard.nextLine();
                     NewQuestion = answer;
                     System.out.println("For " + NewAnswer + ", would you answer yes or no to this question (Y/N)?");
                     answer = keyboard.nextLine();
                     System.out.println("Thank you !");
                     this.source.WriteInformation(BadAnswer, NewAnswer, NewQuestion, answer);
                     loop = false;
                  }
               }
            } while(current.getNextY() == null && current.getNextN() == null);

            System.out.println(current.getData() + " (Y/N)?");
         }
      }
   }
}
