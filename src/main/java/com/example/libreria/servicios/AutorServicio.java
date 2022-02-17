
package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositores.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {
    
    
    @Autowired AutorRepositorio autorRepositorio;
    
  public void CrearAutor( String nombre, Boolean alta) throws ErrorServicio{
        
        validar(nombre, alta);
        
       Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(alta);
        
        autorRepositorio.save(autor);
    }
    
    public void ModificarAutor(String id, String nombre, Boolean alta)throws ErrorServicio{
        
        validar(nombre, alta);
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            autor.setAlta(alta);
            
            autorRepositorio.save(autor);
            
        }else{
            throw new ErrorServicio("no se encontro autor con ese id");
        }
    }
    
     public void BajaAutor(String id)throws ErrorServicio{
        
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Autor autor = respuesta.get();

            autorRepositorio.delete(autor);
            
        }else{
            throw new ErrorServicio("no se encontro autor con ese id");
        }
        
        
    }
    
    public void ConsultaAutorPorId(String id) throws ErrorServicio{
        
       Optional<Autor> respuesta = autorRepositorio.findById(id);
               
        
        if (respuesta.isPresent()) {
            autorRepositorio.BuscarAutorPorId(id);
        }else{
            throw new ErrorServicio("no se encontro Autor con esa ID");
        }
    }
    public List<Autor>ListarAutor(){
        return autorRepositorio.findAll();
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
