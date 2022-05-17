package AVL;

public class Rotacion {
    
    // ROTACION IZQUIERDA SIMPLE
    public Naipe izquierdaSimple(PiramideAVL piramideAVL, Naipe naipe) {
        Naipe auxiliar = naipe.getHijoIzquierdo();
        naipe.setHijoIzquierdo(auxiliar.getHijoDerecho());
        auxiliar.setHijoDerecho(naipe);
        naipe.setFe(Math.max(piramideAVL.obtenerFE(naipe.getHijoIzquierdo()), piramideAVL.obtenerFE(naipe.getHijoDerecho()))+1);
        auxiliar.setFe(Math.max(piramideAVL.obtenerFE(auxiliar.getHijoIzquierdo()), piramideAVL.obtenerFE(auxiliar.getHijoDerecho()))+1);
        return auxiliar;
    }

    // ROTACION DERECHA 
    public Naipe derechaSimple(PiramideAVL piramideAVL, Naipe naipe) {
        Naipe auxiliar = naipe.getHijoDerecho();
        naipe.setHijoDerecho(auxiliar.getHijoIzquierdo());
        auxiliar.setHijoIzquierdo(naipe);
        naipe.setFe(Math.max(piramideAVL.obtenerFE(naipe.getHijoIzquierdo()), piramideAVL.obtenerFE(naipe.getHijoDerecho()))+1);
        auxiliar.setFe(Math.max(piramideAVL.obtenerFE(auxiliar.getHijoIzquierdo()), piramideAVL.obtenerFE(auxiliar.getHijoDerecho()))+1);
        return auxiliar;
    }

    // ROTACION IZQUIERDA DOBLE
    public Naipe izquierdaDoble(PiramideAVL piramideAVL, Naipe naipe) {
        Naipe temporal;
        naipe.setHijoIzquierdo(derechaSimple(piramideAVL, naipe.getHijoIzquierdo()));
        temporal = izquierdaSimple(piramideAVL, naipe);
        return temporal;
    }

    // ROTACION DERECHA DOBLE
    public Naipe derechaDoble(PiramideAVL piramideAVL, Naipe naipe) {
        Naipe temporal;
        naipe.setHijoDerecho(izquierdaSimple(piramideAVL, naipe.getHijoDerecho()));
        temporal = derechaSimple(piramideAVL, naipe);
        return temporal;
    }
}
