package br.com.expurgacao.riverblaze.level.one;

import android.graphics.Canvas;

/**
 * Created by Nysio on 21/02/2016.
 */
public abstract class Quadrante {

    protected long startTime;
    protected int widthScreen;
    protected int heightScreen;

    protected abstract void update();
    protected abstract void draw(Canvas canvas);
    protected abstract boolean quadranteFinalizado();
    protected abstract void finalizar();

}
