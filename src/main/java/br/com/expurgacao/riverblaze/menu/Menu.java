package br.com.expurgacao.riverblaze.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.Map;
import br.com.expurgacao.riverblaze.Game;
import br.com.expurgacao.riverblaze.enums.GameSoundEnum;
import br.com.expurgacao.riverblaze.splash.R;

public class Menu extends Activity {

    private SoundPool gameSound;
    private Map<String, Integer> gameSoundMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // ----------------------------------------------------------------------------------------------------------------------------
        gameSoundMap = new HashMap<String, Integer>();
        gameSound = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        gameSoundMap.put(GameSoundEnum.ITEM_LIFEUP_07.getName(), gameSound.load(this, R.raw.item07lifeup, 1));
        gameSoundMap.put(GameSoundEnum.ITEM_06.getName(), gameSound.load(this, R.raw.item06, 1));
        gameSoundMap.put(GameSoundEnum.EXPLOSION_HELLICO_01.getName(), gameSound.load(this, R.raw.explosionhellico01, 1));

        gameSound.play(gameSoundMap.get(GameSoundEnum.ITEM_LIFEUP_07.getName()).intValue(), 1, 1, 1, 1, 1);
        // ----------------------------------------------------------------------------------------------------------------------------

        Button sair = (Button)findViewById(R.id.btnSair);
        Button continuar = (Button)findViewById(R.id.btnContinue);
        Button iniciarGame = (Button)findViewById(R.id.btnStartGame);

        //ImageView ivTituloVerde = (ImageView)findViewById(R.id.ivTituloVerde);
        ImageView ivTituloAzul = (ImageView)findViewById(R.id.ivTituloAzul);
        ImageView ivTituloVermelho = (ImageView)findViewById(R.id.ivTituloVermelho);
        ImageView ivTituloAmarelo = (ImageView)findViewById(R.id.ivTituloAmarelo);

        sair.setOnClickListener(listenerBtnSair);

        continuar.setOnClickListener(listenerBtnContinuar);
        continuar.setEnabled(Boolean.FALSE);

        iniciarGame.setOnClickListener(listenerBtnJogar);

        //ivTituloVerde.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titulo_menu_verde));
        ivTituloAzul.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titulo_menu_azul));
        //ivTituloVermelho.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titulo_menu_vermelho));
        //ivTituloAmarelo.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.titulo_menu_amarelo));

    }

    private View.OnClickListener listenerBtnSair = new View.OnClickListener(){
        public void onClick(View v) {
            gameSound.play(gameSoundMap.get(GameSoundEnum.ITEM_06.getName()).intValue(), 1, 1, 1, 1, 1);
            finish();
        }
    };

    private View.OnClickListener listenerBtnContinuar = new View.OnClickListener() {
        public void onClick(View v) {
        // restart game from last check point
        }
    };

    private View.OnClickListener listenerBtnJogar = new View.OnClickListener() {
        public void onClick(View v) {
        gameSound.play(gameSoundMap.get(GameSoundEnum.EXPLOSION_HELLICO_01.getName()).intValue(), 1, 1, 99, 1, 1);
        Intent game = new Intent(getCurrentActivity(), Game.class);
        getCurrentActivity().startActivity(game);
        }
    };

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    private Context getCurrentActivity(){
        return this;
    }

    public void reload(){
        finish();
        startActivity(getIntent());
    }

}
