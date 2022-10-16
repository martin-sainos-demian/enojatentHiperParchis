/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author PC
 */
public class Fonts {
    Map<String, BufferedImage> sotn;
    public Fonts(){
        init();
    }
    private void init(){
        sotn = loadFont("sotn.png","a",16,16);
    }
    private HashMap<String, BufferedImage> loadFont(String path, String letters, int width, int height){
        Divide sheet = new Divide(ImageLoader.loadImage(Sprites.path+"fonts/"+path));
        HashMap<String, BufferedImage> map = new HashMap<String, BufferedImage>();
        
        BufferedImage a=sheet.crop(0, 0, width, height);
        map.put("a", a);
        
        return map;
    }
}
