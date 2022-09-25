
package enojatenthiperparchis.states;

import enojatenthiperparchis.Game;
import enojatenthiperparchis.gfx.Audio;
import enojatenthiperparchis.object.Mouse;
import enojatenthiperparchis.object.Thing;
import java.awt.Graphics;

public abstract class State {
    public Mouse mouse;
    Game game;
    Thing selected;
    Audio audio;
    
    public State(Game game){
        this.game=game;
        mouse=new Mouse(game.getAll(),0,this,game.mouseInput);
        this.audio=game.getAll().audio();
    }
    
    public abstract void tick();
    public abstract void render(Graphics g);

    public Game getGame() {
        return game;
    }

    public Thing getSelected() {
        return selected;
    }

    public void setSelected(Thing selected) {
        this.selected = selected;
    }
    
    public void stop(){
        audio.stop();
    }
}
