package com.example.Isotel.Usuario;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuario data;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }
    
    @Override
    public List<Usuario> ListarEm() {
        return data.findAllByAdmin(true);
    }
    
    @Override
    public List<Usuario> ListarNoEm() {
        return data.findAllNoByAdmin(true);
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override 
    public List<Usuario> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Usuario> OrdenAscendente() {
        return data.orderAsc();
    }

    @Override
    public List<Usuario> OrdenDescendente() {
        return data.orderDesc();
    }

    @Override
    public Usuario BuscarA(String dato) {
        return data.findUser(dato);
    }

    @Override
    public List<String> ComproUsername() {
        return data.findUserName();
    }

    @Override
    public void Actualizar(Usuario u, int id) {
        String nombre = u.getNombre();
        String apellidos = u.getApellidos();
        Date nacimiento = u.getNacimiento();
        String documento = u.getDocumento();
        String telefono = u.getTelefono();
        String direccion = u.getDireccion();
        String correo = u.getCorreo();
        String username = u.getUsername();
        String password = u.getPassword();
        boolean admin = u.isAdmin();
        data.actualizarUsuario(id, nombre, apellidos, documento, telefono, correo, direccion, nacimiento, username, password, admin);
    }
}
