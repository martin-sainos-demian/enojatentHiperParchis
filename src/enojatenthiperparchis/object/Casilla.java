 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enojatenthiperparchis.object;

import enojatenthiperparchis.All;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author PC
 */
public class Casilla extends Thing {

    public Canica canica;
    public boolean hueco=false;
    Table table;
    public Player player;
    
    public Casilla(All all, float x, float y, int id, Table table){
        super(all, x, y, id, table.getState());
        this.table=table;
    }
    public Casilla(All all, float x, float y, int id, Table table, boolean hueco){
        this(all,x,y,id,table);
        this.hueco=hueco;
        if(!hueco){
            canica=null;
        }
    }
    
    @Override
    public void tick() {
        super.update();
        if(hueco){
            if(canica!=null){
                if(!canica.assigned){
                    canica=null;
                }
            }
        }
    }

    @Override
    public void render(Graphics g){
        render(g,all.sprites().huecoFront[0],all.sprites().huecoBack[0]);
    }
    public void render(Graphics g, BufferedImage front, BufferedImage back) {
        int playerId=-1;
        for(Player p:table.players){
            for(Canica c:p.bolsa){
                if(c==canica){
                    playerId=p.id;
                }
            }
        }
        if(hueco){
            g.drawImage(back, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff,null);
            if(canica!=null){
                if(playerId!=-1)
                    canica.render(g, true);
                else
                    canica.render(g, true);
            }
            g.drawImage(front, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff,null);    
        }else{
            g.drawImage(all.sprites().sinHueco, (int)x-all.getCamara().xOff, (int)y-all.getCamara().yOff,null);
        }
    }
    
    public void setCanica(Canica canicanew){
        if(hueco){
            canica=canicanew;
            canica.assigned=true;
            canica.x=x;
            canica.y=y;
            afterSetCanica();
        }
    }
    
    public void afterSetCanica(){};
    
}
