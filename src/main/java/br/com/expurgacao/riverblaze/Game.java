package br.com.expurgacao.riverblaze;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.GestureDetector.OnGestureListener;

import br.com.expurgacao.riverblaze.draw.Draw;
import br.com.expurgacao.riverblaze.enums.BitmapsNamesEnum;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.load.SoundLoad;
import br.com.expurgacao.riverblaze.load.SpritsLoad;
import br.com.expurgacao.riverblaze.move.Control;
import br.com.expurgacao.riverblaze.objetos.shot.VimanaShot;

public class Game extends Activity implements OnGestureListener{

    private GamePanel gamePanel;
    private GestureDetector gestureDetector;
    private SpritsLoad spritsLoad;
    private SoundLoad soundLoad;
    private ConfigLoad configLoad;

    private float lastY = 0f;
    private float lastX = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        gestureDetector = new GestureDetector(this, this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        spritsLoad = new SpritsLoad(this, metrics.heightPixels, metrics.widthPixels);
        // CARREGAR OS BITMAPS
        spritsLoad.loadBitmaps();

        soundLoad = new SoundLoad(this);
        // CARREGAR O AUDIO DO GAME
        soundLoad.loadAllAudios();

        configLoad = new ConfigLoad(metrics);

        gamePanel = new GamePanel(this, getWindow(), metrics.heightPixels, metrics.widthPixels, soundLoad, spritsLoad, configLoad);

        setContentView(gamePanel);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        gamePanel.getPlayer().isUpdate(true);
        if(gamePanel != null && gamePanel.getPlayer()!=null) {
            if(!gamePanel.getPlayer().getPlaying() && gamePanel.isNewGameCreated() && gamePanel.isReset()){
                gamePanel.getPlayer().setPlaying(true);
            }
            if(gamePanel.getPlayer().getPlaying()){
                if(!gamePanel.isStarted()){
                    gamePanel.setStarted(true);
                }
                gamePanel.setReset(false);
            }
            // -------------------------------------------------------------------------------------
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                if(!((event.getX() >= ((gamePanel.WIDTH_SCREEN/5) * 4) && event.getY() >= ((gamePanel.HEIGHT/5) * 4) &&
                    (event.getX() >= ((gamePanel.WIDTH_SCREEN/5) * 4) && event.getY() >= gamePanel.HEIGHT))) ) {

                    gamePanel.getPlayer().setTouchY(event.getY());

                    double rad = Math.atan2(lastY - event.getY(), lastX - event.getX()) + Math.PI;
                    Control.getInstance().defineDirection(gamePanel, (int) Math.abs((rad * 180 / Math.PI + 180) % 360));

                    lastY = event.getY();
                    lastX = event.getX();

                    return true;
                }
            }

            /* O CÓDIGO ABAIXO É O INICIO DE UMA TENTATIVA DE IMPLMENTAR UM PEQUENO IMPULSO NA VIMANA
             *  SERIA UTIL QUANDO O PLAYER ESTIVER FUGINDO DE UM SHOT, OU INDO EM DIRECAO A UM BONUS IMPORTANTE PARA ELE EM DADO MOMENTO DO GAMEPLAY
             *  - INICIALMENTE FUNCIONOU, FALTA REFINAMENTO
             *
            if((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN){
                //Toco na tela com two fingers
                gamePanel.getPlayer().setBreveImpulso(true);
            }

            if(event.getAction() == MotionEvent.ACTION_POINTER_2_UP){
                //Soltei o segundo dedo, nesse caso a velocidade da vimana volta ao normal.
                gamePanel.getPlayer().setBreveImpulso(false);
            }
            */

        }
        gamePanel.getPlayer().isUpdate(false);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent){
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent){

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        gamePanel.getPlayer().isUpdate(true);
        if(gamePanel != null && gamePanel.getPlayer()!=null) {
            if (!gamePanel.getPlayer().getPlaying() && gamePanel.isNewGameCreated() && gamePanel.isReset()) {
                gamePanel.getPlayer().setPlaying(true);
            }
            if (gamePanel.getPlayer().getPlaying()) {
                if (!gamePanel.isStarted()) {
                    gamePanel.setStarted(true);
                }
                gamePanel.setReset(false);
            }
            if (((motionEvent.getX() >= ((gamePanel.WIDTH_SCREEN / 5) * 4) && motionEvent.getY() >= ((gamePanel.HEIGHT / 5) * 4) &&
                    (motionEvent.getX() >= ((gamePanel.WIDTH_SCREEN / 5) * 4) && motionEvent.getY() >= gamePanel.HEIGHT)))) {
                if (gamePanel.getPlayer().getBombas() > 0) {
                    gamePanel.getPlayer().addShot(new VimanaShot(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BOMBA), gamePanel.getPlayer().getX(), gamePanel.getPlayer().getY(), 50, 50, 13, KindShotEnum.ATOMIC_BOMB, false, false));
                    //gamePanel.getPlayer().addShot(new VimanaShot(BitmapFactory.decodeResource(getResources(), R.drawable.bomba), gamePanel.getPlayer().getX(), gamePanel.getPlayer().getY(), 50, 50, 13, KindShotEnum.ATOMIC_BOMB, false, false));
                    gamePanel.getPlayer().removerBomba();
                }
                return true;
            }
            // VERIFICAR SE O EVENTO PARA ONTAP OCORREU NA REGIÃO DA IMAGEM DO PLAY PAUSE;
            if (motionEvent.getX() >= gamePanel.WIDTH_SCREEN - 30 && motionEvent.getY() <= 40) {
                if (gamePanel.getPlayPause().isPause()) {
                    gamePanel.getPlayPause().setSpritesheet(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BTN_PAUSE));
                    //gamePanel.getPlayPause().setSpritesheet(BitmapFactory.decodeResource(getResources(), R.drawable.btn_pause));
                    gamePanel.getPlayPause().setPause(false);
                    gamePanel.update();
                    gamePanel.play();
                    return true;
                }
                gamePanel.getPlayPause().setSpritesheet(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BTN_PLAY));
                //gamePanel.getPlayPause().setSpritesheet(BitmapFactory.decodeResource(getResources(), R.drawable.btn_play));
                gamePanel.getPlayPause().setPause(true);
                gamePanel.update();
                gamePanel.pause();
                return true;
            }
        }

        gamePanel.getPlayer().isUpdate(false);
        return super.onTouchEvent(motionEvent);

    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {

        //System.out.println("--------------------------------------------------------------");
        //System.out.println("Scrolling ----- !!!");
        //System.out.println("--------------------------------------------------------------");

        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v2) {
        Log.d(null, "onFling: " + e1.toString() + e2.toString());

        return true;
    }

}
