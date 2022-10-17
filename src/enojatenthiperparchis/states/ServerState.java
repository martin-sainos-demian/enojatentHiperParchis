
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.object.Eclipse;
import enojatenthiperparchis.object.Mouse;
import enojatenthiperparchis.object.text.DisplayText;
import enojatenthiperparchis.sockets.ServerHost;
import java.awt.Graphics;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerState extends State{

    ServerHost cte;
    public ArrayList<Socket> listaCliente=new ArrayList();
    public Socket cliente;
    ServerSocket ss;    
    DisplayText ip;   
    DisplayText msg;   
    DisplayText restart;
    
    public ServerState(Game game){
        super(game);  
        All all=game.getAll();
        mouse=new Mouse(all,0,this, game.mouseInput);
        try {			
            ss = new ServerSocket (5000);
            ip=new DisplayText(all,50,50,0,this,InetAddress.getLocalHost().getHostAddress(),all.fonts().sotnNums);
            msg=new DisplayText(all,50,80,0,this,"IP-ADRESS",all.fonts().sotn);
            restart=new DisplayText(all,128,160,0,this,"",all.fonts().sotn);
        }catch(Exception e) { System.out.println("Error de comunicacion"+e);   }
    }
    
    @Override
    public void tick() {
        ip.tick();
        msg.tick();
        restart.tick();
        try {
            cliente = ss.accept();
        }catch(Exception e) { System.out.println("Error de comunicacion"+e);   }
        System.out.println("Conexión exitosa");
        listaCliente.add(cliente);				
        cte = new ServerHost(listaCliente,cliente); //Creación de un proceso para intercambio de info con cada Cliente que se conecte
        mouse.tick();        
    }

    @Override
    public void render(Graphics g) {
        ip.render(g);
        msg.render(g);
        restart.render(g);
        mouse.render(g);
    }
    
}
