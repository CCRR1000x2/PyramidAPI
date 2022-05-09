
package com.example;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ControladorRest {
    
    @GetMapping("Game")
    public String hola() {
        return "Hola Mundo Spring Boot";
    }
    
    @PostMapping("Game/start")
    public String iniciar(JSONObject json) {
        return "Hola Mundo Spring Boot";
    }
    
    @PostMapping("Game/add")
    public void insertar(JSONObject json) {
        
    }
    
    @DeleteMapping("Game/delete")
    public void eliminar(JSONObject json) {
        
    }
    
    @GetMapping("Game/status-avltree")
    public int getEstado() {
        return 0;
    }

    @GetMapping("Game/get-level?level={no.level}")
    public int getNivel() {
        return 0;
    }
    
    
}
