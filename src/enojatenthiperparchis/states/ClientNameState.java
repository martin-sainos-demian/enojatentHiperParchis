
package enojatenthiperparchis.states;

import enojatenthiperparchis.All;
import enojatenthiperparchis.Game;
import enojatenthiperparchis.input.KeyInput;
import enojatenthiperparchis.object.text.DisplayText;
import enojatenthiperparchis.object.text.InputText;
import java.awt.Graphics;

public class ClientNameState extends State{
    
    DisplayText name,nameTxt,place,ready;
    InputText inText;
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
                    ready.setText("READY");
                    stateNum++;
                }
                if(stateNum==2){
                    game.setState(new ClientState(game,nameTxt.text,text));
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
        ready.tick();
        inText.render(g);
    }
}
