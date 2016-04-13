class Spielfeld {
  
  private int [][] feld;
  private int [][] PListe;
  
  public Spielfeld (){
    this(20,50);
  }
  
  public Spielfeld (int b, int a){
    feld = new int [a][b];
  }
  
  public Spielfeld (int b, int a, int [][] Liste){
    this(a,b);
    PListe = Liste;
  }
  
  public int getSeiteA(){
    return feld.length;
  }
    
  public int getSeiteB(){
    return feld[0].length;
  }  
  
  public int getPListe(){
    return PListe.length;
  }
  
  public void print(){
    String bild = new String();
    boolean test = false;
    int art=0;
    
    for (int i=0;i<getSeiteB();i++) {
      bild=bild + "+";
    } // end of for
    
    System.out.println("\n+"+bild+"+");
    
    bild="";
    
    for (int i=0;i<getSeiteA();i++) {
      for (int j=0;j<getSeiteB();j++) {
        Position hier = new Position(i,j);
        for (int p=0;p<getPListe();p++) {
          if (hier.getPosb()==PListe[p][0] && hier.getPosa()==PListe[p][1]) {
            test=true;
            art=PListe[p][2];
            break;
          }
        } // end of for
        if (test) {
          switch (art) {
            case  1: 
              bild = bild+"p";
              break;
            case  2: 
              bild = bild+"h";
              break;
           } // end of switch
           art=0;
        } else {
          bild = bild+" ";
        } // end of if-else
        test=false;
      } // end of for
      System.out.println("+"+bild+"+");
      bild="";
    } // end of for
    
    for (int i=0;i<getSeiteB();i++) {
      bild=bild + "+";
    } // end of for
    System.out.println("+"+bild+"+\n");
    
  }
  
  
  
}