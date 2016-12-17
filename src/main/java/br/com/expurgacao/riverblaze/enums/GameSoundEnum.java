package br.com.expurgacao.riverblaze.enums;

/**
 * Created by NysioFontes on 05/01/2016.
 */
public enum GameSoundEnum {

    EXPLOSION_01("explosion01"),
    EXPLOSION_02("explosion02"),
    EXPLOSION_03("explosion03"),
    EXPLOSION_04("explosion04"),
    EXPLOSION_05("explosion05"),
    EXPLOSION_06("explosion06"),
    EXPLOSION_07("explosion07"),
    EXPLOSION_08("explosion08"),
    EXPLOSION_BOSS_01("explosion_boss01"),
    EXPLOSION_BOSS_02("explosion_boss02"),
    EXPLOSION_HELLICO_01("explosion_hellico02"),
    HELICOPTER_01("helicopter01"),

    ITEM_01("item01"),
    ITEM_02("item02"),
    ITEM_POWERUP_03("item03powerup"),
    ITEM_04("item04"),
    ITEM_05("item05"),
    ITEM_06("item06"),
    ITEM_LIFEUP_07("item07lifeup"),

    LOW_LIFE_01("lowlife01"),
    LOW_LIFE_02("lowlife02"),
    TOTAL_POINTS_COUNTER_07("totalpointscounter01");

    private String name;

    GameSoundEnum(String name){
        this.name=name;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

}
