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

import com.example.demo.entity.models.Habilidad;
import com.example.demo.entity.service.IHabilidadService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Habilidades")
public class HabilidadController {
    
    @Autowired
    private IHabilidadService habilidadService;

    @GetMapping({"/", "/ListaHabilidades"})
    public String listarHabilidades(Model model) {
        List<Habilidad>listadoHabilidades = habilidadService.listarHabilidad();
        model.addAttribute("habilidades", listadoHabilidades);
        return "/Vistas/Habilidades/ListaHabilidades";
    }

    @GetMapping("/Galeria")
    public String mostrarHabilidades(Model model) {
        List<Habilidad>listadoHabilidades = habilidadService.listarHabilidad();
        model.addAttribute("habilidades", listadoHabilidades);
        return "/Vistas/Habilidades/GaleriaHabilidades";
    }

    @GetMapping("/Registrar")
    public String registrar(Model model) {
        Habilidad habilidad = new Habilidad();
        model.addAttribute("titulohab", "Formulario de Habilidades");
        model.addAttribute("habilidad", habilidad);
        return "/Vistas/Habilidades/FrmHabilidades";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Habilidad habilidad, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {
        
        if (result.hasErrors()) {
            model.addAttribute("titulohab", "Formulario de Habilidades");
            model.addAttribute("habilidad", habilidad);
            return "/Vistas/Habilidades/FrmHabilidades";
        }
        
        if (!imagen.isEmpty()) {
            Path directorioHImg  = Paths.get("src//main//resources//static//images//AdminIMGs/Habilidades");
            String rutaAbsoluta = directorioHImg.toFile().getAbsolutePath();
            try {
                byte[] bytesEImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesEImg);
                habilidad.setImagen_hab(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        habilidadService.guardarHabilidad(habilidad);
        attributes.addFlashAttribute("success", "La habilidad se guardo de forma exitosa");
        return "redirect:/Vistas/Habilidades/";
    }

    @GetMapping("/Editar/{id_habilidad}")
    public String editar(@PathVariable("id_habilidad") Long idHabilidad, Model model, RedirectAttributes attributes) {
        Habilidad habilidad = null;
        if (idHabilidad > 0) {
            habilidad = habilidadService.buscarHabilidadPorId(idHabilidad);
            if (habilidad == null) {
                attributes.addFlashAttribute("warning", "El ID de la habilidad no existe");
                return "redirect:/Vistas/Habilidades/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID de la habilidad no existe");
            return "redirect:/Vistas/Habilidades/";
        }
        model.addAttribute("titulohab", "Formulario de Edicion de la Habilidad");
        model.addAttribute("habilidad", habilidad);
        return "/Vistas/Habilidades/FrmHabilidades";
    }

    @GetMapping("/Eliminar/{id_habilidad}")
    public String eliminar(@PathVariable("id_habilidad") Long idHabilidad, RedirectAttributes attributes) {
        Habilidad habilidad = null;
        if (idHabilidad > 0) {
            habilidad = habilidadService.buscarHabilidadPorId(idHabilidad);
            if (habilidad == null) {
                attributes.addFlashAttribute("error", "El ID de la habilidad no existe");
                return "redirect:/Vistas/Habilidades/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID de la habilidad no existe");
            return "redirect:/Vistas/Habilidades/";
        }
        habilidadService.eliminarHabilidad(idHabilidad);
        return "redirect:/Vistas/Habilidades/";
    }
}
