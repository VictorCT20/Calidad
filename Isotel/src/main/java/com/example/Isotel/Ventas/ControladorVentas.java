package com.example.Isotel.Ventas;

import com.example.Isotel.Dispositivo.Dispositivo;
import com.example.Isotel.Dispositivo.IDispositivoService;
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

@RequestMapping("/ventas/")
@Controller
public class ControladorVentas {
    String carpeta="Ventas/";
    
    @Autowired
    private IVentasService service;
    
    @Autowired
    private IDispositivoService serviceD;
    
    @Autowired
    private IUsuarioService serviceU;
    
    @GetMapping("/")
    public String Mostrar(Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        List<Ventas> ventas = service.Listar();
        model.addAttribute("ventas", ventas);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < ventas.size(); i++) 
        {
            cantidad++;
            soles+=ventas.get(i).getTotal();
        }
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
        
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        Usuario empleado = serviceU.BuscarA(userDetails.getUsername());
        model.addAttribute("usuario", empleado);
        
        //System.out.println("isAdmin: {}" + isAdmin);
        //System.out.println("Usuario: " + empleado.getNombre());
        
        
        return carpeta+"listaVentas"; //listaVentas.html
    }
    
    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model)
    {
        List<Ventas> ventas = service.Buscar(dato);
        model.addAttribute("ventas", ventas);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < ventas.size(); i++) 
        {
            cantidad++;
            soles+=ventas.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaVentas"; //listaVentas.html
    }
    
    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        service.Eliminar(id);
        return Mostrar(model, userDetails);
    }
    
    @PostMapping("/registrar")
    public String Registrar(@RequestParam("nom") String nom,
                            @RequestParam("ape") String ape,
                            @RequestParam("cor") String cor,
                            @RequestParam("tel") String tel,
                            @RequestParam("can") int can,
                            //@RequestParam("tot") float tot,
                            @RequestParam("dis") Dispositivo dis,
                            Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        Ventas v = new Ventas();
        Usuario emp = serviceU.BuscarA(userDetails.getUsername());
        
        v.setNombre(nom);
        v.setApellidos(ape);
        v.setCorreo(cor);
        v.setTelefono(tel);
        v.setCantidad(can);
        v.setTotal(dis.getPrecio()*can);
        v.setDispositivo(dis);
        v.setEmpleado(emp);
        
        dis.setStock(dis.getStock()-can);
        
        service.Guardar(v);
        
        return Mostrar(model, userDetails);
    }
    
    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id, Model model)
    {
        Optional<Ventas> ventas = service.ConsultarId(id);
        model.addAttribute("ventas", ventas);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        return carpeta+"editar"; //editar.html
    }
    
    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
                            @RequestParam("nombre") String nom,
                            @RequestParam("apellidos") String ape,
                            @RequestParam("correo") String cor,
                            @RequestParam("telefono") String tel,
                            Model model, @AuthenticationPrincipal UserDetails userDetails)
    {
        Ventas v = new Ventas();
        Usuario emp = serviceU.BuscarA(userDetails.getUsername());
        v.setNombre(nom);
        v.setApellidos(ape);
        v.setCorreo(cor);
        v.setTelefono(tel);
        v.setEmpleado(emp);
        
        service.Guardar(v);
        
        return Mostrar(model, userDetails);
    }
    
    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model)
    {
        List<Ventas> ventas = service.OrdenAscendente();
        model.addAttribute("ventas", ventas);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < ventas.size(); i++) 
        {
            cantidad++;
            soles+=ventas.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaVentas"; //listaVentas.html
    }
    
    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model)
    {
        List<Ventas> ventas = service.OrdenDescendente();
        model.addAttribute("ventas", ventas);
        
        List<Dispositivo> dispositivos = serviceD.Listar();
        model.addAttribute("dispositivos", dispositivos);
        
        List<Usuario> empleados = serviceU.ListarEm();
        model.addAttribute("empleados", empleados);
        
        int cantidad=0;
        float soles=0;
        
        for(int i = 0; i < ventas.size(); i++) 
        {
            cantidad++;
            soles+=ventas.get(i).getTotal();
        }
        
        model.addAttribute("cantidad", cantidad);
        model.addAttribute("soles", soles);
        
        return carpeta+"listaVentas"; //listaVentas.html
    }
}
