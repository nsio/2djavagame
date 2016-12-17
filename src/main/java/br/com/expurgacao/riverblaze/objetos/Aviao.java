package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.animation.Animation;

/**
 * Created by Nysio on 20/02/2016.
 */
public class Aviao extends GameObject {

    private float x;
    private float y;
    private float tempX;

    private int widthScreen;
    private int heightScreen;
    private float dx = 5.f;

    private Bitmap aviao;
    private Animation animation = new Animation();

    public Aviao(Bitmap aviao, float x, float y, int widthScreen, int heightScreen){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
    }

    public void update(){

        System.out.println("---------------------------------------------------------------------");
        System.out.println(this.tempX + "   " + this.x + "," +this.y);
        System.out.println("---------------------------------------------------------------------");

        this.tempX = (float)(this.x * this.x) / (GamePanel.HEIGHT/2.f * 4.f);

        // best 1.3 to y

        // quando y configurado para 0.7 ocorre um movimento senoidal no eixo horizontal (X)
        this.y += 1.4f;
        //this.x = this.tempX;

        if(this.y >= GamePanel.HEIGHT/2.f){
            this.x += dx;
        }else{
            this.x -= dx;
        }

    }

    public void draw(Canvas canvas){
       // System.out.println(x + "," +y);
        try{
            canvas.drawBitmap(aviao, tempX, y, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject) {
        return false;
    }

}
