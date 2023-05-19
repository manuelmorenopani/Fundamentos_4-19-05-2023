package com.example.fundamentos_4.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Curso {
    int idcurso;
    String nombre;
    int duracion;
    int idprofesor;


}
