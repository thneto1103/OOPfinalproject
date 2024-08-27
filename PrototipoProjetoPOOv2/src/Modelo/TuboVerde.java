package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class TuboVerde extends Personagem implements Serializable {
    
    public TuboVerde (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.ehTuboVerde = true;
        this.bTransponivel = true;
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        
    }
    
}

