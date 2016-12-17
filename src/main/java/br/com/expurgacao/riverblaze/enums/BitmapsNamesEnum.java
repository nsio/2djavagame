package br.com.expurgacao.riverblaze.enums;

/**
 * Created by NysioFontes on 24/02/2016.
 */
public enum BitmapsNamesEnum {

    BACKGROUND("background", 1),
    VIMANA("vimana", 2),
    BTN_PAUSE("btn_pause", 3),
    BTN_PLAY("btn_play", 4),
    BOMBA("bomba", 5),
    ESFERA_AZUL("esfera azul", 6),
    ESFERA_LARANJA("esfera laranja", 7),
    BONUS_BOMBA("bonus bomba", 8),
    BONUS_SHOT("bonus shot", 9),
    VIMANA_LIFE("vimana life", 10),
    AVIAO("aviao", 11),
    ATIRADOR("atirador", 12);

    private String bitMapName;
    private int indexOfBitmap;

    BitmapsNamesEnum(String bitMapName, int indexOfBitmap){
        this.bitMapName = bitMapName;
        this.indexOfBitmap = indexOfBitmap;
    }

    public String getBitMapName(){
        return bitMapName;
    }
    public int getIndexOfBitmap(){
        return indexOfBitmap;
    }
    public void setBitMapName(String bitMapName){
        this.bitMapName=bitMapName;
    }
    public void setIndexOfBitmap(int indexOfBitmap){
        this.indexOfBitmap=indexOfBitmap;
    }

}
