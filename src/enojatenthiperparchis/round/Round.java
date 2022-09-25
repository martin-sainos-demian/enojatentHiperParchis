
package enojatenthiperparchis.round;

import enojatenthiperparchis.All;
import enojatenthiperparchis.object.Dado;
import enojatenthiperparchis.object.Player;
import enojatenthiperparchis.object.Table;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

public class Round {
    private State state;
    private All all;
    public Table tabla;
    Player players[];
    Dado dado;
    
    
    public Round(All all,int playerNumber, int espacios, State state){
        this.all=all;
        this.state=state;
        players=new Player[playerNumber];
        tabla=new Table(all,0,0,espacios,0,this);
        for(int p=0;p<players.length;p++){
            players[p]=new Player(all,0,0,p,tabla);
            
        }
        tabla.setPlayers(players);
        tabla.innit();
        dado=new Dado(all,all.getTotalWidth()/2,all.getTotalHeight()/2,0,state,tabla);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public void tick(){
        tabla.tick();
        dado.tick();
    }
    
    public void render(Graphics g){
        tabla.render(g);
        dado.render(g);
    }
    
    public Player getPlayerById(int id){
        return players[id];
    }
}