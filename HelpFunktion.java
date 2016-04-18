import java.util.*;

class HelpFunktion{
  public static int EingabeInt(int min, int max,Scanner input){
    int erg=0;
    while (true) {
      System.out.print("Bereich von "+min+" - "+max+" : ");
      erg = input.nextInt();
  
      if (erg>=min && erg<=max) {
        break;
      } // end of if
      
      System.out.println("Eingegebener Wert nicht im Bereich.");
      System.out.println("Neuen Wert bitte eingeben.");
    } // end of while
    return erg;
  } // end of EingabeInt
    
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
    System.out.println("Panzertypen ausw�hlen ("+Wert[0]+" Punkte zu vergeben):");
    System.out.println("Typ 1("+Wert[1]+" Punkte): Normale Panzer, 10 HP, 5 DMG");
    System.out.println("Typ 2("+Wert[2]+" Punkte): Schwerer Panzer, 20 HP, 10 DMG");
    System.out.println("Typ 3("+Wert[3]+" Punkte): Panzerjaeger, 15 HP, 5 DMG\n");
  }
    
  public static int showPanzerliste(Panzer [] Liste,Scanner input){
    int auswahl=0;
    for (int i=1;i-1<Liste.length;i++) {                                        // Verf�gbare Panzer1 anzeigen
      if (Liste[i-1].getTyp().getHP()>0) {
        System.out.println("Pz "+i+", Position: ("+Liste[i-1].getPos().getPosh()+")("+Liste[i-1].getPos().getPosv()+"), "+Liste[i-1].getTyp().toString());
      } else {
        System.out.println("Panzer Nummer ("+i+") zerstoert!");
      } // end of if-else
    } // end of for
    
    System.out.println("\nPanzernummer fuer die Auswahl eingeben.");              // Panzer auswaehlen
    auswahl=EingabeInt(1,Liste.length,input);
    
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
  
  




}