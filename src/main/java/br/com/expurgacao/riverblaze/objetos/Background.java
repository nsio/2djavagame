package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import br.com.expurgacao.riverblaze.GamePanel;

/**
 * Created by Nysio on 03/12/2015.
 */
public class Background {

    private Bitmap image;
    private int x, y, dx;

    public Background(Bitmap res){
        this.image = res;
        dx = GamePanel.MOVESPEED;
        this.x = 0;
    }

    public void update(){
        if(this.x > -GamePanel.WIDTH_TOTAL){
            this.x+=dx;
        }
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(image, this.x, y, null);
        if(x<0){
            canvas.drawBitmap(image, this.x+GamePanel.WIDTH_SCREEN, y, null);
        }
    }

    public int getX(){
        return this.x;
    }

}
