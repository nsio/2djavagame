package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.animation.Animation;

/**
 * Created by Nysio on 15/12/2015.
 */
public class Explosion {

    private int x;
    private int y;
    private int width;
    private int height;
    private int row;
    private Animation animation = new Animation();
    private Bitmap spriteSheet;

    public Explosion(Bitmap res, int x, int y, int w, int h, int numFrames){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        Bitmap[] image = new Bitmap[numFrames];

        spriteSheet = res;

        for(int i = 0; i<image.length; i++){
            if(i%5==0&&i>0){
                row++;
            }
            image[i] = Bitmap.createBitmap(spriteSheet, (i-(5*row))*width, row*height, width, height);
        }
        animation.setFrames(image);
        animation.setDelay(10);

    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }

    public void update(){
        animation.update();
    }

    public int getHeight(){
        return height;
    }
}
