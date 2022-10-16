
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Letter extends Boton{
    
    private int timer=0;
    
    public Letter(All all, float x, float y, int width, int height, int id, State state, BufferedImage[] images){
        super(all, x, y, width, height, id, state, images);
    }
    @Override
    public void tick(){
        super.tick();
    }
    @Override
    public void render(Graphics g) {
        if(images!=null){
            if(images.length>2){
                if(images[0]!=null&&!hovered){
                    g.drawImage(images[0], (int)x, (int)y, null);
                }
                if(images[1]!=null&&hovered){
                    if(timer==1)
                        g.drawImage(images[1], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
                    else if(images[2]!=null)
                        g.drawImage(images[2], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
                }
                if(images[3]!=null&&clicked){
                    g.drawImage(images[3], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
                }
            } else if(images.length>0){
                g.drawImage(images[0], (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
            }
        } else {
            g.drawImage(all.sprites().botonDef, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff, null);
        }
    }
}