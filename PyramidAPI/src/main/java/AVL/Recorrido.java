package AVL;

public class Recorrido {
    
    PiramideAVL piramideAVL;
    
    public Recorrido(PiramideAVL piramideAVL) {
        this.piramideAVL = piramideAVL;
    }

    // RECORRIDO INORDER
    public String inOrder(Naipe naipe) {
    
        String cadena = cadenaInOrder(naipe, "");
        cadena = formatoJSON(cadena);
        return cadena;
    }

    public String cadenaInOrder(Naipe naipe, String cadena) {
        
        if (naipe != null) {
            cadena = cadenaInOrder(naipe.getHijoIzquierdo(), cadena);
            cadena += naipe.getId() + ",";
            cadena = cadenaInOrder(naipe.getHijoDerecho(), cadena);
        } 
        return cadena;
    }

    // RECORRIDO PREORDER
    public String preOrder(Naipe naipe) {

        String cadena = cadenaPreOrder(naipe, "");
        cadena = formatoJSON(cadena);
        return cadena;
    }

    public String cadenaPreOrder(Naipe naipe, String cadena) {
        
        if (naipe != null) {
            cadena += naipe.getId() + ",";
            cadena = cadenaPreOrder(naipe.getHijoIzquierdo(), cadena);
            cadena = cadenaPreOrder(naipe.getHijoDerecho(), cadena);
        } 
        return cadena;
    }

    // RECORRIDO POSTORDER
    public String postOrder(Naipe naipe) {

        String cadena = cadenaPostOrder(naipe, "");
        cadena = formatoJSON(cadena);
        return cadena;
    }

    public String cadenaPostOrder(Naipe naipe, String cadena) {
        
        if (naipe != null) {
            cadena = cadenaPostOrder(naipe.getHijoIzquierdo(), cadena);
            cadena = cadenaPostOrder(naipe.getHijoDerecho(), cadena);
            cadena += naipe.getId() + ",";
        } 
        return cadena;
    }

    // Le da un formato JSON a la cadena que recibe
    public String formatoJSON(String cadenaOrden) {

        String json = "{";
        String[] cartas = cadenaOrden.split(",");

        for (int i = 0; i < cartas.length; i++) {
            json += "\"" + i + "\": \"" + cartas[i] + "\",";
        }
        json = json.substring(0, json.length()-1) + "}";
        return json;
    }

    // Devuelve todo los naipes que se encuentran en el mismo nivel
    public String naipesNivel(int nivel) {
        int nivelRaiz = 0;
        if (nivel < nivelRaiz) {
            // Nivel no valido
            return "";
        } else if (nivel == nivelRaiz) {
            // Devuelve solo la raiz
            return formatoJSON(piramideAVL.getRaiz().getId());
        } else {
            String cadena = addNodosNivel(nivelRaiz, nivel, "", piramideAVL.getRaiz());
            if (cadena.isBlank()) {
                // Nivel no valido
                return "";
            } else {
                cadena = formatoJSON(cadena);
                return cadena;
            }
            
        }
    }

    private String addNodosNivel(int nivelTemp, int nivelFinal, String cadena, Naipe naipe) {
        
        if (naipe != null) {
            if (nivelFinal-nivelTemp==1) {
                if (naipe.getHijoIzquierdo()!=null) {
                    cadena += naipe.getHijoIzquierdo().getId() + ",";
                }
                if (naipe.getHijoDerecho()!=null) {
                    cadena += naipe.getHijoDerecho().getId() + ",";
                }
            } else {
                nivelTemp++;
                cadena = addNodosNivel(nivelTemp, nivelFinal, cadena, naipe.getHijoIzquierdo());
                cadena = addNodosNivel(nivelTemp, nivelFinal, cadena, naipe.getHijoDerecho());
            }
        }
        return cadena;
    }

    
}
