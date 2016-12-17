package br.com.expurgacao.riverblaze.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.objetos.Player;

/**
 * Created by Nysio on 16/01/2016.
 * param sizeThetext que aparece em alguns metodos 1 - BigText, 2 - Medium Text, 3 - Small Text
 *
 */
public class Draw {

    private Context context;
    private ConfigLoad configLoad;

    public Draw(Context context, ConfigLoad configLoad){
        this.context = context;
        this.configLoad = configLoad;
    }

    private float getTextSize(int sizeTheText){
        if(sizeTheText == 3){
            return this.configLoad.textSizeBig();
        }else if(sizeTheText == 2) {
            return this.configLoad.textSizeMedium();
        } else if(sizeTheText == 1){
            return this.configLoad.textSizeSmall();
        }else {
            return 0f;
        }
    }

    public void drawTextAndBitmap(Canvas canvas, String text, Bitmap bitmap, int xBitmap, int yBitmap, int xText, int yText, int corTexto, int sizeTheText){
        Paint paint = new Paint();
        paint.setColor(corTexto);

        paint.setTextSize(getTextSize(sizeTheText));

        paint.setTypeface(Typeface.createFromAsset(context.getAssets(), "Minecraft/minecraft_font_by_pwnage_block-d37t6nb.ttf"));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD));
        canvas.drawBitmap(bitmap, xBitmap, yBitmap, null);
        canvas.drawText(text, xText, yText, paint);
    }

    public void drawText(Canvas canvas, String text, int x, int y, int sizeTheText){

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(getTextSize(sizeTheText));
        paint.setTypeface(Typeface.createFromAsset(context.getAssets(), "Minecraft/minecraft_font_by_pwnage_block-d37t6nb.ttf"));

        canvas.drawText(text, x, y, paint);
    }

    public void drawText(Canvas canvas, boolean newGameCreated, boolean reset, Player player, int sizeTheText){

        Paint paint = new Paint();

        paint.setColor(Color.WHITE);
        paint.setTextSize(getTextSize(sizeTheText));
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        if(!player.getPlaying() && newGameCreated && reset){

            Paint paint1 = new Paint();
            paint1.setColor(Color.BLACK);
            paint1.setTextSize(getTextSize(sizeTheText));
            paint.setTypeface(Typeface.createFromAsset(context.getAssets(), "Minecraft/minecraft_font_by_pwnage_block-d37t6nb.ttf"));

            canvas.drawText("TOQUE PARA INICIAR", (GamePanel.WIDTH_SCREEN/2)/2, GamePanel.HEIGHT/2, paint1);
        }

    }

    public void drawLine(Canvas canvas){

        Paint paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3f);
        canvas.drawLine((GamePanel.WIDTH_SCREEN / 5) * 4, (GamePanel.HEIGHT / 5) * 4, GamePanel.WIDTH_SCREEN, (GamePanel.HEIGHT / 5) * 4, paint);
        canvas.drawLine((GamePanel.WIDTH_SCREEN/5) * 4, (GamePanel.HEIGHT/5) * 4, (GamePanel.WIDTH_SCREEN/5) * 4, GamePanel.HEIGHT, paint);

    }

    public void drawRect(Canvas canvas, Rect rect){
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        canvas.drawRect(rect, paint);
    }

}
