package com.example.Isotel.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> Listar();
    public List<Usuario> ListarEm();
    public List<Usuario> ListarNoEm();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario u);
    public void Actualizar(Usuario u, int id);
    public void Eliminar(int id);
    public List<Usuario> Buscar(String dato);
    public Usuario BuscarA(String dato);
    public List<Usuario> OrdenAscendente();
    public List<Usuario> OrdenDescendente();
    public List<String> ComproUsername();
}
