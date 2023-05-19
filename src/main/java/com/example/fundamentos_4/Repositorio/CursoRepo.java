package com.example.fundamentos_4.Repositorio;

import com.example.fundamentos_4.modelo.Curso;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepo implements ICursoRepo{

    public List<Curso> cursos = new ArrayList<>();
    @Override
    public List<Curso> consultartodos() {
        return cursos;
    }

    @Override
    public Curso consultaruno(int id) {
        Curso curso = this.cursos.stream()
                .filter(c -> c.getIdcurso() == id)
                .findFirst()
                .orElse(null);
        return curso;
    }

    @Override
    public Curso crear(Curso curso) {
        cursos.add(curso);
        return curso;
    }
    @Override
    public Curso modificar(Curso curso) {
        Curso cursoAux = null;
        for (Curso c : this.cursos){
            if (c.getIdcurso()== curso.getIdcurso()){
                cursoAux = c;

            }
        }
        this.cursos.set(this.cursos.indexOf(cursoAux),curso);
        return curso;
    }
    @Override
    public void eliminar(int id)
    { cursos.removeIf(c -> c.getIdcurso() == id);}
}

