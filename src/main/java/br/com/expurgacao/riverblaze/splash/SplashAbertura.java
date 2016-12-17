package br.com.expurgacao.riverblaze.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import br.com.expurgacao.riverblaze.GamePanel;

/**
 * Created by Nysio on 15/12/2015.
 */
public class SplashAbertura extends Activity {

    private String[] letters = {"G","a","m","e","s"};
    private SplashThread splashThread;
    private int lastLetter = 0;
    private int x;
    private int y;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        // CHAMA O VIEDE ABERTURA, A THREAD DA SEGUNDA ABERTURA AGARDA O TEMPO DO VIDEO E INCIA A ENTRADA DO EXPURGA GAMES
        Intent activityVideoIntro = new Intent(this, SplashVideo.class);
        startActivity(activityVideoIntro);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SplashSurface splashSurface = new SplashSurface(this, GamePanel.WIDTH, GamePanel.HEIGHT);
        setContentView(splashSurface);

    }

}
