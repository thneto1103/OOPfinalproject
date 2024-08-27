package Controler;

import Modelo.Personagem;
import Modelo.Caveira;
import Modelo.Hero;
import Modelo.BichinhoVaiVemHorizontal;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.Cogumelo;
import Modelo.Flor;
import Modelo.Parede;
import Modelo.Planta;
import Modelo.Tubo;
import Modelo.TuboVerde;
import Modelo.ZigueZague;
import Modelo.saver;
import auxiliar.Posicao;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JButton;


public final class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Hero hero;
    private ArrayList<Personagem> faseAtual;
    private ArrayList<Personagem> ZigZag;
    private ArrayList<Personagem> BichinhoVaieVemHorizontal;
    private ArrayList<Personagem> Parede;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private int contaFase = 1;
    private boolean flag = true;
    
    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        faseAtual = new ArrayList<Personagem>();
         if (!carregaFase()) {
         this.criaFase();
             salvaFase(faseAtual);
         }
    }
    
    public void criaFase(){
            if(contaFase == 1){
            criaFase1();
            }
            if(contaFase == 2){
            criaFase2();
            }
            if(contaFase == 3){
            criaFase3();
            }
            if(contaFase == 4){
            criaFase4();
            }
    }
    
    public void resetaJogo(){
        this.faseAtual = new ArrayList<Personagem>(); 
    }
    
    public boolean salvaFase(ArrayList<Personagem> fase) {
        FileOutputStream fileOut;
        try {
            saver saveGame = new saver(this.faseAtual, this.contaFase);
            File saveGameFile = new File("saveGame.ser");
            fileOut = new FileOutputStream(saveGameFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(saveGame);
            out.close();
            fileOut.close();
            System.out.println("Jogo salvo na fase: " + (this.contaFase));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro em salvar");
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro em salvar");
            return true;
        }
        return true;

    }
        
    @SuppressWarnings("unchecked")
    public boolean carregaFase() {
        saver saveGame;
        saveGame = null;

        File saveGameFile = new File("saveGame.ser");

        if (saveGameFile.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(saveGameFile);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                saveGame = (saver) in.readObject();
                in.close();
                fileIn.close();

                this.faseAtual = saveGame.getPersonagens();
                this.contaFase = saveGame.getFase();
                System.out.println("Jogo Salvo encontrado! Fase carregada: " + this.contaFase);

            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();

                System.out.println("Erro ao carregar o jogo");
            }

            return true;
        } else {
            try {
                saveGameFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro ao criar o arquivo de salvamento");
            }
            return false;
        }
    }
    
    public void criaFase1(){
        
        
        int [][] mapaDaFase = new int[][] { {1, 0, 5, 0 , 0, 5, 0, 0, 5, 0, 0,0}, 
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 6},
                                          };
        for(int i = 0; i<12; i++){
            for(int j = 0; j<12 ; j++){
                switch (mapaDaFase[i][j]) {
                    case 1 -> {
                        hero = new Hero("mario.png.png");
                        hero.setPosicao(i, j);
                        this.addPersonagem(hero);
                    }
                    case 2 -> {
                        ZigueZague zz = new ZigueZague("bichinho.png");
                        zz.setPosicao(i, j);
                        this.addPersonagem(zz);
                    }
                    case 3 -> {
                        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
                        bBichinhoH.setPosicao(i, j);
                        this.addPersonagem(bBichinhoH);
                    }
                    case 4 -> {
                        Caveira bV = new Caveira("caveira.png");
                        bV.setPosicao(i, j);
                        this.addPersonagem(bV);
                    }
                    case 5 -> {
                        Parede muro = new Parede("parede.png");
                        muro.setPosicao(i, j);
                        this.addPersonagem(muro);
                    }  
                    case 6 -> {
                       TuboVerde tubo = new TuboVerde ("tubo.png");
                        tubo.setPosicao(i, j);
                        this.addPersonagem(tubo);
                    }
                    case 7 -> {
                        Planta planta = new Planta ("planta.png");
                        planta.setPosicao (i,j);
                        this.addPersonagem (planta);
                    }
                    case 8 -> {
                        Tubo tubo = new Tubo ("tubo.png");
                        tubo.setPosicao (i,j);
                        this.addPersonagem(tubo);
                    }
                    case 9 -> {
                        Cogumelo cogumelo = new Cogumelo ("cogumelo.png");
                        cogumelo.setPosicao (i,j);
                        this.addPersonagem(cogumelo);
                    }
                    case 10 -> {
                        Flor flor = new Flor ("Flor.png");
                        flor.setPosicao (i,j);
                        this.addPersonagem(flor);
                    }
                    default -> {
                    } 
                }
            }
        }
    }
    
    public void criaFase2(){
        
        
        int [][] mapaDaFase = new int[][] { {1, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0}, 
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 7, 0, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 8, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 3, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 7, 0, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 8, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 0},
                                            {0, 0, 5, 0, 0, 5, 0, 0, 0, 5, 0, 6}
                                          };
        for(int i = 0; i<12; i++){
            for(int j = 0; j<12 ; j++){
                switch (mapaDaFase[i][j]) {
                    case 1 -> {
                        hero = new Hero("mario.png.png");
                        hero.setPosicao(i, j);
                        this.addPersonagem(hero);
                    }
                    case 2 -> {
                        ZigueZague zz = new ZigueZague("bichinho.png");
                        zz.setPosicao(i, j);
                        this.addPersonagem(zz);
                    }
                    case 3 -> {
                        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
                        bBichinhoH.setPosicao(i, j);
                        this.addPersonagem(bBichinhoH);
                    }
                    case 4 -> {
                        Caveira bV = new Caveira("caveira.png");
                        bV.setPosicao(i, j);
                        this.addPersonagem(bV);
                    }
                    case 5 -> {
                        Parede muro = new Parede("parede.png");
                        muro.setPosicao(i, j);
                        this.addPersonagem(muro);
                    }  
                    case 6 -> {
                       TuboVerde tubo = new TuboVerde ("tubo.png");
                        tubo.setPosicao(i, j);
                        this.addPersonagem(tubo);
                    }
                    case 7 -> {
                        Planta planta = new Planta ("planta.png");
                        planta.setPosicao (i,j);
                        this.addPersonagem (planta);
                    }
                    case 8 -> {
                        Tubo tubo = new Tubo ("tubo.png");
                        tubo.setPosicao (i,j);
                        this.addPersonagem(tubo);
                    }
                    case 9 -> {
                        Cogumelo cogumelo = new Cogumelo ("cogumelo.png");
                        cogumelo.setPosicao (i,j);
                        this.addPersonagem(cogumelo);
                    } 
                    case 10 -> {
                        Flor flor = new Flor ("Flor.png");
                        flor.setPosicao (i,j);
                        this.addPersonagem(flor);
                    }
                    default -> {
                    } 
                }
            }
        }
    }
    
    public void criaFase3(){
        
        
        int [][] mapaDaFase = new int[][] { {1, 5, 0, 0, 0, 0, 0,5 ,0, 0, 5,6}, 
                                            {0, 5, 5, 5, 5, 5, 5,5, 0, 0, 5, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0},
                                            {0, 5, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0},
                                            {0, 5, 5, 5, 5, 0, 0, 5, 0, 0, 5, 0},
                                            {0, 5, 0, 0, 5, 0, 0, 0, 7, 0, 5, 0},
                                            {0, 5, 0, 0, 5, 5, 5, 5, 8, 0, 5, 0},
                                            {0, 5, 0, 0, 5, 10, 0, 5, 0, 0, 5, 0},
                                            {0, 5, 0, 0, 5, 0, 0, 5, 0, 0, 5, 0},
                                            {0, 5, 0, 0, 5, 0, 0, 5, 0, 7, 0, 0},
                                            {0, 5, 5, 5, 5, 0, 0, 5, 0, 8, 5, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 5, 0}
                                          };
        for(int i = 0; i<12; i++){
            for(int j = 0; j<12 ; j++){
                switch (mapaDaFase[i][j]) {
                    case 1 -> {
                        hero = new Hero("mario.png.png");
                        hero.setPosicao(i, j);
                        this.addPersonagem(hero);
                    }
                    case 2 -> {
                        ZigueZague zz = new ZigueZague("bichinho.png");
                        zz.setPosicao(i, j);
                        this.addPersonagem(zz);
                    }
                    case 3 -> {
                        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
                        bBichinhoH.setPosicao(i, j);
                        this.addPersonagem(bBichinhoH);
                    }
                    case 4 -> {
                        Caveira bV = new Caveira("caveira.png");
                        bV.setPosicao(i, j);
                        this.addPersonagem(bV);
                    }
                    case 5 -> {
                        Parede muro = new Parede("parede.png");
                        muro.setPosicao(i, j);
                        this.addPersonagem(muro);
                    }  
                    case 6 -> {
                       TuboVerde tubo = new TuboVerde ("tubo.png");
                        tubo.setPosicao(i, j);
                        this.addPersonagem(tubo);
                    }
                    case 7 -> {
                        Planta planta = new Planta ("planta.png");
                        planta.setPosicao (i,j);
                        this.addPersonagem (planta);
                    }
                    case 8 -> {
                        Tubo tubo = new Tubo ("tubo.png");
                        tubo.setPosicao (i,j);
                        this.addPersonagem(tubo);
                    }
                    case 9 -> {
                        Cogumelo cogumelo = new Cogumelo ("cogumelo.png");
                        cogumelo.setPosicao (i,j);
                        this.addPersonagem(cogumelo);
                    }
                    case 10 -> {
                        Flor flor = new Flor ("Flor.png");
                        flor.setPosicao (i,j);
                        this.addPersonagem(flor);
                    }
                    default -> {
                    } 
                }
            }
        }
    }
    
    public void criaFase4(){
        
        
        int [][] mapaDaFase = new int[][] { {1, 5, 0, 5 ,9, 5, 0, 0, 0, 0, 0, 6}, 
                                            {0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 5, 5, 0, 5, 5, 5, 5, 5, 5, 0},
                                            {7, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
                                            {8, 5, 5, 5 , 0,5, 5, 5, 5, 5, 5, 5},
                                            {0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 10},
                                            {0, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5}
                                          };
        for(int i = 0; i<12; i++){
            for(int j = 0; j<12 ; j++){
                switch (mapaDaFase[i][j]) {
                    case 1 -> {
                        hero = new Hero("mario.png.png");
                        hero.setPosicao(i, j);
                        this.addPersonagem(hero);
                    }
                    case 2 -> {
                        ZigueZague zz = new ZigueZague("bichinho.png");
                        zz.setPosicao(i, j);
                        this.addPersonagem(zz);
                    }
                    case 3 -> {
                        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
                        bBichinhoH.setPosicao(i, j);
                        this.addPersonagem(bBichinhoH);
                    }
                    case 4 -> {
                        Caveira bV = new Caveira("caveira.png");
                        bV.setPosicao(i, j);
                        this.addPersonagem(bV);
                    }
                    case 5 -> {
                        Parede muro = new Parede("parede.png");
                        muro.setPosicao(i, j);
                        this.addPersonagem(muro);
                    }  
                    case 6 -> {
                       TuboVerde tubo = new TuboVerde ("tubo.png");
                        tubo.setPosicao(i, j);
                        this.addPersonagem(tubo);
                    }
                    case 7 -> {
                        Planta planta = new Planta ("planta.png");
                        planta.setPosicao (i,j);
                        this.addPersonagem (planta);
                    }
                    case 8 -> {
                        Tubo tubo = new Tubo ("tubo.png");
                        tubo.setPosicao (i,j);
                        this.addPersonagem(tubo);
                    }
                    case 9 -> {
                        Cogumelo cogumelo = new Cogumelo ("cogumelo.png");
                        cogumelo.setPosicao (i,j);
                        this.addPersonagem(cogumelo);
                    }
                    case 10 -> {
                        Flor flor = new Flor ("Flor.png");
                        flor.setPosicao (i,j);
                        this.addPersonagem(flor);
                    }
                    default -> {
                    } 
                }
            }
        }
    }
    
    public void criaGameOver(){
        
        
        int [][] mapaDaFase = new int[][] { {1, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0}, 
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0 , 0,0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
                                          };
        for(int i = 0; i<12; i++){
            for(int j = 0; j<12 ; j++){
                switch (mapaDaFase[i][j]) {
                    case 1 -> {
                        hero = new Hero("");
                        hero.setPosicao(i, j);
                        this.addPersonagem(hero);
                    }
                    case 2 -> {
                        ZigueZague zz = new ZigueZague("bichinho.png");
                        zz.setPosicao(i, j);
                        this.addPersonagem(zz);
                    }
                    case 3 -> {
                        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("bichinho.png");
                        bBichinhoH.setPosicao(i, j);
                        this.addPersonagem(bBichinhoH);
                    }
                    case 4 -> {
                        Caveira bV = new Caveira("caveira.png");
                        bV.setPosicao(i, j);
                        this.addPersonagem(bV);
                    }
                    case 5 -> {
                        Parede muro = new Parede("parede.png");
                        muro.setPosicao(i, j);
                        this.addPersonagem(muro);
                    }  
                    case 6 -> {
                       TuboVerde tubo = new TuboVerde ("tubo.png");
                        tubo.setPosicao(i, j);
                        this.addPersonagem(tubo);
                    }
                    case 7 -> {
                        Planta planta = new Planta ("planta.png");
                        planta.setPosicao (i,j);
                        this.addPersonagem (planta);
                    }
                    case 8 -> {
                        Tubo tubo = new Tubo ("tubo.png");
                        tubo.setPosicao (i,j);
                        this.addPersonagem(tubo);
                    }
                    case 9 -> {
                        Cogumelo cogumelo = new Cogumelo ("cogumelo.png");
                        cogumelo.setPosicao (i,j);
                        this.addPersonagem(cogumelo);
                    }
                    case 10 -> {
                        Flor flor = new Flor ("Flor.png");
                        flor.setPosicao (i,j);
                        this.addPersonagem(flor);
                    }
                    default -> {
                    } 
                }
            }
        }
    }
    
    public boolean ehPosicaoValida(Posicao p){
        return cj.ehPosicaoValida(this.faseAtual, p);
    }
    public void addPersonagem(Personagem umPersonagem) {
        faseAtual.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.remove(umPersonagem);
    }

    public Graphics getGraphicsBuffer(){
        return g2;
    }
    @Override
    public void paint(Graphics gOld) {
        int contador = 0;
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        
        if(hero.contaFase == -1){
            System.out.println("Game Over");
            System.out.println("O jogo vai recomecar sozinho");
            hero.contaFase = 1 ;
            contaFase = 1;
            resetaJogo();
            criaFase();
            }
        
        
        /*************draw the background**************/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "fundo.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Hero hero = (Hero) this.faseAtual.get(0);
        if (e.getKeyCode() == KeyEvent.VK_C) {
            contaFase = 1 ;
            resetaJogo();
            criaFase();
            salvaFase(faseAtual);
        }if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.isUp = true;
            hero.isDown = false;
            hero.isRight = false;
            hero.isLeft = false;
            hero.moveUp();
            if(hero.ehMortal(hero)){
            hero.setImagem("marioCimaPequeno.png");
            }
            if(!hero.ehMortal(hero)){
            hero.setImagem("marioCimaGrande.png");
            }
        }if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.isUp = false;
            hero.isDown = true;
            hero.isRight = false;
            hero.isLeft = false;
            hero.moveDown();
            if(hero.ehMortal(hero)){
            hero.setImagem("marioBaixoPequeno.png");
            }
            if(!hero.ehMortal(hero)){
            hero.setImagem("marioBaixoGrande.png");
            }
        }if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.isUp = false;
            hero.isDown = false;
            hero.isRight = false;
            hero.isLeft = true;
            hero.moveLeft();
            if(hero.ehMortal(hero)){
            hero.setImagem("marioEsquerdaPequeno.png");
            }
            if(!hero.ehMortal(hero)){
            hero.setImagem("marioEsquerdaGrande.png");
            }
        }if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.isUp = false;
            hero.isDown = false;
            hero.isRight = true;
            hero.isLeft = false;
            hero.moveRight();
            if(hero.ehMortal(hero)){
            hero.setImagem("marioDireitaPequeno.png");
            }
            if(!hero.ehMortal(hero)){
            hero.setImagem("marioDireitaGrande.png");
            }
            }
        if(e.getKeyCode() == KeyEvent.VK_R) {
                salvaFase(faseAtual);
            }
        if(e.getKeyCode() == KeyEvent.VK_N) {
            if(cj.podePassarFase()){
            contaFase++;
            if(contaFase==1){
            this.faseAtual.clear();
            criaFase1();
            }
            if(contaFase==2){
            this.faseAtual.clear();
            criaFase2();
            }
            if(contaFase==3){
            this.faseAtual.clear();
            criaFase3();
            }
            if(contaFase==4){
            this.faseAtual.clear();
            criaFase4();
            }
            if(contaFase==5){
            this.faseAtual.clear();
            criaGameOver();
            System.out.println("Voce chegou ao fim do jogo ");
            System.out.println("Parabens !!! ");
            System.out.println("Aperte C para Recomecar");
            }
            cj.setPassarFase(false);
            }
            
        }
        
        
        if(!ehPosicaoValida(hero.getPosicao())){
            hero.voltaAUltimaPosicao();
        }
            
        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", "
                + (hero.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.hero.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         
        repaint();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
