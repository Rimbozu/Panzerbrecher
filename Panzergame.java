
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
    
    int h,v,srichtig=0;
    boolean richtig = true;
    
    
    for (int i=0;i<PAnz;i++) {                                                  // Panzer Spieler 1 erzeugen
      
      h=(int) (Math.random()*b);
      v=(int) (Math.random()*a);
      
      richtig=checkListeP(h,v,i,PanzerlisteP1);
      if (richtig) {
        PanzerlisteP1[i]= new Panzer(h,v,1);
      } else {
        i -=1;
      } // end of if-else 
      
      richtig=true;
    } // end of for
    
    richtig=true;
    h=v=0;
    
    for (int i=0;i<PAnz;i++) {                                                  // Panzer Spieler 2 erzeugen
      
      h=(int) (Math.random()*b);
      v=(int) (Math.random()*a);
      
      richtig=checkListeP(h,v,PanzerlisteP1.length,PanzerlisteP1);
      if (richtig) {
        richtig=checkListeP(h,v,i,PanzerlisteP2);
        if (richtig) {
          PanzerlisteP2[i]= new Panzer(h,v,2);
        } else {
          i -=1;
        } // end of if-else  
      } else {
        i -=1;
      } // end of if-else
      
      richtig=true;
    } // end of for
    
    richtig=true;
    h=v=0;
    
    for (int i=0;i<HAnz;i++) {                                                  // Hindernisse erzeugen
      
      h=(int) (Math.random()*b);
      v=(int) (Math.random()*a);
      
      richtig=checkListeP(h,v,PanzerlisteP1.length,PanzerlisteP1);
      if (richtig) {
        richtig=checkListeP(h,v,PanzerlisteP2.length,PanzerlisteP2);
        if (richtig) {
          richtig=checkListeH(h,v,i,Hindernisliste);
          if (richtig) {
            Hindernisliste[i]= new Hindernis(h,v);
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
      int hi=0,p=0, pz=0,pf=0,pzf=0;
      
      if (spiel%2==1) {                                                         // Spieler 1 Anweisungen
        switch (action) {
          case  0:                                                              // Panzer bewegen
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP1[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP1[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<Hindernisliste.length;i++) {
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  hi=k;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP2.length;i++) {                        // Gegnerischer Panzer Test
                if (ziel.getPosh() == PanzerlisteP2[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP2[i].getPos().getPosv()) {
                  pz=i;
                  p=k;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP1.length;i++) {                        // eigener Panzer Test
                if (i!=auswahl-1) {
                  if (ziel.getPosh() == PanzerlisteP1[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP1[i].getPos().getPosv()) {
                    pf=k;
                    pzf=i;
                    break;
                  } // end of if
                } // end of for
              } // end of if
              
              if (ziel.getPosh()<0 || ziel.getPosh()>=b ||ziel.getPosv()<0 || ziel.getPosv()>=a ) {
                PanzerlisteP1[auswahl-1].move(richtung,(k-1));
                PanzerlisteP1[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein den Kartenrand gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (hi>0) {                                                       // Hindernis gerammt
                PanzerlisteP1[auswahl-1].move(richtung,hi-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein Hindernis gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (p>0) {                                                        // Gegner gerammt
                PanzerlisteP1[auswahl-1].move(richtung,p-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/3);
                PanzerlisteP2[pz].subHP(kraft/2);
                System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP2[pz].getHP()<=0) {
                  System.out.println("Gegnerischer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf>0) {                                                       // eigenen gerammt
                PanzerlisteP1[auswahl-1].move(richtung,pf-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/3);
                PanzerlisteP1[pzf].subHP(kraft/2);
                System.out.println("Du bist gegen einen eigenen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der andere Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP1[pzf].getHP()<=0) {
                  System.out.println("Anderer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (k==kraft) {                                                   // nichts gerammt
                PanzerlisteP1[auswahl-1].move(richtung,kraft);         
                break;
              } // end of if
            } // end of for
            break;
          case  1:                                                              // Panzer schießen
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP1[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP1[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<PanzerlisteP2.length;i++) {
                if (ziel.getPosh() == PanzerlisteP2[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP2[i].getPos().getPosv()) {
                  treffer = true;
                  PanzerlisteP2[i].subHP(PanzerlisteP1[auswahl-1].getDamage()*k/kraft);
                  
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
              
              for (int i=0;i<Hindernisliste.length;i++) {                       // Hinderniss Test
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  hi=k;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP1.length;i++) {                        // Gegnerischer Panzer Test
                if (ziel.getPosh() == PanzerlisteP1[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP1[i].getPos().getPosv()) {
                  p=k;
                  pz=i;
                  break;
                } // end of if
              } // end of for
              
              for (int i=0;i<PanzerlisteP2.length;i++) {                        // eigener Panzer Test
                if (i!=auswahl-1) {
                  if (ziel.getPosh() == PanzerlisteP2[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP2[i].getPos().getPosv()) {
                    pf=k;
                    pzf=i;
                    break;
                  } // end of if
                } // end of for
              } // end of if
              
              if (ziel.getPosh()<0 || ziel.getPosh()>=b ||ziel.getPosv()<0 || ziel.getPosv()>=a ) {
                PanzerlisteP2[auswahl-1].move(richtung,(k-1));
                PanzerlisteP2[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein den Kartenrand gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (hi>0) {                                                       // Hindernis gerammt
                PanzerlisteP2[auswahl-1].move(richtung,hi-1);
                PanzerlisteP2[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein Hindernis Gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (p>0) {                                                        // Gegner gerammt
                PanzerlisteP2[auswahl-1].move(richtung,p-1);
                PanzerlisteP2[auswahl-1].subHP(kraft/3);
                PanzerlisteP1[pz].subHP(kraft/2);
                System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP1[pz].getHP()<=0) {
                  System.out.println("Gegnerischer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf>0) {                                                       // eigenen gerammt
                PanzerlisteP2[auswahl-1].move(richtung,pf-1);
                PanzerlisteP2[auswahl-1].subHP(kraft/3);
                PanzerlisteP2[pzf].subHP(kraft/2);
                System.out.println("Du bist gegen einen eigenen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der andere Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP2[pzf].getHP()<=0) {
                  System.out.println("Anderer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (k==kraft) {                                                   // nichts gerammt
                PanzerlisteP2[auswahl-1].move(richtung,kraft);         
                break;
              } // end of if
            } // end of for
            break;
            
          case  1:                                                              // Panzer schießen
            for (int k=1;k<=kraft;k++ ) {
              ziel = new Position(PanzerlisteP2[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP2[auswahl-1].target(richtung,k).getPosv());
              for (int i=0;i<PanzerlisteP1.length;i++) {
                if (ziel.getPosh() == PanzerlisteP1[i].getPos().getPosh() && ziel.getPosv() == PanzerlisteP1[i].getPos().getPosv()) {
                  treffer = true;
                  PanzerlisteP1[i].subHP(PanzerlisteP2[auswahl-1].getDamage()*k/kraft);
                  
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
            
            if (treffer==false) {                                               // Treffer ausgabe
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
  } // end of CheckListeP
              
  public static boolean checkListeH(int x, int y, int Anz, Hindernis [] HListe){
    boolean erg = true;
    for (int i=0;i<Anz;i++) {
      if (x == HListe[i].getPos().getPosh() && y == HListe[i].getPos().getPosv()) {
        erg = false;
      } // end of if
    } // end of for
    return erg;
  } // end of CheckListeH
              
  public static int getMin(int  a, int b, int c){
    if (a==0) {
      if (b==0) {
        if (c==0) {
          return 0;
        } else {
          return 3;
        } // end of if-else
      } else {
        if (c==0) {
          return 2;
        } else {
          if (b<c) {
            return 2;
          } else {
            return 3;
          } // end of if-else
        } // end of if-else
      } // end of if-else
    } else {
      if (b==0) {
        if (c==0) {
          return 1;
        } else {
          if (a<c) {
            return 1;
          } else {
            return 3;
          } // end of if-else
        } // end of if-else
      } else {
        if (c==0) {
          if (a<b) {
            return 1;
          } else {
            return 2;
          } // end of if-else
        } else {
          if (a<b) {
            if (a<c) {
              return 1;
            } else {
              return 3;
            } // end of if-else
          } else {
            if (b<c) {
              return 2;
            } else {
              return 3;
            } // end of if-else
          } // end of if-else
        } // end of if-else
      } // end of if-else
    } // end of if-else
  } // end of getMin
              
} // end of class Panzergame
              