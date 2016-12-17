package br.com.expurgacao.riverblaze.load;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import java.util.HashMap;
import java.util.Map;
import br.com.expurgacao.riverblaze.enums.GameSoundEnum;
import br.com.expurgacao.riverblaze.splash.R;

/**
 * Created by NysioFontes on 24/02/2016.
 */
public class SoundLoad {

    private SoundPool gameSound;
    private Map<String, Integer> gameSoundMap;
    private Context context;

    public SoundLoad(Context context){
        this.gameSoundMap = new HashMap<String, Integer>();
        this.gameSound = new SoundPool(30, AudioManager.STREAM_MUSIC, 0);
        this.context = context;
    }

    public void loadAllAudios(){
        for(GameSoundEnum gameSoundEnum : GameSoundEnum.values()){
            gameSoundMap.put(gameSoundEnum.getName(), gameSound.load(this.context, R.raw.explosion01, 1));
        }
        /*
        gameSoundMap.put(GameSoundEnum.EXPLOSION_01.getName(), gameSound.load(this.context, R.raw.explosion01, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_02.getName(), gameSound.load(this.context, R.raw.explosion02, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_03.getName(), gameSound.load(this.context, R.raw.explosion03, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_04.getName(), gameSound.load(this.context, R.raw.explosion04, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_05.getName(), gameSound.load(this.context, R.raw.explosion05, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_06.getName(), gameSound.load(this.context, R.raw.explosion06, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_07.getName(), gameSound.load(this.context, R.raw.explosion07, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_08.getName(), gameSound.load(this.context, R.raw.explosion08, 1));

        gameSoundMap.put(GameSoundEnum.EXPLOSION_BOSS_01.getName(), gameSound.load(this.context, R.raw.explosionboss01, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_BOSS_02.getName(), gameSound.load(this.context, R.raw.explosionboss02, 1));

        gameSoundMap.put(GameSoundEnum.EXPLOSION_HELLICO_01.getName(), gameSound.load(this.context, R.raw.explosionhellico01, 1));
        gameSoundMap.put(GameSoundEnum.HELICOPTER_01.getName(), gameSound.load(this.context, R.raw.helicopter01, 1));

        gameSoundMap.put(GameSoundEnum.ITEM_01.getName(), gameSound.load(this.context, R.raw.item01, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_02.getName(), gameSound.load(this.context, R.raw.item02, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_POWERUP_03.getName(), gameSound.load(this.context, R.raw.item03_powerup, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_04.getName(), gameSound.load(this.context, R.raw.item04, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_05.getName(), gameSound.load(this.context, R.raw.item05, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_06.getName(), gameSound.load(this.context, R.raw.item06, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_LIFEUP_07.getName(), gameSound.load(this.context, R.raw.item07lifeup, 1));

        gameSoundMap.put(GameSoundEnum.LOW_LIFE_01.getName(), gameSound.load(this.context, R.raw.lowlife01, 1));
        gameSoundMap.put(GameSoundEnum.LOW_LIFE_02.getName(), gameSound.load(this.context, R.raw.lowlife02, 1));

        gameSoundMap.put(GameSoundEnum.TOTAL_POINTS_COUNTER_07.getName(), gameSound.load(this.context, R.raw.totalpointscounter01, 1));
         */
    }

    public SoundPool getGameSound() {
        return gameSound;
    }

    public Map<String, Integer> getGameSoundMap() {
        return gameSoundMap;
    }
}
