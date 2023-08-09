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

import com.example.demo.entity.models.Villano;
import com.example.demo.entity.service.IVillanoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Villanos")
public class VillanoController {
    
    @Autowired
    private IVillanoService villanoService;

    @GetMapping({"/", "/ListaVillanos"})
    public String listarVillanos(Model model) {
        List<Villano>listadoVillanos = villanoService.listarVillano();
        model.addAttribute("villanos", listadoVillanos);
        return "/Vistas/Villanos/ListaVillanos";
    }

    @GetMapping("/Galeria")
    public String mostrarEventos(Model model) {
        List<Villano>listadoVillanos = villanoService.listarVillano();
        model.addAttribute("villanos", listadoVillanos);
        return "/Vistas/Villanos/GaleriaVillanos";
    }

    @GetMapping("/Registrar")
    public String register(Model model) {
        Villano villano = new Villano();
        model.addAttribute("titulovil", "Formulario de Villanos");
        model.addAttribute("villano", villano);
        return "/Vistas/Villanos/FrmVillanos";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Villano villano, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            model.addAttribute("titulovil", "Formulario de Villanos");
            model.addAttribute("villano", villano);
            return "/Vistas/Villanos/FrmVillanos";
        }
        if (!imagen.isEmpty()) {
            Path directorioEImg  = Paths.get("src//main//resources//static//images//AdminIMGs/Villanos");
            String rutaAbsoluta = directorioEImg.toFile().getAbsolutePath();
            try {
                byte[] bytesVImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesVImg);
                villano.setImagen_vil(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        villanoService.guardarVillano(villano);
        attributes.addFlashAttribute("success", "El villano se guardo de forma exitosa");
        return "redirect:/Vistas/Villanos/";
    }

    @GetMapping("/Editar/{id_villano}")
    public String editar(@PathVariable("id_villano") Long idVillano, Model model, RedirectAttributes attributes) {
        Villano villano = null;
        if (idVillano > 0) {
            villano = villanoService.buscarVillanoPorId(idVillano);
            if (villano == null) {
                attributes.addFlashAttribute("warning", "El ID del villano no existe");
                return "redirect:/Vistas/Villanos/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID del villano no existe");
            return "redirect:/Vistas/Villanos/";
        }
        model.addAttribute("titulovil", "Formulario de Edicion del Villano");
        model.addAttribute("villano", villano);
        return "/Vistas/Villanos/FrmVillanos";
    }

    @GetMapping("/Eliminar/{id_villano}")
    public String eliminar(@PathVariable("id_villano") Long idVillano, RedirectAttributes attributes) {
        Villano villano = null;
        if (idVillano > 0) {
            villano = villanoService.buscarVillanoPorId(idVillano);
            if (villano == null) {
                attributes.addFlashAttribute("error", "El ID del villano no existe");
                return "redirect:/Vistas/Villanos/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID del villano no existe");
            return "redirect:/Vistas/Villanos/";
        }
        villanoService.eliminarVillano(idVillano);
        return "redirect:/Vistas/Villanos/";
    }
}
