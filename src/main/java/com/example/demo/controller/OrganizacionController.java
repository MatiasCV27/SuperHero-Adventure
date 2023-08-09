package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.models.Organizacion;
import com.example.demo.entity.service.IOrganizacionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Organizaciones")
public class OrganizacionController {
    
    @Autowired
    private IOrganizacionService organizacionService;

    @GetMapping({"/", "/ListaOrganizaciones"})
    public String ListarOrganizaciones(Model model) {
        List<Organizacion>listadoOrganizaciones = organizacionService.listarOrganizacion();
        model.addAttribute("organizaciones", listadoOrganizaciones);
        return "/Vistas/Organizaciones/ListaOrganizaciones";
    }

    @GetMapping("/Galeria")
    public String mostrarEventos(Model model) {
        List<Organizacion>listadoOrganizaciones = organizacionService.listarOrganizacion();
        model.addAttribute("organizaciones", listadoOrganizaciones);
        return "/Vistas/Organizaciones/GaleriaOrganizaciones";
    }

    @GetMapping("/Registrar")
    public String registrar(Model model) {
        Organizacion organizacion = new Organizacion();
        model.addAttribute("tituloorg", "Formulario de Organizaciones");
        model.addAttribute("organizacion", organizacion);
        return "/Vistas/Organizaciones/FrmOrganizaciones";
    } 

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Organizacion organizacion, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            model.addAttribute("tituloorg", "Formulario de Organizaciones");
            model.addAttribute("organizacion", organizacion);
            return "/Vistas/Organizaciones/FrmOrganizaciones";
        }
        if (!imagen.isEmpty()) {
            Path directorioOImg  = Paths.get("src//main//resources//static//images//AdminIMGs/Organizaciones");
            String rutaAbsoluta = directorioOImg.toFile().getAbsolutePath();
            try {
                byte[] bytesEImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesEImg);
                organizacion.setImagen_org(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        organizacionService.guardarOrganizacion(organizacion);
        attributes.addFlashAttribute("success", "La organización se guardo de forma exitosa");
        return "redirect:/Vistas/Organizaciones/";
    }

    @GetMapping("/Editar/{id_organizacion}")
    public String editar(@PathVariable("id_organizacion") Long idOrg, Model model, RedirectAttributes attributes) {
        Organizacion org = null;
        if (idOrg > 0) {
            org = organizacionService.buscarOrganizacionPorId(idOrg);
            if (org == null) {
                attributes.addFlashAttribute("warning", "El ID de la organiación no existe");
                return "redirect:/Vistas/Organizaciones/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID de la organización no existe");
            return "redirect:/Vistas/Organizaciones/";
        }
        model.addAttribute("tituloorg", "Formulario de Edicion de la Organización");
        model.addAttribute("organizacion", org);
        return "/Vistas/Organizaciones/FrmOrganizaciones";
    }

    @GetMapping("/Eliminar/{id_organizacion}")
    public String eliminar(@PathVariable("id_organizacion") Long idOrg, RedirectAttributes attributes) {
        Organizacion org = null;
        if (idOrg > 0) {
            org = organizacionService.buscarOrganizacionPorId(idOrg);
            if (org == null) {
                attributes.addFlashAttribute("error", "El ID de la organiación no existe");
                return "redirect:/Vistas/Organizaciones/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID de la organiación no existe");
            return "redirect:/Vistas/Organizaciones/";
        }
        organizacionService.eliminarOrganizacion(idOrg);
        return "redirect:/Vistas/Organizaciones/";
    }
}
