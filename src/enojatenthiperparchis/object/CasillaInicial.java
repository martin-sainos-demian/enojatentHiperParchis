
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import java.awt.Graphics;

/**
 *
 * @author PC
 */
public class CasillaInicial extends Casilla{
    
    public CasillaInicial(All all, float x, float y, int id, Table table, Player player){
        super(all, x, y, id, table);
        this.player=player;
        hueco=true;
    }    
    
    @Override
    public void setCanica(Canica canica){
        super.setCanica(canica);
        if(canica.playerId==player.id){
            all.audio().play("/sfx/tan.wav");
        }
    }
    
    @Override
    public void render(Graphics g){
        render(g,all.sprites().huecoFront[player.id+1],all.sprites().huecoBack[player.id+1]);
    }
}