
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
    int spiel=0;
    while (true) {                                                              // Spielbegin
      boolean exit = false;
      spiel+=1; 
      int spieler,auswahl=0;
      
      if (spiel%2==1) {
        spieler = 1;
      } else {
        spieler = 2;
      } // end of if-else
      
      System.out.println("\nSpieler "+spieler+" ist an der Reihe.");
      System.out.println("\nMit welchen Panzer möchten Sie die nächste aktion ausführen?");
      System.out.println("Ihre Panzer:\n");
      
      if (spiel%2==1) {
        for (int i=1;i-1<PanzerlisteP1.length;i++) {                            // Verfügbare Panzer1 anzeigen
          if (PanzerlisteP1[i-1].getHP()>0) {
            System.out.println("Pz "+i+", Position: ("+PanzerlisteP1[i-1].getPos().getPosh()+")("+PanzerlisteP1[i-1].getPos().getPosv()+"), HP: "+PanzerlisteP1[i-1].getHP()+", DMG: "+PanzerlisteP1[i-1].getDamage());
          } else {
            System.out.println("Panzer Nummer ("+i+") zerstoert!");
          } // end of if-else
        } // end of for
        
        System.out.print("\nPanzernummer fuer die Auswahl eingeben.");          // Panzer auswaehlen
        auswahl=input.nextInt();
        
        for (int i=0;i<PanzerlisteP1.length;i++) {                              // Test, ob zerstoert
          if (PanzerlisteP1[auswahl-1].getPlayer()==0) {
            System.out.println("Dieser Panzer ist zerstoert, bitte waehle einen anderen Panzer.");
            System.out.print("\nPanzernummer fuer die Auswahl eingeben. ");
            auswahl=input.nextInt();
            i=0;
          } // end of if
        } // end of for
      } else {
        for (int i=1;i-1<PanzerlisteP2.length;i++) {                            // Verfügbare Panzer2 anzeigen
          if (PanzerlisteP2[i-1].getHP()>0) {
            System.out.println("Pz "+i+", Position: ("+PanzerlisteP2[i-1].getPos().getPosh()+")("+PanzerlisteP2[i-1].getPos().getPosv()+"), HP: "+PanzerlisteP2[i-1].getHP()+", DMG: "+PanzerlisteP2[i-1].getDamage());
          } else {
            System.out.println("Panzer Nummer ("+i+") zerstoert!");
          } // end of if-else
        } // end of for
        
        System.out.print("\nPanzernummer fuer die Auswahl eingeben.");          // Panzer auswaehlen
        auswahl=input.nextInt();
        
        for (int i=0;i<PanzerlisteP2.length;i++) {                              // Test, ob zerstoert
          if (PanzerlisteP2[auswahl-1].getPlayer()==0) {
            
            System.out.println("Dieser Panzer ist zerstoert, bitte waehle einen anderen Panzer.");
            System.out.print("\nPanzernummer fuer die Auswahl eingeben. ");
            auswahl=input.nextInt();
            i=0;
          } // end of if
        } // end of for
      } // end of if-else
      
      System.out.print("Panzer bewegen(0) oder schießen(1)?  ");                  // Aktionsparameter eingeben
      int  action=input.nextInt();
      System.out.print("Richtung?(Numpad Richtig Bsp 9: oben rechts)  ");
      int  richtung=input.nextInt();
      System.out.print("Kraft? ");
      int  kraft=input.nextInt();
      
      Position ziel;
      boolean treffer=false;
      int h=0,p=0, pz=0;
      
      if (spiel%2==1) {                                                         // Spieler 1 Anweisungen
        switch (action) {
          case  0:                                                              // Panzer bewegen
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP1[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP1[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<Hindernisliste.length;i++) {
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  treffer=true;
                  h=k;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP2.length;i++) {
                if (ziel.getPosh() == PanzerlisteP2[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP2[i].getPos().getPosv()) {
                  treffer=true;
                  pz=i;
                  p=k;
                  break;
                } // end of if
              } // end of for
              
              if (treffer) {
                if (h>0 && h<p || p>0 && h>0 && h<p || p==0) {
                  PanzerlisteP1[auswahl-1].move(richtung,h-1);
                  PanzerlisteP1[auswahl-1].setHP(kraft/2);
                  System.out.println("Du bist gegen ein Hindernis Gefahren und hast "+kraft/2+" Schaden genommen!");
                  if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                    System.out.println("Eigener Panzer wurde zerstoert!");
                  } // end of if
                } else {
                  PanzerlisteP1[auswahl-1].move(richtung,p-1);
                  PanzerlisteP1[auswahl-1].setHP(kraft/3);
                  PanzerlisteP2[pz].setHP(kraft/2);
                  System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                  System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                  if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                    System.out.println("Eigener Panzer wurde zerstoert!");
                  } // end of if
                  if (PanzerlisteP2[pz].getHP()<=0) {
                    System.out.println("Gegnerischer Panzer wurde zerstoert!");
                  } // end of if
                } // end of if-else
                break;
              } // end of if
            } // end of for
            
            if (treffer == false) {
              PanzerlisteP1[auswahl-1].move(richtung,kraft);
            } // end of if
            
            break;
          case  1:                                                              // Panzer schießen
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP1[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP1[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<PanzerlisteP2.length;i++) {
                if (ziel.getPosh() == PanzerlisteP2[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP2[i].getPos().getPosv()) {
                  treffer = true;
                  PanzerlisteP2[i].setHP(PanzerlisteP1[auswahl-1].getDamage()*k/kraft);
                  
                  System.out.println("\nPanzer getroffen und "+PanzerlisteP1[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");                      
                  if (PanzerlisteP2[i].getHP()==0) {
                    System.out.println("Panzer erfolgreich zerstoert!");
                  } // end of if
                  break;
                } // end of if
              } // end of for
              if (treffer) {
                break;
              } // end of if
            } // end of for
            
            if (treffer==false) {                                                    
              System.out.println("\nNichts getroffen.");
            } // end of if
            break;      
            
        } // end of switch
      } else {                                                                  // Spieler 2 Anweisungen
        switch (action) {
          case  0:                                                              // Panzer bewegen
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP2[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP2[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<Hindernisliste.length;i++) {
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  treffer=true;
                  h=k;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP1.length;i++) {
                if (ziel.getPosh() == PanzerlisteP1[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP1[i].getPos().getPosv()) {
                  treffer=true;
                  p=k;
                  pz=i;
                  break;
                } // end of if
              } // end of for
              
              if (treffer) {
                if (h>0 && h<p || p>0 && h>0 && h<p || p==0) {
                  PanzerlisteP2[auswahl-1].move(richtung,h-1);
                  PanzerlisteP2[auswahl-1].setHP(kraft/2);
                  System.out.println("Du bist gegen ein Hindernis Gefahren und hast "+kraft/2+" Schaden genommen!");
                  if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                    System.out.println("Eigener Panzer wurde zerstoert!");
                  } // end of if
                } else {
                  PanzerlisteP2[auswahl-1].move(richtung,p-1);
                  PanzerlisteP2[auswahl-1].setHP(kraft/3);
                  PanzerlisteP1[pz].setHP(kraft/2);
                  System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                  System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                  if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                    System.out.println("Eigener Panzer wurde zerstoert!");
                  } // end of if
                  if (PanzerlisteP1[pz].getHP()<=0) {
                    System.out.println("Gegnerischer Panzer wurde zerstoert!");
                  } // end of if
                } // end of if-else
                break;
              } // end of if
            } // end of for
            
            if (treffer == false) {
              PanzerlisteP2[auswahl-1].move(richtung,kraft);
            } // end of if
            
            break;
          case  1:                                                              // Panzer schießen
            for (int k=1;k<=kraft;k++ ) {
              ziel = new Position(PanzerlisteP2[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP2[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<PanzerlisteP1.length;i++) {
                if (ziel.getPosh() == PanzerlisteP1[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP1[i].getPos().getPosv()) {
                  treffer = true;
                  PanzerlisteP1[i].setHP(PanzerlisteP2[auswahl-1].getDamage()*k/kraft);
                  
                  System.out.println("\nPanzer getroffen und "+PanzerlisteP2[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");
                  if (PanzerlisteP1[i].getHP()==0) {
                    System.out.println(" und zerstoert!");
                  } // end of if
                  break;
                } // end of if
              } // end of for
              if (treffer) {
                break;
              } // end of if
            } // end of for
            
            if (treffer==false) {                                                      // Treffer ausgabe
              System.out.println("\nNichts getroffen.");
            } // end of if
            break;      
            
        } // end of switch
      } // end of if-else
      
      System.out.print("Aktuelles Spielfeld anzeigen? (ja=1): ");
      int  feld=input.nextInt();
      
      if (feld==1) {
        Feld1.print();
      } // end of if 
      int destroy=0;
      if (spiel%2==1) {
        for (int i=0;i<PanzerlisteP2.length;i++) {    
          if (PanzerlisteP2[i].getPlayer()==0) {
            destroy+=1;
          } // end of if
          if (destroy==PanzerlisteP2.length) {
            System.out.println("Der Gegner wurde besiegt. Spieler Nr. 1 hat gewonnen");
            exit=true;
            break;
          } // end of if
        } // end of for
      } else {
        for (int i=0;i<PanzerlisteP1.length;i++) {    
          if (PanzerlisteP1[i].getPlayer()==0) {
            destroy+=1;
          } // end of if
          if (destroy==PanzerlisteP2.length) {
            System.out.println("Der Gegner wurde besiegt. Spieler Nr. 2 hat gewonnen");
            exit=true;
            break;
          } // end of if
        }
      } // end of if-else
      
      destroy=0;
      
      if (exit) {
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
              