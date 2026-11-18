package com.mercado.controller;
import com.mercado.model.Fornecedor;import com.mercado.repository.FornecedorRepository;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/fornecedores") public class FornecedorController {
 private final FornecedorRepository r;public FornecedorController(FornecedorRepository r){this.r=r;}
 @GetMapping public String listar(@RequestParam(defaultValue="")String q,Model m){m.addAttribute("fornecedores",q.isBlank()?r.findAll():r.findByNomeContainingIgnoreCase(q));m.addAttribute("q",q);return "fornecedores";}
 @PostMapping("/salvar") public String salvar(@ModelAttribute Fornecedor f,RedirectAttributes a){r.save(f);a.addFlashAttribute("sucesso","Fornecedor salvo.");return "redirect:/fornecedores";}
 @PostMapping("/{id}/alternar") public String alternar(@PathVariable Long id){Fornecedor f=r.findById(id).orElseThrow();f.setAtivo(!f.isAtivo());r.save(f);return "redirect:/fornecedores";}
}
