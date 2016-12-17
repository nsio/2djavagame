package br.com.expurgacao.riverblaze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import br.com.expurgacao.riverblaze.database.ConfigDataBase;
import br.com.expurgacao.riverblaze.database.DataBaseHelper;
import br.com.expurgacao.riverblaze.draw.Draw;
import br.com.expurgacao.riverblaze.enums.BitmapsNamesEnum;
import br.com.expurgacao.riverblaze.enums.GameSoundEnum;
import br.com.expurgacao.riverblaze.enums.KindShotEnum;
import br.com.expurgacao.riverblaze.level.one.LevelMappingManager;
import br.com.expurgacao.riverblaze.load.ConfigLoad;
import br.com.expurgacao.riverblaze.load.SoundLoad;
import br.com.expurgacao.riverblaze.load.SpritsLoad;
import br.com.expurgacao.riverblaze.objetos.Background;
import br.com.expurgacao.riverblaze.objetos.Bonus;
import br.com.expurgacao.riverblaze.objetos.Explosion;
import br.com.expurgacao.riverblaze.objetos.PlayPause;
import br.com.expurgacao.riverblaze.objetos.Player;
import br.com.expurgacao.riverblaze.objetos.shot.VimanaShot;
import br.com.expurgacao.riverblaze.splash.R;

