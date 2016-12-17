package br.com.expurgacao.riverblaze.objetos.aviao;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.animation.Animation;

/**
 * Created by NysioFontes on 01/03/2016.
 */
public class AviaoPQFormacaoInferior extends Aviao {

    private float x;
    private float y;
    private float tempX;

    private int widthScreen;
    private int heightScreen;
    private float dx = 5.f;

    private Bitmap aviao;

    public AviaoPQFormacaoInferior(Bitmap aviao, float x, float y, int widthScreen, int heightScreen){
        this.aviao = aviao;
        this.x = x;
        this.y = y;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
    }

    // ALEM DA FORMAÇÃO SUPERIOR E INFERIOR HÁ TAMBÉM OS VAIÕES DE OUTROS QUADRANTES, ENTÃO ESSE METOÓDO UPDATE DEVERÁ SER SOBRESCRITO,
    // CRIAR UMA CALSSE ABSTRATA AVIAO
    // DEPOIS CRIAR CLASSE CONCRETAS DO TIPO, AVIAO_PRIMEIRA_FORMACAO_PRIMEIRO_QUADRANTE, E ETC...
    // CRIAR UM ENUM PARA IDENTIFICAR O TIPO DE AVIAO, PRIMEIRO_QUADRANTE_FORMACAO 1, SEGUNDO_QUADRANTE_ .....
    @Override
    public void update(){
        this.tempX = (this.x * this.x) / (GamePanel.HEIGHT / 2.f * 4.f);
        this.y -= 1.4f;
        if (this.y >= GamePanel.HEIGHT / 2.f) {
            this.x -= dx;
        } else {
            this.x += dx;
        }
    }

    @Override
    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(this.aviao, this.tempX, this.y, null);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

}
