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

import com.example.demo.entity.models.Evento;
import com.example.demo.entity.service.IEventoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/Eventos")
public class EventoController {

    @Autowired
    private IEventoService eventoService;

    @GetMapping({"/", "/ListaEventos"})
    public String listarEventos(Model model) {
        List<Evento>listadoEventos = eventoService.listarEvento();
        model.addAttribute("eventos", listadoEventos);
        return "/Vistas/Eventos/ListaEventos";
    }

    @GetMapping("/Galeria")
    public String mostrarEventos(Model model) {
        List<Evento>listadoEventos = eventoService.listarEvento();
        model.addAttribute("eventos", listadoEventos);
        return "/Vistas/Eventos/GaleriaEventos";
    }

    @GetMapping("/Registrar")
    public String registrar(Model model) {
        Evento evento = new Evento();
        model.addAttribute("tituloeve", "Formulario de Eventos");
        model.addAttribute("evento", evento);
        return "/Vistas/Eventos/FrmEventos";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute Evento evento, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {
            
        if (result.hasErrors()) {
            model.addAttribute("tituloeve", "Formulario de Eventos");
            model.addAttribute("evento", evento);
            return "/Vistas/Eventos/FrmEventos";
        }

        if (!imagen.isEmpty()) {
            Path directorioEImg  = Paths.get("src//main//resources//static//images//AdminIMGs/Eventos");
            String rutaAbsoluta = directorioEImg.toFile().getAbsolutePath();
            try {
                byte[] bytesEImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesEImg);
                evento.setImagen_eve(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        eventoService.guardarEvento(evento);
        attributes.addFlashAttribute("success", "El evento se guardo de forma exitosa");
        return "redirect:/Vistas/Eventos/";
    }

    @GetMapping("/Editar/{id_evento}")
    public String editar(@PathVariable("id_evento") Long idEvento, Model model, RedirectAttributes attributes) {
        Evento evento = null;
        if (idEvento > 0) {
            evento = eventoService.buscarEventoPorId(idEvento);
            if (evento == null) {
                attributes.addFlashAttribute("warning", "El ID del evento no existe");
                return "redirect:/Vistas/Eventos/";
            } 
        } else {
            attributes.addFlashAttribute("warning", "El ID del evento no existe");
            return "redirect:/Vistas/Eventos/";
        }
        model.addAttribute("tituloeve", "Formulario de Edicion del Evento");
        model.addAttribute("evento", evento);
        return "/Vistas/Eventos/FrmEventos";
    }

    @GetMapping("/Eliminar/{id_evento}")
    public String eliminar(@PathVariable("id_evento") Long idEvento, RedirectAttributes attributes) {
        Evento evento = null;
        if (idEvento > 0) {
            evento = eventoService.buscarEventoPorId(idEvento);
            if (evento == null) {
                attributes.addFlashAttribute("error", "El ID del evento no existe");
                return "redirect:/Vistas/Eventos/";
            } 
        } else {
            attributes.addFlashAttribute("error", "El ID del evento no existe");
            return "redirect:/Vistas/Eventos/";
        }
        eventoService.eliminarEvento(idEvento);
        return "redirect:/Vistas/Eventos/";
    }
}
