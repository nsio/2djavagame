package br.com.expurgacao.riverblaze.objetos.atirador;

import android.graphics.Canvas;

import br.com.expurgacao.riverblaze.GameObject;

/**
 * Created by Nysio on 01/03/2016.
 */
public abstract class Atirador {

    protected float x;
    protected float y;

    public abstract void update();
    public abstract void draw(Canvas canvas);
    public abstract boolean hasCollision(GameObject gameObject);

    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }


}
