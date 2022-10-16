
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

public class Canica extends Thing {
    static Player player;
    public static int playerId;
    public int casillaX=0,casillaY=0;
    
    public Canica(All all, float x, float y, int id, Player player) {
        super(all, x, y, id, player.getState());
        this.player=player;
        clickable=true;
        canAssign=true;
        final int playId=player.id;
        playerId=playId;
        sprite=all.sprites().canicas[player.id];
    }

    @Override
    public void tick() {
        super.update();
    }
    
    @Override
    public void click(){
        assigned=false;
    }
    @Override
    public void clickStop(){
        for(Casilla[] row : player.tabla.casillas){
            for(Casilla cell: row){
                if(midCollition(cell)){
                    cell.setCanica(this);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(!assigned){
            render(g,true);
        }
    }
    public void render(Graphics g, boolean inHole) {
        if(inHole){
            draw(g);
        }
    }
    
    public static State getState(){
        if(player==null){
            System.out.println("▓ Player nulo");
            return null;
        }
        return player.getState();
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        Canica.player = player;
    }
}