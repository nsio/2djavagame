package br.com.expurgacao.riverblaze.animation;

import android.graphics.Bitmap;

/**
 * Created by Nysio on 05/12/2015.
 */
public class Animation {

    private Bitmap[] fames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames(Bitmap[] frames){
        this.fames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void setDelay(long d){
        delay = d;
    }

    public void setFrame(int i){
        currentFrame = i;
    }

    public void update(){
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed > delay){
            currentFrame++;
            startTime = System.nanoTime();
        }

        if (currentFrame==fames.length){
            currentFrame=0;
            playedOnce=true;
        }

    }

    public Bitmap getImage(){
        return fames[currentFrame];
    }

    public int getFrame() {
        return currentFrame;
    }

    public boolean playedOne(){
        return playedOnce;
    }

}
