package com.example.libreria.controladores;

import com.example.libreria.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/")
public class AutorController{
    
    
    @Autowired
    private AutorServicio autorServicio;
    
    
    @GetMapping("/addautor")
    public String addautor(){
    return "/addautor"; 
}
    
    
    
    @PostMapping("/saveAutor")
    public String save(@RequestParam String nombre) {
        
        try {
            autorServicio.CrearAutor(nombre, true);
        } catch (Exception e) {
 
        }
        return "/libreria.html";
    }
    
    @GetMapping("/listarautor")
    public String list (Model model){
    
    
    model.addAttribute("titulo", "Lista de Autores");
    model.addAttribute("autor",autorServicio.ListarAutor());
     
    return "listarautor.html";
}

  @GetMapping("/eliminarautor")
    public String eliminar(@RequestParam(required = true) String id){
        
        try {
           autorServicio.BajaAutor(id);
        } catch (Exception e) {
        }
    
    return "redirect:/listarautor";
}
    
}