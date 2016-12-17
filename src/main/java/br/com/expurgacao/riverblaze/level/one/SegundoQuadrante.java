package br.com.expurgacao.riverblaze.level.one;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.util.ArrayList;
import java.util.List;

import br.com.expurgacao.riverblaze.enums.DisplayResolutionEnum;
import br.com.expurgacao.riverblaze.objetos.atirador.Atirador;
import br.com.expurgacao.riverblaze.objetos.atirador.AtiradorSQ;
import br.com.expurgacao.riverblaze.objetos.aviao.Aviao;
import br.com.expurgacao.riverblaze.objetos.aviao.AviaoSQDiagonalInferior;
import br.com.expurgacao.riverblaze.objetos.aviao.AviaoSQDiagonalSuperior;
import br.com.expurgacao.riverblaze.objetos.aviao.AviaoSQPrimeiraFormacao;

/**
 * Created by Alex Ney on 29/02/2016.
 */
public class SegundoQuadrante extends Quadrante {

    /*
    *
    * VIMANA TERA TIRO PARABOLA, ACIONADO PELO AONDE ERA A AREA DA BOMBA MAGNETICA
    * BOMBA MAGNETICA SERA ACIONADA COM TOQUES LONGOS DE DOIS DEDOS
    *
    * ADICIONAR BONUS PARA O TIRO PARABOLA
    *
    * QUADRANTE 2 (INIMIGOS NO SOLO)
      Durante o passar do quadrante 2, existem 6 inimigos no solo, atirando com metralhadoras. Cada tiro é uma rajada de 3 tiros.
      - DOIS GRUPOS DE TRES ATIRADORES; DISTANCIA FIXA ENTRE OS MEMBROS DO GRUPO;

      No meio do quadrante 2, mais duas passagens de aviões em formação.
      - A FORMAÇÃO DA PASSAGEM SERÁ ORGANIZADA NO EIXO VERTICAL, CADA FORMAÇÃO TERA TRES AVIAOES, ENTRANDO UM DE CADA VEZ NA TELA, EM SEQUENCIA COM UM PEQUENO INTERVALO DE ENTRADA;
      - A VELOCIDADE DESSES AVIÕES SERÁ MAIOR QUE A VELOCIDADE DOS AVIÕES DA PARABOLA;

        QUANDO O PRIMEIRO GRUPO DE ATIRADORES ESTIVER NA PRIMEIRA METADE DA TELA COMEÇAR A PASSAGEM DE AVIÕES, A PRIMEIRA FORMACAO INICIA DO INICIO AO FINAL DA TELA; A SEGUNDA FORMAÇAO INICIA DO FINAL DA TELA, FAZENDO U "X" COM OS AVIÕES DAS
        EXTREMIDADES E O AVIÃO DO MEIO SEGUE EM LINHA RETA;
    *
    * */
    private List<Aviao> formacaoPrimeiraPassagem;
    private List<Aviao> formacaoSegundaPassagem;
    private List<Atirador> atiradoresPrimeiroGrupo;
    private List<Atirador> atiradoresSegundoGrupo;
    private Bitmap aviao;
    private Bitmap atirador;
    private DisplayResolutionEnum displayResolution;
    private long startTimeSegundoQuadrante;
    private long timeFinalOfPrimeiroQuadrante;
    private boolean canStart = false;
    private boolean canSegundoGrupo = false;

    public SegundoQuadrante(Bitmap aviao, Bitmap atirador, int widthScreen, int heightScreen, DisplayResolutionEnum displayResolution){

        this.startTimeSegundoQuadrante = System.currentTimeMillis();

        this.aviao = aviao;
        this.atirador = atirador;

        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;

        this.displayResolution = displayResolution;

        // LOAD ATIRADORES
        this.atiradoresPrimeiroGrupo = new ArrayList<Atirador>();

        // PRIMEIRO GRUPO
        this.atiradoresPrimeiroGrupo.add(new AtiradorSQ(this.atirador, this.widthScreen, (this.heightScreen-this.atirador.getHeight())));
        this.atiradoresPrimeiroGrupo.add(new AtiradorSQ(this.atirador, (this.widthScreen + this.atirador.getWidth()) + 50, (this.heightScreen-this.atirador.getHeight())));
        this.atiradoresPrimeiroGrupo.add(new AtiradorSQ(this.atirador, (this.widthScreen + this.atirador.getWidth() * 2) + 100, (this.heightScreen-this.atirador.getHeight())));

        // SEGUNDO GRUPO
        this.atiradoresSegundoGrupo = new ArrayList<Atirador>();

        this.atiradoresSegundoGrupo.add(new AtiradorSQ(this.atirador, this.widthScreen, (this.heightScreen-this.atirador.getHeight())));
        this.atiradoresSegundoGrupo.add(new AtiradorSQ(this.atirador, (this.widthScreen + this.atirador.getWidth()) + 50, (this.heightScreen-this.atirador.getHeight())));
        this.atiradoresSegundoGrupo.add(new AtiradorSQ(this.atirador, (this.widthScreen + this.atirador.getWidth() * 2) + 100, (this.heightScreen-this.atirador.getHeight())));

        // LOAD AVIÕES PRIMEIRA PASSAGEM
        this.formacaoPrimeiraPassagem = new ArrayList<Aviao>();

        this.formacaoPrimeiraPassagem.add(new AviaoSQPrimeiraFormacao(this.aviao, this.widthScreen, 40));
        float y = this.heightScreen/2.f - 80.f;
        if((DisplayResolutionEnum.MDPI.equals(displayResolution)) || (DisplayResolutionEnum.LDPI.equals(displayResolution))){
            y = this.heightScreen/2.f - 45;
        }
        this.formacaoPrimeiraPassagem.add(new AviaoSQPrimeiraFormacao(this.aviao, (this.widthScreen + this.aviao.getWidth()) + (this.widthScreen/4), y));
        y = this.heightScreen-(this.atirador.getHeight() + 80);
        if((DisplayResolutionEnum.MDPI.equals(displayResolution)) || (DisplayResolutionEnum.LDPI.equals(displayResolution))){
            y = this.heightScreen-(this.atirador.getHeight() + 60);
        }
        this.formacaoPrimeiraPassagem.add(new AviaoSQPrimeiraFormacao(this.aviao, (this.widthScreen + this.aviao.getWidth()) + (this.widthScreen/2), y));

        // LOAD DA SEGUNDA PASSAGEM
        this.formacaoSegundaPassagem = new ArrayList<Aviao>();

        this.formacaoSegundaPassagem.add(new AviaoSQDiagonalSuperior(this.aviao, this.widthScreen, 20.f));
        this.formacaoSegundaPassagem.add(new AviaoSQPrimeiraFormacao(this.aviao, this.widthScreen, this.heightScreen/2, 7.f));
        this.formacaoSegundaPassagem.add(new AviaoSQDiagonalInferior(this.aviao, this.widthScreen, this.heightScreen-30));

    }

