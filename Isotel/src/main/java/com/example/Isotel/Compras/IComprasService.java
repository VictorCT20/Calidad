package com.example.Isotel.Compras;

import com.example.Isotel.Ventas.Ventas;
import java.util.List;
import java.util.Optional;

public interface IComprasService {
    public List<Compras> Listar();
    public Optional<Compras> ConsultarId(int id);
    public void Guardar(Compras c);
    public void Eliminar(int id);
    public List<Compras> Buscar(String dato);
    public List<Compras> OrdenAscendente();
    public List<Compras> OrdenDescendente();
    public List<Compras> VentasXDispositivo(int id);
    public int CantDispo(int id);
}
