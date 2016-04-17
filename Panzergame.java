
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    
    boolean test= true,richtig=true;
    int b=0,a=0,c,PAnz,HAnz,APanz=0,h,v,spiel=0 ,srichtig=0;
    int [] TypenWert = new int [4];
    int Restpunkte=0;
    int T1P1,T2P1,T3P1,T1P2,T2P2,T3P2;
    
    Hindernis [] Hindernisliste;
    Panzer [] PanzerlisteP1, PanzerlisteP2;
    
    System.out.print("2 Spieler Spielmodus"); 
    
    System.out.println("\nWie Breit soll das Spielfeld sein? ");                //Spielfeldgröße abfragen 
    b=FeldParameter(5,15,input);                                                                      
    
    System.out.println("Wie Lang soll das Spielfeld sein? ");
    a=FeldParameter(5,40,input);
    
    c=a*b;
    
    System.out.println("\nWie wie viele Panzerpunkte möchten sie haben? (wenig-viele)");             //Panzeranzahl abfragen
    PAnz=FeldParameter(1,c/20,input);
    
    System.out.println("\nWie wie viele Hindernisse möchten sie haben? ");          //Hindernisse abfragen
    HAnz=FeldParameter(0,c/10,input);
    
    TypenWert[0]=c;
    TypenWert[1]=c/(PAnz*2);
    TypenWert[2]=TypenWert[1]*2;
    TypenWert[3]=TypenWert[1]/10*15;
    
    System.out.println("\nSpieler 1 Panzerauswahl:");                           // Panzerauswahl Spieler 1
    showPanzertypen(TypenWert);
    
    System.out.println("Anzahl Typ 1 Panzer:");
    T1P1=FeldParameter(0,TypenWert[0]/TypenWert[1],input);
    Restpunkte=TypenWert[0]-T1P1*TypenWert[1];
    System.out.println("\n"+Restpunkte+" Punkte noch vorhanden");
    System.out.println("Anzahl Typ 2 Panzer:");
    T2P1=FeldParameter(0,(Restpunkte)/TypenWert[2],input);
    Restpunkte -=T2P1*TypenWert[2];
    System.out.println("\n"+Restpunkte+" Punkte noch vorhanden");
    System.out.println("Anzahl Typ 2 Panzer:");
    T3P1=FeldParameter((Restpunkte)/TypenWert[3],(Restpunkte)/TypenWert[3],input);
    
    Restpunkte=TypenWert[0];
    System.out.println("\nSpieler 2 Panzerauswahl:");                           // Panzerauswahl Spieler 2
    showPanzertypen(TypenWert);
    
    System.out.println("Anzahl Typ 1 Panzer:");
    T1P2=FeldParameter(0,TypenWert[0]/TypenWert[1],input);
    Restpunkte -= T1P2*TypenWert[1];
    System.out.println("\n"+Restpunkte+" Punkte noch vorhanden");
    System.out.println("Anzahl Typ 2 Panzer:");
    T2P2=FeldParameter(0,(Restpunkte)/TypenWert[2],input);
    Restpunkte -=T2P2*TypenWert[2];
    System.out.println("\n"+Restpunkte+" Punkte noch vorhanden");
    System.out.println("Anzahl Typ 2 Panzer:");
    T3P2=FeldParameter((Restpunkte)/TypenWert[3],(Restpunkte)/TypenWert[3],input);
    
    PanzerlisteP1 = new Panzer[T1P1+T2P1+T3P1];                                           //1. Inizialisieren
    PanzerlisteP2 = new Panzer[T1P2+T2P2+T3P2];
    Hindernisliste = new Hindernis[HAnz];
    
    Spielfeld Feld1 = new Spielfeld(b, a, PanzerlisteP1, PanzerlisteP2, Hindernisliste);               
    
    for (int i=0;i<PanzerlisteP1.length;i++) {                                                  // Panzer Spieler 1 erzeugen
      h=(int) (Math.random()*b);
      v=(int) (Math.random()*a);
      
      richtig=checkListeP(h,v,i,PanzerlisteP1);
      if (richtig) {
        
        if (i+1<=T1P1) {
          PanzerlisteP1[i]= new Panzer(h,v,1,0);
        } else {
          if (i+1+T1P1<=T2P1) {
            PanzerlisteP1[i]= new Panzer(h,v,1,1);
          } else {
            PanzerlisteP1[i]= new Panzer(h,v,1,2);
          }// end of if-else
        } // end of if-else
        
      } else {
        i -=1;
      } // end of if-else 
      richtig=true;
    } // end of for
    
    richtig=true;
    h=v=0;
    
    for (int i=0;i<PanzerlisteP2.length;i++) {                                  // Panzer Spieler 2 erzeugen
      h=(int) (Math.random()*b);
      v=(int) (Math.random()*a);
      
      richtig=checkListeP(h,v,PanzerlisteP1.length,PanzerlisteP1);
      if (richtig) {
        richtig=checkListeP(h,v,i,PanzerlisteP2);
        if (richtig) {
          if (i+1<=T1P2) {
            PanzerlisteP2[i]= new Panzer(h,v,2,0);
          } else {
            if (i+1+T1P2<=T2P2) {
              PanzerlisteP2[i]= new Panzer(h,v,2,1);
            } else {
              PanzerlisteP2[i]= new Panzer(h,v,2,2);
            }// end of if-else
          } // end of if-else
          
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
    Feld1.printLegende();
    
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
        auswahl=showPanzerliste(PanzerlisteP1,input);
      } else {
        auswahl=showPanzerliste(PanzerlisteP2,input);
      } // end of if-else
      
      System.out.print("Panzer bewegen(0) oder schießen(1)?  ");                  // Aktionsparameter eingeben
      int  action=input.nextInt();
      System.out.print("Richtung?(Numpad Richtig Bsp 9: oben rechts)  ");
      int  richtung=input.nextInt();
      System.out.print("Kraft? ");
      int  kraft=input.nextInt();
      
      if (spiel%2==1) {                                                         // Spieler 1 Anweisungen
        switch (action) {
          case  0:                                                              // Panzer bewegen
            Feld1.Panzerbewegen(PanzerlisteP1,PanzerlisteP2,auswahl,richtung,kraft);
            break;
          case  1:                                                              // Panzer schießen
            Feld1.Panzerschiessen(PanzerlisteP1,PanzerlisteP2,auswahl,richtung,kraft);
            break;
            
        } // end of switch
      } else {                                                                  // Spieler 2 Anweisungen
        switch (action) {
          case  0:                                                              // Panzer bewegen
            Feld1.Panzerbewegen(PanzerlisteP2,PanzerlisteP1,auswahl,richtung,kraft);
            break;
          case 1:
            Feld1.Panzerschiessen(PanzerlisteP2,PanzerlisteP1,auswahl,richtung,kraft);
            break;
            
        } // end of switch
      } // end of if-else
      
      System.out.print("Aktuelles Spielfeld anzeigen? (ja=1): ");
      int  feld=input.nextInt();
      
      if (feld==1) {
        Feld1.print();
      } // end of if 
      
      exit=TestSpielende(PanzerlisteP1);
      exit=TestSpielende(PanzerlisteP2);
      
      if (exit) {
        break;
      } // end of if
      
    } // end of while
  } // end of main
  
  public static int FeldParameter(int min, int max,Scanner input){
    int erg=0;
    while (true) { 
      System.out.print("Bereich von "+min+" - "+max+" : ");
      erg =input.nextInt(); 
      if (erg>=min && erg<=max) {
        break;
      } // end of if
      System.out.println("Eingegebener Wert nicht im Bereich.");
      System.out.println("Neuen Wert bitte eingeben.");
    } // end of while
    return erg;
  } // end of FeldParameter
    
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
  
  public static void showPanzertypen(int [] Wert){
    System.out.println("Panzertypen auswählen ("+Wert[0]+" Punkte zu vergeben):");
    System.out.println("Typ 1("+Wert[1]+" Punkte): Normale Panzer, 10 HP, 5 DMG");
    System.out.println("Typ 2("+Wert[2]+" Punkte): Schwerer Panzer, 20 HP, 10 DMG");
    System.out.println("Typ 3("+Wert[3]+" Punkte): Panzerjaeger, 15 HP, 5 DMG\n");
  }
    
  public static int showPanzerliste(Panzer [] Liste,Scanner input){
    int auswahl=0;
    for (int i=1;i-1<Liste.length;i++) {                                        // Verfügbare Panzer1 anzeigen
      if (Liste[i-1].getTyp().getHP()>0) {
        System.out.println("Pz "+i+", Position: ("+Liste[i-1].getPos().getPosh()+")("+Liste[i-1].getPos().getPosv()+"), "+Liste[i-1].getTyp().toString());
      } else {
        System.out.println("Panzer Nummer ("+i+") zerstoert!");
      } // end of if-else
    } // end of for
    
    System.out.println("\nPanzernummer fuer die Auswahl eingeben.");              // Panzer auswaehlen
    auswahl=FeldParameter(1,Liste.length,input);
    
    
    for (int i=0;i<Liste.length;i++) {                                          // Test, ob zerstoert
      if (Liste[auswahl-1].getTyp().getHP()<=0) {               
        System.out.println("Dieser Panzer ist zerstoert, bitte waehle einen anderen Panzer.");
        System.out.print("\nPanzernummer fuer die Auswahl eingeben. ");
        auswahl=input.nextInt();
        i=0;
      } // end of if
    } // end of for
    return auswahl;
  }
   
  public static int [] TestPanzer(Position ziel, Panzer [] Liste, int k){       //Test auf Panzer
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
  
  public static boolean TestSpielende(Panzer [] Liste){                         //Test auf Spielende
    int destroy=0;
    for (int i=0;i<Liste.length;i++) {    
      if (Liste[i].getTyp().getHP()<=0) {
        destroy+=1;
      } // end of if
      if (destroy==Liste.length) {
        
        System.out.println("Der Gegner wurde besiegt. Spieler Nr."+Liste[0].getPlayer()+" hat gewonnen");
        return true;
      } // end of if
    } // end of for
    return false;
  }
  
  
    
} // end of class Panzergame
