package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Tubo extends Personagem implements Serializable {
    
    public Tubo (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        
    }
    
}
