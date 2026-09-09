
EA2_Implementacion_de_Diagrama_de_clases
// Implementacion lineas de codigo EA2
package biblioteca.modelo;

public abstract class Libro {

   private int id;
    private String titulo;
    private boolean disponible;

   public Libro(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.disponible = true; // un libro nuevo empieza disponible
    }

   // Comportamiento común: no cambia entre subclases
    public void prestar() {
        this.disponible = false;
    }

   public void devolver() {
        this.disponible = true;
    }

   // Método abstracto: cada subclase decide cómo describirse
    public abstract String getDescripcion();

   // Getters y setters (encapsulamiento)
    public int getId() {
        return id;
    }

   public String getTitulo() {
        return titulo;
    }

   public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   public boolean isDisponible() {
        return disponible;
   }
}


Evidencia de Aprendizaje 1 - Diagrama de Clases UML

## Descripción

Este repositorio contiene la evidencia del trabajo colaborativo del Grupo 4 para el diseño de clases UML de un sistema de biblioteca. En el proyecto se representan las entidades principales del sistema, sus atributos, métodos y las relaciones entre ellas, aplicando los conceptos de programación orientada a objetos.

## Objetivo

Documentar y comunicar la estructura del sistema de biblioteca mediante un diagrama de clases que permita comprender mejor su funcionamiento y organización.

## Documento colaborativo

[Documento colaborativo del Grupo 4 link ](https://docs.google.com/document/d/1J6-XcQ_R1F7sWz5lQIi1x7Zw9vqACkhe/edit?usp=sharing&ouid=110130008599282704927&rtpof=true&sd=true&authuser=1) 

## Archivo relacionado

[Documento PDF de la evidencia](https://github.com/user-attachments/files/31604648/EA1_DiagramaClases_Grupo4.docx.pdf)

## Vídeo explicativo del diagrama

[Vídeo colaborativo del Grupo 4](https://drive.google.com/file/d/1TO_zBSyQlPHSgFwRhU7XZzLJuLUGqfDg/view?usp=sharing&authuser=1)

## Imagen diagrama UML

[Imagen diagrama del Grupo 4](https://drive.google.com/file/d/15Gi6whZ8X5wprKPkupELUdAVHhRyb7m_/view?usp=sharing&authuser=1)
## Codigo Uml
Link del codigo UML
(https://drive.google.com/file/d/1tTy6n-De2Pz5jJeAG7R-W9KLhPpgZF4k/view?usp=sharing&authuser=1)
# EA1_DiagramaClases_Grupo4
Diagrama de Clases UML del Sistema de Biblioteca grupo 4
![alt text](Diagrama-grupo.4.png)
Imagen del diagrama realizado en PlantUML
main
