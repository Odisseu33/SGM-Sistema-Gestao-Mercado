package com.mercado.controller;
import com.mercado.model.*;import com.mercado.repository.*;import com.mercado.service.CadastroService;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/produtos") public class ProdutoController {
 private final ProdutoRepository repo;private final CategoriaRepository cats;private final FornecedorRepository fors;private final CadastroService service;
 public ProdutoController(ProdutoRepository r,CategoriaRepository c,FornecedorRepository f,CadastroService s){repo=r;cats=c;fors=f;service=s;}
 @GetMapping public String listar(@RequestParam(defaultValue="")String q,Model m){m.addAttribute("produtos",q.isBlank()?repo.findAll():repo.findByNomeContainingIgnoreCase(q,org.springframework.data.domain.Pageable.unpaged()).getContent());m.addAttribute("q",q);return "produtos";}
 @GetMapping("/novo") public String novo(Model m){m.addAttribute("produto",new Produto());form(m);return "produto-form";}
 @GetMapping("/{id}/editar") public String editar(@PathVariable Long id,Model m){m.addAttribute("produto",repo.findById(id).orElseThrow());form(m);return "produto-form";}
 private void form(Model m){m.addAttribute("categorias",cats.findAll());m.addAttribute("fornecedores",fors.findAll());}
 @PostMapping("/salvar") public String salvar(@ModelAttribute Produto produto,@RequestParam(required=false)Long categoriaId,@RequestParam(required=false)Long fornecedorId,RedirectAttributes ra){service.salvarProduto(produto,categoriaId,fornecedorId);ra.addFlashAttribute("sucesso","Produto salvo.");return "redirect:/produtos";}
 @PostMapping("/{id}/alternar") public String alternar(@PathVariable Long id){Produto p=repo.findById(id).orElseThrow();p.setAtivo(!p.isAtivo());repo.save(p);return "redirect:/produtos";}
}
