package br.com.expurgacao.riverblaze;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Nysio on 02/12/2015.
 */
public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    private boolean pause = false;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder=surfaceHolder;
        this.gamePanel=gamePanel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0L;
        int frameCount = 0;
        long targetTime = 1000/this.FPS;
        while(running){
            // ------------------------------------------------------------------------------------
            // Jogo em pausa
            if(pause){
                continue;
            }
            // ------------------------------------------------------------------------------------
            startTime = System.nanoTime();
            canvas = null;
            // lock de canvas
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch(Exception e){}
            finally{
                if(canvas!=null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime()-startTime)/1000000;
            waitTime = targetTime-timeMillis;
            try{
                sleep(Math.abs(waitTime));
            }catch(InterruptedException e){}
            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public void setRunning(boolean running){
        this.running=running;
    }

    public void setPause(boolean pause){
        this.pause=pause;
    }

}
