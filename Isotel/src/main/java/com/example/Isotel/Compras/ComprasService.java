package com.example.Isotel.Compras;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprasService implements IComprasService {
    
    @Autowired
    private ICompras data;

    @Override
    public List<Compras> Listar() {
        return (List<Compras>) data.findAll();
    }

    @Override
    public Optional<Compras> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compras c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Compras> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Compras> OrdenAscendente() {
        return data.OrderAsc();
    }

    @Override
    public List<Compras> OrdenDescendente() {
        return data.OrderDesc();
    }

    @Override
    public List<Compras> VentasXDispositivo(int id) {
        return data.VentasPorDispositivo(id);
    }

    @Override
    public int CantDispo(int id) {
        return data.obtenerStock(id);
    }
    
}
