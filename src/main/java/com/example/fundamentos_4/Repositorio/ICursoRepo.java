package com.example.fundamentos_4.Repositorio;


import com.example.fundamentos_4.modelo.Curso;

import java.util.List;

public interface ICursoRepo {

    List<Curso> consultartodos();

    Curso consultaruno(int id);

    Curso crear(Curso curso);

    Curso modificar(Curso curso);


    void eliminar(int id);


}
