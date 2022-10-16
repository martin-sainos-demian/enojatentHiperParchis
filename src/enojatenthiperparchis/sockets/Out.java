/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package enojatenthiperparchis.sockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Out 
	{
	Socket socket;
	String name;
	Scanner entrada;
        boolean terminar=false;
        OutputStream os;
        DataOutputStream flujoDOS;
        
 	public Out(Socket socket, String name){      //Recibe objeto de tipo Socket para identificar el Socket que está ejecutando el proceso y
                                                                                     // en el parámetro name recibirá como el cliente desea ser nombrado
		entrada = new Scanner(System.in);  //Objeto para recibir datos desde teclado
		this.socket=socket;
		this.name=name;
		run();  //Inicia el Hilo, se llama automáticamente al método run()
		}

    public void run(){
        try {
            os= socket.getOutputStream();
            flujoDOS = new DataOutputStream(os);
        }
        catch(Exception e){
            System.out.println("Error");
        }
    }
    
    public void write(String msg){
        try{
            os= socket.getOutputStream();
            flujoDOS = new DataOutputStream(os);
            flujoDOS.writeUTF(name+" █msg: █"+msg);
        }
        catch(Exception e){
            System.out.println("Error");
            }
        }
    
    public void end(){
        try {
            terminar=true;
            socket.close();
        } catch(Exception e){
            System.out.println("Error");
        }
    }
}

    

