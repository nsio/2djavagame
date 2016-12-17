package br.com.expurgacao.riverblaze.objetos.aviao;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.animation.Animation;

/**
 * Created by Nysio on 20/02/2016.
 */
public abstract class Aviao {

    protected float x;
    protected float y;

    public abstract void update();
    public abstract void draw(Canvas canvas);
    public abstract boolean hasCollision(GameObject gameObject);

    public float getX() {
        return this.x;
    }
    public float getY() {
        return this.y;
    }

}
