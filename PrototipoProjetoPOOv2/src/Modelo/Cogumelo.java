package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Cogumelo extends Personagem implements Serializable {
    
    public Cogumelo (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.ehCogumelo = true;
        
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        
    }
    
}
