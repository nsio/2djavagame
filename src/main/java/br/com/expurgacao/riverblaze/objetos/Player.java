package br.com.expurgacao.riverblaze.objetos;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.Map;
import br.com.expurgacao.riverblaze.GameObject;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.animation.Animation;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;
import br.com.expurgacao.riverblaze.objetos.shot.VimanaShot;

/**
 * Created by Nysio on 04/12/2015.
 */
public class Player extends GameObject {

    private Bitmap nave;
    private int score;
    private boolean up;
    private boolean rigth;
    private float touchY;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private long startTimeToShot;
    private boolean isUpdate;
    private ArrayList<VimanaShot> shots = new ArrayList<VimanaShot>();
    private boolean isToMoveAxisX=false;
    private boolean isToMoveAxisY=false;
    private KindShotEnum kindShot;
    private Map<KindShotEnum, Bitmap> kindShotBitmapMap;
    private int bombas = 3;

    private Boolean breveImpulso=false;

    public Player(Bitmap res, int w, int h, int numFrames, Map<KindShotEnum, Bitmap> kindShotBitmapMap){
        x = 50;
        y = GamePanel.HEIGHT/2;
        dy = 0;
        dx = 0;
        score = 0;
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        nave = res;

        for(int i=0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(nave, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(1);

        this.kindShotBitmapMap = kindShotBitmapMap;

        startTime = System.nanoTime();
    }

    public void update(){
        for(int i = 0; i < this.shots.size(); i++){
            if(this.shots.get(i).getX() > GamePanel.WIDTH_SCREEN + 3){
                this.shots.remove(i);
                break;
            }
        }
        long elapsedToShot = (System.nanoTime()-startTimeToShot)/1000000;
        if(elapsedToShot >= 500){
            if(KindShotEnum.ATOMIC_BOMB.equals(this.kindShot)){
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), this.getY(), 10, 10, 15, this.kindShot, false, false));
            }
            if(KindShotEnum.SHOT_BLUE_ONE.equals(this.kindShot)){
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), this.getY(), 10, 10, 15, this.kindShot, false, false));
            }
            if(KindShotEnum.SHOT_BLUE_TWO.equals(this.kindShot)) {
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), (this.getY() - 10), 10, 10, 15, this.kindShot, false, false));
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), (this.getY() + 10), 10, 10, 15, this.kindShot, false, false));
            }
            if(KindShotEnum.SHOT_BLUE_TREE.equals(this.kindShot)) {
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), this.getY(), 10, 10, 15, this.kindShot, false, false));
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), (this.getY()+10), 10, 10, 15, this.kindShot, true, false));
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), (this.getY()-10), 10, 10, 15, this.kindShot, false, true));
                addShot(new VimanaShot(this.kindShotBitmapMap.get(this.kindShot), ((this.getX() + this.width) - 9), this.getY(), 10, 10, 15, this.kindShot, false, false));
            }
            startTimeToShot = System.nanoTime();
        }
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100){
            score++;
            startTime = System.nanoTime();
        }
        animation.update();
        if(isUpdate){
            if(isToMoveAxisY){
                if (up) {
                    dy += 7.1;
                } else {
                    dy -= 7.1;
                }
                if (dy > 5) {
                    dy = 5;
                }
                if (dy < -5) {
                    dy = -5;
                }
                if(breveImpulso){
                    dy = dy*5;
                }
                y += dy * 2;
                dy = 0;
                isToMoveAxisY = false;
            }
            if(isToMoveAxisX){
                if (rigth) {
                    dx += 7.1;
                } else {
                    x -= 7.1;
                }
                if (dx > 5) {
                    dx = 5;
                }
                if (dx < -5) {
                    dx = -5;
                }
                if(breveImpulso){
                    dx = dx*5;
                }
                x += dx * 2;
                dx = 0;
                isToMoveAxisX = false;
            }
            if (y >= GamePanel.HEIGHT - 23) {
                y = GamePanel.HEIGHT - 23;
            }
            if (y <= 13) {
                y = 13;
            }
            if(x >= GamePanel.WIDTH -2){
                x = GamePanel.WIDTH -2;
            }
            if(x <= 13){
                x = 13;
            }
            if(breveImpulso){
                breveImpulso=false;
            }
        }
    }

    public void setToMoveAxisX(boolean toMoveAxisX){
        this.isToMoveAxisX = toMoveAxisX;
    }

    public void setToMoveAxisY(boolean toMoveAxisY){
        this.isToMoveAxisY = toMoveAxisY;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public void setUp(boolean b){
        this.up=b;
    }

    public void setRigth(boolean b){
        this.rigth=b;
    }

    public int getScore(){
        return score;
    }

    public boolean getPlaying(){
        return playing;
    }

    public void setPlaying(boolean b){
        this.playing = b;
    }

    public void resetDY(){
        dy = 0;
    }

    public void resetScore(){
        score = 0;
    }

    public void setTouchY(float touchY){
        this.touchY = touchY;
    }

    public void isUpdate(boolean isUpdate){
        this.isUpdate=isUpdate;
    }

    public void addShot(VimanaShot shot){
        this.shots.add(shot);
    }

    public void removeShot(int i){
        this.shots.remove(i);
    }

    public ArrayList<VimanaShot> getShots(){
        return this.shots;
    }

    public void addBomba(){
        this.bombas++;
    }

    public void removerBomba(){
        this.bombas--;
    }

    public int getBombas(){
        return this.bombas;
    }

    public void reset(){
        this.bombas = 3;
        this.shots.clear();
        kindShot = KindShotEnum.SHOT_BLUE_ONE;
    }

    public KindShotEnum getKindShot(){
        return kindShot;
    }

    public void setKindShot(KindShotEnum kindShot){
        this.kindShot = kindShot;
    }

    public void setBreveImpulso(Boolean breveImpulso) {
        this.breveImpulso = breveImpulso;
    }

    @Override
    public boolean hasCollision(GameObject gameObject){
        return false;
    }

}
