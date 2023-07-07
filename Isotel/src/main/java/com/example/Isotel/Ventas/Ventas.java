package com.example.Isotel.Ventas;

import com.example.Isotel.Dispositivo.Dispositivo;
import com.example.Isotel.Usuario.Usuario;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ventas")
public class Ventas {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private int cantidad;
    private float total;
    
    //relacion venta-dispositivo
    @ManyToOne()
    @JoinColumn(name="dispositivo_id") //fk
    private Dispositivo dispositivo;
    
    //relacion venta-empleado
    @ManyToOne()
    @JoinColumn(name="usuario_id") //fk
    private Usuario empleado;
    
    
}
