package Game.Sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    static AudioFormat audioFormat;
    static AudioInputStream audioInputStream;
    static SourceDataLine sourceDataLine;
    static boolean stopPlayback = false;
    static public String basepath = new File("").getAbsolutePath();

    DataLine.Info dataLineInfo;


    public Sound(String file){
        setSound(basepath + file);

    }

    public Sound(){

    }

    public void setSound(String file){
        try{
            File soundFile = new File(file);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioInputStream.getFormat();

            dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);




        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void playSound() {

        new PlayThread().start();
    }


    public static void setPlaySound(String file){
        //for simple, uncommon sounds
        //laziness

        try{
            File soundFile = new File(basepath+file);
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            audioFormat = audioInputStream.getFormat();

            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);

            new PlayThread().start();
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    static class PlayThread extends Thread{
        byte buffer[] = new byte[10000];

        public void run(){
            try {
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                int count;
                while ((count=audioInputStream.read(buffer,0,buffer.length)) != -1 && stopPlayback==false){
                    if(count>0){
                        sourceDataLine.write(buffer,0,count);
                    }
                }

                sourceDataLine.drain();
                sourceDataLine.close();

                stopPlayback = false;

            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }


}
