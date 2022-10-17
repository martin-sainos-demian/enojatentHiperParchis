
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.object.Canica;
import enojatenthiperparchis.object.Casilla;
import enojatenthiperparchis.object.CasillaInicial;
import enojatenthiperparchis.object.Dado;
import enojatenthiperparchis.object.Mouse;
import enojatenthiperparchis.round.Round;
import enojatenthiperparchis.sockets.In;
import enojatenthiperparchis.sockets.Out;
import java.awt.Graphics;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientState extends State{

    String name="";
    Round round;
    All all;
    
    Out outThread;
    In inThread;
    Socket skCliente;
    
    String[] lastMsg;
    
    public ClientState(Game game, String name,String ip){
        super(game);
        this.name=name;
        lastMsg="a".split("");
        	      
        try{             
            skCliente = new Socket (ip, 5000);  						
            outThread =new Out(skCliente,name);
            inThread= new In(skCliente);

            all=game.getAll();
            mouse=new Mouse(all,0,this, game.mouseInput);
            round=new Round(game.getAll(),3,4,this);
            all.setTotalWidth(round.tabla.width);
            all.setTotalHeight(round.tabla.height);
            
            for(int x=0;x<round.tabla.casillas.length;x++){
                for(int y=0;y<round.tabla.casillas[x].length;y++){
                    Canica ramCanica=round.tabla.casillas[x][y].canica;
                    if(round.tabla.casillas[x][y] instanceof CasillaInicial){
                        round.tabla.casillas[x][y]=new CasillaInicial(all
                                ,round.tabla.casillas[x][y].x,round.tabla.casillas[x][y].y,round.tabla.casillas[x][y].id,round.tabla,round.tabla.casillas[x][y].player){
                            @Override
                            public void setCanica(Canica canica){
                                super.setCanica(canica);
                                outThread.write("set");
                            }
                        };
                    }else{
                        boolean hole=round.tabla.casillas[x][y].hueco;
                        round.tabla.casillas[x][y]=new Casilla(all,round.tabla.casillas[x][y].x
                                ,round.tabla.casillas[x][y].y,round.tabla.casillas[x][y].id,round.tabla){
                            @Override
                            public void setCanica(Canica canica){
                                super.setCanica(canica);
                                outThread.write("set█"+canica.id+"█x█"+canica.x+"█y█"+canica.y+"█assigned█"+canica.assigned);
                            };
                        };
                        round.tabla.casillas[x][y].hueco=hole;
                    }
                    round.tabla.casillas[x][y].canica=ramCanica;
                }
            }
            
            round.dado=new Dado(all,round.dado.x,round.dado.y,round.dado.id,this,round.tabla){
                @Override
                public void tick(){
                    if(spin){
                        if(angle>0){
                            angle-=6;
                            roll();
                        }
                        else{
                            outThread.write("set█dice█x█"+x+"█y█"+y+"█num█"+number);
                            spin=false;
                        }
                    }
                    super.update();
                }
            };
            
            outThread.write("connecting");
        }         
      catch (Exception e)    {  
          e.printStackTrace();      
      }
    }
    
    @Override
    public void tick() {
        round.tick();
        mouse.tick();
        String ramLastMsg=toString(lastMsg);
        lastMsg=inThread.getMsg().split("█");
        
        if(!toString(lastMsg).equals(ramLastMsg)){
            if(lastMsg.length>2){
                if(lastMsg[2].equals("set")&&lastMsg.length>8){
                    if(lastMsg[4].equals("x")&&lastMsg[6].equals("y")&&lastMsg[8].equals("assigned")){
                        int id=Integer.parseInt(lastMsg[3]);
                        float newX=Float.parseFloat(lastMsg[5]);
                        float newY=Float.parseFloat(lastMsg[7]);
                        boolean assigned=lastMsg[9].equals("true");
                        round.tabla.setCanica(id, newX, newY,assigned);
                    }
                    if(lastMsg[3].equals("dice")){
                        if(lastMsg[4].equals("x")&&lastMsg[6].equals("y")&&lastMsg[8].equals("num")){
                            int num=Integer.parseInt(lastMsg[9]);
                            float newX=Float.parseFloat(lastMsg[5]);
                            float newY=Float.parseFloat(lastMsg[7]);
                            round.setDice(newX, newY, num);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        round.render(g);
        mouse.render(g);
    }
    
    public String toString(String[] ask){
        String res="";
        for(int i=0;i<ask.length;i++){
            res+="█";
            res+=ask[i];
        }
        return res;
    }
    
    @Override
    public void stop(){
        try {
            super.stop();
            skCliente.close();
        }catch (Exception e){  
          e.printStackTrace();      
        }
    }
}