package br.com.expurgacao.riverblaze.splash.abertura;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.draw.Draw;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.load.SoundLoad;
import br.com.expurgacao.riverblaze.load.SpritsLoad;
import br.com.expurgacao.riverblaze.objetos.shot.VimanaShot;
import br.com.expurgacao.riverblaze.splash.R;

/**
 * Created by Nysio on 16/01/2016.
 */
public class IlustraAbertura extends Activity implements GestureDetector.OnGestureListener {

    private IlustraAberturaSurface aberturaSurface;
    private GestureDetector gestureDetector;
    private ConfigLoad configLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        gestureDetector = new GestureDetector(this, this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        configLoad = new ConfigLoad(metrics);

        aberturaSurface = new IlustraAberturaSurface(this, configLoad);
        setContentView(aberturaSurface);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.aberturaSurface.goNextActivity();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}
