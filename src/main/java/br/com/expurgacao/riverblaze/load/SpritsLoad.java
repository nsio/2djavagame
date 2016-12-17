package br.com.expurgacao.riverblaze.load;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.HashMap;
import java.util.Map;
import br.com.expurgacao.riverblaze.enums.BitmapsNamesEnum;
import br.com.expurgacao.riverblaze.splash.R;

/**
 * Created by NysioFontes on 24/02/2016.
 */
public class SpritsLoad {

    private final int LARGURA_TOTAL_LEVEL_1 = 5000;

    private Context context;
    private Map<BitmapsNamesEnum, Bitmap> bitmapMap;
    int height;
    int widthScreen;

    public SpritsLoad(Context context, int height, int widthScreen){
        this.context = context;
        this.height = height;
        this.widthScreen = widthScreen;
    }

    public void loadBitmaps(){
        if(bitmapMap == null){
            bitmapMap = new HashMap<BitmapsNamesEnum, Bitmap>();
        }

        Bitmap temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.grassbg1_no_loop_final);
        bitmapMap.put(BitmapsNamesEnum.BACKGROUND, Bitmap.createScaledBitmap(temp, LARGURA_TOTAL_LEVEL_1, height + 5, true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.vimana);
        bitmapMap.put(BitmapsNamesEnum.VIMANA, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_pause);
        bitmapMap.put(BitmapsNamesEnum.BTN_PAUSE, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn_play);
        bitmapMap.put(BitmapsNamesEnum.BTN_PLAY, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomba);
        bitmapMap.put(BitmapsNamesEnum.BOMBA, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.esferaazul);
        bitmapMap.put(BitmapsNamesEnum.ESFERA_AZUL, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.esferalaranja);
        bitmapMap.put(BitmapsNamesEnum.ESFERA_LARANJA, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bonus_bomba);
        bitmapMap.put(BitmapsNamesEnum.BONUS_BOMBA, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.bonus_shot);
        bitmapMap.put(BitmapsNamesEnum.BONUS_SHOT, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.vimana_icone_life);
        bitmapMap.put(BitmapsNamesEnum.VIMANA_LIFE, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.aviao);
        bitmapMap.put(BitmapsNamesEnum.AVIAO, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

        temp = BitmapFactory.decodeResource(context.getResources(), R.drawable.atirador);
        bitmapMap.put(BitmapsNamesEnum.ATIRADOR, Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), true));

    }

    public Map<BitmapsNamesEnum, Bitmap> getBitmapMap(){
        return bitmapMap;
    }

}
