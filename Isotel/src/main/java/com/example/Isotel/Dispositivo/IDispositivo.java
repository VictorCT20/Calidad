package com.example.Isotel.Dispositivo;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDispositivo extends CrudRepository<Dispositivo,Integer>{
    @Query(value="SELECT * FROM dispositivo "
            + "WHERE dispositivo.modelo LIKE %?1% "
            + "OR dispositivo.descripcion LIKE %?1% "
            + "OR dispositivo.precio LIKE %?1% "
            + "OR dispositivo.stock LIKE %?1% ",nativeQuery=true)
    List<Dispositivo> buscarPorTodo(String dato);
    
    @Query(value="SELECT * FROM dispositivo "
            + "ORDER BY dispositivo.stock ASC",nativeQuery=true)
    List<Dispositivo> orderAsc();

    @Query(value="SELECT * FROM dispositivo "
            + "ORDER BY dispositivo.stock DESC",nativeQuery=true)
    List<Dispositivo> orderDesc();
}
