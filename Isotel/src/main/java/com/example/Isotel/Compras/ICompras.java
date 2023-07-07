package com.example.Isotel.Compras;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompras extends CrudRepository<Compras,Integer> {
    @Query(value="SELECT * FROM compras "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN usuario ON ventas.usuario_id = usuario_id "
            + "WHERE ventas.nombre LIKE %?1% "
            + "OR compras.usuario.apellidos LIKE %?1% "
            + "OR compras.usuario.correo LIKE %?1% "
            + "OR compras.usuario.telefono LIKE %?1% "
            + "OR dispositivo.modelo LIKE %?1% ",nativeQuery=true)
    List<Compras> buscarPorTodo(String dato);
    
    @Query(value="SELECT * FROM compras "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN empleado ON ventas.empleado_id = empleado_id "
            + "ORDER BY compras.usuario.nombre ASC",nativeQuery=true)
    List<Compras> OrderAsc();
    
    @Query(value="SELECT * FROM compras "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN empleado ON ventas.empleado_id = empleado_id "
            + "ORDER BY compras.usuario.nombre DESC",nativeQuery=true)
    List<Compras> OrderDesc();
    /*
    @Query(value="SELECT * FROM compras "
            + "INNER JOIN empleado ON ventas.empleado_id = empleado.id "
            + "WHERE ventas.empleado_id = ?1",nativeQuery=true)
    List<Compras> VentasPorEmpleado(int id);*/
    
    @Query(value="SELECT * FROM compras "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo.id "
            + "WHERE compras.dispositivo_id = ?1",nativeQuery=true)
    List<Compras> VentasPorDispositivo(int id);
    
    @Query(value = "SELECT d.stock FROM Dispositivo d WHERE d.id = ?1")
    int obtenerStock(int id);
    
    
}
