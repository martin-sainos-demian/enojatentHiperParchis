
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.object.Boton;
import enojatenthiperparchis.object.Mouse;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuState extends State {
    private Boton[] botones;
    private All all;
    private int width,height;
    
    public MenuState(Game game){
        super(game);
        all=game.getAll();
        mouse=new Mouse(all,0,this, game.mouseInput);
        botones=new Boton[2];
        botones[0]=new Boton(all
                ,0,0,all.getDefaultWidth()*2
                ,all.getDefaultHeight(),1,this,all.sprites().botonDef)
                {@Override
                public void clicked(){
                    System.out.println("uwu");
                    game.nextState();
                }};
        botones[1]=new Boton(all, 64, 64, 128, 128, 2, this, all.sprites().credits);
        width=0;
        height=0;
        for(Boton b:botones){
            if(b.x+b.hitbox.width>width){
                width=(int)b.x+b.hitbox.width;
            }
            if(b.y+b.hitbox.height>height){
                height=(int)b.y+b.hitbox.height;
            }
        }
        all.setTotalWidth(width);
        all.setTotalHeight(height);
        
        audio.loop("title.wav");
    }
    
    @Override
    public void tick() {
        botones[0].x=(all.getScreenWidth()-botones[0].hitbox.width)/2;
        botones[0].y=(all.getScreenHeight()-botones[0].hitbox.height)/2;
        for(Boton b : botones){
            b.tick();
        }
        mouse.tick();
    }

    @Override
    public void render(Graphics g) {
        for(Boton b : botones){
            b.render(g);
        }
        mouse.render(g);
    }
    
}
