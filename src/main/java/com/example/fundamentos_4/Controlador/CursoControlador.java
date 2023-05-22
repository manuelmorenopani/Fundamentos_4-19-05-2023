package com.example.fundamentos_4.Controlador;

import com.example.fundamentos_4.Exceptions.ExcepcionNoEncontradoModelo;
import com.example.fundamentos_4.Servicio.ICursosServicio;
import com.example.fundamentos_4.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcAffordanceBuilderDsl;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("/hateos/{id}")
    public EntityModel<Curso> consultarUnoH(@PathVariable("id")int id ) throws Exception{
        Curso resultadoBBDD = servicio.consultaruno(id);
        if(resultadoBBDD == null){
            throw new ExcepcionNoEncontradoModelo("Id no encontrado" + id);
        }
        WebMvcLinkBuilder link1= linkTo(methodOn(this.getClass()).consultaruno(id));
        return EntityModel.of(resultadoBBDD).add(link1.withRel("curso-link"));
    }


    @PostMapping
    public ResponseEntity<Curso>crear(@RequestBody Curso curso) throws Exception{
        System.out.println(curso.toString());
        Curso odj = servicio.crear(curso);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(odj.getIdcurso()).toUri();
        return ResponseEntity.created(location).build();
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
