package br.com.expurgacao.riverblaze.level.one;

import android.graphics.Canvas;
import br.com.expurgacao.riverblaze.enums.BitmapsNamesEnum;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.load.SoundLoad;
import br.com.expurgacao.riverblaze.load.SpritsLoad;

/**
 * Created by NysioFontes on 01/03/2016.
 */
public class LevelMappingManager {

    // RESOURCES
    private SoundLoad soundLoad;
    private SpritsLoad spritsLoad;
    private ConfigLoad configLoad;
    // QUADRANTES
    private PrimeiroQuadrante primeiroQuadrante;
    private SegundoQuadrante segundoQuadrante;
    private TerceiroQuadrante terceiroQuadrante;
    private QuartoQuadrante quartoQuadrante;
    private QuintoQuadrante quintoQuadrante;
    private SextoQuadrante sextoQuadrante;
    private SetimoQuadrante setimoQuadrante;
    private OitavoQuadrante oitavoQuadrante;
    //
    private long startTime;
    private boolean timeFinalPrimeiroQuadranteCapturado = false;


    public LevelMappingManager(SoundLoad soundLoad, SpritsLoad spritsLoad, ConfigLoad configLoad, int widthScreen, int heightScreen, long gameStatrTime){

        this.startTime = gameStatrTime;

        this.spritsLoad = spritsLoad;
        this.soundLoad = soundLoad;
        this.configLoad = configLoad;

        this.primeiroQuadrante = new PrimeiroQuadrante(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.AVIAO), widthScreen, heightScreen, this.startTime);
        this.segundoQuadrante = new SegundoQuadrante(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.AVIAO), spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ATIRADOR),
                                    widthScreen, heightScreen, configLoad.resolution());

    }

    public void updateAllObjectsOf(){
        if(!this.primeiroQuadrante.quadranteFinalizado()){
            this.primeiroQuadrante.update();
            return;
        }
        if(!this.segundoQuadrante.quadranteFinalizado()){
            if(!this.timeFinalPrimeiroQuadranteCapturado) {
                this.segundoQuadrante.setTimeFinalOfPrimeiroQuadrante(System.currentTimeMillis());
                this.timeFinalPrimeiroQuadranteCapturado = true;
            }
            this.segundoQuadrante.update();
            return;
        }
    }

    public void drawObjectsOf(Canvas canvas){
        if(!this.primeiroQuadrante.quadranteFinalizado()) {
            this.primeiroQuadrante.draw(canvas);
            return;
        }
        if(!this.segundoQuadrante.quadranteFinalizado()) {
            this.segundoQuadrante.draw(canvas);
            return;
        }
    }

}
