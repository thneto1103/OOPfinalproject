package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Parede extends Personagem implements Serializable {
    
    public Parede(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.ehParede = true;
        this.bTransponivel = false;
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        
    }
    
}
