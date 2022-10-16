
package enojatenthiperparchis.states;

import enojatenthiperparchis.Game;
import enojatenthiperparchis.sockets.Server;
import enojatenthiperparchis.sockets.ServerHost;
import java.awt.Graphics;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerState extends State{

    ServerHost cte;
    public ArrayList<Socket> listaCliente=new ArrayList();
    public Socket cliente;
    ServerSocket ss;    
    
    public ServerState(Game game){
        super(game);  
       try {			
           ss = new ServerSocket (5000);
       }catch(Exception e) { System.out.println("Error de comunicacion"+e);   }
    }
    
    @Override
    public void tick() {				
        try {
            cliente = ss.accept();
        }catch(Exception e) { System.out.println("Error de comunicacion"+e);   }
                  System.out.println("Conexión exitosa");
                  listaCliente.add(cliente);				
                  cte = new ServerHost(listaCliente,cliente); //Creación de un proceso para intercambio de info con cada Cliente que se conecte
                  
    }

    @Override
    public void render(Graphics g) {
    }
    
}
