package br.com.expurgacao.riverblaze.display;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by NysioFontes on 07/01/2016.
 */
public class VimanaLife {

    private Bitmap spritesheet;
    private int x;
    private int y;

    public VimanaLife(Bitmap image, int x, int y){

        this.x = x;
        this.y = y;

        //width = w;
        //height = h;
        this.spritesheet = image;
    }

    public void update(){

    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(spritesheet, x, y, null);
        }catch(Exception e){}
    }

}
