class Position{
  
  private int b,a;
  
  public Position(int x,int y){
    b=x;
    a=y;
  }
    
  public int getPosb (){
    return b;
  }
  public int getPosa (){
    return a;
  }  
  
  public void setPosb (int x){
    b+=x;
  }
  public void setPosa (int x){
    a+=x;
  }  
}