class Spielfeld {
  
  private int [][] feld;
  private int [][] PListe;
  private Panzer [] PListe1;
  private Panzer [] PListe2;
  private Hindernis [] HListe;
  
  public Spielfeld (){
    this(20,50);
  }
  
  public Spielfeld (int v, int h){
    feld = new int [v][h];
  }
  
  public Spielfeld (int v, int h, Panzer [] Liste1, Panzer [] Liste2, Hindernis [] Liste3){
    this(v,h);
    PListe1 = Liste1;
    PListe2 = Liste2;
    HListe = Liste3;
  }
  
  public int getSeiteH(){
    return feld.length;
  }
    
  public int getSeiteV(){
    return feld[0].length;
  }  
  
  public int getPListe1(){
    return PListe1.length;
  }
  
  public int getPListe2(){
    return PListe2.length;
  }
  
  public int getHListe(){
    return HListe.length;
  }
  
  public void Panzerbewegen(Panzer [] Panzerliste1, Panzer [] Panzerliste2,int auswahl,int richtung, int kraft){
    int hi=0;
    int [] pg;
    int [] pf;
    Position ziel;
    
    for (int k=1;k<=kraft;k++) {
      ziel = new Position(Panzerliste1[auswahl-1].target(richtung,k).getPosh(),Panzerliste1[auswahl-1].target(richtung,k).getPosv());
      for (int i=0;i<HListe.length;i++) {
        if (ziel.getPosh() == HListe[i].getPos().getPosh() && ziel.getPosv() == HListe[i].getPos().getPosv()) {
          hi=k;
          break;
        } // end of if
      } // end of for
      
      if (ziel.getPosh()<0 || ziel.getPosh()>=getSeiteH() || ziel.getPosv()<0 || ziel.getPosv()>=getSeiteV() ) {
        Panzerliste1[auswahl-1].move(richtung,(k-1));
        Panzerliste1[auswahl-1].getTyp().subHP(kraft/2);
        System.out.println("Du bist gegen ein den Kartenrand gefahren und hast "+kraft/2+" Schaden genommen!");
        if (Panzerliste1[auswahl-1].getTyp().getHP()<=0) {
          System.out.println("Eigener Panzer wurde zerstoert!"); 
        } // end of if
        break;
      } // end of if
      
      pg=Panzergame.TestPanzer(ziel,Panzerliste2,k);                              // Gegnerischer Panzer Test
      pf=Panzergame.TestPanzer(ziel,Panzerliste1,k);                              // eigener Panzer Test
      
      if (hi>0) {                                                       // Hindernis gerammt
        Panzerliste1[auswahl-1].move(richtung,hi-1);
        Panzerliste1[auswahl-1].getTyp().subHP(kraft/2);
        System.out.println("Du bist gegen ein Hindernis gefahren und hast "+kraft/2+" Schaden genommen!");
        if (Panzerliste1[auswahl-1].getTyp().getHP()<=0) {
          System.out.println("Eigener Panzer wurde zerstoert!"); 
        } // end of if
        break;
      } // end of if
      
      if (pg[0]>0) {                                                        // Gegner gerammt
        Panzerliste1[auswahl-1].move(richtung,pg[0]-1);
        Panzerliste1[auswahl-1].getTyp().subHP(kraft/3);
        Panzerliste2[pg[1]].getTyp().subHP(kraft/2);
        System.out.println("Du bist gegen einen gegnerischen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
        System.out.println("Der gegnerische Panzer hat "+kraft/2+" Schaden genommen!");
        if (Panzerliste1[auswahl-1].getTyp().getHP()<=0) {
          System.out.println("Eigener Panzer wurde zerstoert!");
        } // end of if
        if (Panzerliste2[pg[1]].getTyp().getHP()<=0) {
          System.out.println("Gegnerischer Panzer wurde zerstoert!");
        } // end of if
        break;
      } // end of if
      
      if (pf[0]>0) {                                                       // eigenen gerammt
        Panzerliste1[auswahl-1].move(richtung,pf[0]-1);
        Panzerliste1[auswahl-1].getTyp().subHP(kraft/3);
        Panzerliste1[pf[1]].getTyp().subHP(kraft/2);
        System.out.println("Du bist gegen einen eigenen Panzer gefahren und hast "+kraft/3+" Schaden genommen!");
        System.out.println("Der andere Panzer hat "+kraft/2+" Schaden genommen!");
        if (Panzerliste1[auswahl-1].getTyp().getHP()<=0) {
          System.out.println("Eigener Panzer wurde zerstoert!");
        } // end of if
        if (Panzerliste1[pf[1]].getTyp().getHP()<=0) {
          System.out.println("Anderer Panzer wurde zerstoert!");
        } // end of if
        break;
      } // end of if
      
      if (k==kraft) {                                                   // nichts gerammt
        Panzerliste1[auswahl-1].move(richtung,kraft);         
        break;
      } // end of if
    } // end of for
  }
  
  public void Panzerschiessen(Panzer [] Panzerliste1, Panzer [] Panzerliste2,int auswahl,int richtung, int kraft){
    int hi=0;
    int [] pg;
    int [] pf;
    Position ziel;
    
    for (int k=1;k<=kraft;k++) {
      ziel = new Position(Panzerliste1[auswahl-1].target(richtung,k).getPosh(),Panzerliste1[auswahl-1].target(richtung,k).getPosv());
      
      for (int i=0;i<HListe.length;i++) {                       // Hinderniss Test
        if (ziel.getPosh() == HListe[i].getPos().getPosh() && ziel.getPosv() == HListe[i].getPos().getPosv()) {
          hi=k;
          break;
        } // end of if
      } // end of for
      
      pg=Panzergame.TestPanzer(ziel,Panzerliste2,k);                              // Gegnerischer Panzer Test
      pf=Panzergame.TestPanzer(ziel,Panzerliste1,k);                              // eigener Panzer Test
      
      if (ziel.getPosh()<0 || ziel.getPosh()>=getSeiteH() ||ziel.getPosv()<0 || ziel.getPosv()>=getSeiteV() ) {
        System.out.println("Du hast gegen den Kartenrand geschossen!");
        break;
      } // end of if
      
      if (hi>0) {                                                       // Hindernis getroffen
        System.out.println("Du hast gegen ein Hindernis geschossen!");
        break;
      } // end of if
      
      if (pg[0]>0) {                                                    // Gegner getroffen
        Panzerliste2[pg[1]].getTyp().subHP(Panzerliste1[auswahl-1].getTyp().getDMG()*k/kraft);
        
        System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+Panzerliste1[auswahl-1].getTyp().getDMG()*k/kraft+" Schaden gemacht.");
        if (Panzerliste2[pg[1]].getTyp().getHP()<=0) {
          System.out.println("Der Panzer wurde zerstoert!");
        } // end of if
        break;
      } // end of if
      
      if (pf[0]>0) {                                                    // eigenen getroffen
        Panzerliste2[pf[1]].getTyp().subHP(Panzerliste1[auswahl-1].getTyp().getDMG()*k/kraft);
        
        System.out.println("\nDu hast einen gegnerischen Panzer getroffen und "+Panzerliste1[auswahl-1].getTyp().getDMG()*k/kraft+" Schaden gemacht.");
        if (Panzerliste1[pf[1]].getTyp().getHP()<=0) {
          System.out.println("Der Panzer wurde zerstoert!");
        } // end of if
        break;
      } // end of if
      
      if (k==kraft) {                                                   // nichts getroffen
        System.out.println("\nDu hast nichts getroffen.");      
        break;
      } // end of if
    } // end of for
  }

