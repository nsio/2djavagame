package br.com.expurgacao.riverblaze;

import android.graphics.Rect;
import br.com.expurgacao.riverblaze.enums.EnemyEnum;

/**
 * Created by Nysio on 04/12/2015.
 */
public abstract class GameObject{

    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int width;
    protected int height;
    protected int power;
    protected EnemyEnum enemyEnum;

    public abstract boolean hasCollision(GameObject gameObject);

    public int getPower(){
        return power;
    }

    public void setPower(int power){
        this.power=power;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public EnemyEnum getEnemyEnum(){
        return enemyEnum;
    }

    public void setEnemyEnum(EnemyEnum enemyEnum){
        this.enemyEnum=enemyEnum;
    }

    public Rect getRect(){
        return new Rect(x, y, x+width, y+height);
    }

}
