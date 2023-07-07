package com.example.Isotel.Dispositivo;

import com.example.Isotel.Usuario.IUsuarioService;
import com.example.Isotel.Usuario.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/dispositivoA/")
@Controller
public class ControladorDispositivoA {

    String carpeta = "Dispositivo/";

    @Autowired
    private IDispositivoService service;
    @Autowired
    private IUsuarioService service2;

    @GetMapping("/")
    public String Mostrar(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Dispositivo> dispositivos = service.Listar();
        model.addAttribute("dispositivos", dispositivos);

        int cantidad = 0;

        for (int i = 0; i < dispositivos.size(); i++) {
            cantidad += dispositivos.get(i).getStock();
        }

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        System.out.println("isAdmin: {}" + isAdmin);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("cantidad", cantidad);

        return carpeta + "listaDispositivoA"; //listaDispositivos.html
    }

    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model) {
        List<Dispositivo> dispositivos = service.Buscar(dato);
        model.addAttribute("dispositivos", dispositivos);

        int cantidad = 0;

        for (int i = 0; i < dispositivos.size(); i++) {
            cantidad += dispositivos.get(i).getStock();
        }

        model.addAttribute("cantidad", cantidad);

        return carpeta + "listaDispositivo"; //listaDispositivos.html
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        service.Eliminar(id);

        return Mostrar(model, userDetails);
    }

    @PostMapping("/registrar")
    public String Registrar(@RequestParam("mod") String mod,
            @RequestParam("des") String des,
            @RequestParam("pre") float pre,
            @RequestParam("stk") int stk,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Dispositivo d = new Dispositivo();
        d.setModelo(mod);
        d.setDescripcion(des);
        d.setPrecio(pre);
        d.setStock(stk);

        service.Guardar(d);

        return Mostrar(model, userDetails);
    }

    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id, Model model) {
        Optional<Dispositivo> dispositivos = service.ConsultarId(id);
        model.addAttribute("dispositivos", dispositivos);

        return carpeta + "editar"; //editar.html
    }

    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
            @RequestParam("modelo") String mod,
            @RequestParam("descripcion") String des,
            @RequestParam("precio") float pre,
            @RequestParam("stock") int stk,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Dispositivo d = new Dispositivo();
        d.setId(id);
        d.setModelo(mod);
        d.setDescripcion(des);
        d.setPrecio(pre);
        d.setStock(stk);

        service.Guardar(d);

        return Mostrar(model, userDetails);
    }

    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model) {
        List<Dispositivo> dispositivos = service.OrdenAscendente();
        model.addAttribute("dispositivos", dispositivos);

        int cantidad = 0;

        for (int i = 0; i < dispositivos.size(); i++) {
            cantidad += dispositivos.get(i).getStock();
        }

        model.addAttribute("cantidad", cantidad);

        return carpeta + "listaDispositivoA"; //listaDispositivos.html
    }

    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model) {
        List<Dispositivo> dispositivos = service.OrdenDescendente();
        model.addAttribute("dispositivos", dispositivos);

        int cantidad = 0;

        for (int i = 0; i < dispositivos.size(); i++) {
            cantidad += dispositivos.get(i).getStock();
        }

        model.addAttribute("cantidad", cantidad);

        return carpeta + "listaDispositivoA"; //listaDispositivos.html
    }
}
