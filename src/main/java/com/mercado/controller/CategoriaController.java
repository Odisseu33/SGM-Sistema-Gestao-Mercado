package com.mercado.controller;
import com.mercado.model.Categoria;import com.mercado.repository.CategoriaRepository;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/categorias") public class CategoriaController {
 private final CategoriaRepository r;public CategoriaController(CategoriaRepository r){this.r=r;}
 @GetMapping public String listar(Model m){m.addAttribute("categorias",r.findAll());return "categorias";}
 @PostMapping("/salvar") public String salvar(@ModelAttribute Categoria c,RedirectAttributes a){r.save(c);a.addFlashAttribute("sucesso","Categoria salva.");return "redirect:/categorias";}
 @PostMapping("/{id}/alternar") public String alternar(@PathVariable Long id){Categoria c=r.findById(id).orElseThrow();c.setAtivo(!c.isAtivo());r.save(c);return "redirect:/categorias";}
}
