
package AVL;

/**
 *
 * @author CIROSS
 */
public class Naipe {

    private int fe, peso, valor;
    private char simbolo;
    private String id;
    private Naipe hijoIzquierdo, hijoDerecho;

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

        if (ch.length == 2) {

            if (Character.isDigit(ch[0]) && Character.getNumericValue(ch[0]) > 1) {
                return Character.getNumericValue(ch[0]);
            } else if (ch[0] == 'A') {
                return 1;
            } else if (ch[0] == 'J') {
                return 11;
            } else if (ch[0] == 'Q') {
                return 12;
            } else if (ch[0] == 'K') {
                return 13;
            }

        } else if (ch.length == 3) {

            if ((ch[0] == '1') && (ch[1] == '0')) {
                return 10;
            }
        }

        return 0;
    }

    private char setSimbolo(String valor) {

        char s = '-';
        int posSimb = 0;
        char[] ch = valor.toCharArray();

        if (ch.length == 2) {
            posSimb = 1;
        } else if ( (ch.length == 3) && (ch[0] == '1') && (ch[1] == '0') ) {
            posSimb = 2;
        }
        
        if (posSimb > 0) {
            
            switch (ch[posSimb]) {
                //case (char) 5:
                case '♣':    // Trebol   = Alt + 5
                    s = '♣';
                    break;
                //case (char) 4:
                case '♦':    // Diamante = Alt + 4
                    s = '♦';
                    break;
                //case (char) 3:
                case '♥':    // Corazon  = Alt + 3
                    s = '♥';
                    break;
                //case (char) 6: 
                case '♠':    // Pica   = Alt + 6
                    s = '♠';
                    break;
                default:
                    break;
            }
        }
        return s;
    }

    private int setValor(char simbolo, int peso) {
        
        int n = 0;

        switch (simbolo) {
            case '♣':
                n = peso;
                break;
            case '♦':
                n = peso + 20;
                break;
            case '♥':
                n = peso + 40;
                break;
            case '♠':
                n = peso + 60;
                break;
            default:
                break;
        }
        return n;
    }

    public boolean isHoja() {
        return (this.hijoIzquierdo==null && this.hijoDerecho==null);
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

    public boolean isValido() {
        return ( (this.peso > 0) && (this.simbolo != '-') );
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
