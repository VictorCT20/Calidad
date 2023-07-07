package com.example.Isotel.Dispositivo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/dispositivo/")
@Controller
public class ControladorDispositivoU {
    String carpeta = "Dispositivo/";
    
    @Autowired
    private IDispositivoService service;
    
    @GetMapping("/")
    public String Mostrar(Model model){
        List<Dispositivo> dispositivos = service.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        int cantidad=0;
        
        for(int i = 0; i < dispositivos.size(); i++) 
        {
            cantidad += dispositivos.get(i).getStock();
        }
        
        model.addAttribute("cantidad", cantidad);
        
        return carpeta+"listaDispositivo"; //listaDispositivos.html
    }
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model){
        List<Dispositivo> dispositivos = service.Buscar(dato);
        model.addAttribute("dispositivos", dispositivos);
        
        int cantidad=0;
        
        for(int i = 0; i < dispositivos.size(); i++) 
        {
            cantidad += dispositivos.get(i).getStock();
        }
        
        model.addAttribute("cantidad", cantidad);
        
        return carpeta+"listaDispositivo"; //listaDispositivos.html
    }
    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model)
    {
        List<Dispositivo> dispositivos = service.OrdenAscendente();
        model.addAttribute("dispositivos", dispositivos);
        
        int cantidad=0;
        
        for(int i = 0; i < dispositivos.size(); i++) 
        {
            cantidad += dispositivos.get(i).getStock();
        }
        
        model.addAttribute("cantidad", cantidad);
        
        return carpeta+"listaDispositivo"; //listaDispositivos.html
    }
    
    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model)
    {
        List<Dispositivo> dispositivos = service.OrdenDescendente();
        model.addAttribute("dispositivos", dispositivos);
        
        
        int cantidad=0;
        
        for(int i = 0; i < dispositivos.size(); i++) 
        {
            cantidad += dispositivos.get(i).getStock();
        }
        
        model.addAttribute("cantidad", cantidad);
        
        return carpeta+"listaDispositivo"; //listaDispositivos.html
    }
}
