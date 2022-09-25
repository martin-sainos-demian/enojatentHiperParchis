
package enojatenthiperparchis.gfx;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
    
    public boolean completed=false;
    Clip clip;
    
    public void play(String musicLoc){
        completed=false;
        try {
            File musicPath = new File("res/music/"+musicLoc);
            if(musicPath.exists()){ 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }else{
                System.out.println("Couldn't find Music file");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void loop(String musicLoc){
        try {
            File musicPath = new File("res/music/"+musicLoc);
            if(musicPath.exists()){ 
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                clip.start();
            }else{
                System.out.println("Couldn't find Music file");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void stop(){
        try{
            if(clip!=null){
                clip.stop();
                completed=true;
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void tick(){
        if(clip.getMicrosecondLength() == clip.getMicrosecondPosition()){
            completed=true;
        }
    }
}