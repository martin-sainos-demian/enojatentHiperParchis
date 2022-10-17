
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.object.Eclipse;
import enojatenthiperparchis.object.Mouse;
import enojatenthiperparchis.object.text.DisplayText;
import enojatenthiperparchis.sockets.ServerHost;
import java.awt.Graphics;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerState extends State{

    ServerHost cte;
    public ArrayList<Socket> listaCliente=new ArrayList();
    public Socket cliente;
    ServerSocket ss;    
    DisplayText ip;   
    DisplayText msg;   
    DisplayText restart;
    Eclipse eclipse;
    boolean runnin=false;
    Thread get;
    
    public ServerState(Game game){
        super(game); 
        All all=game.getAll();
        try {		
        ss = new ServerSocket (5000);
            ip=new DisplayText(all,50,50,0,this,InetAddress.getLocalHost().getHostAddress(),all.fonts().sotnNums);
            msg=new DisplayText(all,50,80,0,this,"IP-ADRESS",all.fonts().sotn);
            restart=new DisplayText(all,128,160,0,this,"",all.fonts().sotn);
            runnin=true;
            get=new Thread(){
            @Override
            public void run(){
                while(runnin){
                    try {
                        cliente = ss.accept();
                    }catch(Exception e) { System.out.println("Error de comunicacion b "+e);   
                    runnin=false;}
                    System.out.println("Conexión exitosa");
                    listaCliente.add(cliente);				
                    cte = new ServerHost(listaCliente,cliente);
                    mouse.tick();  
                }
            }
        };
        get.start();
        eclipse=new Eclipse(all,100,all.getScreenHeight()/2,0,this){
            @Override
            public void completed(){
                restart.setText("EXIT");
                runnin=false;
            }
            @Override
            public void timmerOff(){
                restart.setText("");
                System.exit(0);
            }
        };
    }catch(Exception e) { System.out.println("Error de comunicacion"+e);   }
    }
    
    
    @Override
    public void tick() {
        ip.tick();
        msg.tick();
        restart.tick();    
        eclipse.tick();
        mouse.tick();
    }

    @Override
    public void render(Graphics g) {
        ip.render(g);
        msg.render(g);
        restart.render(g);
        eclipse.render(g);
        mouse.render(g);
    }
}
