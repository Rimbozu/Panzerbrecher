
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    
    boolean test= true;
    int b,a,PAnz,HAnz,APanz=0;
    Hindernis [] Hindernisliste;
    Panzer [] PanzerlisteP1, PanzerlisteP2;
    
    System.out.print("2 Spieler Spielmodus");                                                                                        
    
    System.out.print("\nWie Breit soll das Spielfeld sein? ");                  //Spielfeldgröße abfragen
    b =input.nextInt();                                                      
    System.out.print("Wie Lang soll das Spielfeld sein? ");
    a =input.nextInt();
    
    System.out.print("\nWie wie viele Panzer möchten sie haben? ");             //Panzeranzahl abfragen
    PAnz =input.nextInt();
    System.out.print("Wie wie viele Hindernisse möchten sie haben? ");          //Hindernisse abfragen
    HAnz =input.nextInt();
    
    PanzerlisteP1 = new Panzer[PAnz];                                           //1. Inizialisieren
    PanzerlisteP2 = new Panzer[PAnz];
    Hindernisliste = new Hindernis[HAnz];
    
    Spielfeld Feld1 = new Spielfeld(b, a, PanzerlisteP1, PanzerlisteP2, Hindernisliste);               
    
    int x,y,srichtig=0;
    boolean richtig = true;
    
    
    for (int i=0;i<PAnz;i++) {                                                  // Panzer Spieler 1 erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      richtig=checkListeP(x,y,i,PanzerlisteP1);
      if (richtig) {
        PanzerlisteP1[i]= new Panzer(x,y,1);
      } else {
        i -=1;
      } // end of if-else 
      
      richtig=true;
    } // end of for
    
    richtig=true;
    x=y=0;
    
    for (int i=0;i<PAnz;i++) {                                                  // Panzer Spieler 2 erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      richtig=checkListeP(x,y,PanzerlisteP1.length,PanzerlisteP1);
      if (richtig) {
        richtig=checkListeP(x,y,i,PanzerlisteP2);
        if (richtig) {
          PanzerlisteP2[i]= new Panzer(x,y,2);
        } else {
          i -=1;
        } // end of if-else  
      } else {
        i -=1;
      } // end of if-else
      
      richtig=true;
    } // end of for
    
    richtig=true;
    x=y=0;
    
    for (int i=0;i<HAnz;i++) {                                                  // Hindernisse erzeugen
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      richtig=checkListeP(x,y,PanzerlisteP1.length,PanzerlisteP1);
      if (richtig) {
        richtig=checkListeP(x,y,PanzerlisteP2.length,PanzerlisteP2);
        if (richtig) {
          richtig=checkListeH(x,y,i,Hindernisliste);
          if (richtig) {
            Hindernisliste[i]= new Hindernis(x,y);
          } else {
            i -=1;
          } // end of if-else
        } else {
          i -=1;
        } // end of if-else
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
      
      for (int i=1;i-1<Panzerliste1.length;i++) {                               // Verfügbare Panzer anzeigen
        if (Panzerliste1[i-1].getPlayer()!=0) {
          System.out.println("Panzer Nummer ("+i+") an Position: "+Panzerliste1[i-1].getPos().getPosh()+" - "+Panzerliste1[i-1].getPos().getPosv());
        } else {
          System.out.println("Panzer Nummer ("+i+") zerstoert!");
        } // end of if-else
      } // end of for
      
      System.out.print("\nPanzernummer fuer die Auswahl eingeben.");            // Panzer auswaehlen
      int  auswahl=input.nextInt();
      
      for (int i=0;i<Panzerliste1.length;i++) {                                 // Test, ob zerstoert
        if (Panzerliste1[i-1].getPlayer()==0) {
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
          break;
        case  1:                                                                // Panzer schießen
          boolean treffer = false;
          Position ziel = new Position(Panzerliste1[auswahl-1].shoot(richtung,kraft).getPosh(),Panzerliste1[auswahl-1].shoot(richtung,kraft).getPosv());
          for (int i=0;i<Panzerliste2.length;i++) {
            if (ziel.getPosh() == Panzerliste2[i].getPos().getPosh() && ziel.getPosv() == Panzerliste2[i].getPos().getPosh()) {
              treffer = true;
              Panzerliste2[i].setDestroy();
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
              
  public static boolean checkListeP(int x, int y, int Anz, Panzer [] PListe){
    boolean erg = true;
    
    for (int i=0;i<Anz;i++) {
      if (x == PListe[i].getPos().getPosh() && y == PListe[i].getPos().getPosv()) {
        erg = false;
      } // end of if
    } // end of for
    
    return erg;
    
  }
              
  public static boolean checkListeH(int x, int y, int Anz, Hindernis [] HListe){
    boolean erg = true;
    
    for (int i=0;i<Anz;i++) {
      if (x == HListe[i].getPos().getPosh() && y == HListe[i].getPos().getPosv()) {
        erg = false;
      } // end of if
    } // end of for
    
    return erg;
    
  } 
              
} // end of class Panzergame
            