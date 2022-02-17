
package com.example.libreria.controladores;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class Control {
    


    
    @GetMapping("/")
    public String inicio(){
        return "libreria.html";
    }
    
    


}

