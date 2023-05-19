package com.example.fundamentos_4.Controlador;

import com.example.fundamentos_4.Exceptions.ExcepcionNoEncontradoModelo;
import com.example.fundamentos_4.Servicio.ICursosServicio;
import com.example.fundamentos_4.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoControlador {
    @Autowired
    private ICursosServicio servicio;
    @GetMapping
    public ResponseEntity<List<Curso>> consultartodos(){
        return new ResponseEntity<>(servicio.consultartodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Curso> consultaruno(@PathVariable("id")int id ){
        Curso resultadoBBDD = servicio.consultaruno(id);
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + id);
        }
        return new ResponseEntity(servicio.consultaruno(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Curso>crear(@RequestBody Curso curso){
        System.out.println(curso.toString());
        return new ResponseEntity<>(servicio.crear(curso), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<Curso>modificar(@RequestBody Curso curso){
        Curso resultadoBBDD = servicio.consultaruno(curso.getIdcurso());
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + curso.getIdcurso());
        }
        return new ResponseEntity<>(servicio.modificar(curso), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") int id){
        Curso resultadoBBDD = servicio.consultaruno(id);
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("ID no encontrado" + id);
        }
        servicio.eliminar(id);
    }
}
