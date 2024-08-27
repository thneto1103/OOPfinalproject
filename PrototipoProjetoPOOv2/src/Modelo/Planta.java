
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

    public class Planta extends Personagem implements Serializable {
        
    private int iContaIntervalos;

    public Planta (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.ehPlanta = true;
        setFlag(false);
        this.bTransponivel = true;
        this.ehMonstro = true;
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMER){
            this.iContaIntervalos = 0;
        if(flag){
        this.setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
        setFlag(false);
        }
        else{
        this.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
        setFlag(true);
        }
    }}
    
 
}

