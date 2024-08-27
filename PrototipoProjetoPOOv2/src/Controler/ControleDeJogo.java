package Controler;

import Auxiliar.Consts;
import Modelo.Personagem;
import Modelo.Hero;
import auxiliar.Posicao;
import java.util.ArrayList;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class ControleDeJogo {
    
    public boolean passaFaseFlag = false;
    public boolean inicioDoJogoFlag = true ;
    
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    
    public void processaTudo(ArrayList<Personagem> umaFase){
        if(inicioDoJogoFlag){
            System.out.println("Bem vindo ao jogo do mario ");
            System.out.println("Use as setas para se mover ");
            System.out.println("Aperte N para passar de fase");
            System.out.println("Aperte R para salvar o jogo");
            System.out.println("Aperte C para resetar o jogo ");
            inicioDoJogoFlag = false;
        }
        Hero hero = (Hero)umaFase.get(0);
        Personagem pIesimoPersonagem;
        Personagem pJesimoPersonagem;
        if(!hero.ehMortal(hero)){
        hero.iContaIntervalos++;
        if(hero.iContaIntervalos == 50){
                    hero.setImagem("mario.png.png");
                    hero.iContaIntervalos = 0;
                    hero.setMortal(hero, true);
                    }}
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            pJesimoPersonagem = umaFase.get(i);
            if(hero.vidas == 0 ){
                hero.contaFase = -1;
            }
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                if(pIesimoPersonagem.ehTuboVerdeTeste(pIesimoPersonagem)){
                    hero.contaFase = hero.contaFase+1;
                    System.out.println("Aperte N para ir para a proxima fase");
                    hero.pPosicao.volta();
                    passaFaseFlag = true;
                }
                if(pIesimoPersonagem.ehMonstroTeste(pIesimoPersonagem)){
                    if(hero.ehMortal(hero)){
                    hero.vidas--;
                    System.out.println("heroi perdeu vida, voce tem "+ hero.vidas +" restantes");
                    hero.pPosicao.setPosicao(0,0);
                    }
                    else{
                    umaFase.remove(pIesimoPersonagem);
                    hero.setMortal(hero, true);
                    }
                    }
                if(pIesimoPersonagem.ehCogumeloTeste(pIesimoPersonagem)){
                    hero.iContaIntervalos = 0;
                    hero.setImagem("marioCimaGrande.png");
                    hero.setMortal(hero, false);
                    umaFase.remove (pIesimoPersonagem);
                }
                if(pIesimoPersonagem.ehFlorTeste(pIesimoPersonagem) ){
                    hero.vidas++;
                    System.out.println("heroi ganhou vida, voce tem "+ hero.vidas +" restantes");
                    umaFase.remove (pIesimoPersonagem);
                }
                
            }
      }
      for(int i = 1; i < umaFase.size(); i++){
           pIesimoPersonagem = umaFase.get(i);
          for(int j = 1; j < umaFase.size(); j++){
            pJesimoPersonagem = umaFase.get(j);
              if(pJesimoPersonagem.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                  pJesimoPersonagem.pPosicao.setPosicao(pJesimoPersonagem.pPosicao.getLinhaAnterior(),pJesimoPersonagem.pPosicao.getColunaAnterior());
                  pJesimoPersonagem.pPosicao.setPosicao(pJesimoPersonagem.pPosicao.getLinhaAnterior(),pJesimoPersonagem.pPosicao.getColunaAnterior());
              }
          }
      }
    }
    
    public boolean podePassarFase(){
        return this.passaFaseFlag;
    }

    public void setPassarFase(boolean flag){
        this.passaFaseFlag = flag;
    }
    
    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);            
            if(!pIesimoPersonagem.isbTransponivel())
                if(pIesimoPersonagem.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}
