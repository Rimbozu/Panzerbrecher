class Position{
  
  private int h,v;
  
  public Position(int h,int v){
    this.h=h;
    this.v=v;
  }
    
  public int getPosh (){
    return h;
  }
  public int getPosv (){
    return v;
  }  
  
  public void addPosh (int a){
    h+=a;
  }
  public void addPosv (int a){
    v+=a;
  }  
}          