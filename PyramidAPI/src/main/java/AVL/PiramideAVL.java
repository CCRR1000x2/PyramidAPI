package AVL;

public class PiramideAVL {
    
    private Naipe raiz;

    public PiramideAVL(Naipe raiz) {
        this.setRaiz(null);
    }
    
    //Insertar nodo
    
    //Buscar nodo
    public Naipe buscarCarta(String carta, Naipe nodo) {
        
        Naipe nuevaCarta = new Naipe(carta);

        if (nodo == null) {
            return null;
        } else if(nuevaCarta.valor == nodo.valor) {
            return nodo;
        } else if(nodo.valor < nuevaCarta.valor) {
            return buscarCarta(carta, nodo.hijoDerecho);
        } else {
            return buscarCarta(carta, nodo.hijoIzquierdo);
        }
    }
    
    public int obtenerFE(Naipe nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return nodo.fe;
        }
    }
    
    //Rotación Simple Izquierda
    public Naipe rotacionIzquierda(Naipe c) {
        Naipe auxiliar = c.hijoIzquierdo;
        c.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    
    //Rotación Simple Derecha
    public Naipe rotacionDerecha(Naipe c) {
        Naipe auxiliar = c.hijoDerecho;
        c.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo), obtenerFE(c.hijoDerecho))+1;
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo), obtenerFE(auxiliar.hijoDerecho))+1;
        return auxiliar;
    }
    
    //Rotacion Doble a la Derecha
    public Naipe rotacionDobleIzquierda(Naipe c) {
        Naipe temporal;
        c.hijoIzquierdo = rotacionDerecha(c.hijoIzquierdo);
        temporal = rotacionIzquierda(c);
        return temporal;
    }

    // Rotación Doble a la Izquierda
    public Naipe rotacionDobleDerecha(Naipe c) {
        Naipe temporal;
        c.hijoDerecho = rotacionIzquierda(c.hijoDerecho);
        temporal = rotacionDerecha(c);
        return temporal;
    }

    public Naipe insertar(Naipe nuevo, Naipe subAr) {
        Naipe nuevoPadre = subAr;
        if (nuevo.valor < subAr.valor) {
            if (subAr.hijoIzquierdo == null) {
                subAr.hijoIzquierdo = nuevo;
            } else {
                subAr.hijoIzquierdo = insertar(nuevo, subAr.hijoIzquierdo);
                if ((obtenerFE(subAr.hijoIzquierdo) - obtenerFE(subAr.hijoDerecho) == 2)) {
                    if (nuevo.valor < subAr.hijoIzquierdo.valor) {
                        nuevoPadre = rotacionIzquierda(subAr);
                    } else {
                        nuevoPadre = rotacionDobleIzquierda(subAr);
                    }
                }
            }

        } else if (nuevo.valor > subAr.valor) {
            if (subAr.hijoDerecho == null) {
                subAr.hijoDerecho = nuevo;
            } else {
                subAr.hijoDerecho = insertar(nuevo, subAr.hijoDerecho);
                if ((obtenerFE(subAr.hijoDerecho)-obtenerFE(subAr.hijoIzquierdo)==2)) {
                    if (nuevo.valor > subAr.hijoDerecho.valor) {
                        nuevoPadre = rotacionDerecha(subAr);
                    } else {
                        nuevoPadre = rotacionDobleDerecha(subAr);
                    }
                }
            }

        } else {
            System.out.println("Nodo Duplicado");
        }

        // Actualizando la altura
        if ((subAr.hijoIzquierdo == null) && (subAr.hijoDerecho != null)) {
            subAr.fe = subAr.hijoDerecho.fe+1;
        } else if ((subAr.hijoDerecho == null) && (subAr.hijoIzquierdo != null)) {
            subAr.fe = subAr.hijoIzquierdo.fe + 1;
        } else {
            subAr.fe = Math.max(obtenerFE(subAr.hijoIzquierdo), obtenerFE(subAr.hijoDerecho)) + 1;
        }
    
        return nuevoPadre;
    }

    //Insertar
    public void insertar1(String d) {
        Naipe nuevo = new Naipe(d);
        if (raiz==null) {
            raiz = nuevo;
        } else {
            raiz = insertar(nuevo, raiz);
        }
    }

    //Recorridos
    public void inOrden(Naipe r) {
        if (r != null) {
            inOrden(r.hijoIzquierdo);
            System.out.println(r.valor + ", ");
            inOrden(r.hijoDerecho);
        }
    }

    public void preOrden(Naipe r) {
        if (r != null) {
            System.out.println(r.valor + ", ");
            preOrden(r.hijoIzquierdo);
            preOrden(r.hijoDerecho);
        }
    }

    public void postOrden(Naipe r) {
        if (r != null) {
            preOrden(r.hijoIzquierdo);
            preOrden(r.hijoDerecho);
            System.out.println(r.valor + ", ");
        }
    }

    public Naipe getRaiz() {
        return raiz;
    }

    public void setRaiz(Naipe raiz) {
        this.raiz = raiz;
    }
}
