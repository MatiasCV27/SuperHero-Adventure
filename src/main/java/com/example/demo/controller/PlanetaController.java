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

import com.example.demo.entity.models.Planeta;
import com.example.demo.entity.service.IPlanetaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Planetas")
public class PlanetaController {

    @Autowired
    private IPlanetaService planetaService;

    @GetMapping({"/", "/ListaPlanetas"})
    public String listarPlantetas(Model model) {
        List<Planeta>listadoPlaneta = planetaService.listarPlaneta();
        model.addAttribute("planetas", listadoPlaneta);
        return "/Vistas/Planetas/ListaPlanetas";
    }

    @GetMapping("/Galeria")
    public String mostrarPlanetas(Model model) {
        List<Planeta>listadoPlaneta = planetaService.listarPlaneta();
        model.addAttribute("planetas", listadoPlaneta);
        return "/Vistas/Planetas/GaleriaPlanetas";
    }

    @GetMapping("/Registrar")
    public String registrar(Model model) {
        Planeta planeta = new Planeta();
        model.addAttribute("titulopla", "Formulario de Planetas");
        model.addAttribute("planeta", planeta);
        return "/Vistas/Planetas/FrmPlanetas";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Planeta planeta, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("titulopla", "Formulario de Planetas");
            model.addAttribute("planeta", planeta);
            return "/Vistas/Planetas/FrmPlanetas";
        }

        if (!imagen.isEmpty()) {
            Path directorioEImg  = Paths.get("src//main//resources//static//images//AdminIMGs/Planetas");
            String rutaAbsoluta = directorioEImg.toFile().getAbsolutePath();
            try {
                byte[] bytesPImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesPImg);
                planeta.setImagen_pla(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        planetaService.guardarPlaneta(planeta);
        attributes.addFlashAttribute("success", "El planeta se guardo de forma exitosa");
        return "redirect:/Vistas/Planetas/";
    }

    @GetMapping("/Editar/{id_planeta}")
    public String editar(@PathVariable("id_planeta") Long idPlaneta, Model model, RedirectAttributes attributes) {
        Planeta planeta = null;
        if (idPlaneta > 0) {
            planeta = planetaService.buscarPlanetaPorId(idPlaneta);
            if (planeta == null) {
                attributes.addFlashAttribute("warning", "El ID del planeta no existe");
                return "redirect:/Vistas/Planetas/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID del planeta no existe");
            return "redirect:/Vistas/Planetas/";
        }
        model.addAttribute("titulopla", "Formulario de Edicion del Planeta");
        model.addAttribute("planeta", planeta);
        return "/Vistas/Planetas/FrmPlanetas";
    }

    @GetMapping("/Eliminar/{id_planeta}")
    public String eliminar(@PathVariable("id_planeta") Long idPlaneta, RedirectAttributes attributes) {
        Planeta planeta = null;
        if (idPlaneta > 0) {
            planeta = planetaService.buscarPlanetaPorId(idPlaneta);
            if (planeta == null) {
                attributes.addFlashAttribute("error", "El ID del planeta no existe");
                return "redirect:/Vistas/Planetas/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID del planeta no existe");
            return "redirect:/Vistas/Planetas/";
        }
        planetaService.eliminarPlaneta(idPlaneta);
        return "redirect:/Vistas/Planetas/";
    }
}
