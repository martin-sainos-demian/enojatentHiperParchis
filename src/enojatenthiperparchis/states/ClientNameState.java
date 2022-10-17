
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.input.KeyInput;
import enojatenthiperparchis.object.text.DisplayText;
import enojatenthiperparchis.object.text.InputText;
import java.awt.Graphics;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientNameState extends State{
    
    DisplayText name,nameTxt,place,ready;
    InputText inText;
    Socket socket;
    int stateNum=0;
    
    public ClientNameState(Game game){
        super(game);
        All all=game.getAll();
        audio.loop("pls name.wav");
        name=new DisplayText(all, 50, 32, 0, this,"+=:", all.fonts().sotn);
        inText=new InputText(all, name.x+48, 32, 0, this, all.fonts().sotn,game.keyInput){
            @Override
            public void enter(){
                if(stateNum==0&&!text.equals("")){
                    nameTxt.setText(text);
                    y+=64;
                    text="";
                    font=all.fonts().sotnNums;
                    stateNum++;
                }
                if(stateNum==1&&!text.equals("")){
                    stateNum++;
                }
                if(stateNum==2){
                    game.setState(new ClientState(game,nameTxt.text,text));
                }
            }
            public void esc(){
                if(stateNum>0){
                    text="";
                    font=all.fonts().sotn;
                    nameTxt.setText(text);
                    y=32;
                    stateNum=0;
                }else{
                    game.setState(1);
                }
            }
        };
        nameTxt=new DisplayText(all, inText.x, inText.y, 0, this,"", all.fonts().sotn);
        ready=new DisplayText(all, inText.x, inText.y+128, 0, this,"", all.fonts().sotn);
        place=new DisplayText(all, name.x, name.y+64, 0, this,"*()", all.fonts().sotn);
    }

    @Override
    public void tick() {
        name.tick();
        nameTxt.tick();
        place.tick();
        ready.tick();
        inText.tick();
    }

    @Override
    public void render(Graphics g) {
        name.render(g);
        nameTxt.render(g);
        place.render(g);
        ready.render(g);
        inText.render(g);
    }
    public boolean isSocketAlive(String hostName, int port) {
        boolean isAlive = false;
        // Creates a socket address from a hostname and a port number
        SocketAddress socketAddress = new InetSocketAddress(hostName, port);
        socket = new Socket();
        // Timeout required - it's in milliseconds
        int timeout = 2000;
        System.out.println("hostName: " + hostName + ", port: " + port);
        try {
            socket.connect(socketAddress, timeout);
//            socket.close();
            isAlive = true;
        } catch (Exception exception) {
            System.out.println(
                    "IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
        }
        return isAlive;
    }

}
