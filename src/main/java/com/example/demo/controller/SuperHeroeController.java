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
import com.example.demo.entity.models.Habilidad;
import com.example.demo.entity.models.Organizacion;
import com.example.demo.entity.models.Planeta;
import com.example.demo.entity.models.SuperHeroe;
import com.example.demo.entity.models.Villano;
import com.example.demo.entity.service.IEventoService;
import com.example.demo.entity.service.IHabilidadService;
import com.example.demo.entity.service.IOrganizacionService;
import com.example.demo.entity.service.IPlanetaService;
import com.example.demo.entity.service.ISuperHeroeService;
import com.example.demo.entity.service.IVillanoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/Vistas/SuperHeroes")
public class SuperHeroeController {
    
    @Autowired
    private ISuperHeroeService superHeroeService;
    @Autowired
    private IOrganizacionService organizacionService;
    @Autowired
    private IPlanetaService planetaService;
    @Autowired
    private IVillanoService villanoService;
    @Autowired
    private IHabilidadService habilidadService;
    @Autowired
    private IEventoService eventoService;

    @GetMapping({"/", "ListaSuperH"})
    public String listarSuperH(Model model) {
        List<SuperHeroe>listarSuperH = superHeroeService.listarSuperhH();
        model.addAttribute("superheroe", listarSuperH);
        return "/Vistas/SuperHeroes/ListaSuperH";
    }

    @GetMapping("/Galeria")
    public String mostrarEventos(Model model) {
        List<SuperHeroe>listadoSuperH = superHeroeService.listarSuperhH();
        model.addAttribute("superheroe", listadoSuperH);
        return "/Vistas/SuperHeroes/GaleriaSuperHeroes";
    }

    @GetMapping("/Registrar")
    public String regitrar(Model model) {
        SuperHeroe superHeroe = new SuperHeroe();
        List<Organizacion>listOrg = organizacionService.listarOrganizacion();       
        List<Planeta>listPla = planetaService.listarPlaneta();
        List<Villano>listVil = villanoService.listarVillano();
        List<Habilidad>listHab = habilidadService.listarHabilidad();
        List<Evento>listEve = eventoService.listarEvento();

        model.addAttribute("titulosh", "Formulario de Super Heroes");
        model.addAttribute("superHeroe", superHeroe);       
        model.addAttribute("organizaciones", listOrg);
        model.addAttribute("planetas", listPla);      
        model.addAttribute("villanos", listVil);
        model.addAttribute("habilidades", listHab);
        model.addAttribute("eventos", listEve);

        return "/Vistas/SuperHeroes/FrmSuperH";
    }

    @PostMapping("/Guardar")
    public String guardar(@Valid @ModelAttribute SuperHeroe superHeroe, BindingResult result, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attributes) {

        List<Organizacion>listOrg = organizacionService.listarOrganizacion();       
        List<Planeta>listPla = planetaService.listarPlaneta();
        List<Villano>listVil = villanoService.listarVillano();
        List<Habilidad>listHab = habilidadService.listarHabilidad();
        List<Evento>listEve = eventoService.listarEvento();

        if (result.hasErrors()) {
            model.addAttribute("titulosh", "Formulario de Super Heroes");
            model.addAttribute("superHeroe", superHeroe);       
            model.addAttribute("organizaciones", listOrg);
            model.addAttribute("planetas", listPla);      
            model.addAttribute("villanos", listVil);
            model.addAttribute("habilidades", listHab);
            model.addAttribute("eventos", listEve);
            return "/Vistas/SuperHeroes/FrmSuperH";
        }

        if (!imagen.isEmpty()) {
            Path directorioEImg  = Paths.get("src//main//resources//static//images//AdminIMGs/SuperHeroes");
            String rutaAbsoluta = directorioEImg.toFile().getAbsolutePath();
            try {
                byte[] bytesSHImg = imagen.getBytes();
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
                Files.write(rutaCompleta,bytesSHImg);
                superHeroe.setImagen_sh(imagen.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        superHeroeService.guardarSuperH(superHeroe);
        attributes.addFlashAttribute("success", "El superheroe se guardo de forma exitosa");
        return "redirect:/Vistas/SuperHeroes/";
    }

    @GetMapping("/Editar/{id_superheroe}")
    public String editar(@PathVariable("id_superheroe") Long idSuperH, Model model, RedirectAttributes attributes) {
        SuperHeroe superHeroe = null;
        if (idSuperH > 0) {
            superHeroe = superHeroeService.buscarSuperHPorId(idSuperH);
            if (superHeroe == null) {
                attributes.addFlashAttribute("warning", "El ID del superheroe no existe");
                return "redirect:/Vistas/SuperHeroes/";
            }
        } else {
            attributes.addFlashAttribute("warning", "El ID del superheroe no existe");
            return "redirect:/Vistas/SuperHeroes/";
        }
        List<Organizacion>organizaciones = organizacionService.listarOrganizacion();
        List<Planeta>planetas = planetaService.listarPlaneta();
        List<Villano>listVil = villanoService.listarVillano();
        List<Habilidad>listHab = habilidadService.listarHabilidad();
        List<Evento>listEve = eventoService.listarEvento();
        model.addAttribute("titulosh", "Formulario de Edicion del Super Heroe");
        model.addAttribute("superHeroe", superHeroe);     
        model.addAttribute("organizaciones", organizaciones);
        model.addAttribute("planetas", planetas);  
        model.addAttribute("villanos", listVil);
        model.addAttribute("habilidades", listHab);
        model.addAttribute("eventos", listEve);
        return "/Vistas/SuperHeroes/FrmSuperH";
    }

    @GetMapping("/Eliminar/{id_superheroe}")
    public String eliminar(@PathVariable("id_superheroe") Long idSuperH, RedirectAttributes attributes) {
        SuperHeroe superHeroe = null;
        if (idSuperH > 0) {
            superHeroe = superHeroeService.buscarSuperHPorId(idSuperH);
            if (superHeroe == null) {
                attributes.addFlashAttribute("error", "El ID del superheroe no existe");
                return "redirect:/Vistas/SuperHeroes/";
            }
        } else {
            attributes.addFlashAttribute("error", "El ID del superheroe no existe");
            return "redirect:/Vistas/SuperHeroes/";
        }
        superHeroeService.eliminarSuperH(idSuperH);
        return "redirect:/Vistas/SuperHeroes/";
    }
}
