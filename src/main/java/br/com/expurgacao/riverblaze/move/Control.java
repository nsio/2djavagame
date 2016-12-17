package br.com.expurgacao.riverblaze.move;


import br.com.expurgacao.riverblaze.GamePanel;

/**
 * Created by Nysio on 20/12/2015.
 */
public class Control {

    private static final Control instance = new Control();

    private Control(){
        if(instance != null){
            throw new IllegalStateException("Control instanciado, use o método getInstance");
        }
    }

    public static Control getInstance() {
        return instance;
    }

    public void defineDirection(GamePanel gamePanel, int angulo){
        if(angulo == 90){
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setToMoveAxisX(false);
            gamePanel.getPlayer().setUp(false);
            //System.out.println("Top");
            return;
        }
        if(angulo == 270){
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setToMoveAxisX(false);
            gamePanel.getPlayer().setUp(true);
            //System.out.println("dOWN");
            return;
        }
        if(angulo == 0){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(false);
            gamePanel.getPlayer().setRigth(false);
            //System.out.println("Left");
            return;
        }
        if(angulo == 180){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(false);
            gamePanel.getPlayer().setRigth(true);
            //System.out.println("rIGHT");
            return;
        }
        if(angulo > 0 && angulo <= 90){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setUp(false);
            gamePanel.getPlayer().setRigth(false);
            //System.out.println("TOP, Left");
            return;
        }
        if(angulo > 90 && angulo <= 180){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setUp(false);
            gamePanel.getPlayer().setRigth(true);
            //System.out.println("TOP, rIGHT");
            return;
        }
        if(angulo > 180 && angulo <= 270){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setUp(true);
            gamePanel.getPlayer().setRigth(true);
            //System.out.println("DOWN, Left");
            return;
        }
        if(angulo > 270 && angulo <= 360){
            gamePanel.getPlayer().setToMoveAxisX(true);
            gamePanel.getPlayer().setToMoveAxisY(true);
            gamePanel.getPlayer().setUp(true);
            gamePanel.getPlayer().setRigth(false);
            //System.out.println("DOWN, rIGHT");
            return;
        }
    }

}
