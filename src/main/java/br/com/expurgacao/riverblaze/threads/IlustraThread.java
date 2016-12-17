package br.com.expurgacao.riverblaze.threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.splash.abertura.IlustraAberturaSurface;

/**
 * Created by Nysio on 16/01/2016.
 */
public class IlustraThread extends Thread {

    private IlustraAberturaSurface ilustraAberturaSurface;
    private SurfaceHolder surfaceHolder;
    private boolean running;
    public static Canvas canvas;

    public IlustraThread(SurfaceHolder surfaceHolder, IlustraAberturaSurface ilustraAberturaSurface){
        super();
        this.surfaceHolder=surfaceHolder;
        this.ilustraAberturaSurface=ilustraAberturaSurface;
    }

    @Override
    public void run(){
        short aberturaIndex=1;

        while(running){

            canvas = null;
            // lock de canvas
            try{

                canvas = this.surfaceHolder.lockCanvas();

                synchronized (surfaceHolder){

                    this.ilustraAberturaSurface.draw(canvas, aberturaIndex);

                }

            }catch(Exception e){

            } finally{
                if(canvas!=null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            try{

                sleep(Math.abs(15000));

            }catch(InterruptedException e){}
            aberturaIndex++;
            if(aberturaIndex == 6){
                this.ilustraAberturaSurface.setToGoNextActivity(true);
            }

        }

    }

    public void setRunning(boolean running){
        this.running=running;
    }

}
