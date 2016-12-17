package br.com.expurgacao.riverblaze.objetos.aviao;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.animation.Animation;

/**
 * Created by NysioFontes on 01/03/2016.
 */
public class AviaoSQPrimeiraFormacao extends Aviao{

    private int widthScreen;
    private int heightScreen;
    private float vx = 5.f;

    private Bitmap aviao;

    public AviaoSQPrimeiraFormacao(Bitmap aviao, float x, float y){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
    }

    public AviaoSQPrimeiraFormacao(Bitmap aviao, float x, float y, float vx){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
        this.vx = vx;
    }

    @Override
    public void update(){
        this.x -= this.vx;
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
