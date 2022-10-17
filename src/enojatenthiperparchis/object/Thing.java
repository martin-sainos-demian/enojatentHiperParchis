
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Thing {
    public float x, y;
    public int id;
    public State state;
    All all;
    public Rectangle hitbox;
    BufferedImage sprite;
    public boolean assigned, canAssign=false, clickable=false, clicked=false;
    
    public Thing(All all, float x, float y, int id, State state, int width, int height){
        this.x=x;
        this.y=y;
        this.id=id;
        this.state=state;
        this.all=all;
        hitbox=new Rectangle((int)x, (int)y, width,  height);
    }
    
    public Thing(All all, float x, float y, int id, State state){
        this(all, x, y, id, state, all.getDefaultWidth(), all.getDefaultHeight());
    }
    
    public void update(){
        hitbox.x=(int)x-all.getCamara().xOff;
        hitbox.y=(int)y-all.getCamara().yOff;
        if(clickable){
            if(hitbox.contains(state.mouse.x,state.mouse.y)){
                if(state.mouse.clickLeft){
                    clicked=!clicked;
                    click();
                }
                if(state.mouse.left){
                    whileClick();
                    if(canAssign){
                        if(clicked&&(state.getSelected()==null||state.getSelected()==this)){
                            state.setSelected(this);
                            int xMouseOff=0;
                            int yMouseOff=0;
                            x=state.mouse.x+xMouseOff-(state.mouse.hitbox.width/2)+all.getCamara().xOff;
                            y=state.mouse.y+yMouseOff-(state.mouse.hitbox.height/2)+all.getCamara().yOff;
                            assigned=false;
                            if(state.mouse.clickLeft){
                                state.setSelected(null);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);

    public boolean isOverlapping(Thing other) {
        if (hitbox.y >= other.hitbox.y+other.hitbox.height
          || hitbox.y+hitbox.height <= other.hitbox.y) {
            return false;
        }
        if (hitbox.x >= other.hitbox.x+other.hitbox.width
          || hitbox.x+hitbox.width <= other.hitbox.x) {
            return false;
        }
        return true;
    }
    public boolean midCollition(Thing other){
        return other.hitbox.contains(
                hitbox.x+(hitbox.width/2),
                hitbox.y+(hitbox.height/2));
    }
    
    public void draw(Graphics g, BufferedImage image){
        g.drawImage(image, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
    }
    public void draw(Graphics g){
        draw(g, sprite);
    }
    public void click(){};
    public void clickStop(){};
    public void whileClick(){};
}