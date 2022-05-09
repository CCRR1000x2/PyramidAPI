/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AVL;

/**
 *
 * @author CIROSS
 */
public class Naipe {

    int fe, peso, valor;
    char simbolo;
    String id;
    Naipe hijoIzquierdo, hijoDerecho;

    public Naipe(String id) {
        this.fe = 0;
        this.id = id;
        this.peso = setPeso(id);
        this.simbolo = setSimbolo(id);
        this.valor = setValor(simbolo, peso);
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    private int setPeso(String valor) {

        char[] ch = valor.toCharArray();

        if (Character.isDigit(ch[0]) && Character.getNumericValue(ch[0]) > 1) {
            return Character.getNumericValue(ch[0]);
        } else if (ch.length > 2 && ch[0] == '1' && ch[1] == '0') {
            return 10;
        } else if (ch[0] == 'A') {
            return 1;
        } else if (ch[0] == 'J') {
            return 11;
        } else if (ch[0] == 'Q') {
            return 12;
        } else if (ch[0] == 'K') {
            return 13;
        }
        return 0;
    }

    private char setSimbolo(String valor) {

        char s = '-';
        int i = 0;
        char[] ch = valor.toCharArray();

        if (ch.length == 2) {
            i = 1;
        } else if (ch.length > 2 && ch[0] == '1' && ch[1] == '0') {
            i = 2;
        }
        
        if (i > 0) {
            switch (ch[i]) {
                case 't':
                    s = 't';
                    break;
                case 'd':
                    s = 'd';
                    break;
                case 'c':
                    s = 'c';
                    break;
                case 'p':
                    s = 'p';
                    break;
                default:
                    break;
            }
        }
        return s;
    }

    private int setValor(char simbolo, int num) {
        
        int n = 0;

        switch (simbolo) {
            case 't':
                n = num;
                break;
            case 'd':
                n = num + 20;
                break;
            case 'c':
                n = num + 40;
                break;
            case 'p':
                n = num + 60;
                break;
            default:
                break;
        }
        return n;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Naipe getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(Naipe hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public Naipe getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(Naipe hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    

}
