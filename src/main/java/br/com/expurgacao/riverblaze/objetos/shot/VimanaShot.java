package br.com.expurgacao.riverblaze.objetos.shot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.enums.EnemyEnum;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;

/**
 * Created by Nysio on 29/12/2015.
 */
public class VimanaShot extends Shot {

    private Bitmap spritesheet;
    private int speed;
    private KindShotEnum kindShotEnum;
    private boolean up;
    private boolean down;

    public VimanaShot(Bitmap image, int x, int y, int w, int h, int speed, KindShotEnum kindShotEnum, boolean up, boolean down) {
        super.x = x;
        super.y = y;

        xDown = x;
        yDown = y;

        width = w;
        height = h;

        xUp = x;
        yUp = y;

        this.up = up;
        this.down = down;

        this.speed = speed;
        this.spritesheet = image;
        this.kindShotEnum = kindShotEnum;
    }

    @Override
    public void update() {
        if(KindShotEnum.SHOT_BLUE_TREE.equals(this.kindShotEnum)){
            xUp += speed;
            yUp -= 5;

            xDown += speed;
            yDown += 5;

            if(up){
                x = xUp;
                y = yUp;
            }
            if(down){
                x = xDown;
                y = yDown;
            }
            x += speed;
        }else{
            x += speed;
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(!up && !down){
            canvas.drawBitmap(spritesheet,x,y,null);
            return;
        }
        if(up){
            canvas.drawBitmap(spritesheet,xUp,yUp,null);
            return;
        }
        if(down){
            canvas.drawBitmap(spritesheet,xDown,yDown,null);
            return;
        }
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public boolean shotCollision(GameObject gameObject){
        if(gameObject.getEnemyEnum().equals(EnemyEnum.ATIRADOR)){
            if (this.getX() >= gameObject.getX() && this.getX() <= (gameObject.getX() + gameObject.getWidth()) &&
                    this.getY() <= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) && this.getY() >= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) - gameObject.getHeight()) {
                return true;
            }
            if (KindShotEnum.SHOT_BLUE_TREE.equals(this.kindShotEnum)) {
                if (xDown >= gameObject.getX() && xDown <= (gameObject.getX() + gameObject.getWidth()) &&
                        yDown <= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) && yDown >= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) - gameObject.getHeight()
                        ||
                        xUp >= gameObject.getX() && xUp <= (gameObject.getX() + gameObject.getWidth()) &&
                                yUp <= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) && yUp >= (GamePanel.HEIGHT - (GamePanel.HEIGHT - gameObject.getY())) - gameObject.getHeight()
                        ) {
                    return true;
                }
            }
        }
        if(gameObject.getEnemyEnum().equals(EnemyEnum.CEO_TRASH)) {
            if(Rect.intersects(this.getRect(),gameObject.getRect())){
                return true;
            }
        }
        if(gameObject.getEnemyEnum().equals(EnemyEnum.AIR_PLANE)){
            if(Rect.intersects(this.getRect(),gameObject.getRect())){
                return true;
            }
        }
        // -----------------------------------------------------------------------------------------------------------------------------
        return false;
    }

}

