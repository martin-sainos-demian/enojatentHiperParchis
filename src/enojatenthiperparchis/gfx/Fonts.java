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
    public Map<String, BufferedImage> sotn;
    public Map<String, BufferedImage> sotnNums;
    public Fonts(){
        init();
    }
    private void init(){
        sotn = loadFont("sotn.png","ABCDEFGHIJKLMNOPQRSTUVWXYZ&!-.'?+=:*()",16,16);
        sotnNums = loadFont("sotnNums.png","0123456789.",8,8);
    }
    private HashMap<String, BufferedImage> loadFont(String path, String letters, int width, int height){
        BufferedImage src=ImageLoader.loadImage(Sprites.path+"fonts/"+path);
        Divide sheet = new Divide(src);
        HashMap<String, BufferedImage> map = new HashMap<String, BufferedImage>();
        
        int w=src.getWidth()/width;
        int h=src.getHeight()/height;
        
        String[] chars=letters.split("");
        
        for(int x=0;x<w;x++){
            for(int y=0;y<h;y++){
                if(x+(y*w)>=chars.length)
                    break;
                System.out.println("x "+x+" y "+x);
                BufferedImage img=sheet.crop(width*x, height*y, width, height);
                map.put(chars[x+(y*w)], img);
            }
        }
        
        return map;
    }
}
