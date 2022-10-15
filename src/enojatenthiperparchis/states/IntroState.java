/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.object.Mouse;
import enojatenthiperparchis.object.intro.Intro;
import java.awt.Graphics;

/**
 *
 * @author PC
 */
public class IntroState extends State{

    private boolean completed;
    
    private All all;
    private Intro intro;
    
    public IntroState(Game game){
        super(game);
        audio.play("intro.wav");
        
        mouse=new Mouse(all,0,this, game.mouseInput);
        intro=new Intro(all,0,0,0,this);
    }
    
    @Override
    public void tick() {
        if(audio.completed){
            completed=true;
        }
        if(completed){
            game.nextState();
        }
        mouse.tick();
        if(mouse.clickLeft){
            game.nextState();
        }
        if(intro!=null){
            intro.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if(intro!=null){
            intro.render(g);
        }
    }
    
}
