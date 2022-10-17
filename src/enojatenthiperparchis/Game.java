
package enojatenthiperparchis;

import enojatenthiperparchis.gfx.*;
import enojatenthiperparchis.input.KeyInput;
import enojatenthiperparchis.input.MouseInput;
import enojatenthiperparchis.states.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game {
    static String titulo;
    static int width, height;
    static Display display;
    static BufferStrategy buffer;
    static Graphics g;
    static State state;
    static State[] stateList;
    public static int currentStateId;
    static Sprites sprites;
    public MouseInput mouseInput;
    public static KeyInput keyInput;
    static All all;
    public static Camara camara;
    static Audio audio;
    static Fonts fonts;
    
    static boolean running=false;
    
    public Game(String titulo, int width, int height){
        this.titulo=titulo;
        this.width=width;
        this.height=height;
        
        mouseInput=new MouseInput();
        keyInput=new KeyInput();
        display=new Display(titulo,width,height);
	display.getFrame().addKeyListener(keyInput);
        display.getFrame().addMouseListener(mouseInput);
        display.getFrame().addMouseMotionListener(mouseInput);
        display.getCanvas().addMouseListener(mouseInput);
        display.getCanvas().addMouseMotionListener(mouseInput);
        
        
        
        sprites=new Sprites();
        all=new All(this);
        camara=new Camara(all);
        currentStateId=0;
        audio=new Audio();
        fonts=new Fonts();
        setState(currentStateId);
    }
    public static void run(){
        if(running){
            return;
        }
        else {
            running=true;
            while(running){
                tick();
                render();
            }
        }
    }
    
    public static void tick(){
	keyInput.tick();
        if(state!=null){
            state.tick();
        }
        if(camara!=null){
            camara.tick();
        }
        if(audio!=null){
            audio.tick();
        }
        
        width=display.getFrame().getWidth();
        height=display.getFrame().getHeight();
    }
    public static void render(){
        buffer=display.getCanvas().getBufferStrategy();
        if(buffer==null){
            display.getCanvas().createBufferStrategy(2);
            buffer=display.getCanvas().getBufferStrategy();
        }
        g=buffer.getDrawGraphics();
        g.setColor(new Color(167,54,169));
        g.fillRect(0, 0, width, height);
        //Dibujar
        state.render(g);
        //Fin del dibujo
        buffer.show();
        g.dispose();
    }

    public static Display getDisplay() {
        return display;
    }

    public static BufferStrategy getBuffer() {
        return buffer;
    }

    public static State getState() {
        return state;
    }

    public static Sprites getSprites() {
        return sprites;
    }
    
    public void nextState(){
        state.stop();
        setState(currentStateId+1);
    }
    public void setState(int id){
        if(state!=null)
            state.stop();
        boolean def=false;
        switch(id){
            case 0:
                state=new IntroState(this);
                break;
            case 1:
                state=new MenuState(this);
                break;
            case 2:
                state=new GameState(this);
                break;
            case 3:
                state=new ServerState(this);
                break;
            case 4:
                state=new ClientNameState(this);
                break;
            default:
                currentStateId=0;
                state=new MenuState(this);
                def=true;
                break;
        }
        if(!def){
            currentStateId=id;
        }
    }
    public void setState(State state){
        state.stop();
        this.state=state;
    }

    public static All getAll() {
        return all;
    }

}