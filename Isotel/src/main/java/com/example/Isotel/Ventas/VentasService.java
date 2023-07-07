package com.example.Isotel.Ventas;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentasService implements IVentasService {
    
    @Autowired
    private IVentas data;
    
    @Override
    public List<Ventas> Listar() {
       return (List<Ventas>) data.findAll();
    }

    @Override
    public Optional<Ventas> ConsultarId(int id) {
       return data.findById(id);
    }

    @Override
    public void Guardar(Ventas v) {
        data.save(v);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Ventas> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Ventas> OrdenAscendente() {
        return data.OrderAsc();
    }

    @Override
    public List<Ventas> OrdenDescendente() {
        return data.OrderDesc();
    }
    
    @Override
    public List<Ventas> VentasXEmpleado(int id) {
        return data.VentasPorEmpleado(id);
    }
    
    @Override
    public List<Ventas> VentasXDispositivo(int id) {
        return data.VentasPorDispositivo(id);
    }
}
