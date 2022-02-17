package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.servicios.EditorialServicio;
import com.example.libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;





@Controller
@RequestMapping("/")
public class LibroController{



@Autowired
    private LibroServicio libroServicio;
@Autowired
    private EditorialServicio editorialServicio;
@Autowired
private AutorServicio autorServicio;

    @GetMapping("/addlibro")
    public String addeditorial(Model model) {
       
   model.addAttribute("autor",autorServicio.ListarAutor());
   
    model.addAttribute("editorial",editorialServicio.ListarEditoriales());
        
        
    
    
        return "addlibro.html";
    }
    
    

@PostMapping("/savelibro")
public String save( ModelMap modelo,  Long isbn,String titulo, Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes,
            Autor autor, Editorial editorial) {
    
    try {
        libroServicio.CreacionLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        modelo.put("exito", "registro exitoso");
    } catch (Exception e) {
        modelo.put("error", "Falto algun dato");
    }
   
    return "libreria.html";
}

@GetMapping("/listarlibros")
public String list (Model model){
    
    
    model.addAttribute("titulo", "Lista de libros");
    model.addAttribute("libros",libroServicio.listarLibros());
     
    return "listarlibros.html";
}

@GetMapping("/eliminarlibro")
public String eliminarlibro(@RequestParam(required = true)String id){
            try {
           libroServicio.BajaLibro(id);

        } catch (Exception e) {
        }
        return "redirect:/listarlibrosl";
    
}




}