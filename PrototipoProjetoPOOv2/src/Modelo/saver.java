package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class saver implements Serializable {
    private ArrayList<Personagem> personagens;
    private int contaFase;

    public saver(ArrayList<Personagem> personagens, int valorNumerico) {
        this.personagens = personagens;
        this.contaFase = valorNumerico;
    }

    public ArrayList<Personagem> getPersonagens() {
        return personagens;
    }

    public int getFase() {
        return contaFase;
    }
}
