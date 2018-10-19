/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idat.edu.pe.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import idat.edu.pe.bean.Persona;
import idat.edu.pe.pojo.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonException;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LuisAngel
 */

@RestController
@RequestMapping("/pagos")
public class ServiceController {
    

    @RequestMapping(value="/pago", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN)
    public String pago(){
       return "Mi primer servicio SPRING";     
    }   
    
    @RequestMapping(value="/CalculoIMC/{talla}/{peso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public String CalculoIMC(@PathVariable Double talla, @PathVariable Double peso){
                talla = talla/100;        
        Double ResultadoIMC = peso / (talla * talla);
        String Clasificacion ="";
        if(ResultadoIMC < 18.5){
            Clasificacion = "Bajo de peso.";
        }else if(ResultadoIMC < 24.9){
            Clasificacion = "Peso normal";
        }else if(ResultadoIMC < 29.9){
            Clasificacion = "Sobre peso";
        }else if(ResultadoIMC < 34.5){
            Clasificacion = "Obsidad grado 1";
        }else if(ResultadoIMC < 39.9){
            Clasificacion = "Obsidad grado 2";
        }else if(ResultadoIMC > 40){
            Clasificacion = "Obsidad grado 3";
        }
       return respuestaIMC("Indice de masa corporal", String.valueOf(ResultadoIMC), Clasificacion);     
    }
    
    
    @RequestMapping(value="/listPersonas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String getPersona(){
        List<Persona> lista = new ArrayList<Persona>();
        Persona p = new Persona();
        p.setNombre("angel");
        p.setApellido("Pizarro");
        lista.add(p);
        Persona p1 = new Persona();
        p1.setNombre("gema");
        p1.setApellido("Vilcabana");
        lista.add(p1);        
       return new Gson().toJson(lista);     
    }   
    
        @RequestMapping(value="/listEmpleados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody String getEmpleado(){
        List<Empleado> lista = new ArrayList<Empleado>();
        Empleado e = new Empleado("maria", 35);
        lista.add(e);        
        Empleado e2 = new Empleado("maria", 35);
        lista.add(e2);        
       return new Gson().toJson(lista);     
    } 
    
    
    private String respuestaIMC(String operation, String parameter, String result) {
        JsonObject obj = new JsonObject();
        try {
            obj.addProperty("operation", operation);
            obj.addProperty("parameter", parameter);
            obj.addProperty("result", result);            
            return obj.toString();
        } catch (JsonException ex) {
            System.err.println("JSONException: " + ex.getMessage());
        }
        return "";
    }
}
