
package AVL;

public class PiramideAVL {
    
    private Naipe raiz;
    private Recorrido recorrido;
    
    public PiramideAVL() {
        this.setRaiz(null);
        this.setRecorrido(new Recorrido(this));
    }
    
    public int obtenerFE(Naipe nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.getFe();
        }
    }
    
    //ROTACIONES
    //Rotación Simple Izquierda
    public Naipe rotacionIzquierda(Naipe c) {
        Rotacion rotacion = new Rotacion();
        return rotacion.izquierdaSimple(this, c);
    }
    
    //Rotación Simple Derecha
    public Naipe rotacionDerecha(Naipe c) {
        Rotacion rotacion = new Rotacion();
        return rotacion.derechaSimple(this, c);
    }
    
    //Rotacion Doble a la Derecha
    public Naipe rotacionDobleIzquierda(Naipe c) {
        Rotacion rotacion = new Rotacion();
        return rotacion.izquierdaDoble(this, c);
    }

    // Rotación Doble a la Izquierda
    public Naipe rotacionDobleDerecha(Naipe c) {
        Rotacion rotacion = new Rotacion();
        return rotacion.derechaDoble(this, c);
    }
    
    //Insertar
    public void insertarNaipe(Naipe naipe) {
        
        if (this.raiz==null) {
            this.raiz = naipe;
        } else {
            this.raiz = insertar(naipe, raiz);
        }
    }

    public Naipe insertar(Naipe nuevo, Naipe subAr) {
        
        if (subAr==null) {
            return nuevo;
        }

        // El nodo es menor
        if (nuevo.getValor() < subAr.getValor()) {
            subAr.setHijoIzquierdo(insertar(nuevo, subAr.getHijoIzquierdo()));
            if ((obtenerFE(subAr.getHijoIzquierdo()) - obtenerFE(subAr.getHijoDerecho()) == 2)) {
                if (nuevo.getValor() < subAr.getHijoIzquierdo().getValor()) {
                    subAr = rotacionIzquierda(subAr);
                } else {
                    subAr = rotacionDobleIzquierda(subAr);
                }
            }
        // El nodo es mayor
        } else if (nuevo.getValor() > subAr.getValor()) {
            subAr.setHijoDerecho(insertar(nuevo, subAr.getHijoDerecho()));
            if ((obtenerFE(subAr.getHijoDerecho())-obtenerFE(subAr.getHijoIzquierdo())==2)) {
                if (nuevo.getValor() > subAr.getHijoDerecho().getValor()) {
                    subAr = rotacionDerecha(subAr);
                } else {
                    subAr = rotacionDobleDerecha(subAr);
                }
            }
        } else {
            return subAr;
        }

        // Actualizando la altura
        if ((subAr.getHijoIzquierdo() == null) && (subAr.getHijoDerecho() != null)) {
            subAr.setFe(subAr.getHijoDerecho().getFe()+1);
        } else if ((subAr.getHijoDerecho() == null) && (subAr.getHijoIzquierdo() != null)) {
            subAr.setFe(subAr.getHijoIzquierdo().getFe() + 1);
        } else {
            subAr.setFe(Math.max(obtenerFE(subAr.getHijoIzquierdo()), obtenerFE(subAr.getHijoDerecho())) + 1);
        }
        return subAr;
    }

    public boolean isHojaPiramide(Naipe naipe, Naipe temp) {
        
        if (temp!=null) {
            if (naipe.getValor()<temp.getValor()) {
                return isHojaPiramide(naipe, temp.getHijoIzquierdo());
            } else if (naipe.getValor()>temp.getValor()) {
                return isHojaPiramide(naipe, temp.getHijoDerecho());
            } else {
                if (temp.isHoja()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void eliminarNaipe(Naipe naipe) {
        
        if (naipe.getValor() == this.raiz.getValor()) {
            if (this.raiz.isHoja()) {
                this.raiz = null;
                //return true;
            } else {
                //return false;
            }
        }
        eliminar(naipe, this.raiz);
    }

    private void eliminar(Naipe naipe, Naipe padre) {
        
        if (padre!=null) {
            if (naipe.getValor() < padre.getValor()) {
                if (naipe.getValor() == padre.getHijoIzquierdo().getValor()) {
                    padre.setHijoIzquierdo(null);
                    
                } else {
                    eliminar(naipe, padre.getHijoIzquierdo());
                }
            } else if (naipe.getValor() > padre.getValor()) {
                if (naipe.getValor() == padre.getHijoDerecho().getValor()) {
                    padre.setHijoDerecho(null);
                    
                } else {
                    eliminar(naipe, padre.getHijoDerecho());
                }
            } 
        }
        
    }

    public String[][] getMatriz(String cadena) {

        cadena = cadena.replaceAll("\\s", "");
        cadena = cadena.replace("{", "");
        cadena = cadena.replace("}", "");
        cadena = cadena.replace("\"", "");
        
        String[] filas = cadena.split(",");
        String[][] matriz = new String[filas.length][2];

        for (int i = 0; i < matriz.length; i++) {
            matriz[i] = filas[i].split(":");
        }

        return matriz;
    }

    public boolean isDuplicado(Naipe naipe, Naipe raiz) {

        String cadenaCartas = this.recorrido.cadenaInOrder(raiz, "");
        String[] cartas = cadenaCartas.split(",");

        for (int i = 0; i < cartas.length; i++) {
            if (naipe.getId().equals(cartas[i])) {
                return true;
            }
        }
        return false;
    }

    public Naipe getRaiz() {
        return raiz;
    }

    public void setRaiz(Naipe raiz) {
        this.raiz = raiz;
    }

    public Recorrido getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(Recorrido recorrido) {
        this.recorrido = recorrido;
    }

    
}
