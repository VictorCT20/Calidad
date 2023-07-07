package com.example.Isotel.Dispositivo;

import java.util.List;
import java.util.Optional;

public interface IDispositivoService {
    public List<Dispositivo> Listar();
    public Optional<Dispositivo> ConsultarId(int id);
    public void Guardar(Dispositivo d);
    public void Eliminar(int id);
    public List<Dispositivo> Buscar(String dato);
    public List<Dispositivo> OrdenAscendente();
    public List<Dispositivo> OrdenDescendente();
}
