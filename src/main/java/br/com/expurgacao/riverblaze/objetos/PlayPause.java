package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;

/**
 * Created by NysioFontes on 22/12/2015.
 */
public class PlayPause extends GameObject {

    private Bitmap spritesheet;
    private boolean pause=false;

    public PlayPause(Bitmap image, int x, int y){

        super.x = x;
        super.y = y;

        spritesheet = image;

    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(spritesheet, x, y, null);
        }catch(Exception e){

        }
    }

    @Override
    public int getWidth(){
        return width-10;
    }

    public void setSpritesheet(Bitmap spritesheet){
        this.spritesheet = spritesheet;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    @Override
    public boolean hasCollision(GameObject gameObject) {
        return false;
    }

}
