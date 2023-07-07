package com.example.Isotel.Usuario;

import java.sql.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUsuario extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuario "
            + "WHERE nombre LIKE %?1% "
            + "OR apellidos LIKE %?1% "
            + "OR documento LIKE %?1% "
            + "OR telefono LIKE %?1% "
            + "OR correo LIKE %?1% "
            + "OR direccion LIKE %?1% ", nativeQuery = true)
    List<Usuario> buscarPorTodo(String dato);

    @Query(value = "SELECT * FROM usuario "
            + "ORDER BY nombre ASC", nativeQuery = true)
    List<Usuario> orderAsc();

    @Query(value = "SELECT * FROM usuario "
            + "ORDER BY nombre DESC", nativeQuery = true)
    List<Usuario> orderDesc();

    @Query(value = "SELECT * FROM usuario "
            + "WHERE admin = true", nativeQuery = true)
    List<Usuario> findAllByAdmin(boolean admin);

    @Query(value = "SELECT * FROM usuario "
            + "WHERE admin = false", nativeQuery = true)
    List<Usuario> findAllNoByAdmin(boolean admin);

    @Query(value = "SELECT * FROM usuario "
            + "WHERE username = ?1 ", nativeQuery = true)
    Usuario findUser(String dato);

    @Query(value = "SELECT username FROM usuario ", nativeQuery = true)
    List<String> findUserName();

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario "
            + "SET nombre = :nombre, apellidos = :apellidos, documento = :documento, "
            + "telefono = :telefono, correo = :correo, direccion = :direccion, "
            + "nacimiento = :nacimiento, username = :username, password = :password,"
            + "admin = :admin "
            + "WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") int id, @Param("nombre") String nombre,
            @Param("apellidos") String apellidos, @Param("documento") String documento,
            @Param("telefono") String telefono, @Param("correo") String correo,
            @Param("direccion") String direccion, @Param("nacimiento") Date nacimiento,
            @Param("username") String username,@Param("password") String password,
            @Param("admin") boolean admin);

}
