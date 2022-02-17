/*consulta, creación, modificación y dar de baja*/
package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.errores.ErrorServicio;
import com.example.libreria.repositores.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {
    
    @Autowired
    private LibroRepositorio libroRepositorio;
    
    
    
    
    public void CreacionLibro(Long isbn, String titulo,Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados,Integer ejemplaresRestantes,
            Autor autor, Editorial editorial) throws ErrorServicio{
 
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        
        
        Libro libro = new Libro();
        libro.setIbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
       
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
    }
    
    public void ModificarLibro (String id,Long isbn, String titulo,Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados,Integer ejemplaresRestantes,
            Boolean alta, Autor autor, Editorial editorial) throws ErrorServicio{
        
        validar(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autor, editorial);
        
        
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
        Libro libro = respuesta.get();
        libro.setIbn(isbn);
        libro.setTitulo(titulo);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(ejemplaresPrestados);
        libro.setEjemplaresRestantes(ejemplaresRestantes);
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepositorio.save(libro);
        }else{
            throw  new ErrorServicio("no se encontro el libro");
        }
        
      
        
    }
    
    public void BajaLibro(String id) throws ErrorServicio{
            
        Optional<Libro> respuesta = libroRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
           Libro libro = respuesta.get();
        libroRepositorio.delete(libro);
        }else{
            throw new ErrorServicio("no se encontro el libro con ese ID");
        }
    }
    
    
    
    public void ConsultaLibroPorId(String id) throws ErrorServicio{
        
         Optional<Libro> respuesta = libroRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            
        libroRepositorio.buscarPorId(id);
            
        }else{
            throw new ErrorServicio("no se encontro el libro con ese ID");
        }
        
    }
    public List<Libro> listarLibros(){
        return libroRepositorio.findAll();
    }
    

   /* public void ConsultaPorTitulo(String tilulo) throws ErrorServicio{
        
         
        
        if (respuesta.isPresent()) {
            
       
            
        }else{
            throw new ErrorServicio("no se encontro el libro con ese ID");
        }
       
    }    */
    
    private void validar(Long isbn, String titulo,Integer anio,
            Integer ejemplares, Integer ejemplaresPrestados,Integer ejemplaresRestantes,
            Autor autor, Editorial editorial) throws ErrorServicio{
                
        
        if (isbn == null || isbn <=0 ) {
            throw  new ErrorServicio("el isbn es nulo");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new ErrorServicio("el titulo no puede ser nulo");
        }
         if (anio == null) {
            throw new ErrorServicio("el año no puede ser nulo");
        }       
        if (ejemplares == null) {
            throw new ErrorServicio("debe ingresar la cantidad de ejemplares aunque sea 0");
        }
        if (ejemplaresRestantes == null) {
            throw new ErrorServicio("debe ingresar la cantidad de ejemplares aunque sea 0");
        }
        
        if (ejemplaresPrestados == null) {
            throw new ErrorServicio("debe ingresar la cantidad de ejemplares aunque sea 0");
        }
        if (autor == null) {
            throw new ErrorServicio("el autor no puede ser nulo");
        }
        if (editorial == null) {
            throw new ErrorServicio("el editorial no puede ser nulo");
        }
       
    }
    
    
    
   
}
