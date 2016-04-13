
import java.util.*;

public class Panzergame {
  
  public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);
    
    System.out.println("Willkommen zum Panzerspiel.\n");
    System.out.println("Idee von Benjamin Böhm.");                              // Anfangsausgabe
    System.out.println("Programmiert von Florian Kaselow.\n");
    
    
    System.out.println("Wie Breit soll das Spielfeld sein?");                   //Spielfeldgröße abfragen
    int b =input.nextInt();                                                     
    System.out.println("Wie Lang soll das Spielfeld sein?");
    int a =input.nextInt();
    
    System.out.println("Wie wie viele Panzer möchten sie haben?");              //Panzeranzahl abfragen
    int PAnz =input.nextInt();
    System.out.println("Wie wie viele Hindernisse möchten sie haben?");         //Hindernisse abfragen
    int HAnz =input.nextInt();
    
    
    
    
    
    Panzer [] Panzerliste = new Panzer[PAnz];  
    Hindernis [] Hindernisliste = new Hindernis[HAnz];                               
    int [][] Aliste = new int[PAnz+HAnz][3];
    
    Spielfeld Feld1 = new Spielfeld(b,a,Aliste);                           //Spielfeld inizialisieren           
    
    int x,y,z=0,richtig=0;
    
    for (int i=0;i<PAnz;i++) {
      
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
    
    for (int i=0;i<HAnz;i++) {
      
      x=(int) (Math.random()*b);
      y=(int) (Math.random()*a);
      
      for (int j=0;j<i;j++) {
        if (x != Aliste[j][0] && y != Aliste[j][1]) {
          richtig +=1;
        } // end of if
      } // end of for
      
      if (richtig==i) {
        Hindernisliste[i]= new Hindernis(x,y);
        Aliste[i+z][0]=x;
        Aliste[i+z][1]=y;
        Aliste[i+z][2]=2;
      } else {
        i -=1;
      } // end of if-else  
      richtig=0;
    } // end of for
    
    Feld1.print();
    
    boolean exit= true;
    
    while (exit) { 
      System.out.println("Mit welchen Panzer möchten sie die nächste aktion ausführen?");
      System.out.println("Ihre Panzer:\n");
      for (int i=1;i-1<Panzerliste.length;i++) {
        System.out.println("Panzer Nummer ("+i+") an Position: "+Panzerliste[i-1].getPos().getPosb()+" - "+Panzerliste[i-1].getPos().getPosa());
      } // end of for
      int  auswahl=input.nextInt();
      
      System.out.println("Panzer bewegen(0) oder schießen(1)?");
      int  action=input.nextInt();
      
      System.out.println("Richtung?(oben=0,rechts=1,unten=2,links=3)");
      int  richtung=input.nextInt();
      
      System.out.println("Kraft?");
      int  kraft=input.nextInt();
      
      switch (action) {
        case  0: 
          Panzerliste[auswahl-1].move(richtung,kraft);
          Aliste[auswahl-1][0]=Panzerliste[auswahl-1].getPos().getPosb();
          Aliste[auswahl-1][1]=Panzerliste[auswahl-1].getPos().getPosa();
          break;
        case  1: 
          
          break;
        default: 
          
      } // end of switch
      
      Feld1.print();
      
      //System.out.println("Spiel beenden?(0)(1)");
      //int  Iexit=input.nextInt();
      
      //if (Iexit==0) {
      //  break;
      //} // end of if
    } // end of while
    
    
  } // end of main
  
} // end of class Panzergame
