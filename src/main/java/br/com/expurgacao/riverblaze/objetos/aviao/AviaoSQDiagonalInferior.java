package br.com.expurgacao.riverblaze.objetos.aviao;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;

/**
 * Created by NysioFontes on 01/03/2016.
 */
public class AviaoSQDiagonalInferior extends Aviao {

    private float dx = 6.f;
    private float dy = 2.f;

    private Bitmap aviao;

    public AviaoSQDiagonalInferior(Bitmap aviao, float x, float y){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(){
        this.x -= dx;
        this.y -= dy;
    }

    @Override
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(this.aviao, this.x, this.y, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

}
