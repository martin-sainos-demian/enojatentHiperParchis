
package enojatenthiperparchis.object.text;

import enojatenthiperparchis.All;
import enojatenthiperparchis.object.Thing;
import enojatenthiperparchis.states.State;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

public class DisplayText extends Thing{
    public String text;
    public Map<String, BufferedImage> font;
    
    public DisplayText(All all, float x, float y, int id, State state, String text, Map<String, BufferedImage> font){
        super(all, x, y, id, state);
        this.text=text;
        this.font=font;
        formatText();
    }
    
    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        String[] chars=text.split("");
        for(int i=0;i<chars.length;i++){
            BufferedImage letter=font.get(chars[i]);
            if(letter!=null)
                g.drawImage(letter,(int)x+i*letter.getWidth(),(int) y, null);
        }
    }
    
    public void formatText(){
        String newText="";
        String[] chars=text.split("");
        for(int c=0;c<chars.length;c++){
            if(font.get(chars[c])!=null){
                newText+=chars[c];
            }
        }
        text=newText;
    }

    public void setText(String text) {
        this.text = text;
        formatText();
    }
    
}