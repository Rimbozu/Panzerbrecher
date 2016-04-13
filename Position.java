class Position{
  
  private int h,v;
  
  public Position(int x,int y){
    h=x;
    v=y;
  }
    
  public int getPosh (){
    return h;
  }
  public int getPosv (){
    return v;
  }  
  
  public void setPosh (int x){
    h+=x;
  }
  public void setPosv (int x){
    v+=x;
  }  
}