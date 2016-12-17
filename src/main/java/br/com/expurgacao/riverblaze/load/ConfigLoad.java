package br.com.expurgacao.riverblaze.load;

import android.util.DisplayMetrics;
import br.com.expurgacao.riverblaze.enums.DisplayResolutionEnum;

/**
 * Created by NysioFontes on 25/02/2016.
 */
public class ConfigLoad {

    private DisplayMetrics displayMetrics;

    private int bigTextXXXXXXXXHDPI = 70;
    private int bigTextXHDPI = 60;
    private int bigTextTVDPI = 50;
    private int bigTextMDPI = 40;
    private int bigTextLDPI = 30;

    public ConfigLoad(DisplayMetrics displayMetrics){
        this.displayMetrics = displayMetrics;
    }

    public int textSizeBig(){
        if(resolution().equals(DisplayResolutionEnum.XXXXXXXXHDPI)){
            return this.bigTextXXXXXXXXHDPI;
        }
        if(resolution().equals(DisplayResolutionEnum.XHDPI)){
            return this.bigTextXHDPI;
        }
        if(resolution().equals(DisplayResolutionEnum.TVDPI)){
            return this.bigTextTVDPI;
        }
        if(resolution().equals(DisplayResolutionEnum.MDPI)){
            return this.bigTextMDPI;
        }
        if(resolution().equals(DisplayResolutionEnum.LDPI)){
            return this.bigTextLDPI;
        }
        /*
        System.out.println("*********************************************************************");
        System.out.println("Resolution find: " + resolution().getDisplayResolutionDesc());
        System.out.println("Densidade: " + this.displayMetrics.density);
        System.out.println("Densidade DPI: " + this.displayMetrics.densityDpi);
        System.out.println("Scale Densidade: " + this.displayMetrics.scaledDensity);
        System.out.println(this.displayMetrics.toString());
        System.out.println("*********************************************************************");
        */
        return 0;
    }

    public int textSizeMedium(){
        if(resolution().equals(DisplayResolutionEnum.XXXXXXXXHDPI)){
            return this.bigTextXXXXXXXXHDPI/2;
        }
        if(resolution().equals(DisplayResolutionEnum.XHDPI)){
            return this.bigTextXHDPI/2;
        }
        if(resolution().equals(DisplayResolutionEnum.TVDPI)){
            return this.bigTextTVDPI/2;
        }
        if(resolution().equals(DisplayResolutionEnum.MDPI)){
            return this.bigTextMDPI/2;
        }
        if(resolution().equals(DisplayResolutionEnum.LDPI)){
            return this.bigTextLDPI/2;
        }
        return 0;
    }

    public int textSizeSmall(){
        if(resolution().equals(DisplayResolutionEnum.XXXXXXXXHDPI)){
            return Math.abs(this.bigTextXXXXXXXXHDPI/3);
        }
        if(resolution().equals(DisplayResolutionEnum.XHDPI)){
            return Math.abs(this.bigTextXHDPI/3);
        }
        if(resolution().equals(DisplayResolutionEnum.TVDPI)){
            return Math.abs(this.bigTextTVDPI/3);
        }
        if(resolution().equals(DisplayResolutionEnum.MDPI)){
            return Math.abs(this.bigTextMDPI/3);
        }
        if(resolution().equals(DisplayResolutionEnum.LDPI)){
            return Math.abs(this.bigTextLDPI/3);
        }
        return 0;
    }

    //*******************************************************************************************************************************
    public DisplayResolutionEnum resolution(){
        if(this.displayMetrics.scaledDensity > 2f){
            return DisplayResolutionEnum.XXXXXXXXHDPI;
        }
        if(this.displayMetrics.scaledDensity >= 1.5f && this.displayMetrics.scaledDensity <= 2f){
            return DisplayResolutionEnum.XHDPI;
        }
        if(this.displayMetrics.scaledDensity >= 1.33f && this.displayMetrics.scaledDensity <= 1.5f){
            return DisplayResolutionEnum.TVDPI;
        }
        if(this.displayMetrics.scaledDensity >= 1f && this.displayMetrics.scaledDensity <= 1.33f){
            return DisplayResolutionEnum.MDPI;
        }
        if(this.displayMetrics.scaledDensity >= 0.75f && this.displayMetrics.scaledDensity <= 1f){
            return DisplayResolutionEnum.LDPI;
        }
        return null;
    }
    //*******************************************************************************************************************************

}
