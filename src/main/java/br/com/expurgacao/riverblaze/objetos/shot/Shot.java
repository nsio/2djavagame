package br.com.expurgacao.riverblaze.objetos.shot;

import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.GameObject;

/**
 * Created by Nysio on 19/12/2015.
 */
public abstract class Shot extends GameObject {

    protected int xDown;
    protected int yDown;
    protected int xUp;
    protected int yUp;

    public abstract void update();

    public abstract void draw(Canvas canvas);

    public abstract int getWidth();

    public abstract boolean shotCollision(GameObject gameObject);

}
