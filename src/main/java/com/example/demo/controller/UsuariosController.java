package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.example.demo.entity.models.Roles;
import com.example.demo.entity.models.Usuarios;
// import com.example.demo.entity.service.IRolesService;
import com.example.demo.entity.service.IUsuariosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Usuarios")
public class UsuariosController {

    @Autowired
    private IUsuariosService usuariosService;

    // @Autowired
    // private IRolesService rolesService;

    @GetMapping({"/", "ListaUsuarios"})
    public String listarUsuarios(Model model) {
        List<Usuarios>listadoUsuarios = usuariosService.listarUsuarios();
        model.addAttribute("users", listadoUsuarios);
        return "/Vistas/Usuarios/ListaUsuarios";
    }
    
    @GetMapping("/Registrar")
    public String registrar(Model model) {
        Usuarios usuarios = new Usuarios();
        // List<Roles>listaRoles = rolesService.listarRoles();
        model.addAttribute("titulous", "Formulario de Usuarios");
        model.addAttribute("usuarios", usuarios);
        // model.addAttribute("roles", listaRoles);
        return "/Vistas/Usuarios/FrmUsuarios";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Usuarios usuarios, BindingResult result, Model model, RedirectAttributes attributes) {
        // List<Roles> listRoles = rolesService.listarRoles();
        if (result.hasErrors()) {
            model.addAttribute("titulous", "Formulario de Usuarios");
            model.addAttribute("usuarios", usuarios);
            // model.addAttribute("roles", listRoles);
            return "/Vistas/Usuarios/FrmUsuarios";
        }
        String password = usuarios.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        usuarios.setPassword(hashedPassword);

        usuariosService.guardarUsuarios(usuarios);
        model.addAttribute("success", "El nuevo usuario se guardo de forma exitosa");
        return "redirect:/Vistas/Usuarios/";
    }

    @GetMapping("/Editar/{id}")
    public String editar(@PathVariable("id") Long idUs, Model model, RedirectAttributes attributes) {
        Usuarios usuarios = null;
        if(idUs > 0) {
            usuarios = usuariosService.buscarUsuariosPorId(idUs);
            if (usuarios == null) {
                attributes.addFlashAttribute("warning", "El ID del usuario no existe");
                return "redirect:/Vistas/Usuarios/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID del usuario no existe");
                return "redirect:/Vistas/Usuarios/";
        }
        // List<Roles>roles = rolesService.listarRoles();
        model.addAttribute("titulous", "Formulario de Usuarios");
        model.addAttribute("usuarios", usuarios);
        // model.addAttribute("roles", roles);
        return "/Vistas/Usuarios/FrmUsuarios";
    }

    @GetMapping("/Eliminar/{id}")
    public String eliminar(@PathVariable("id") Long idUs, Model model, RedirectAttributes attributes) {
        Usuarios usuarios = null;
        if(idUs > 0) {
            usuarios = usuariosService.buscarUsuariosPorId(idUs);
            if (usuarios == null) {
                attributes.addFlashAttribute("error", "El ID del usuario no existe");
                return "redirect:/Vistas/Usuarios/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID del usuario no existe");
                return "redirect:/Vistas/Usuarios/";
        }
        usuariosService.eliminarUsuarios(idUs);
        return "redirect:/Vistas/Usuarios/";
    }
}
