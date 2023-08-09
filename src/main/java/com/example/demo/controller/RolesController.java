package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.models.Roles;
import com.example.demo.entity.models.Usuarios;
import com.example.demo.entity.service.IRolesService;
import com.example.demo.entity.service.IUsuariosService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Usuarios")
public class RolesController {
    
    @Autowired
    private IRolesService rolesService;

    @Autowired
    private IUsuariosService usuariosService;

    @GetMapping("/Asignar")
    public String asignar(Model model) {
        List<Usuarios> listUsuarios = usuariosService.listarUsuarios();
        Roles roles = new Roles();
        model.addAttribute("titulorol", "Asignar Roles");
        model.addAttribute("roles", roles);
        model.addAttribute("usuarios", listUsuarios);
        return "/Vistas/Usuarios/FrmRoles";
    }

    @PostMapping("/GuardarRol")
    public String guardarRol(@Valid @ModelAttribute Roles roles, BindingResult result, Model model, RedirectAttributes attributes) {
        List<Usuarios> listaUsuarios = usuariosService.listarUsuarios();
        if (result.hasErrors()) {
            model.addAttribute("titulorol", "Asignar Roles");
            model.addAttribute("roles", roles);
            model.addAttribute("usuarios", listaUsuarios);
            return "/Vistas/Usuarios/FrmRoles";
        }
        rolesService.guardarRoles(roles);
        model.addAttribute("success", "El rol se ha asignado correctamente");
        return "redirect:/Vistas/Usuarios/";
    }
}
