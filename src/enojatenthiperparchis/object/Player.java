
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

public class Player extends Thing {

    Canica bolsa[];
    public Table tabla;
    
    public Player(All all, float x, float y, int id, Table tabla) {
        super(all, x, y, id, tabla.getState());
        this.tabla=tabla;
    }

    @Override
    public void tick() {
        super.update();
        for(Canica c:bolsa){
            if(c!=null){
                c.tick();
            }
        }
    }

    @Override
    public void render(Graphics g) {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Canica[] getBolsa() {
        return bolsa;
    }

    public void setBolsa(Canica[] bolsa) {
        this.bolsa = bolsa;
    }
}