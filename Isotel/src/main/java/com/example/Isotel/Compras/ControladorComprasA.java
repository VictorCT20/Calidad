package com.example.Isotel.Compras;

import com.example.Isotel.Dispositivo.Dispositivo;
import com.example.Isotel.Dispositivo.IDispositivoService;
import com.example.Isotel.Usuario.IUsuarioService;
import com.example.Isotel.Usuario.Usuario;
import com.example.Isotel.Ventas.Ventas;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/comprasA/")
@Controller
public class ControladorComprasA {
    String carpeta="Compras/";
    
    
    @Autowired
    private IComprasService service;
    
    @Autowired
    private IDispositivoService serviceD;
    
    @Autowired
    private IUsuarioService serviceU;
    
    @GetMapping("/")
    public String Mostrar(Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        List<Compras> compras = service.Listar();
        
        model.addAttribute("compras", compras);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < compras.size(); i++) 
        {
            cantidad++;
            soles+=compras.get(i).getTotal();
        }
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        
        for (int i = 0; i < compras.size(); i++) {
            
        }
        
        
        
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        Usuario empleado = serviceU.BuscarA(userDetails.getUsername());
        model.addAttribute("usuario", empleado);
        
        //System.out.println("isAdmin: {}" + isAdmin);
        //System.out.println("Usuario: " + empleado.getNombre());
        
        
        return carpeta+"listaCompras"; //listaVentas.html
    }
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model)
    {
        List<Compras> compras = service.Buscar(dato);
        model.addAttribute("compras", compras);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < compras.size(); i++) 
        {
            cantidad++;
            soles+=compras.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaCompras"; //listaVentas.html
    }
    
    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        service.Eliminar(id);
        return Mostrar(model, userDetails);
    }
    
    @PostMapping("/registrar")
    public String Registrar(@RequestParam("can") int can,
                            //@RequestParam("tot") float tot,
                            @RequestParam("dis") Dispositivo dis,
                            Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        Compras c = new Compras();
        Usuario usu = serviceU.BuscarA(userDetails.getUsername());
        
        
        c.setUsuario(usu);
        c.setCantidad(can);
        c.setTotal(dis.getPrecio()*can);
        c.setDispositivo(dis);
        
        dis.setStock(dis.getStock()-can);
        
        service.Guardar(c);
        
        return Mostrar(model, userDetails);
    }
    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id, Model model)
    {
        Optional<Compras> compras = service.ConsultarId(id);
        model.addAttribute("compras", compras);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        return carpeta+"editar"; //editar.html
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
                            Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        Compras c = new Compras();
        Usuario usu = serviceU.BuscarA(userDetails.getUsername());
        c.setUsuario(usu);
        
        service.Guardar(c);
        
        return Mostrar(model, userDetails);
    }
    
    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model)
    {
        List<Compras> compras = service.OrdenAscendente();
        model.addAttribute("compras", compras);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < compras.size(); i++) 
        {
            cantidad++;
            soles+=compras.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaCompras"; //listaVentas.html
    }
    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model)
    {
        List<Compras> compras = service.OrdenDescendente();
        model.addAttribute("compras", compras);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < compras.size(); i++) 
        {
            cantidad++;
            soles+=compras.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaCompras"; //listaVentas.html
    }
    
    @GetMapping("/compro_stock")
    public ResponseEntity<Boolean> ComprobarStock(Model model, int id, int ndis) {
        int cant = service.CantDispo(id) - ndis;

        boolean SiCant = false;
        
        if(cant>0) SiCant = true;

        return ResponseEntity.ok(SiCant);
    }
}
