package com.example.Isotel.Dispositivo;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="dispositivo")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String modelo;
    private String descripcion;
    private float precio;
    private int stock;
}
