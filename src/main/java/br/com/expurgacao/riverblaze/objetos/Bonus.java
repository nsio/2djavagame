package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.Random;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;

/**
 * Created by NysioFontes on 22/12/2015.
 */
public class Bonus extends GameObject {

    private Bitmap spritesheet;
    private Random rnd = new Random();
    private KindShotEnum kindShotEnum;
    private int countExec = 0;
    private int numVezes = 0;
    private boolean up = false;
    private boolean left = false;

    public Bonus(Bitmap image, int x, int y, int w, int h, int speed, KindShotEnum kindShotEnum){

        super.x = x;
        super.y = y;

        dx = 0;
        dy = 0;

        width = w;
        height = h;

        this.spritesheet = image;
        this.kindShotEnum = kindShotEnum;
    }

    public void update(){
        countExec++;

        if(numVezes == 0){
            numVezes = rnd.nextInt(190);
        }

        if(left){
            x -= 0.5;
        }else{
            x += 0.5;
        }

        if(up){
            y -= 1;
        }else{
            y += 1;
        }

        if(countExec == numVezes){
            countExec = 0;
            numVezes = 0;
            if(up){
                up=false;
            }else{
                up=true;
            }
            if(left){
                left=false;
            }else{
                left=true;
            }
        }

    }

    public void draw(Canvas canvas){
        try{
            canvas.drawBitmap(spritesheet, x, y, null);
        }catch(Exception e){}
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

    @Override
    public int getWidth(){
        return width-10;
    }

    public KindShotEnum getKindShotEnum(){
        return kindShotEnum;
    }

}
