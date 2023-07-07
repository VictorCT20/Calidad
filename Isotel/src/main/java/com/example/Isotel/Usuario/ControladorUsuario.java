package com.example.Isotel.Usuario;

import com.example.Isotel.Ventas.IVentasService;
import com.example.Isotel.Ventas.Ventas;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuarios/")
public class ControladorUsuario {

    String carpeta = "Usuario/";

    @Autowired
    private IUsuarioService service;
    @Autowired
    private IVentasService serviceV;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Mostrar(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Usuario> usuarios = service.Listar();
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        System.out.println("isAdmin: {}" + isAdmin);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("usuarios", usuarios);

        return carpeta + "listaUsuarios"; //listaEmpleados.html
    }

    @PostMapping("/buscar")
    public String Buscar(@RequestParam("dato") String dato, Model model) {
        List<Usuario> usuarios = service.Buscar(dato);
        model.addAttribute("usuarios", usuarios);

        return carpeta + "listaUsuarios"; //listaEmpleados.html
    }

    @GetMapping("/eliminar")
    public String Eliminar(@RequestParam("id") int id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        service.Eliminar(id);

        return Mostrar(model, userDetails);
    }

    @GetMapping("/nuevo")
    public String Nuevo() {
        return carpeta + "nuevo"; //nuevo.html
    }

    @PostMapping("/registrar")
    public String Registrar(@RequestParam("use") String use,
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("nac") Date nac,
            @RequestParam("doc") String doc,
            @RequestParam("tel") String tel,
            @RequestParam("cor") String cor,
            @RequestParam("dir") String dir,
            @RequestParam("pass") String pass,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario u = new Usuario();
        u.setNombre(nom);
        u.setApellidos(ape);
        u.setNacimiento(nac);
        u.setDocumento(doc);
        u.setTelefono(tel);
        u.setCorreo(cor);
        u.setDireccion(dir);
        u.setUsername(use);
        String encryptedPassword = passwordEncoder.encode(pass); // Encriptar la contraseña
        u.setPassword(encryptedPassword);
        u.setAdmin(false);

        service.Guardar(u);

        return Mostrar(model, userDetails);
    }

    @PostMapping("/registrarL")
    public String RegistrarL(@RequestParam("use") String use,
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("nac") Date nac,
            @RequestParam("doc") String doc,
            @RequestParam("tel") String tel,
            @RequestParam("cor") String cor,
            @RequestParam("dir") String dir,
            @RequestParam("pass") String pass,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario u = new Usuario();
        u.setNombre(nom);
        u.setApellidos(ape);
        u.setNacimiento(nac);
        u.setDocumento(doc);
        u.setTelefono(tel);
        u.setCorreo(cor);
        u.setDireccion(dir);
        u.setUsername(use);
        String encryptedPassword = passwordEncoder.encode(pass); // Encriptar la contraseña
        u.setPassword(encryptedPassword);
        u.setAdmin(false);

        service.Guardar(u);

        return "redirect:/Login";
    }

    @GetMapping("/editar")
    public String Editar(@RequestParam("id") int id, Model model) {
        Optional<Usuario> usuarios = service.ConsultarId(id);
        model.addAttribute("usuarios", usuarios);
        return carpeta + "editar"; //editar.html
    }

    @PostMapping("/actualizar")
    public String Actualizar(@RequestParam("id") int id,
            @RequestParam("nombre") String nom,
            @RequestParam("apellidos") String ape,
            @RequestParam("nacimiento") Date nac,
            @RequestParam("documento") String doc,
            @RequestParam("telefono") String tel,
            @RequestParam("correo") String cor,
            @RequestParam("direccion") String dir,
            @RequestParam("username") String use,
            @RequestParam("password") String pass,
            @RequestParam("admin") String adm,
            Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Usuario u = new Usuario();
        u.setNombre(nom);
        u.setApellidos(ape);
        u.setNacimiento(nac);
        u.setDocumento(doc);
        u.setTelefono(tel);
        u.setCorreo(cor);
        u.setDireccion(dir);
        u.setUsername(use);
        String encryptedPassword = passwordEncoder.encode(pass); // Encriptar la contraseña
        u.setPassword(encryptedPassword);
        if (adm.equals("true")) {
            u.setAdmin(true);
        } else {
            u.setAdmin(false);
        }

        service.Actualizar(u, id);

        return Mostrar(model, userDetails);
    }

    @GetMapping("/orden_asc")
    public String OrdenarAsc(Model model) {
        List<Usuario> usuarios = service.OrdenAscendente();
        model.addAttribute("usuarios", usuarios);

        return carpeta + "listaUsuarios"; //listaEmpleados.html
    }

    @GetMapping("/orden_desc")
    public String OrdenarDesc(Model model) {
        List<Usuario> usuarios = service.OrdenDescendente();
        model.addAttribute("usuarios", usuarios);

        return carpeta + "listaUsuarios"; //listaEmpleados.html
    }

    @GetMapping("/compro_username")
    public ResponseEntity<Boolean> ComprobarUsername(Model model, String username) {
        List<String> usuarios = service.ComproUsername();

        boolean usernameExiste = usuarios.contains(username);

        return ResponseEntity.ok(usernameExiste);
    }

    //Reportes
    public class ReporteEmpleado {

        private String nombre;
        private int cantidad;
        // Constructor, getters y setters

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

    }

    @GetMapping("/reporte")
    public String ReporteGrafico(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Integer> graphData = new TreeMap<>();
        List<Usuario> empleados = service.ListarEm();
        List<ReporteEmpleado> reporteEmpleados = new ArrayList<>();

        for (int i = 0; i < empleados.size(); i++) {
            int id = empleados.get(i).getId();
            String nom = empleados.get(i).getNombre() + " " + empleados.get(i).getApellidos();

                List<Ventas> ventas = serviceV.VentasXEmpleado(id);
                int cant = ventas.size()/5;

            graphData.put(nom, cant);

            ReporteEmpleado reporteEmpleado = new ReporteEmpleado();
            reporteEmpleado.setNombre(nom);
            reporteEmpleado.setCantidad(cant);

            reporteEmpleados.add(reporteEmpleado);
        }

        model.addAttribute("reporteEmpleados", reporteEmpleados);
        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));
        System.out.println("isAdmin: {}" + isAdmin);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("graphData", graphData);
        return "Reporte/grafico"; //grafico.html
    }
}
