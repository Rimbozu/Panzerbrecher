class Panzertyp {
  
  private int typNr, hp, dmg;
  
  public Panzertyp (int typ){
    typNr=typ;
    switch (typ) {
      case  0: 
        hp=10;
        dmg=5;
        break;  
      case  1:
        hp=20;
        dmg=10;
        break;
      case  2:
        hp=5;
        dmg=15;
        break;  
        
    } // end of switch  
  }
  
  public int getTypNr(){
    return typNr;
  }
  
  public int getHP(){
    return hp;
  }
  
  public int getDMG(){
    return dmg;
  }
  
  public void subHP(int damage){
    hp -= damage;
  }
  
  public String toString (){
    switch (typNr) {
      case  0: 
        return "Normale Panzer, HP:  "+hp+", DMG: "+dmg;
      case  1:
        return "Schwerer Panzer, HP:  "+hp+", DMG: "+dmg;
      case  2:
        return "Panzerjaeger, HP:  "+hp+", DMG: "+dmg;        
      default: 
        return "Typ nicht vorhanden";  
        
    } // end of switch
  }
}