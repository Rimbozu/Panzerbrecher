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
  
  public int getSeiteA(){
    return feld.length;
  }
    
  public int getSeiteB(){
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
  
  public void print(){
    String bild = new String();
    boolean test = false;
    boolean belegt =false;
    
    bild="  ";
    
    for (int i=0;i<getSeiteB();i++) {
      if (i%5==0) {
        bild = bild + i/5;
      } else {
        bild = bild + " ";
      } // end of if-else
    } // end of for
    
    System.out.println("\n"+bild);
    bild="";
    
    for (int i=0;i<getSeiteB();i++) {
      bild=bild + "+";
    } // end of for
    
    System.out.println(" +"+bild+"+");
    bild="";
    
    int pl=0;
    
    for (int i=0;i<getSeiteA();i++) {
      for (int j=0;j<getSeiteB();j++) {
        belegt=false;
        Position hier = new Position(i,j);
        
        for (int t=0;t<getPListe1();t++) {
          if (hier.getPosh()==PListe1[t].getPos().getPosh() && hier.getPosv()==PListe1[t].getPos().getPosv()) {
            if (PListe1[t].getTyp().getHP()<=0) {
              bild = bild+"h";
            } else {
              bild = bild+"A";
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
                bild = bild+"B";
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
    
    for (int i=0;i<getSeiteB();i++) {
      bild=bild + "+";
    } // end of for
    
    System.out.println(" +"+bild+"+");
    
    bild="  ";
    
    for (int i=0;i<getSeiteB();i++) {
      if (i%5==0) {
        bild = bild + i/5;
      } else {
        bild = bild + " ";
      } // end of if-else
    } // end of for
    
    System.out.println(bild+"\n");
    
  }
    
    
    
}
