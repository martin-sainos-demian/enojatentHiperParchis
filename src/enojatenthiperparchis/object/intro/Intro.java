
package enojatenthiperparchis.object.intro;

import enojatenthiperparchis.All;
import enojatenthiperparchis.object.Thing;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.Image;

public class Intro extends Thing{
    public All all;
    public Image img;
    public double maxX=50,stepX=0.1,minX=-10;
    public boolean left=false;
    public Intro(All all, float x, float y, int id, State state){
        super(all, x, y, id, state);
        this.all=all;
        img=all.sprites().intro;
    }
    @Override
    public void tick() { 
       if(!left){
           x+=stepX;
           if(maxX<x){
               left=true;
           }
       }else{
           x-=stepX;
           if(x<minX){
               left=false;
           }
       }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }
    
}