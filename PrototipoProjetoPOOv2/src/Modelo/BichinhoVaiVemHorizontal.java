package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichinhoVaiVemHorizontal extends Personagem  implements Serializable{
    private boolean bRight;
    private int contador=0;

    public BichinhoVaiVemHorizontal(String sNomeImagePNG) {
        super(sNomeImagePNG);
        bRight = true;
        this.bMortal= true;
        this.naopassaParede = true;
        this.ehMonstro = true ;
    }
    public void autoDesenho(){
        contador++;
        if(contador>=2){
        if(bRight){
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
            contador=0;
            bRight = !bRight;}
            else{
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
            contador=0;
            bRight = !bRight;}
        }
        super.autoDesenho();
    }
}
