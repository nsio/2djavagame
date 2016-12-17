package br.com.expurgacao.riverblaze.enums;

public enum EnemyEnum {

    ATIRADOR("atirador", 1),
    CEO_TRASH("ceo_trash", 2),
    AIR_PLANE("air_plane", 3);

    private String enemyKind;
    private int indexOfEnemy;

    EnemyEnum(String enemyKind, int indexOfEnemy){
        this.enemyKind=enemyKind;
        this.indexOfEnemy=indexOfEnemy;
    }

    public String getEnemyKind(){
        return enemyKind;
    }
    public void setEnemyKind(String enemyKind){
        this.enemyKind=enemyKind;
    }
    public int getIndexOfEnemy(){
        return indexOfEnemy;
    }
    public void setIndexOfEnemy(int indexOfEnemy){
        this.indexOfEnemy=indexOfEnemy;
    }

}
