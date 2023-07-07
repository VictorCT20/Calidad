package com.example.Isotel.Dispositivo;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService implements IDispositivoService {
    @Autowired
    private IDispositivo data;

    @Override
    public List<Dispositivo> Listar() {
        return (List<Dispositivo>) data.findAll();
    }
 
    @Override
    public Optional<Dispositivo> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Dispositivo d) {
        data.save(d);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Dispositivo> Buscar(String dato) {
        return data.buscarPorTodo(dato);
    }

    @Override
    public List<Dispositivo> OrdenAscendente() {
        return data.orderAsc();
    }

    @Override
    public List<Dispositivo> OrdenDescendente() {
        return data.orderDesc();
    }
}
