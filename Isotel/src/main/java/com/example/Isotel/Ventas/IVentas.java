package com.example.Isotel.Ventas;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentas extends CrudRepository<Ventas,Integer> {
    @Query(value="SELECT * FROM ventas "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN usuario ON ventas.usuario_id = usuario_id "
            + "WHERE ventas.nombre LIKE %?1% "
            + "OR ventas.apellidos LIKE %?1% "
            + "OR ventas.correo LIKE %?1% "
            + "OR ventas.telefono LIKE %?1% "
            + "OR dispositivo.modelo LIKE %?1% "
            + "OR usuario.nombre LIKE %?1% ",nativeQuery=true)
    List<Ventas> buscarPorTodo(String dato);
    
    @Query(value="SELECT * FROM ventas "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN empleado ON ventas.usuario_id = usuario_id "
            + "ORDER BY ventas.nombre ASC",nativeQuery=true)
    List<Ventas> OrderAsc();
    
    @Query(value="SELECT * FROM ventas "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo_id "
            + "INNER JOIN empleado ON ventas.usuario_id = usuario_id "
            + "ORDER BY ventas.nombre DESC",nativeQuery=true)
    List<Ventas> OrderDesc();
    
    @Query(value="SELECT * FROM ventas "
            + "INNER JOIN usuario ON ventas.usuario_id = usuario_id "
            + "WHERE ventas.usuario_id = :id",nativeQuery=true)
    List<Ventas> VentasPorEmpleado(@Param("id") int id);
    
    @Query(value="SELECT * FROM ventas "
            + "INNER JOIN dispositivo ON ventas.dispositivo_id = dispositivo.id "
            + "WHERE ventas.dispositivo_id = ?1",nativeQuery=true)
    List<Ventas> VentasPorDispositivo(int id);
}
