package br.com.expurgacao.riverblaze.enums;

/**
 * Created by NysioFontes on 25/02/2016.
 */
public enum DisplayResolutionEnum {

    XXXXXXXXHDPI(1, "DPI ALTISSIMO FULL HD - AINDA NÃO TEM UM NOME DEFINIDO", 3f),
    XHDPI(2, "xhdpi", 2.f),
    HDPI(3, "hdpi", 1.5f),
    TVDPI(4, "tvdpi", 1.33f),
    MDPI(5, "mdpi", 1f),
    LDPI(6, "ldpi", 0.75f);

    private int index;
    private String displayResolutionDesc;
    private float densidade;

    DisplayResolutionEnum(int index, String displayResolutionDesc, float densidade){
        this.index = index;
        this.displayResolutionDesc = displayResolutionDesc;
        this.densidade = densidade;
    }

    public int getIndex(){
        return index;
    }
    public float getDensidade(){
        return densidade;
    }
    public void setIndex(int index){
        this.index=index;
    }
    public void setDensidade(float densidade){
        this.densidade=densidade;
    }
    public String getDisplayResolutionDesc(){
        return displayResolutionDesc;
    }
    public void setDisplayResolutionDesc(String displayResolutionDesc){
        this.displayResolutionDesc=displayResolutionDesc;
    }

}
