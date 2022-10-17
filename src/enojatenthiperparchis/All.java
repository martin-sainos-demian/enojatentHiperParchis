/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis;

import enojatenthiperparchis.gfx.Audio;
import enojatenthiperparchis.gfx.Camara;
import enojatenthiperparchis.gfx.Fonts;
import enojatenthiperparchis.gfx.Sprites;
import enojatenthiperparchis.input.MouseInput;

/**
 *
 * @author PC
 */
public class All {
    static Game game;
    int totalWidth=0, totalHeight=0;

    public int getTotalWidth() {
        return totalWidth;
    }

    public void setTotalWidth(int totalWidth) {
        this.totalWidth = totalWidth;
    }

    public int getTotalHeight() {
        return totalHeight;
    }

    public void setTotalHeight(int totalHeight) {
        this.totalHeight = totalHeight;
    }
    
    public All(Game game){
        this.game=game;
    }
    
    public static int getDefaultWidth(){
        return game.getSprites().getDefaultWidth();
    }
    public static int getDefaultHeight(){
        return game.getSprites().getDefaultHeight();
    }
    public static int getScreenWidth(){
        return game.width;
    }
    public static int getScreenHeight(){
        return game.height;
    }
    public static Sprites sprites(){
        return game.sprites;
    }
    public static Camara getCamara(){
        return game.camara;
    }
    public static MouseInput mouseInput(){
        return game.mouseInput;
    }
    public static Audio audio(){
        return game.audio;
    }
    public static Fonts fonts(){
        return game.fonts;
    }
}