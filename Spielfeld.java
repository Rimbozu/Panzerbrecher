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
    
    for (int i=0;i<getSeiteA();i++) {
      for (int j=0;j<getSeiteB();j++) {
        Position hier = new Position(i,j);
        for (int p=0;p<getPListe();p++) {
          if (hier.getPosh()==PListe[p][0] && hier.getPosv()==PListe[p][1]) {
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