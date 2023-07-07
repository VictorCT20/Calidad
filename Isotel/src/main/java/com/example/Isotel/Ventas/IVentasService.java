package com.example.Isotel.Ventas;

import java.util.List;
import java.util.Optional;

public interface IVentasService {
    public List<Ventas> Listar();
    public Optional<Ventas> ConsultarId(int id);
    public void Guardar(Ventas v);
    public void Eliminar(int id);
    public List<Ventas> Buscar(String dato);
    public List<Ventas> OrdenAscendente();
    public List<Ventas> OrdenDescendente();
    public List<Ventas> VentasXEmpleado(int id);
    public List<Ventas> VentasXDispositivo(int id);
}
