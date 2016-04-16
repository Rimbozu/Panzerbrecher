
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    
    boolean test= true,richtig=true;
    int b,a,PAnz,HAnz,APanz=0,h,v,spiel=0 ,srichtig=0, hi=0;
    
    Hindernis [] Hindernisliste;
    Panzer [] PanzerlisteP1, PanzerlisteP2;
    
    Position ziel;
    
    int [] pg = new int [2];
    int [] pf = new int [2];
    
    System.out.print("2 Spieler Spielmodus");                                                                                        
    
    System.out.print("\nWie Breit soll das Spielfeld sein? ");                  //Spielfeldgr��e abfragen
    b =input.nextInt();                                                      
    System.out.print("Wie Lang soll das Spielfeld sein? ");
    a =input.nextInt();
    
    System.out.print("\nWie wie viele Panzer m�chten sie haben? ");             //Panzeranzahl abfragen
    PAnz =input.nextInt();
    System.out.print("Wie wie viele Hindernisse m�chten sie haben? ");          //Hindernisse abfragen
    HAnz =input.nextInt();
    
    PanzerlisteP1 = new Panzer[PAnz];                                           //1. Inizialisieren
    PanzerlisteP2 = new Panzer[PAnz];
    Hindernisliste = new Hindernis[HAnz];
    
    Spielfeld Feld1 = new Spielfeld(b, a, PanzerlisteP1, PanzerlisteP2, Hindernisliste);               
    
    
    
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
      System.out.println("\nMit welchen Panzer m�chten Sie die n�chste aktion ausf�hren?");
      System.out.println("Ihre Panzer:\n");
      
      if (spiel%2==1) {
        for (int i=1;i-1<PanzerlisteP1.length;i++) {                            // Verf�gbare Panzer1 anzeigen
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
        for (int i=1;i-1<PanzerlisteP2.length;i++) {                            // Verf�gbare Panzer2 anzeigen
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
      
      System.out.print("Panzer bewegen(0) oder schie�en(1)?  ");                  // Aktionsparameter eingeben
      int  action=input.nextInt();
      System.out.print("Richtung?(Numpad Richtig Bsp 9: oben rechts)  ");
      int  richtung=input.nextInt();
      System.out.print("Kraft? ");
      int  kraft=input.nextInt();
      
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
              
              if (ziel.getPosh()<0 || ziel.getPosh()>=b || ziel.getPosv()<0 || ziel.getPosv()>=a ) {
                PanzerlisteP1[auswahl-1].move(richtung,(k-1));
                PanzerlisteP1[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein den Kartenrand gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              pg=TestPanzer(ziel,PanzerlisteP2,k);                              // Gegnerischer Panzer Test
              pf=TestPanzer(ziel,PanzerlisteP1,k);                              // eigener Panzer Test
              
              if (hi>0) {                                                       // Hindernis gerammt
                PanzerlisteP1[auswahl-1].move(richtung,hi-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/2);
                System.out.println("Du bist gegen ein Hindernis gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (pg[0]>0) {                                                        // Gegner gerammt
                PanzerlisteP1[auswahl-1].move(richtung,pg[0]-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/3);
                PanzerlisteP2[pg[1]].subHP(kraft/2);
                System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP2[pg[1]].getHP()<=0) {
                  System.out.println("Gegnerischer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf[0]>0) {                                                       // eigenen gerammt
                PanzerlisteP1[auswahl-1].move(richtung,pf[0]-1);
                PanzerlisteP1[auswahl-1].subHP(kraft/3);
                PanzerlisteP1[pf[1]].subHP(kraft/2);
                System.out.println("Du bist gegen einen eigenen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der andere Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP1[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP1[pf[1]].getHP()<=0) {
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
          case  1:                                                              // Panzer schie�en
            
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP1[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP1[auswahl-1].target(richtung,k).getPosv());
              
              for (int i=0;i<Hindernisliste.length;i++) {                       // Hinderniss Test
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  hi=k;
                  break;
                } // end of if
              } // end of for
              
              pg=TestPanzer(ziel,PanzerlisteP2,k);                              // Gegnerischer Panzer Test
              pf=TestPanzer(ziel,PanzerlisteP1,k);                              // eigener Panzer Test
              
              if (ziel.getPosh()<0 || ziel.getPosh()>=b ||ziel.getPosv()<0 || ziel.getPosv()>=a ) {
                System.out.println("Du hast gegen den Kartenrand geschossen!");
                break;
              } // end of if
              
              if (hi>0) {                                                       // Hindernis getroffen
                System.out.println("Du hast gegen ein Hindernis geschossen!");
                break;
              } // end of if
              
              if (pg[0]>0) {                                                    // Gegner getroffen
                PanzerlisteP2[pg[1]].subHP(PanzerlisteP1[auswahl-1].getDamage()*k/kraft);
                
                System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+PanzerlisteP1[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");
                if (PanzerlisteP2[pg[1]].getHP()==0) {
                  System.out.println("Der Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf[0]>0) {                                                    // eigenen getroffen
                PanzerlisteP2[pf[1]].subHP(PanzerlisteP1[auswahl-1].getDamage()*k/kraft);
                
                System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+PanzerlisteP1[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");
                if (PanzerlisteP1[pf[1]].getHP()==0) {
                  System.out.println("Der Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (k==kraft) {                                                   // nichts getroffen
                System.out.println("\nDu hast nichts getroffen.");      
                break;
              } // end of if
            } // end of for
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
              
              pg=TestPanzer(ziel,PanzerlisteP1,k);                              // Gegnerischer Panzer Test
              pf=TestPanzer(ziel,PanzerlisteP2,k);                              // eigener Panzer Test
              
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
                System.out.println("Du bist gegen ein Hindernis gefahren und hast "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!"); 
                } // end of if
                break;
              } // end of if
              
              if (pg[0]>0) {                                                    // Gegner gerammt
                PanzerlisteP2[auswahl-1].move(richtung,pg[0]-1);
                PanzerlisteP2[auswahl-1].subHP(kraft/3);
                PanzerlisteP1[pg[1]].subHP(kraft/2);
                System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP1[pg[1]].getHP()<=0) {
                  System.out.println("Gegnerischer Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf[0]>0) {                                                       // eigenen gerammt
                PanzerlisteP2[auswahl-1].move(richtung,pf[0]-1);
                PanzerlisteP2[auswahl-1].subHP(kraft/3);
                PanzerlisteP2[pf[1]].subHP(kraft/2);
                System.out.println("Du bist gegen einen eigenen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
                System.out.println("Der andere Panzer hat "+kraft/2+" Schaden genommen!");
                if (PanzerlisteP2[auswahl-1].getHP()<=0) {
                  System.out.println("Eigener Panzer wurde zerstoert!");
                } // end of if
                if (PanzerlisteP2[pf[1]].getHP()<=0) {
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
            
          case  1:                                                              // Panzer schie�en
            for (int k=1;k<=kraft;k++) {
              ziel = new Position(PanzerlisteP2[auswahl-1].target(richtung,k).getPosh(),PanzerlisteP2[auswahl-1].target(richtung,k).getPosv());
              
              for (int i=0;i<Hindernisliste.length;i++) {                       // Hinderniss Test
                if (ziel.getPosh() == Hindernisliste[i].getPos().getPosh() && ziel.getPosv() == Hindernisliste[i].getPos().getPosv()) {
                  hi=k;
                  break;
                } // end of if
              } // end of for
              
              pg=TestPanzer(ziel,PanzerlisteP1,k);                              // Gegnerischer Panzer Test
              pf=TestPanzer(ziel,PanzerlisteP2,k);                              // eigener Panzer Test
              
              if (ziel.getPosh()<0 || ziel.getPosh()>=b ||ziel.getPosv()<0 || ziel.getPosv()>=a ) {
                System.out.println("Du hast gegen den Kartenrand geschossen!");
                break;
              } // end of if
              
              if (hi>0) {                                                       // Hindernis getroffen
                System.out.println("Du hast gegen ein Hindernis geschossen!");
                break;
              } // end of if
              
              if (pg[0]>0) {                                                    // Gegner getroffen
                PanzerlisteP1[pg[1]].subHP(PanzerlisteP2[auswahl-1].getDamage()*k/kraft);
                
                System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+PanzerlisteP2[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");
                if (PanzerlisteP1[pg[1]].getHP()==0) {
                  System.out.println("Der Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (pf[0]>0) {                                                    // eigenen getroffen
                PanzerlisteP1[pf[1]].subHP(PanzerlisteP2[auswahl-1].getDamage()*k/kraft);
                
                System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+PanzerlisteP2[auswahl-1].getDamage()*k/kraft+" Schaden gemacht.");
                if (PanzerlisteP2[pf[1]].getHP()==0) {
                  System.out.println("Der Panzer wurde zerstoert!");
                } // end of if
                break;
              } // end of if
              
              if (k==kraft) {                                                   // nichts getroffen
                System.out.println("\nDu hast nichts getroffen.");      
                break;
              } // end of if
            } // end of for
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
    
    //Panzertest
  public static int [] TestPanzer(Position ziel, Panzer [] Liste, int k){
    int [] erg = new int [2];
    for (int i=0;i<Liste.length;i++) {                       
      if (ziel.getPosh() == Liste[i].getPos().getPosh() && ziel.getPosv() == Liste[i].getPos().getPosv()) {
        erg[0]=k;
        erg[1]=i;
        break;
      } // end of if
    } // end of for 
    return erg;
  } // end of TestPanzer           
} // end of class Panzergame
  