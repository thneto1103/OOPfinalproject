package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Personagem implements Serializable {

    protected ImageIcon iImage;
    public Posicao pPosicao;
    protected boolean bTransponivel=true; /*Pode passar por cima?*/
    protected boolean bMortal=true;     /*Se encostar, morre?*/
    protected boolean naopassaParede;
    protected boolean ehFogo=false;        
    protected boolean ehParede=false;
    protected boolean ehMonstro=false;
    protected boolean ehHeroi = false;
    protected boolean ehTuboVerde = false;
    protected boolean ehPlanta=false;
    protected boolean ehCogumelo = false;
    protected boolean ehFlor = false;
    protected boolean ehHeroFogo = false;
    protected boolean flag=false;
    public boolean isUp = false;
    public boolean isRight = false;
    public boolean isLeft = false;
    public boolean isDown = true;
    public int linhaAnterior;
    public int colunaAnterior;
    public static int contaFase = 1;
    public int vidas = 1;
    
    
    
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    protected Personagem(String sNomeImagePNG) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Posicao getPosicao() {
        /*TODO: Retirar este método para que objetos externos nao possam operar
         diretamente sobre a posição do Personagem*/
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }
    
    public boolean isnaopassaParede (){
        return naopassaParede;
    }
    public void setnaopassaParede (boolean naopassaParede){
        this.naopassaParede = naopassaParede;
    }
    
    public void autoDesenho(){
        Desenho.desenhar(this.iImage, this.pPosicao.getColuna(), this.pPosicao.getLinha());        
    }

    public boolean setPosicao(int linha, int coluna) {
        linhaAnterior = this.pPosicao.getLinha();
        colunaAnterior = this.pPosicao.getColuna();
        return pPosicao.setPosicao(linha, coluna);
    }
    
    public boolean setPosicaoAnterior(){
        return pPosicao.setPosicao(linhaAnterior, colunaAnterior);
    }

    public boolean moveUp() {
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        return this.pPosicao.moveLeft();
    }
    
    public boolean ehFogoTeste(Personagem personagem){
        return personagem.ehFogo;
    }
     public boolean ehMonstroTeste(Personagem personagem){
        return personagem.ehMonstro;
    }
    public boolean ehHeroiTeste(Personagem personagem){
        return personagem.ehHeroi;
    }
    public boolean ehTuboVerdeTeste(Personagem personagem){
        return personagem.ehTuboVerde;
    }
    public boolean ehCogumeloTeste (Personagem personagem ){
            return personagem.ehCogumelo;
    }
    public boolean ehMortal (Personagem personagem ){
            return personagem.bMortal;
    }
    public boolean ehFlorTeste (Personagem personagem ){
            return personagem.ehFlor;
    }
    public boolean ehHeroFogoTeste (Personagem personagem ){
            return personagem.ehHeroFogo;
    }
    public void setMortal (Personagem personagem, boolean ehMortal){
        personagem.bMortal = ehMortal;
    }
    public void setHeroFogo(Personagem personagem, boolean ehHeroFogo){
        personagem.ehHeroFogo = ehHeroFogo;
    }
    public void setImagem(String NovaImagemPNG){
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + NovaImagemPNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
