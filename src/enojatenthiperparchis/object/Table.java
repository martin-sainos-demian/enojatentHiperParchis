
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import enojatenthiperparchis.round.Round;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;

public class Table extends Thing{

    Casilla casillas[][];
    int esp;
    public Player players[];
    public int width=0, height=0;
    Round round;
    
    public Table(All all, float x, float y, int espacios, int id, Round round) {
        super(all, x, y, id, round.getState());
        this.esp=espacios+2;
        this.round=round;
        casillas=new Casilla[(esp*2)+5][(esp*2)+5];
    }
    
    public void innit(){
        for(int x=0;x<casillas.length;x++){
            for(int y=0;y<casillas[x].length;y++){
                casillas[x][y]=new Casilla(all,x*all.getDefaultWidth(),y*all.getDefaultHeight()
                        ,id+(x*casillas[x].length+y),this);
            }
        }
        
        for(int i=1;i<casillas.length-1;i++){
            casillas[i][esp].hueco=true;
            casillas[i][esp+4].hueco=true;
            casillas[esp][i].hueco=true;
            casillas[esp+4][i].hueco=true;
        }
        for(int i=1;i<casillas.length-1;i++){
            casillas[i][esp+1].hueco=false;
            casillas[i][esp+3].hueco=false;
            casillas[esp+1][i].hueco=false;
            casillas[esp+3][i].hueco=false;
        }
        for(int i=0;i<5;i++){
            casillas[esp+i][1].hueco=true;
            casillas[esp+i][casillas.length-2].hueco=true;
            casillas[1][esp+i].hueco=true;
            casillas[casillas.length-2][esp+i].hueco=true;
        }
        for(int i=1;i<casillas.length-1;i++){
            casillas[i][esp+2].hueco=true;
            casillas[esp+2][i].hueco=true;
        }
        
        if(players.length>0){
        casillas[esp][1]=new CasillaInicial(all,esp*all.getDefaultWidth()
                ,all.getDefaultHeight(),casillas[esp][1].id,this,players[0]);
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    casillas[i/2+1][1].hueco=true;
                }else{
                    casillas[i/2+1][2].hueco=true;
                }
            }
            Canica bolsa[]=new Canica[esp];
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    bolsa[i]=new Canica(all,(i+2)*all.getDefaultWidth()/2,all.getDefaultHeight(),i,players[0]);
                }
                else{
                    bolsa[i]=new Canica(all,(i+1)*all.getDefaultWidth()/2,2*all.getDefaultHeight(),i,players[0]);
                }
            }
            players[0].setBolsa(bolsa);
        }
        if(players.length>1){
        casillas[casillas.length-2][esp]=new CasillaInicial(all,(casillas.length-2)*all.getDefaultWidth()
                ,esp*all.getDefaultHeight(),casillas[casillas.length-2][esp].id,this,players[1]);
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    casillas[casillas.length-2][i/2+1].hueco=true;
                }else{
                    casillas[casillas.length-3][i/2+1].hueco=true;
                }
            }
            Canica bolsa[]=new Canica[esp];
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    bolsa[i]=new Canica(all,(casillas.length-2)*all.getDefaultWidth(),(i+2)*all.getDefaultHeight()/2,i,players[1]);
                }
                else{
                    bolsa[i]=new Canica(all,(casillas.length-3)*all.getDefaultWidth(),(i+1)*all.getDefaultHeight()/2,i,players[1]);
                }
            }
            players[1].setBolsa(bolsa);
        }
        if(players.length>2){
        casillas[casillas.length-esp-1][casillas[0].length-2]=new CasillaInicial(all,(casillas.length-esp-1)*all.getDefaultWidth()
                ,(casillas[0].length-2)*all.getDefaultHeight(),casillas[casillas.length-esp-1][casillas[0].length-2].id,this,players[2]);
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    casillas[casillas.length-(i/2+1)-1][casillas[0].length-2].hueco=true;
                }else{
                    casillas[casillas.length-(i/2+1)-1][casillas[0].length-3].hueco=true;
                }
            }
            Canica bolsa[]=new Canica[esp];
            for(int i=0;i<esp;i++){
                if(i%2==0){
                    bolsa[i]=new Canica(all,(casillas.length-(i/2)-2)*all.getDefaultWidth(),(casillas[0].length-2)*all.getDefaultHeight(),i,players[2]);
                }
                else{
                    bolsa[i]=new Canica(all,(casillas.length-(i/2)-2)*all.getDefaultWidth(),(casillas[0].length-3)*all.getDefaultHeight(),i,players[2]);
                }
            }
            players[2].setBolsa(bolsa);
        }
        
        settleCanicas();

        
        width=casillas.length*all.getDefaultWidth()+all.getDefaultWidth()/2;
        height=(casillas[0].length+1)*all.getDefaultHeight();
    }

    public void settleCanicas(){
        for(Casilla[] x:casillas){
            for(Casilla y:x){
                y.canica=null;
            }
        }
        for(Player p:players){
            for(Canica c:p.getBolsa()){
                c.assigned=false;
                for(Casilla[] x:casillas){
                    for(Casilla y:x){
                        if(y.hueco&&y.canica==null
                                &&y.isOverlapping(c)&&!c.assigned){
                            y.setCanica(c);
                            c.x=y.x;
                            c.y=y.y;
                        }
                    }
                }
                c.setPlayer(round.getPlayerById(p.id));
                c.playerId=p.id;
            }
        }
    }
    
    @Override
    public void tick() {
        super.update();
        for(Player p:players){
            if(p!=null){
                p.tick();
            }
                
        }
        for(Casilla[] x:casillas){
            for(Casilla y:x){
                if(y!=null)
                    y.tick();
            }
        }
    }

    @Override
    public void render(Graphics g) {
        for(Casilla[] x:casillas){
            for(Casilla y:x){
                y.render(g);
            }
        }
        for(Player p:players){
            for(Canica c:p.bolsa){
                if(!c.assigned)
                    c.render(g);
            }
            p.render(g);
        }
    }

    public int getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Player[] getPlayers() {
        return players;
    }
    public void setPlayers(Player[] players) {
        this.players=players;
    }
}