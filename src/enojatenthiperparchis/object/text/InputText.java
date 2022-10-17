
package enojatenthiperparchis.object.text;

import enojatenthiperparchis.All;
import enojatenthiperparchis.input.KeyInput;
import enojatenthiperparchis.states.State;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Map;

public class InputText extends DisplayText{
    
    KeyInput keys;
    
    public InputText(All all, float x, float y, int id, State state, Map<String, BufferedImage> font, KeyInput keys){
        super(all,x,y,id,state,"",font);
        this.keys=keys;
    }
    @Override
    public void tick(){
        super.tick();
        boolean[] typed=keys.getTyped();
        if(keys.keyJustPressed(KeyEvent.VK_ENTER)){
            enter();
        }
        if(keys.keyJustPressed(KeyEvent.VK_BACK_SPACE)){
            if(text.length()>0){
                text=text.substring(0, text.length()-1);
            }
        }
        for(int i=0;i<typed.length;i++){
            if(keys.keyJustPressed(i)){
                String letter=KeyEvent.getKeyText(i);
                if(i==KeyEvent.VK_PERIOD)
                    letter=".";
                if(i==KeyEvent.VK_SPACE)
                    letter=" ";
                System.out.println(letter);
                if(font.get(letter)!=null){
                    text+=letter;
                }
            }
        }
    }
    public void enter(){}
}