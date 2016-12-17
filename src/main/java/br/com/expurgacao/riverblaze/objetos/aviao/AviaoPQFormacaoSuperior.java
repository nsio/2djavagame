package br.com.expurgacao.riverblaze.objetos.aviao;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;

/**
 * Created by NysioFontes on 01/03/2016.
 */
public class AviaoPQFormacaoSuperior extends Aviao {

    private float tempX;

    private int widthScreen;
    private int heightScreen;
    private float dx = 5.f;

    private Bitmap aviao;

    public AviaoPQFormacaoSuperior(Bitmap aviao, float x, float y, int widthScreen, int heightScreen){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
    }

    @Override
    public void update(){
        this.tempX = (this.x * this.x) / (GamePanel.HEIGHT / 2.f * 4.f);
        // best of best to y 1.4
        // best 1.3 to y
        // quando y configurado para 0.7 ocorre um movimento senoidal no eixo horizontal (X)
        this.y += 1.4f;
        if (this.y >= GamePanel.HEIGHT / 2.f) {
            this.x += dx;
        } else {
            this.x -= dx;
        }
    }

    @Override
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(aviao, tempX, y, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

}
