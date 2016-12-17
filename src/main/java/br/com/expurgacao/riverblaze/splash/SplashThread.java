package br.com.expurgacao.riverblaze.splash;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Nysio on 15/12/2015.
 */
public class SplashThread extends Thread{

    private SurfaceHolder surfaceHolder;
    private SplashSurface splashSurface;
    public static Canvas canvas;

    public SplashThread(SurfaceHolder surfaceHolder, SplashSurface splashSurface){
        super();
        this.surfaceHolder=surfaceHolder;
        this.splashSurface=splashSurface;
    }

    @Override
    public void run(){
        boolean firstRun = true;
        int animationTime = 9000;
        int videoIntroTime = 0;
        int counter = 0;

        while(counter < animationTime) {

            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.splashSurface.draw(canvas);
                }
            }catch (Exception e){}
            finally {
                if(canvas!=null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
           try{
               sleep(1000);
               firstRun = false;
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(!firstRun) {
                counter += 1000;
            }
        }

        this.splashSurface.callNextActivity();

        boolean retry = true;
        int count = 0;
        while(retry && count<1000){
            count++;
            try{
                this.join();
                retry = false;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
