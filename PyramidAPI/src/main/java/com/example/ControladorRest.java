
package com.example;

import AVL.Naipe;
import AVL.PiramideAVL;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ControladorRest {

    private PiramideAVL piramide;
    
    @PostMapping("Game/start")
    public ResponseEntity<?> iniciar(@RequestBody String json) {
        
        piramide = new PiramideAVL(); // creacion de nueva Piramide
        String[][] cartasInicio = piramide.getMatriz(json);
        
        for (int i = 0; i < cartasInicio.length; i++) {

            // La clave del naipe no es un numero
            if (!esNumero(cartasInicio[i][0])) {
                return new ResponseEntity<>("Error: La solicitud es incorrecta", HttpStatus.BAD_REQUEST);
            }

            Naipe naipe = new Naipe(cartasInicio[i][1]);
            
            // Verifica si el naipe es valido
            if (!naipe.isValido()) {
                return new ResponseEntity<>("Error: " + naipe.getId() +" peso: "+naipe.getPeso()+" simbolo: "+naipe.getSimbolo() + " no es una carta valida", HttpStatus.BAD_REQUEST);
            }
            // Rectifica si el naipe es duplicado
            if (piramide.isDuplicado(naipe, piramide.getRaiz())) {
                return new ResponseEntity<>("Error: " + naipe.getId() +" peso: "+naipe.getPeso()+" simbolo: "+naipe.getSimbolo() + " es una carta duplicada", HttpStatus.NOT_ACCEPTABLE);    
            }
            // Inserta el naipe
            piramide.insertarNaipe(naipe);
        }
        return new ResponseEntity<>("Las cartas se insertaron correctamente", HttpStatus.OK);
    }
    
    @PostMapping("Game/add")
    public ResponseEntity<?> insertar(@RequestBody String json) {
        
        String[][] cartasInicio = piramide.getMatriz(json);
        
        // La clave de la solicitud es distinta de "insert" o tiene más de una solicitud de insercion
        if (cartasInicio.length>1 || !cartasInicio[0][0].equals("insert")) {
            return new ResponseEntity<>("Error: La solicitud es incorrecta", HttpStatus.BAD_REQUEST);
        }

        Naipe naipe = new Naipe(cartasInicio[0][1]);

        // Validacion de naipe
        if (!naipe.isValido()) {
            return new ResponseEntity<>("Error: " + naipe.getId() + " no es una carta valida", HttpStatus.BAD_REQUEST);
        }
        // Rectifica si el naipe es duplicado
        if (piramide.isDuplicado(naipe, piramide.getRaiz())) {
            return new ResponseEntity<>("Error: " + naipe.getId() + " es una carta duplicada", HttpStatus.NOT_ACCEPTABLE);    
        }
        // Inserta el naipe
        piramide.insertarNaipe(naipe);
        
        return new ResponseEntity<>("La carta se inserto correctamente", HttpStatus.OK);
    }
    
    @DeleteMapping("Game/delete")
    public ResponseEntity<?> eliminar(@RequestBody String json) {
        
        String[][] cartasDelete = piramide.getMatriz(json);

        //peticion invalida, más de 3 cartas en la eliminacion
        if (cartasDelete.length>2) {
            return new ResponseEntity<>("Error: La solicitud es incorrecta", HttpStatus.BAD_REQUEST);
        }

        Naipe[] naipes = new Naipe[cartasDelete.length];

        for (int i = 0; i < cartasDelete.length; i++) {
            //La solicitud no contiene la palabra "delete"
            String solicitud = cartasDelete[i][0].substring(0, 6);
            if (!solicitud.equals("delete")) {
                return new ResponseEntity<>("Error: La solicitud es incorrecta", HttpStatus.BAD_REQUEST);
            }

            naipes[i] = new Naipe(cartasDelete[i][1]);
            // Validacion de naipe
            if (!naipes[i].isValido()) {
                return new ResponseEntity<>("Error: " + naipes[i].getId() + " no es una carta valida", HttpStatus.BAD_REQUEST);
            }
            // El naipe no esta en la piramide
            if (!piramide.isDuplicado(naipes[i], piramide.getRaiz())) {
                return new ResponseEntity<>("Error: " + naipes[i].getId() + " no se encuentra en la piramide", HttpStatus.NOT_FOUND);
            } 
            // La carta no es un nodo hoja
            if (!piramide.isHojaPiramide(naipes[i], piramide.getRaiz())) {
                return new ResponseEntity<>("Error: No se pueden eliminar las cartas que estan bloqueadas", HttpStatus.NOT_ACCEPTABLE);
            }
        }

        // Eliminacion de una carta
        if (naipes.length==1) {
            if (naipes[0].getPeso() == 13) {
                piramide.eliminarNaipe(naipes[0]);
            } else {
                // la carta no tiene el valor de 13
                return new ResponseEntity<>("Error: El valor de la carta no es 13", HttpStatus.NOT_ACCEPTABLE);
            }
        // Eliminacion de dos cartas
        } else if (naipes.length==2) {
            if (naipes[0].getPeso() + naipes[1].getPeso() == 13) {
                piramide.eliminarNaipe(naipes[0]);
                piramide.eliminarNaipe(naipes[1]);
            } else {
                // Las cartas no suman 13
                return new ResponseEntity<>("Error: Los valores de las cartas no suman 13", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>("La eliminacion se realizo exitosamente", HttpStatus.OK);
    }
    
    @GetMapping("Game/get-level")
    public ResponseEntity<?> getNivel(@RequestParam (value ="level", defaultValue = "1") int nivel) {

        String nodosNivel = piramide.getRecorrido().naipesNivel(nivel);
        // Rectificar que el nivel no sea menor que 0 ni mayor que el nivel de la piramide
        if (nodosNivel.isBlank() || nodosNivel.isBlank()) {
            return new ResponseEntity<>("El nivel especificado no es correcto", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(nodosNivel, HttpStatus.OK);
    }
    
    @GetMapping("Game/avltree")
    public ResponseEntity<?> obtenerArbol(@RequestParam (value ="transversal") String orden) {
    
        String cadena = "El orden solicitado no es correcto";

        if (piramide.getRaiz()==null) {
            return new ResponseEntity<>("Aun no hay cartas en la piramide", HttpStatus.OK);
        }
        
        switch(orden) {
            case "inOrder":
                cadena = piramide.getRecorrido().inOrder(piramide.getRaiz());
                break;
            case "preOrder":
                cadena = piramide.getRecorrido().preOrder(piramide.getRaiz());
                break;
            case "postOrder":
                cadena = piramide.getRecorrido().postOrder(piramide.getRaiz());
                break;
            default:
                break;
        }
        return new ResponseEntity<>(cadena, HttpStatus.OK);
    }

    @GetMapping("Game/status-avltree")
    public int getEstado() {
        return 0;
    }

    private boolean esNumero(String num) {

        char[] digs = num.toCharArray();
        for (char c : digs) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
