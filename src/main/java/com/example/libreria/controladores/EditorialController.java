package com.example.libreria.controladores;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.servicios.EditorialServicio;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class EditorialController {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/addeditorial")
    public String addeditorial() {
        
        return "addeditorial.html";
    }
    
    
 /*  @GetMapping("/editeditorial")
    public String editeditorial(Model model,@RequestParam(required = false) String id) {
        Optional<Editorial> optional =  editorialServicio.findById(id);
        

        model.addAttribute("ciudad", Optional)
        return "editeditorial.html";
    }
    */
    
    @PostMapping("/saveEditorial")
    public String save(ModelMap modelo, @RequestParam String nombre) {

        try {
          

                editorialServicio.CrearEditorial(nombre, true);
            

            modelo.put("exito", "registro exitoso");
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
        }

        return "libreria.html";
    }

    @GetMapping("/listareditorial")
    public String list(Model model) {
        model.addAttribute("titulo", "Lista de Editoriales");
        model.addAttribute("editorial", editorialServicio.ListarEditoriales());

        return "listareditorial.html";
    }

    @GetMapping("/eliminareditorial")
    public String eliminar(@RequestParam(required = true) String id) {

        try {
            editorialServicio.BajaEditorial(id);

        } catch (Exception e) {
        }
        return "redirect:/listareditorial";
    }

    
    
    
     @PostMapping("/editeeditorial")
    public String edit(ModelMap modelo, @RequestParam String nombre, @RequestParam String id) {

        try {
          

                editorialServicio.ModificarEditorial(id, nombre, true);
            

            modelo.put("exito", "registro exitoso");
        } catch (Exception e) {
            modelo.put("error", "Falto algun dato");
        }

        return "libreria.html";
    }


}
