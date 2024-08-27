
package Modelo;

import java.io.Serializable;

public class Flor extends Personagem implements Serializable {
    
    public Flor (String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.ehFlor = true;
        
    }
    
    @Override
    public void autoDesenho() {
        super.autoDesenho();
        
    }
    
}
