/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import static enojatenthiperparchis.object.Canica.getState;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author PC
 */
public class Boton extends Thing{
    
    private Mouse mouse;
    boolean hovered=false, clicked=false;
    BufferedImage images[];
    
    public Boton(All all, float x, float y, int width, int height, int id, State state, BufferedImage[] images){
        super(all, x, y, id, state, width, height);
        this.mouse=state.mouse;
        this.images=images;
    }
    public Boton(All all, float x, float y, int width, int height, int id, State state, BufferedImage image){
        this(all,x,y,width,height,id,state,new BufferedImage[]{image});
        
    }

    @Override
    public void tick() {
        super.update();
        
        if(super.hitbox.contains(mouse.x, mouse.y)){
            hovered=true;
            if(mouse.left){
                clicked=true;
            } else {
                clicked=false;
            }
        } else {
            hovered=false;
        }
        if(hovered){
            hovered();
        }
        if(clicked){
            clicked();
        }
        
    }

    @Override
    public void render(Graphics g) {
        if(images!=null){
            if(images.length>2){
                if(images[0]!=null&&!hovered){
                    g.drawImage(images[0], (int)x, (int)y, null);
                }
                if(images[1]!=null&&hovered){
                    g.drawImage(images[1], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
                }
                if(images[2]!=null&&clicked){
                    g.drawImage(images[2], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
                }
            } else if(images.length>0){
                g.drawImage(images[0], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
            }
        } else {
            g.drawImage(all.sprites().botonDef, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
        }
    }
    
    public void hovered(){};
    public void clicked(){};
}
