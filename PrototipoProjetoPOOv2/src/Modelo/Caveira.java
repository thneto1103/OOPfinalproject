package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Caveira extends Personagem implements Serializable{
    private int iContaIntervalos;
    
    
    
    public Caveira(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
        this.ehMonstro = true;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
    }    
}
