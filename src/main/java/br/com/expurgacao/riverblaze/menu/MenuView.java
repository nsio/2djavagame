package br.com.expurgacao.riverblaze.menu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by NysioFontes on 07/01/2016.
 */
public class MenuView extends View {

    public MenuView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);
        int x=80;
        int y=80;
        int radius=40;
        Paint paint=new Paint();
        // Use Color.parseColor to define HTML colors
        paint.setColor(Color.parseColor("#CD5C5C"));
        canvas.drawCircle(x,x, radius, paint);

    }

}