/**
 * Created by Nysio on 02/12/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public final static int CHECK_POINT_DIVIDE = 9;
    public final static int WIDTH = (5000/CHECK_POINT_DIVIDE);
    public final static int WIDTH_TOTAL = 5000;
    public static int WIDTH_SCREEN;
    public static int HEIGHT;

    public final static int MOVESPEED = -1;

    private long shotSoundStartTime=0L;

    //----------------------------------------------------------------------------------------------
    private DataBaseHelper dh;
    private ConfigDataBase configDataBase;
    //----------------------------------------------------------------------------------------------

    private MainThread mainThread;
    private Background bg;
    private Player player;
    private PlayPause playPause;

    private Random rand = new Random();

    private boolean newGameCreated=true;

    private Explosion explosion;
    private long startReset;
    private boolean reset=true;
    private boolean dissapear;
    private boolean started=false;

    private int best = 0;

    private int progressDenom;

    private Window window;
    private ArrayList<Bonus> bonuses;
    private long timeOfLastBombaBonus = System.nanoTime();
    private long timeOfLastShotBonus = System.nanoTime();

    private Map<KindShotEnum, Bitmap> shotsByKind;

    private long score = 0;
    private int totalVimanaLifes = 0;
    private Context context;

    private Map<Integer, Integer> checkPointsArea = new HashMap<Integer, Integer>();

    private SoundLoad soundLoad;
    private SpritsLoad spritsLoad;
    private ConfigLoad configLoad;
    private Draw draw;
    private LevelMappingManager levelMappingManager;

    public GamePanel(Context context, Window window, int height, int widthScreen, SoundLoad soundLoad, SpritsLoad spritsLoad, ConfigLoad configLoad){
        super(context);
        getHolder().addCallback(this);
        this.window = window;
        this.context = context;
        this.HEIGHT = height;
        this.WIDTH_SCREEN = widthScreen;
        this.soundLoad = soundLoad;
        this.spritsLoad = spritsLoad;
        this.configLoad = configLoad;
        this.draw = new Draw(this.context, this.configLoad);
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        // -------------------------------------------------------------------------------------------------
        //dh = new DataBaseHelper(context);
        //try {
        //    configDataBase = new ConfigDataBase(dh);
        //    configDataBase.configurarDataBase();
        //}catch(SQLException e){
        //    e.printStackTrace();
        //}
        // -------------------------------------------------------------------------------------------------
        /*
        * LOAD AUDIOS AND BITMAPS
        * */
        // -------------------------------------------------------------------------------------------------
        bg = new Background(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BACKGROUND));
        // -------------------------------------------------------------------------------------------------
        shotsByKind = new HashMap<KindShotEnum, Bitmap>();
        shotsByKind.put(KindShotEnum.SHOT_ORANGE_ONE, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_LARANJA));
        shotsByKind.put(KindShotEnum.SHOT_ORANGE_TWO, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_LARANJA));
        shotsByKind.put(KindShotEnum.SHOT_ORANGE_TREE, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_LARANJA));
        shotsByKind.put(KindShotEnum.SHOT_BLUE_ONE, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_AZUL));
        shotsByKind.put(KindShotEnum.SHOT_BLUE_TWO, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_AZUL));
        shotsByKind.put(KindShotEnum.SHOT_BLUE_TREE, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.ESFERA_AZUL));

        player = new Player(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.VIMANA), 65, 28, 3, shotsByKind);

        player.setKindShot(KindShotEnum.SHOT_BLUE_ONE);
        // -------------------------------------------------------------------------------------------------
        playPause = new PlayPause(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BTN_PAUSE), WIDTH_SCREEN - 40, 10);
        // -------------------------------------------------------------------------------------------------
        bonuses = new ArrayList<Bonus>();
        // -------------------------------------------------------------------------------------------------
        totalVimanaLifes = 5;

        mainThread = new MainThread(getHolder(), this);
        mainThread.setRunning(true);
        mainThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000){
            counter++;
            try{
                mainThread.setRunning(false);
                mainThread.join();
                retry = false;
                mainThread = null;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
//        dh.close();
    }

    public void update(){
        if(player.getPlaying()) {
            // GERENCIADOR DO LEVEL (1)
            if(this.levelMappingManager == null){
                this.levelMappingManager = new LevelMappingManager(this.soundLoad, this.spritsLoad, this.configLoad, this.WIDTH_SCREEN, this.HEIGHT, System.currentTimeMillis());
            }
            if((System.nanoTime()- timeOfLastBombaBonus) / 1000000 > 90000){
                bonuses.add(new Bonus(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BONUS_BOMBA), (WIDTH-5), rand.nextInt(HEIGHT), 30, 30, 5, KindShotEnum.ATOMIC_BOMB));
                timeOfLastBombaBonus = System.nanoTime();
            }
            if((System.nanoTime() - timeOfLastShotBonus) / 1000000 > 30000){
                bonuses.add(new Bonus(spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BONUS_SHOT), (WIDTH - 5), rand.nextInt(HEIGHT), 20, 20, 5, KindShotEnum.SHOT));
                timeOfLastShotBonus = System.nanoTime();
            }
            //--------------------------------------------------------------------------------------
            bg.update();
            //--------------------------------------------------------------------------------------
            if(!dissapear) {
                player.update();
                if((System.nanoTime()-shotSoundStartTime)/1000000 > 550) {
                    soundLoad.getGameSound().play(soundLoad.getGameSoundMap().get(GameSoundEnum.ITEM_02.getName()).intValue(), 1, 1, 99, 1, 1);
                    shotSoundStartTime=System.nanoTime();
                }
                for(int i = 0; i < player.getShots().size(); i++){
                    player.getShots().get(i).update();
                }
                for(int i = 0; i < bonuses.size(); i++){
                    if(collision(bonuses.get(i), player)){
                        if(bonuses.get(i).getKindShotEnum().equals(KindShotEnum.ATOMIC_BOMB)){
                            this.player.addBomba();
                            soundLoad.getGameSound().play(soundLoad.getGameSoundMap().get(GameSoundEnum.ITEM_05.getName()).intValue(), 1, 1, 99, 1, 1);
                        }else{
                            if(this.player.getKindShot().equals(KindShotEnum.SHOT_BLUE_ONE)){
                                this.player.setKindShot(KindShotEnum.SHOT_BLUE_TWO);
                            }else if(this.player.getKindShot().equals(KindShotEnum.SHOT_BLUE_TWO)){
                                this.player.setKindShot(KindShotEnum.SHOT_BLUE_TREE);
                            }else if(this.player.getKindShot().equals(KindShotEnum.SHOT_BLUE_TWO)){
                                this.player.setKindShot(KindShotEnum.SHOT_BLUE_TREE);
                            }
                            soundLoad.getGameSound().play(soundLoad.getGameSoundMap().get(GameSoundEnum.ITEM_05.getName()).intValue(), 1, 1, 99, 1, 1);
                        }
                        bonuses.remove(i);
                        break;
                    }
                }
            }
            // -------------------------------------------------------------------------------------------------------------------------
            // ATUALIZAR OU RETIRAR BONUS DA TELA
            for(int i = 0; i< bonuses.size(); i++){
                bonuses.get(i).update();
                if(bonuses.get(i).getX() < -15 || bonuses.get(i).getY() > GamePanel.HEIGHT + 15 || bonuses.get(i).getY() < -15){
                    bonuses.remove(i);
                }
            }
            // -------------------------------------------------------------------------------------------------------------------------
            this.levelMappingManager.updateAllObjectsOf();
        }else{
            player.resetDY();
            if(!reset){
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                dissapear = true;
                explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion), player.getX(), player.getY()-30,100,100,25);
                soundLoad.getGameSound().play(soundLoad.getGameSoundMap().get(GameSoundEnum.EXPLOSION_08.getName()).intValue(), 1, 1, 99, 1, 1);
            }
            if(explosion!=null){
                explosion.update();
            }
            long resetElapsed = (System.nanoTime()-startReset)/1000000;
            if(resetElapsed > 1300 && !newGameCreated){
                started = false;
                newGame();
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(canvas != null){
            final int savedState = canvas.save();
            bg.draw(canvas);
            playPause.draw(canvas);
            if(!dissapear) {
                player.draw(canvas);
            }
            for(VimanaShot shot : player.getShots()){
                shot.draw(canvas);
                //drawRect(canvas, shot.getRect());
            }
            for(Bonus bonus : bonuses){
                bonus.draw(canvas);
            }
            this.levelMappingManager.drawObjectsOf(canvas);
            if(!player.getPlaying() && started){
                explosion.draw(canvas);
            }
            draw.drawTextAndBitmap(canvas, " " + totalVimanaLifes, spritsLoad.getBitmapMap().get(BitmapsNamesEnum.VIMANA_LIFE), (WIDTH_SCREEN - 89), 15, (WIDTH_SCREEN - 60), 37, Color.RED, 3);
            draw.drawTextAndBitmap(canvas, " " + player.getBombas(), spritsLoad.getBitmapMap().get(BitmapsNamesEnum.BONUS_BOMBA), (WIDTH_SCREEN - 145), 15, (WIDTH_SCREEN - 109), 37, Color.RED, 3);
            draw.drawText(canvas, "BG x: ", WIDTH_SCREEN - WIDTH_SCREEN, 25, 2);
            draw.drawText(canvas, "BG x: " + bg.getX(), 15, 25, 2);
            draw.drawLine(canvas);
            draw.drawText(canvas, newGameCreated, reset, getPlayer(), 1);
            canvas.restoreToCount(savedState);
        }
    }

    public boolean collision(GameObject a, GameObject b){
        if(Rect.intersects(a.getRect(), b.getRect())){
            return true;
        }
        return false;
    }

    public void newGame(){
        dissapear = false;
        player.resetDY();
        player.resetScore();
        player.setY(HEIGHT / 2);
        player.setX(10);
        if(player.getScore()>best){
            best = player.getScore();
        }
        player.reset();
        newGameCreated = true;
        if(totalVimanaLifes == 0) {
            if(bonuses != null){
                bonuses.clear();
            }
            totalVimanaLifes = 5;
            score = 0;
        }
    }

    // ---------------------------------------------------------------------------------------------
    public boolean isNewGameCreated(){
        return newGameCreated;
    }
    public boolean isReset(){
        return reset;
    }
    public void setReset(boolean reset){
        this.reset=reset;
    }
    public boolean isStarted(){
        return started;
    }
    public void setStarted(boolean started){
        this.started=started;
    }
    public Player getPlayer(){
        return player;
    }
    public PlayPause getPlayPause(){
        return playPause;
    }
    public void play(){
        this.mainThread.setPause(false);
    }
    public void pause(){
        this.mainThread.setPause(true);
    }
    // ---------------------------------------------------------------------------------------------
}
