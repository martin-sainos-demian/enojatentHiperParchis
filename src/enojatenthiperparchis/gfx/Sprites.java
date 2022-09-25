
package enojatenthiperparchis.gfx;

import java.awt.image.BufferedImage;

public class Sprites {
    private static final int width=32 , height=32;
    private String path="res/imgHiperPlus/";
    
    public static BufferedImage mouse, botonDef;
    public static BufferedImage boulder;
    public static BufferedImage hueco, huecoFront[], huecoBack[], sinHueco;
    public static BufferedImage dice[];
    public static BufferedImage canicas[];
    public static BufferedImage credits;
    
    public Sprites(){
        Divide sheet = new Divide(ImageLoader.loadImage(path+"sprites.png"));
        
        boulder=sheet.crop(0, 0, width, height);
        mouse=sheet.crop(width, 0, width, height);
        botonDef=sheet.crop(0, height, width*2, height);
        
        huecoFront=new BufferedImage[5];
        huecoBack=new BufferedImage[5];
        hueco=sheet.crop(0, height*2, width, height);
        huecoFront[0]=sheet.crop(width, height*2, width, height);
        huecoBack[0]=sheet.crop(width*2, height*2, width, height);
        huecoFront[1]=sheet.crop(width*2, 0, width, height);
        huecoBack[1]=sheet.crop(width*3, 0, width, height);
        huecoFront[2]=sheet.crop(width*2, height, width, height);
        huecoBack[2]=sheet.crop(width*3, height, width, height);
        huecoFront[3]=sheet.crop(width*4, 0, width, height);
        huecoBack[3]=sheet.crop(width*5, 0, width, height);
        huecoFront[4]=sheet.crop(width*4, height, width, height);
        huecoBack[4]=sheet.crop(width*5, height, width, height);
        sinHueco=sheet.crop(width*3, height*2, width, height);
        
        
        Divide diceSheet = new Divide(ImageLoader.loadImage(path+"playDice.png"));
        dice=new BufferedImage[6];
        
        dice[0]=diceSheet.crop(0, 0, width, height);
        dice[1]=diceSheet.crop(width, 0, width, height);
        dice[2]=diceSheet.crop(0, height, width, height);
        dice[3]=diceSheet.crop(width, height, width, height);
        dice[4]=diceSheet.crop(0, height*2, width, height);
        dice[5]=diceSheet.crop(width, height*2, width, height);
        
        canicas=new BufferedImage[4];
        canicas[0]=sheet.crop(0, height*3, width, height);
        canicas[1]=sheet.crop(width, height*3, width, height);
        canicas[2]=sheet.crop(width*2, height*3, width, height);
        canicas[3]=sheet.crop(width*3, height*3, width, height);
        
        Divide creditsSheet = new Divide(ImageLoader.loadImage(path+"credits.png"));
        credits=creditsSheet.crop(0, 0, 128, 128);
    }
    
    public static int getDefaultWidth(){
        return width;
    }
    public static int getDefaultHeight(){
        return height;
    }
}