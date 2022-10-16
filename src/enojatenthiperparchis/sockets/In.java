/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enojatenthiperparchis.sockets;

/**
 *
 * @author KIKA
 */
import java.net.*;
import java.io.*;

public class In extends Thread      // Creamos Hilo heredando de la clase Thread
	{
	Socket socket;
        String msg="";
	public In( Socket socket)           // Recibe un objeto Socket para indicar qué Socket esta ligado a este proceso
		{
		this.socket=socket;
		start();   //Iniciar el proceso
		}
	public void run(){
		try{
			while(true){     //bucle infinito para lectura
				InputStream aux = socket.getInputStream();
				DataInputStream flujo = new DataInputStream( aux );
                                msg=flujo.readUTF();
				System.out.println(msg);
                                
				}
			}
		catch(Exception e){
			System.out.println("Error");
			}
		}

    public String getMsg() {
        return msg;
    }
        
}