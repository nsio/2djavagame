package br.com.expurgacao.riverblaze.level.one;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;
import br.com.expurgacao.riverblaze.GamePanel;
import br.com.expurgacao.riverblaze.objetos.aviao.Aviao;
import br.com.expurgacao.riverblaze.objetos.aviao.AviaoPQFormacaoInferior;
import br.com.expurgacao.riverblaze.objetos.aviao.AviaoPQFormacaoSuperior;

/**
 * Created by Nysio on 21/02/2016.
 */
public class PrimeiroQuadrante extends Quadrante {

    /*
    *  QUADRANTE 1 (AVIÕES EM FORMAÇÃO)
    *  3 aviões seguidos em formação cortam em parábola a tela da parte superior direta para a parte inferior direita.
    *  3 aviões seguidos em formação cortam em parábola a tela da parte inferior direita para a parte superior direita.
    * */
    private List<Aviao> formacaoInicioSuperior;
    private List<Aviao> formacaoInicioInferior;
    private Aviao aviaoFormSup;
    private Aviao aviaoFormInf;

    private Bitmap aviao;

    private boolean canStart = false;

    public PrimeiroQuadrante(Bitmap aviao, int widthScreen, int heightScreen, long statrTime){

        this.startTime = statrTime;

        this.aviao = aviao;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;

        // LOADING OBJECTS OF
        // *******************************************************************************************************************************************
        this.formacaoInicioSuperior = new ArrayList<Aviao>();

        this.aviaoFormSup = new AviaoPQFormacaoSuperior(this.aviao, GamePanel.WIDTH_SCREEN, 10, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioSuperior.add(this.aviaoFormSup);

        this.aviaoFormSup = new AviaoPQFormacaoSuperior(this.aviao, GamePanel.WIDTH_SCREEN + this.aviao.getWidth() + 50, 10, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioSuperior.add(this.aviaoFormSup);

        this.aviaoFormSup = new AviaoPQFormacaoSuperior(this.aviao, GamePanel.WIDTH_SCREEN + (this.aviao.getWidth() * 2 ) + 100, 10, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioSuperior.add(this.aviaoFormSup);
        // ******************************************************************************************************************************************

        this.formacaoInicioInferior = new ArrayList<Aviao>();

        this.aviaoFormInf = new AviaoPQFormacaoInferior(this.aviao, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioInferior.add(this.aviaoFormInf);

        this.aviaoFormInf = new AviaoPQFormacaoInferior(this.aviao, GamePanel.WIDTH_SCREEN + this.aviao.getWidth() + 50, GamePanel.HEIGHT, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioInferior.add(this.aviaoFormInf);

        this.aviaoFormInf = new AviaoPQFormacaoInferior(this.aviao, GamePanel.WIDTH_SCREEN + (this.aviao.getWidth() * 2 ) + 100, GamePanel.HEIGHT, GamePanel.WIDTH_SCREEN, GamePanel.HEIGHT);
        this.formacaoInicioInferior.add(this.aviaoFormInf);

    }

    public List<Aviao> getFormacaoInicioSuperior(){
        return formacaoInicioSuperior;
    }

    public List<Aviao> getFormacaoInicioInferior(){
        return formacaoInicioInferior;
    }

    @Override
    public void update(){
        if((System.currentTimeMillis() - this.startTime) > 4000) {
            this.canStart = true;
        }
        if(this.canStart){
            if(!quadranteFinalizado()) {
                /*
                // REMOVER OS OBJETO QUE SAIRAM A MAIS DE 10pxs DA TELA
                for (int i = 0; i < this.formacaoInicioSuperior.size(); i++) {
                    if (this.formacaoInicioSuperior.get(i).getY() > (GamePanel.HEIGHT + 10)) {
                        this.formacaoInicioSuperior.remove(i);
                    }
                }
                for (int i = 0; i < this.formacaoInicioInferior.size(); i++) {
                    if (this.formacaoInicioInferior.get(i).getY() < -10) {
                        this.formacaoInicioInferior.remove(i);
                    }
                }
                */
                // AUTALIZAR OS VALORES DOS OBJETOS QUE ESTÃO NA TELA;
                for (int i = 0; i < this.formacaoInicioSuperior.size(); i++) {
                    if (this.formacaoInicioSuperior.get(i).getY() > (GamePanel.HEIGHT + 10)) {
                        this.formacaoInicioSuperior.remove(i);
                        continue;
                    }
                    this.formacaoInicioSuperior.get(i).update();
                }
                for (int i = 0; i < this.formacaoInicioInferior.size(); i++) {
                    if (this.formacaoInicioInferior.get(i).getY() < -10) {
                        this.formacaoInicioInferior.remove(i);
                        continue;
                    }
                    this.formacaoInicioInferior.get(i).update();
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas){
        if(this.canStart) {
            if (!quadranteFinalizado()) {
                for (int i = 0; i < this.formacaoInicioSuperior.size(); i++) {
                    this.formacaoInicioSuperior.get(i).draw(canvas);
                }
                for (int i = 0; i < this.formacaoInicioInferior.size(); i++) {
                    this.formacaoInicioInferior.get(i).draw(canvas);
                }
            }
        }
    }

    @Override
    public boolean quadranteFinalizado(){
        if((this.formacaoInicioSuperior == null || this.formacaoInicioSuperior.isEmpty()) && (this.formacaoInicioInferior == null || this.formacaoInicioInferior.isEmpty())){
            finalizar();
            return true;
        }
        return false;
    }

    @Override
    public void finalizar(){
        this.formacaoInicioSuperior.clear();
        this.formacaoInicioInferior.clear();

        this.aviaoFormSup = null;
        this.aviaoFormInf = null;

        this.canStart = false;
    }

}
