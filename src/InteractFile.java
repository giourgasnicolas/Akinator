// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class InteractFile {
   private String filename;
   private int nbr_line;
   private Vector<String> Info;
   private Vector<Integer> indexY;
   private Vector<Integer> indexN;

   public InteractFile(String filename) {
      this.filename = filename;
      this.nbr_line = 0;
      this.Info = new Vector();
      this.indexY = new Vector();
      this.indexN = new Vector();
   }

   public void ExtractInformation() throws FileNotFoundException {
      Scanner sc = new Scanner(new File(this.filename));

      while(true) {
         String[] Line;
         int size;
         String data;
         int i;
         do {
            if (!sc.hasNextLine()) {
               return;
            }

            Line = sc.nextLine().split(" ");
            size = Line.length;
            data = "";
            if (Line[0].equals("?")) {
               this.indexY.add(Integer.parseInt(Line[1]));
               this.indexN.add(Integer.parseInt(Line[2]));

               for(i = 3; i < size; ++i) {
                  if (i == size - 1) {
                     data = data + Line[i];
                  } else {
                     data = data + Line[i] + " ";
                  }
               }

               this.Info.add(data);
            }

            if (Line[0].equals("=")) {
               this.indexY.add(-1);
               this.indexN.add(-1);

               for(i = 1; i < size; ++i) {
                  if (i == size - 1) {
                     data = data + Line[i];
                  } else {
                     data = data + Line[i] + " ";
                  }
               }

               this.Info.add(data);
            }
         } while(!isNumeric(Line[0]));

         this.nbr_line = Integer.parseInt(Line[0]);
         this.indexY.add(0);
         this.indexN.add(0);

         for(i = 1; i < size; ++i) {
            if (i == size - 1) {
               data = data + Line[i];
            } else {
               data = data + Line[i] + " ";
            }
         }

         this.Info.add(data);
      }
   }

   public static boolean isNumeric(String strNum) {
      if (strNum == null) {
         return false;
      } else {
         try {
            double var1 = Double.parseDouble(strNum);
            return true;
         } catch (NumberFormatException var3) {
            return false;
         }
      }
   }

   public int getNbr_line() {
      return this.nbr_line;
   }

   public Vector<Integer> getIndexN() {
      return this.indexN;
   }

   public Vector<Integer> getIndexY() {
      return this.indexY;
   }

   public Vector<String> getInfo() {
      return this.Info;
   }

   public void WriteInformation(String BadAnswer, String NewAnswer, String NewQuestion, String YN) throws IOException {
      File ancien = new File(this.filename);
      ancien.delete();
      File newfile = new File(this.filename);
      FileWriter f = new FileWriter(newfile, false);
      int indice = 0;

      int i;
      for(i = 0; i < this.Info.size(); ++i) {
         if (((String)this.Info.get(i)).equals(BadAnswer)) {
            indice = i;
         }
      }

      this.nbr_line += 2;

      String var10001;
      for(i = 0; i <= this.nbr_line - 2; ++i) {
         if ((Integer)this.indexN.get(i) == 0) {
            var10001 = Integer.toString(this.nbr_line);
            f.write(var10001 + " " + (String)this.Info.get(i) + "\n");
         }

         if ((Integer)this.indexN.get(i) == -1) {
            Object var10 = this.Info.get(i);
            f.write("= " + (String)var10 + "\n");
         }

         if ((Integer)this.indexN.get(i) != 0 && (Integer)this.indexN.get(i) != -1) {
            if ((Integer)this.indexN.get(i) == indice) {
               var10001 = Integer.toString((Integer)this.indexY.get(i));
               f.write("? " + var10001 + " " + Integer.toString(this.nbr_line - 1) + " " + (String)this.Info.get(i) + "\n");
            }

            if ((Integer)this.indexY.get(i) == indice) {
               var10001 = Integer.toString(this.nbr_line - 1);
               f.write("? " + var10001 + " " + Integer.toString((Integer)this.indexN.get(i)) + " " + (String)this.Info.get(i) + "\n");
            }

            if ((Integer)this.indexY.get(i) != indice && (Integer)this.indexN.get(i) != indice) {
               var10001 = Integer.toString((Integer)this.indexY.get(i));
               f.write("? " + var10001 + " " + Integer.toString((Integer)this.indexN.get(i)) + " " + (String)this.Info.get(i) + "\n");
            }
         }
      }

      if (YN.equals("Y")) {
         var10001 = Integer.toString(this.nbr_line);
         f.write("? " + var10001 + " " + Integer.toString(indice) + " " + NewQuestion + "\n");
      }

      if (YN.equals("N")) {
         var10001 = Integer.toString(indice);
         f.write("? " + var10001 + " " + Integer.toString(this.nbr_line) + " " + NewQuestion + "\n");
      }

      f.write("= " + NewAnswer + "\n");
      f.close();
   }
}
