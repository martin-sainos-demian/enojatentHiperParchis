/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enojatenthiperparchis.sockets;

import java.net.*;
import java.io.*;
import java.util.*;

public class Server{       	
   ServerHost cte;	// Objeto para hacer uso del proceso de lectura y escritura
   ArrayList<Socket> listaCliente=new ArrayList();	    
   public Server()    
      {        
       try        
            {            
            ServerSocket ss = new ServerSocket (5000);			
            Socket cliente;            
            while(true){				
                  cliente = ss.accept();
                  System.out.println("Conexión exitosa");
                  listaCliente.add(cliente);				
                  cte = new ServerHost(listaCliente,cliente); //Creación de un proceso para intercambio de info con cada Cliente que se conecte
                  }	     
            //ss.close();        
            }        
           catch (Exception e){    
               e.printStackTrace();       
           }    
     }
}