class Panzer {
  
  private Position pos;
  private int besitzer;
    
  public Panzer(int b, int a,int player){
    pos = new Position(b,a);
    besitzer=player;
  }
    
  public Position getPos(){
    return pos;  
  } 
  
  public int getPlayer(){
    return besitzer;
  }
  
  public void setDestroy(){
    besitzer=0;
  }
  
  public void move(int r, int entf){
    switch (r) {
      case  8: 
        pos.setPosh(-entf);
        break;
      case  9: 
        pos.setPosh(-entf);
        pos.setPosv(+entf);
        break;
      case  6: 
        pos.setPosv(+entf);
        break;
      case  3: 
        pos.setPosv(+entf);
        pos.setPosh(+entf);
        break; 
      case  2: 
        pos.setPosh(+entf);
        break;
      case  1: 
        pos.setPosh(+entf);
        pos.setPosv(-entf);
        break; 
      case  4: 
        pos.setPosv(-entf);
        break;
      case  7: 
        pos.setPosv(-entf);
        pos.setPosh(-entf);
        break; 
        
    } // end of switch
  }
  
  public Position shoot(int r,int entf){
    Position ziel = new Position(0,0);
    switch (r) {
      case  8: 
        ziel.setPosh(getPos().getPosh()-entf);
        ziel.setPosv(getPos().getPosv());
        break;
      case  9: 
        ziel.setPosh(getPos().getPosh()-entf);
        ziel.setPosv(getPos().getPosv()+entf);
        break;  
      case  6:
        ziel.setPosh(getPos().getPosh());
        ziel.setPosv(getPos().getPosv()+entf);
        break;
      case  3:
        ziel.setPosh(getPos().getPosh()+entf);
        ziel.setPosv(getPos().getPosv()+entf);
        break; 
      case  2: 
        ziel.setPosh(getPos().getPosh()+entf);
        ziel.setPosv(getPos().getPosv());
        break;   
      case  1: 
        ziel.setPosh(getPos().getPosh()+entf);
        ziel.setPosv(getPos().getPosv()-entf);
        break;  
      case  4: 
        ziel.setPosh(getPos().getPosh());
        ziel.setPosv(getPos().getPosv()-entf);
        break;
      case  7: 
        ziel.setPosh(getPos().getPosh()-entf);
        ziel.setPosv(getPos().getPosv()-entf);  
        break;   
    } // end of switch
    
    return ziel;
  }    
}