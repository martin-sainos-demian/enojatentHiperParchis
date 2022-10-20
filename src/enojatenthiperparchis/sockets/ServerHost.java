/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enojatenthiperparchis.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author KIKA
 */
public class ServerHost extends Thread { //Creamos proceso
    public Socket socket;
    public String mensaje="";
    public ArrayList<Socket> listaCliente;
    int cliente;
    private boolean end=false;
       
    public ServerHost(ArrayList<Socket> lista, Socket socket)   // Recibe el ArrayList de Sockets Cliente que actualmente se están atendiendo
		{
		this.listaCliente = lista;
		this.socket=socket;
		start();
		}
	public void run(){
		while(!end){   // Bucle infinito para lectura y escritura
	  	    try {
			InputStream is = socket.getInputStream(); // Se abre flujo de lectura
			DataInputStream flujo = new DataInputStream(is);
			mensaje=flujo.readUTF();  // Permanece escuchando hasta que alguno de los clientes le envía un mensaje
                        ArrayList<Socket> listaClienteRam=listaCliente;
                        for(int cont=0;cont<listaClienteRam.size();cont++){
                            if(listaClienteRam.get(cont).isClosed()){
                                listaCliente.remove(cont);
                                System.out.println(cont+" is closed");
                            }
                        }
                        System.out.println("List cleaned :3");
			for(int cont=0;cont<listaCliente.size();cont++)    //Cuando recibe un mensaje, lo replica a todos los Clientes recorriendo el ArrayList de Sockets y escribe al flujo de cada uno de ellos
				{
                                    System.out.println("a"+cont);
                                    try{
				OutputStream os = listaCliente.get(cont).getOutputStream();
                                if(!listaCliente.get(cont).isClosed()){
                                    DataOutputStream flujoDOS = new DataOutputStream(os);
                                    flujoDOS.writeUTF(mensaje);
                                }else{
                                    listaCliente.get(cont).close();
                                }}catch(SocketException e){
                                        System.out.println("Error s "+e);
                                }
				}
		          }
		      catch(EOFException e) {
                     } catch(IOException e) {
                         System.out.println("Error a "+e);
                        }
		}
        
	}
        public void end(){
            end=true;
        }
}
