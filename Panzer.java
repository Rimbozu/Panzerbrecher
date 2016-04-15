class Panzer {
  
  private Position pos;
  private int besitzer, hp, damage;
  
    
  public Panzer(int h, int v,int player){
    pos = new Position(h,v);
    besitzer=player;
    hp=10;
    damage=5;
  }
    
  public Position getPos(){
    return pos;  
  } 
  
  public int getPlayer(){
    return besitzer;
  }
  
  public int getHP(){
    return hp;
  }
  
  public int getDamage(){
    return damage;
  }
  
  
  public void subHP(int dmg){
    hp -= dmg;
  }
  
  public void move(int r, int entf){
    switch (r) {
      case  8: 
        pos.addPosh(-entf);
        break;
      case  9: 
        pos.addPosh(-entf);
        pos.addPosv(+entf);
        break;
      case  6: 
        pos.addPosv(+entf);
        break;
      case  3: 
        pos.addPosv(+entf);
        pos.addPosh(+entf);
        break; 
      case  2: 
        pos.addPosh(+entf);
        break;
      case  1: 
        pos.addPosh(+entf);
        pos.addPosv(-entf);
        break; 
      case  4: 
        pos.addPosv(-entf);
        break;
      case  7: 
        pos.addPosv(-entf);
        pos.addPosh(-entf);
        break; 
        
    } // end of switch
  }
  
  public Position target(int r,int entf){
    Position ziel = new Position(0,0);
    switch (r) {
      case  8: 
        ziel.addPosh(getPos().getPosh()-entf);
        ziel.addPosv(getPos().getPosv());
        break;
      case  9: 
        ziel.addPosh(getPos().getPosh()-entf);
        ziel.addPosv(getPos().getPosv()+entf);
        break;  
      case  6:
        ziel.addPosh(getPos().getPosh());
        ziel.addPosv(getPos().getPosv()+entf);
        break;
      case  3:
        ziel.addPosh(getPos().getPosh()+entf);
        ziel.addPosv(getPos().getPosv()+entf);
        break; 
      case  2: 
        ziel.addPosh(getPos().getPosh()+entf);
        ziel.addPosv(getPos().getPosv());
        break;   
      case  1: 
        ziel.addPosh(getPos().getPosh()+entf);
        ziel.addPosv(getPos().getPosv()-entf);
        break;  
      case  4: 
        ziel.addPosh(getPos().getPosh());
        ziel.addPosv(getPos().getPosv()-entf);
        break;
      case  7: 
        ziel.addPosh(getPos().getPosh()-entf);
        ziel.addPosv(getPos().getPosv()-entf);  
        break;   
    } // end of switch
    
    return ziel;
  }    
}