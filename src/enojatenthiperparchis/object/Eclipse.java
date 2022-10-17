
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Eclipse extends Boton{
    
    BufferedImage[] moon;
    int step=3;
    int timmer=0;
    int moonX=0;
    int moonOffX=0;
    boolean completed=false;
    
    public Eclipse(All all, float x, float y, int id, State state){
        super(all,x,y,all.sprites().moon.getWidth()*2,all.sprites().moon.getHeight(),id,state,all.sprites().moon);
        moon=new BufferedImage[2];
        moon[0]=all.sprites().moon;
        moon[1]=all.sprites().moonRing;
        moonOffX=moon[0].getWidth()*2;
    }
    @Override
    public void tick(){
        super.tick();
        if(timmer>0){
            timmer--;
            if(timmer%100==0&!completed)
                moonX+=step;
        }
        if(!completed){
            if(moonX>=moonOffX-2){
                completed=true;
                completed();
            }
        }
        if(timmer==1){
            timmerOff();
            moonX=0;
        }
    }
    
    @Override
    public void clicked(){
        timmer=12800;
        completed=false;
    }
    
    @Override
    public void render(Graphics g){
        g.drawImage(moon[1],(int)x+moonOffX,(int)y,null);
        g.drawImage(moon[0],(int)x+moonX,(int)y,null);
    }
    
    public void completed(){};
    public void timmerOff(){};
}