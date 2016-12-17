package br.com.expurgacao.riverblaze.splash;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import br.com.expurgacao.riverblaze.menu.Menu;

/**
 * Created by Nysio on 15/12/2015.
 */
public class SplashSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Paint paint = new Paint();
    private SplashThread splashThread;
    private String[] letters = {"G","a","m","e","s"};
    private String word="";
    private int lastLetter = 0;
    private int x;
    private int y;
    private int nextXPostionLetter = 30;

    public SplashSurface(Context context, int x, int y){
        super(context);
        // add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        setFocusable(true);

        this.x = x;
        this.y = y;

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //splashThread = new SplashThread(this.getHolder(), this);
        // we can safely start the game loop
        //splashThread.start();

        callNextActivity();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){

    }

    @Override
    public void draw(Canvas canvas){
        // ajustar imagem na tela
        final float scaleFatorX = getWidth()/(this.x*1.f);
        final float scaleFatorY = getHeight()/(this.y*1.f);
        if(canvas != null){
            canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.background_expurga_color), scaleFatorX, scaleFatorY, null);

            final int savedState = canvas.save();
            canvas.scale(scaleFatorX, scaleFatorY);

            // PODERIA CRIAR UM OBJETO
            Bitmap expurgaLargeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.expurga_large);
            canvas.drawBitmap(expurgaLargeIcon, (this.x/2)-(expurgaLargeIcon.getHeight()/2) , scaleFatorY, null);

            canvas.restoreToCount(savedState);
            if(lastLetter >= 1) {
                if (lastLetter <= 5) {
                    word += letters[lastLetter - 1];
                    if (lastLetter == 0) {
                        nextXPostionLetter = 30;
                    } else {
                        nextXPostionLetter += 5;
                    }
                }
                paint.setColor(Color.WHITE);
                paint.setTextSize(73);
                paint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Minecraft/minecraft_font_by_pwnage_block-d37t6nb.ttf"));
                canvas.drawText(word, expurgaLargeIcon.getWidth() - 33, (expurgaLargeIcon.getWidth() / 2) + 197, paint);
            }
            lastLetter++;
        }
    }

    public void callNextActivity(){
        Intent menuGame = new Intent(getContext(), Menu.class);
        getContext().startActivity(menuGame);
        ((SplashAbertura)getContext()).finish();
    }

}