    @Override
    protected void update(){
        if(System.currentTimeMillis() - this.timeFinalOfPrimeiroQuadrante > 2000 && !this.canStart){
            this.canStart = true;
        }
        if(this.canStart){
            // TODO: HÁ REPETIÇÃO DE COMPORTAMENTO AO RETIRAR OS OBJETOS DO ARRAY, ESSE METODOS PRECISAM ESTAR ISOLADOS RECEBENDO APENAS O ARRAYLIST
            //System.out.println(this.atiradoresPrimeiroGrupo.size());
            // PRIMEIRO DE ATIRADORES
            for(int i = 0; i< this.atiradoresPrimeiroGrupo.size(); i++){
                //System.out.println(this.atiradoresPrimeiroGrupo.get(i).getX());
                if(!this.canSegundoGrupo && (-this.atiradoresPrimeiroGrupo.get(i).getX() > -this.widthScreen/2)){
                    this.canSegundoGrupo = true;
                }
                if(this.atiradoresPrimeiroGrupo.get(i).getX() < -10){
                    this.atiradoresPrimeiroGrupo.remove(i);
                    continue;
                }
                this.atiradoresPrimeiroGrupo.get(i).update();
            }
            // FORMÇÃO DE AVIÕES DA PRIMEIRA PASSAGEM
            for(int i = 0; i< this.formacaoPrimeiraPassagem.size(); i++){
                if(this.formacaoPrimeiraPassagem.get(i).getX() < -10){
                    this.formacaoPrimeiraPassagem.remove(i);
                    continue;
                }
                this.formacaoPrimeiraPassagem.get(i).update();
            }
            //*************************************************************************************
            // SEGUNDO GRUPO DE ATIRADORES
            if(this.canSegundoGrupo){
                for(int i = 0; i< this.atiradoresSegundoGrupo.size(); i++){
                    if(this.atiradoresSegundoGrupo.get(i).getX() < -10){
                        this.atiradoresSegundoGrupo.remove(i);
                        continue;
                    }
                    this.atiradoresSegundoGrupo.get(i).update();
                }
                for(int i = 0; i< this.formacaoSegundaPassagem.size(); i++){
                    if(this.formacaoSegundaPassagem.get(i).getX() < -10){
                        this.formacaoSegundaPassagem.remove(i);
                        continue;
                    }
                    this.formacaoSegundaPassagem.get(i).update();
                }
            }
        }
        /*
        System.out.println("***************************************************************");
        System.out.println(this.atiradoresPrimeiroGrupo.size() + " - " + this.atiradoresSegundoGrupo.size());
        System.out.println(this.formacaoPrimeiraPassagem.size() + " - " + this.formacaoSegundaPassagem.size());
        System.out.println("***************************************************************");
        */
    }

    @Override
    protected void draw(Canvas canvas){
        if(this.canStart){
            for(Atirador atirador : this.atiradoresPrimeiroGrupo){
                atirador.draw(canvas);
            }
            for(Aviao aviao : this.formacaoPrimeiraPassagem){
                aviao.draw(canvas);
            }
            if(this.canSegundoGrupo){
                for(Atirador atirador : this.atiradoresSegundoGrupo){
                    atirador.draw(canvas);
                }
                for(Aviao aviao : this.formacaoSegundaPassagem){
                    aviao.draw(canvas);
                }
            }
        }
    }

    @Override
    protected boolean quadranteFinalizado(){
        return false;
    }

    @Override
    protected void finalizar() {
        this.canStart = false;
        this.canSegundoGrupo = false;
    }

    public void setTimeFinalOfPrimeiroQuadrante(long timeFinalOfPrimeiroQuadrante){
        this.timeFinalOfPrimeiroQuadrante=timeFinalOfPrimeiroQuadrante;
    }

}
