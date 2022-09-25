/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.gfx;

import enojatenthiperparchis.All;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 *
 * @author PC
 */
public class Camara {
    All all;
    public int xOff=0, yOff=0;
    private int border=64,move=3;
    
    public Camara(All all){
        this.all=all;
    }
    
    public BufferedImage rotate(int angle, BufferedImage image){
        double rotationRequired = Math.toRadians (angle);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing the rotated image at the required drawing locations
        return op.filter(image, null);
    }
    public void tick(){
        if(all.getScreenWidth()<all.getTotalWidth()
                &&all.mouseInput().x>border){
            xOff=all.mouseInput().x-border;
            if(all.getScreenWidth()+xOff>all.getTotalWidth()){
                xOff=all.getTotalWidth()-all.getScreenWidth();
            }
        }else{
            xOff=0;
        }
        if(all.getScreenHeight()<all.getTotalHeight()
                &&all.mouseInput().y>border){
            yOff=all.mouseInput().y-border;
            if(all.getScreenHeight()+yOff>all.getTotalHeight()){
                yOff=all.getTotalHeight()-all.getScreenHeight();
            }
        }else{
            yOff=0;
        }
    }
}
