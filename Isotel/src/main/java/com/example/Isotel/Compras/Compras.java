package com.example.Isotel.Compras;

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
@Table(name="compras")
public class Compras {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne()
    @JoinColumn(name="usuario_id") //fk
    private Usuario usuario;
    
    @ManyToOne()
    @JoinColumn(name="dispositivo_id") //fk
    private Dispositivo dispositivo;
    
    private int cantidad;
    private float total;
   
    
    
    
}
