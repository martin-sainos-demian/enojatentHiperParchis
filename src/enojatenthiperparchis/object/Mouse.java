
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.input.MouseInput;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

public class Mouse extends Thing{   

    MouseInput mouseInput;
    public boolean left, right, clickLeft, clickRight;
    
    public Mouse(All all, int id, State state, MouseInput mouseInput){
        super(all,(float)all.getScreenWidth()/2, (float)all.getScreenHeight()/2, id, state);
        this.mouseInput=mouseInput;
        sprite=all.sprites().mouse;
    }
    
    @Override
    public void tick() {
        x=mouseInput.x;
        y=mouseInput.y;
        if(!left&&mouseInput.left)
            clickLeft=true;
        else
            clickLeft=false;
        if(!right&&mouseInput.right)
            clickRight=true;
        else
            clickRight=false;
        left=mouseInput.left;
        right=mouseInput.right;
        if(!left){
            if(state.getSelected()!=null)
                state.getSelected().clickStop();
            state.setSelected(null);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprite,(int)x,(int)y,null);
    }
}