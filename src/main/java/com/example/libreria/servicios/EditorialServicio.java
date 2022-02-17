/*consulta, creación, modificación y dar de baja*/
package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositores.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EditorialServicio {
    
    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    public void CrearEditorial( String nombre, Boolean alta) throws ErrorServicio{
     
        validar(nombre, alta);
        
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(alta);
        
        editorialRepositorio.save(editorial);
    }
    
    public void ModificarEditorial(String id, String nombre, Boolean alta)throws ErrorServicio{
        
        validar(nombre, alta);
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
            
            editorialRepositorio.save(editorial);
            
        }else{
            throw new ErrorServicio("no se encontro editorial con ese id");
        }
    }
    
     public void BajaEditorial(String id)throws ErrorServicio{
        
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Editorial editorial = respuesta.get();

            editorialRepositorio.delete(editorial);
            
        }else{
            throw new ErrorServicio("no se encontro editorial con ese id");
        }
        
        
    }
    
    public void findById(String id) throws ErrorServicio{
        
       Optional<Editorial> respuesta = editorialRepositorio.findById(id);
               
        
        if (respuesta.isPresent()) {
            editorialRepositorio.BuscarEditorialPorId(id);
        }else{
            throw new ErrorServicio("no se encontro ediotirla con esa ID");
        }
    }
        public List<Editorial>ListarEditoriales(){
        return editorialRepositorio.findAll();
    }
    
    private void validar(String nombre, Boolean alta) throws ErrorServicio{
         
        if (nombre == null || nombre.isEmpty()) {
            throw new ErrorServicio("en nombre no puede ser nulo o estar vacio");
        }
        if (alta == null) {
            throw new ErrorServicio("el alta debe tener alguna informacion");
        }
        
    }
    

}
