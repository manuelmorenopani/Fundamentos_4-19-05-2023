package com.example.fundamentos_4.Servicio;

import com.example.fundamentos_4.Repositorio.ICursoRepo;
import com.example.fundamentos_4.modelo.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CursosServicio implements ICursosServicio{

    @Autowired
    ICursoRepo repo;


    @Override
    public List<Curso> consultartodos() {
        return repo.consultartodos();
    }

    @Override
    public Curso consultaruno(int id) {
        return repo.consultaruno(id);
    }

    @Override
    public Curso crear(Curso curso) {
        return repo.crear(curso);
    }

    @Override
    public Curso modificar(Curso curso) {
        return repo.modificar(curso);
    }

    @Override
    public void eliminar(int id) {
        repo.eliminar(id);

    }
}