  public void print(){
    String bild = new String();
    boolean test = false;
    boolean belegt =false;
    
    bild="  ";
    
    for (int i=0;i<getSeiteV();i++) {
      if (i%5==0) {
        bild = bild + i/5;
      } else {
        bild = bild + " ";
      } // end of if-else
    } // end of for
    
    System.out.println("\n"+bild);
    bild="";
    
    for (int i=0;i<getSeiteV();i++) {
      bild=bild + "+";
    } // end of for
    
    System.out.println(" +"+bild+"+");
    bild="";
    
    int pl=0;
    
    for (int i=0;i<getSeiteH();i++) {
      for (int j=0;j<getSeiteV();j++) {
        belegt=false;
        Position hier = new Position(i,j);
        
        for (int t=0;t<getPListe1();t++) {
          if (hier.getPosh()==PListe1[t].getPos().getPosh() && hier.getPosv()==PListe1[t].getPos().getPosv()) {
            if (PListe1[t].getTyp().getHP()<=0) {
              bild = bild+"h";
            } else {
              switch (PListe1[t].getTyp().getTypNr()) {
                case  0: 
                  bild = bild+"A";
                  break;
                case  1: 
                  bild = bild+"B";
                  break;
                case  2: 
                  bild = bild+"C";
                  break;  
                  
              } // end of switch
            } // end of if-else
            belegt=true;
            break;
          }
        } // end of for
        
        if (belegt == false) {
          for (int t=0;t<getPListe2();t++) {
            if (hier.getPosh()==PListe2[t].getPos().getPosh() && hier.getPosv()==PListe2[t].getPos().getPosv()) {
              if (PListe2[t].getTyp().getHP()<=0) {
                bild = bild+"h";
              } else {
                switch (PListe2[t].getTyp().getTypNr()) {
                  case  0: 
                    bild = bild+"N";
                    break;
                  case  1: 
                    bild = bild+"O";
                    break;
                  case  2: 
                    bild = bild+"P";
                    break;  
                } // end of switch
              } // end of if-else
              belegt=true;
              break;
            }
          } // end of for
        } // end of if
        
        if (belegt == false) {
          for (int t=0;t<getHListe();t++) {
            if (hier.getPosh()==HListe[t].getPos().getPosh() && hier.getPosv()==HListe[t].getPos().getPosv()) {
              bild = bild+"H";
              belegt=true;
              break;
            }
          } // end of for
        } // end of if
        
        if (belegt == false) {
          bild=bild+" ";
        } // end of if
      } // end of for
      
      if (i%5==0) {
        bild = i/5+"+" + bild +"+"+i/5;
      } else {
        bild =" +" + bild + "+";
      } // end of if-else
      
      System.out.println(bild);
      bild="";
    } // end of for
    
    for (int i=0;i<getSeiteV();i++) {
      bild=bild + "+";
    } // end of for
    
    System.out.println(" +"+bild+"+");
    
    bild="  ";
    
    for (int i=0;i<getSeiteV();i++) {
      if (i%5==0) {
        bild = bild + i/5;
      } else {
        bild = bild + " ";
      } // end of if-else
    } // end of for
    
    System.out.println(bild+"\n");
    
  }
    
  public void printLegende(){
    String legende = new String();
    System.out.println("\t\t\tSpieler 1 \tSpieler 2");
    System.out.println("Normaler Panzer: \t A \t\t N");
    System.out.println("Schwerer Panzer: \t B \t\t O");
    System.out.println("Panzerjaeger: \t\t C \t\t P"); 
  }  
    
}
