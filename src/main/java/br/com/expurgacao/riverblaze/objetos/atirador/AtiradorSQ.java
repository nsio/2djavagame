package br.com.expurgacao.riverblaze.objetos.atirador;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;

/**
 * Created by Nysio on 01/03/2016.
 */
public class AtiradorSQ extends Atirador {

    private int widthScreen;
    private int heightScreen;

    private Bitmap atirador;

    public AtiradorSQ(Bitmap atirador, float x, float y){
        this.atirador = atirador;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(){
        this.x -= 2.1;
    }

    @Override
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(this.atirador, this.x, this.y, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }



}
