
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    
    System.out.print("Wie Breit soll das Spielfeld sein? ");                   //Spielfeldgröße abfragen
    int b =input.nextInt();                                                     
    System.out.print("Wie Lang soll das Spielfeld sein? ");
    int a =input.nextInt();
    
    System.out.print("\nWie wie viele Panzer möchten sie haben? ");              //Panzeranzahl abfragen
    int PAnz =input.nextInt();
    System.out.print("Wie wie viele Hindernisse möchten sie haben? ");         //Hindernisse abfragen
    int HAnz =input.nextInt();
    
    
    
    
    
    Panzer [] Panzerliste = new Panzer[PAnz];  
    Hindernis [] Hindernisliste = new Hindernis[HAnz];                               
    int [][] Aliste = new int[PAnz+HAnz][3];
    
    Spielfeld Feld1 = new Spielfeld(b,a,Aliste);                                //Spielfeld inizialisieren           
    
    int x,y,z=0,richtig=0;
    
    for (int i=0;i<PAnz;i++) {                                                  // Panzer erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      for (int j=0;j<i;j++) {
        if (x != Aliste[j][0] && y != Aliste[j][1]) {
          richtig +=1;
        } // end of if
      } // end of for
      
      if (richtig==i) {
        Panzerliste[i]= new Panzer(x,y);
        Aliste[i][0]=x;
        Aliste[i][1]=y;
        Aliste[i][2]=1;
        z++;
      } else {
        i -=1;
      } // end of if-else  
      richtig=0;
    } // end of for
    
    richtig=x=y=0;
    
    for (int i=0;i<HAnz;i++) {                                                  // Hindernisse erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      for (int j=0;j<Aliste.length;j++) {
        if (x != Aliste[j][0] && y != Aliste[j][1]) {
          richtig +=1;
        } // end of if
      } // end of for
      
      if (richtig==Aliste.length) {
        Hindernisliste[i]= new Hindernis(x,y);
        Aliste[i+z][0]=x;
        Aliste[i+z][1]=y;
        Aliste[i+z][2]=2;
      } else {
        i -=1;
      } // end of if-else  
      richtig=0;
    } // end of for
    
    Feld1.print();                                                              // Feld Ausgeben
    
    boolean exit= true;
    
    while (exit) {                                                              //Spielbegin
      
      System.out.println("Mit welchen Panzer möchten sie die nächste aktion ausführen?");
      System.out.println("Ihre Panzer:\n");
      
      for (int i=1;i-1<Panzerliste.length;i++) {                                // Verfügbare Panzer anzeigen
        if (Aliste[i-1][2]==1) {
          System.out.println("Panzer Nummer ("+i+") an Position: "+Panzerliste[i-1].getPos().getPosh()+" - "+Panzerliste[i-1].getPos().getPosv());
        } else {
          System.out.println("Panzer Nummer ("+i+") zerstoert!");
        } // end of if-else
      } // end of for
      
      System.out.print("\nPanzernummer fuer die Auswahl eingeben.");            // Panzer auswaehlen
      int  auswahl=input.nextInt();
      
      for (int i=0;i<Panzerliste.length;i++) {                                  // Test, ob zerstoert
        if (Aliste[auswahl-1][2]==2) {
          System.out.println("Dieser Panzer ist zerstoert, bitte waehle einen anderen Panzer.");
          System.out.print("\nPanzernummer fuer die Auswahl eingeben.");
          auswahl=input.nextInt();
          i=0;
        } // end of if
      } // end of for
      
      System.out.print("Panzer bewegen(0) oder schießen(1)?");                  // Aktionsparameter eingeben
      int  action=input.nextInt();
      System.out.print("Richtung?(oben=0,rechts=1,unten=2,links=3)");
      int  richtung=input.nextInt();
      System.out.print("Kraft? ");
      int  kraft=input.nextInt();
      
      switch (action) {
        case  0:                                                                // Panzer bewegen
          Panzerliste[auswahl-1].move(richtung,kraft);
          Aliste[auswahl-1][0]=Panzerliste[auswahl-1].getPos().getPosh();
          Aliste[auswahl-1][1]=Panzerliste[auswahl-1].getPos().getPosv();
          break;
        case  1:                                                                // Panzer schießen
          boolean treffer = false;
          Position ziel = new Position(Panzerliste[auswahl-1].shoot(richtung,kraft).getPosh(),Panzerliste[auswahl-1].shoot(richtung,kraft).getPosv());
          for (int i=0;i<Panzerliste.length;i++) {
            if (ziel.getPosh() == Panzerliste[i].getPos().getPosh() && ziel.getPosv() == Panzerliste[i].getPos().getPosv()) {
              treffer = true;
              Aliste[i][2]=2;
              break;
            } // end of if
          } // end of for
          if (treffer) {                                                        // Treffer ausgabe
            System.out.println("\nPanzer getroffen und zerstoert!");
          } else {
            System.out.println("\nNichts getroffen.");
          } // end of if-else
          break;      
        default: 
          
      } // end of switch
      
      System.out.print("Aktuelles Spielfeld anzeigen? (ja=0): ");
      int  feld=input.nextInt();
      
      if (feld==0) {
        Feld1.print();
      } // end of if
      
      System.out.print("Spiel beenden? (ja=0): ");
      int  Iexit=input.nextInt();
      
      if (Iexit==0) {
        break;
      } // end of if
    } // end of while
    
    
  } // end of main
      
} // end of class Panzergame
    