/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import static enojatenthiperparchis.object.Canica.player;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

/**
 *
 * @author PC
 */
public class Dado extends Thing{
    
    public int number=6;
    public boolean clicked=false, spin=false;
    public int angle=0;
    boolean goingLeft=false,goingUp=false;
    private Table tabla;
    int moveX=1,moveY=1;
    
    public Dado(All all, float x, float y, int id, State state, Table tabla){
        super(all,x,y,id,state);
        this.tabla=tabla;
        clickable=true;
    }

    @Override
    public void tick() {
        if(spin){
            if(angle>0){
                angle-=6;
                roll();
            }
            else{
                spin=false;
            }
        }
        super.update();
    }

    @Override
    public void click(){
        if(!spin){
            angle=359;
            spin=true;
            goingLeft=(roll()>3);
            goingUp=(roll()%2==0);
            moveX=roll()/2;
            moveY=roll()/2;
            all.audio().play("/sfx/dice.wav");
        }
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(
                all.getCamara().rotate(angle, all.sprites().dice[number-1])
                , (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff,null);
    }
    
    public int roll(){
        int max = 6;
        int min = 1;
        int range = max - min + 1;
 
        number = (int)(Math.random() * range) + min;
        
        if(goingLeft&&x+hitbox.width+moveX<all.getScreenWidth()){
            x+=moveX;
        }
        else if(x-moveX>0){
            x-=moveX;
        }
        if(goingUp&&y+hitbox.height+moveY<all.getScreenHeight()){
            y+=moveY;
        }
        else if(y-moveY>0){
            y-=moveY;
        }
        
        return number;
    }
}
