package br.com.expurgacao.riverblaze.splash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;
import br.com.expurgacao.riverblaze.database.DataBaseAccess;
import br.com.expurgacao.riverblaze.database.DataBaseHelper;
import br.com.expurgacao.riverblaze.menu.Menu;
import br.com.expurgacao.riverblaze.splash.abertura.IlustraAbertura;

public class SplashVideo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        DataBaseAccess.getInstance().setDb(new DataBaseHelper(this));

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFormat(PixelFormat.UNKNOWN);

        setContentView(R.layout.activity_splash_video);

        // PULANDO A ABERTURA E INDO DIRETO AO MENU
        Intent IlusttraAberturaIntent = new Intent(this, Menu.class);
        startActivity(IlusttraAberturaIntent);
        finish();
        // -----------------------------------------------------------------------------

        VideoView videoView = (VideoView)findViewById(R.id.videoViewIntro);

        String urlVideoIntro = "android.resource://"+getPackageName()+"/"+R.raw.expurgavideoabetura;
        videoView.setVideoURI(Uri.parse(urlVideoIntro));

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                callNextActivity();
                finish();
            }
        });
        videoView.start();
    }

    public void callNextActivity(){
        //Intent menuGame = new Intent(this, Menu.class);
        //startActivity(menuGame);
        Intent IlusttraAberturaIntent = new Intent(this, IlustraAbertura.class);
        startActivity(IlusttraAberturaIntent);
    }

}
