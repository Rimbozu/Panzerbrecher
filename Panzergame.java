
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    
    boolean test= true;
    int player=0,b,a,PAnz,HAnz,APanz=0;
    int [][] Aliste;
    Hindernis [] Hindernisliste;
    Panzer [] PanzerlisteP1, PanzerlisteP2;
    
    System.out.print("Spieleranzahl? ");                                        //Anzahl Mitspieler
    player =input.nextInt();                                                       
    
    System.out.print("\nWie Breit soll das Spielfeld sein? ");                  //Spielfeldgröße abfragen
    b =input.nextInt();                                                      
    System.out.print("Wie Lang soll das Spielfeld sein? ");
    a =input.nextInt();
    
    System.out.print("\nWie wie viele Panzer möchten sie haben? ");             //Panzeranzahl abfragen
    PAnz =input.nextInt();
    System.out.print("Wie wie viele Hindernisse möchten sie haben? ");          //Hindernisse abfragen
    HAnz =input.nextInt();
    
    PanzerlisteP1 = new Panzer[PAnz];
    PanzerlisteP2 = new Panzer[PAnz];
    
    
    Hindernisliste = new Hindernis[HAnz];
    Aliste = new int[(2*PAnz)+HAnz][4];
    
    
    Spielfeld Feld1 = new Spielfeld(b,a,Aliste);                                //Spielfeld inizialisieren           
    
    int x,y,z=0,srichtig=0;
    boolean richtig = true;
    
    
    for (int i=0;i<PAnz;i++) {                                                // Panzer Spieler 1 erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      
      for (int j=0;j<i;j++) {
        if (x == Aliste[j][0] && y == Aliste[j][1]) {
          richtig =false;
        } // end of if
      } // end of for
      
      if (richtig) {
        PanzerlisteP1[i]= new Panzer(x,y);
        Aliste[i][0]=x;
        Aliste[i][1]=y;
        Aliste[i][2]=1;
        Aliste[i][3]=0;
        z++;
      } else {
        i -=1;
      } // end of if-else  
      richtig=true;
    } // end of for
    
    int e1=z;
    richtig=true;
    x=y=0;
    
    for (int i=0;i<PAnz;i++) {                                                // Panzer Spieler 2 erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      
      for (int j=0;j<(i+e1);j++) {
        if (x == Aliste[j][0] && y == Aliste[j][1]) {
          richtig =false;
        } // end of if
      } // end of for
      
      if (richtig) {
        PanzerlisteP2[i]= new Panzer(x,y);
        Aliste[i+e1][0]=x;
        Aliste[i+e1][1]=y;
        Aliste[i+e1][2]=1;
        Aliste[i+e1][3]=1;
        z++;
      } else {
        i -=1;
      } // end of if-else  
      richtig=true;
    } // end of for
    
    int e2=z;
    richtig=true;
    x=y=0;
    
    for (int i=0;i<HAnz;i++) {                                                  // Hindernisse erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      for (int j=0;j<(i+e2);j++) {
        if (x == Aliste[j][0] && y == Aliste[j][1]) {
          richtig =false;
        } // end of if
      } // end of for
      
      
      if (richtig) {
        Hindernisliste[i]= new Hindernis(x,y);
        Aliste[i+e2][0]=x;
        Aliste[i+e2][1]=y;
        Aliste[i+e2][2]=2;
        Aliste[i+e2][3]=0;
      } else {
        i -=1;
      } // end of if-else  
      richtig=true;
    } // end of for
    
    Feld1.print();                                                              // Feld Ausgeben
    
    while (true) {                                                              //Spielbegin
      int spiel =1;
      int spieler;
      Panzer [] Panzerliste1;
      Panzer [] Panzerliste2;
      if (spiel%2==1) {
        spieler = 0;
        Panzerliste1 = PanzerlisteP1;
        Panzerliste2 = PanzerlisteP2;
        System.out.println("\nSpieler 1 ist an der Reihe.");
        } else {
        spieler = 1;
        Panzerliste1 = PanzerlisteP2;
        Panzerliste2 = PanzerlisteP1;
        System.out.println("\nSpieler 2 ist an der Reihe.");
      } // end of if-else
      
      System.out.println("\nMit welchen Panzer möchten Sie die nächste aktion ausführen?");
      System.out.println("Ihre Panzer:\n");
      
      for (int i=1;i-1<Panzerliste1.length;i++) {                                // Verfügbare Panzer anzeigen
        if (Aliste[i-1][2]==1) {
          System.out.println("Panzer Nummer ("+i+") an Position: "+Panzerliste1[i-1].getPos().getPosh()+" - "+Panzerliste1[i-1].getPos().getPosv());
        } else {
          System.out.println("Panzer Nummer ("+i+") zerstoert!");
        } // end of if-else
      } // end of for
      
      System.out.print("\nPanzernummer fuer die Auswahl eingeben.");            // Panzer auswaehlen
      int  auswahl=input.nextInt();
      
      for (int i=0;i<Panzerliste1.length;i++) {                                  // Test, ob zerstoert
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
          Panzerliste1[auswahl-1].move(richtung,kraft);
          Aliste[auswahl-1][0]=Panzerliste1[auswahl-1].getPos().getPosh();
          Aliste[auswahl-1][1]=Panzerliste1[auswahl-1].getPos().getPosv();
          break;
        case  1:                                                                // Panzer schießen
          boolean treffer = false;
          Position ziel = new Position(Panzerliste1[auswahl-1].shoot(richtung,kraft).getPosh(),Panzerliste1[auswahl-1].shoot(richtung,kraft).getPosv());
          for (int i=0;i<Panzerliste2.length;i++) {
            if (ziel.getPosh() == Panzerliste2[i].getPos().getPosh() && ziel.getPosv() == Panzerliste2[i].getPos().getPosv()) {
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
    