package br.com.expurgacao.riverblaze.splash.abertura;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.draw.Draw;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.menu.Menu;
import br.com.expurgacao.riverblaze.splash.R;
import br.com.expurgacao.riverblaze.splash.SplashAbertura;
import br.com.expurgacao.riverblaze.threads.IlustraThread;

/**
 * Created by Nysio on 16/01/2016.
 */
public class IlustraAberturaSurface extends SurfaceView implements SurfaceHolder.Callback {

    private IlustraThread ilustraThread;
    private boolean isToGoNextActivity = false;
    private ConfigLoad configLoad;
    private Draw draw;

    public IlustraAberturaSurface(Context context, ConfigLoad configLoad) {
        super(context);

        this.configLoad = configLoad;
        this.draw = new Draw(context, this.configLoad);

        getHolder().addCallback(this);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        ilustraThread = new IlustraThread(getHolder(), this);
        ilustraThread.setRunning(true);
        ilustraThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        configLoad = null;
    }

    public void draw(Canvas canvas, short aberturaIndex){
        final float scaleFatorX = getWidth()/(1024*1.f);
        final float scaleFatorY = getHeight()/(768*1.f);
        if(canvas != null){
            canvas.scale(scaleFatorX, scaleFatorY);
            final int savedState = canvas.save();
            if(aberturaIndex <= 5) {
                switch (aberturaIndex){
                    case 1:
                        //canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.abertura_frame_01), 0, 0, null);
                        draw.drawText(canvas, " :: ILUSTRA (" + aberturaIndex + ") :: ", 70, 70, 2);
                        canvas.restoreToCount(savedState);
                        break;
                    case 2:
                        draw.drawText(canvas, " :: ILUSTRA (" + aberturaIndex + ") :: ", 70, 70, 2);
                        canvas.restoreToCount(savedState);
                        break;
                    case 3:
                        draw.drawText(canvas, " :: ILUSTRA (" + aberturaIndex + ") :: ", 70, 70, 2);
                        canvas.restoreToCount(savedState);
                        break;
                    case 4:
                        draw.drawText(canvas, " :: ILUSTRA (" + aberturaIndex + ") :: ", 70, 70, 2);
                        canvas.restoreToCount(savedState);
                        break;
                    case 5:
                        draw.drawText(canvas, " :: ILUSTRA (" + aberturaIndex + ") :: ", 70, 70, 2);
                        canvas.restoreToCount(savedState);
                    break;
                }
            }
        }
        if(isToGoNextActivity){
            goNextActivity();
        }
    }

    public void setToGoNextActivity(boolean isToGoNextActivity){
        this.isToGoNextActivity=isToGoNextActivity;
    }

    public void goNextActivity(){
        ilustraThread.setRunning(false);
        ilustraThread = null;
        Intent menuGame = new Intent(getContext(), Menu.class);
        getContext().startActivity(menuGame);
        ((IlustraAbertura)getContext()).finish();
        surfaceDestroyed(getHolder());
    }

}
