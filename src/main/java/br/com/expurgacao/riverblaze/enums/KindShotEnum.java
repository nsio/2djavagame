package br.com.expurgacao.riverblaze.enums;

/**
 * Created by NysioFontes on 21/12/2015.
 */
public enum KindShotEnum {

    ATOMIC_BOMB("Bomba Atomica", 0, 100000000),
    SHOT("Shot", -1, -1),
    SHOT_BLUE_ONE("Blue One", 1, 10),
    SHOT_BLUE_TWO("Blue Two", 2, 20),
    SHOT_BLUE_TREE("Blue Tree", 3, 30),
    SHOT_ORANGE_ONE("Yellow oNE", 4, 10),
    SHOT_ORANGE_TWO("Yellow TWO", 5, 20),
    SHOT_ORANGE_TREE("Yellow TREE", 6, 30);

    private String name;
    private int idShot;
    private int power;

    private KindShotEnum(String name, int idShot, int power){
        this.name = name;
        this.idShot = idShot;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getIdShot(){
        return idShot;
    }

    public void setIdShot(int idShot){
        this.idShot=idShot;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
};
