class Panzer {
  
  private Position pos;
  
  public Panzer(){
    this(5,5);
  }
    
  public Panzer(int b, int a){
    pos = new Position(b,a);
  }
    
  public Position getPos(){
    return pos;  
  } 
  
  public void move(int r, int entf){
    switch (r) {
      case  0: 
        pos.setPosb(-entf);
        break;
      case  1: 
        pos.setPosa(+entf);
        break;
      case  2: 
        pos.setPosb(+entf);
        break;
      case  3: 
        pos.setPosa(-entf);
        break;
        
    } // end of switch
  }
  
  public void shoot(int r,int entf){
    
  }    
}