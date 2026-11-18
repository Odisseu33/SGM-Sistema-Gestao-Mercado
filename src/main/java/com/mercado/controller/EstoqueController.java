package com.mercado.controller;
import com.mercado.model.*;import com.mercado.model.Enums.*;import com.mercado.repository.*;import com.mercado.service.EstoqueService;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller @RequestMapping("/estoque") public class EstoqueController {
 private final ProdutoRepository p;private final MovimentacaoEstoqueRepository m;private final EstoqueService s;
 public EstoqueController(ProdutoRepository p,MovimentacaoEstoqueRepository m,EstoqueService s){this.p=p;this.m=m;this.s=s;}
 @GetMapping public String listar(Model model){model.addAttribute("movimentos",m.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC,"dataMovimentacao")));model.addAttribute("produtos",p.findAll());model.addAttribute("tipos",TipoMovimentacao.values());model.addAttribute("motivos",MotivoMovimentacao.values());return "estoque";}
 @PostMapping("/movimentar") public String movimentar(@RequestParam Long produtoId,@RequestParam TipoMovimentacao tipo,@RequestParam int quantidade,@RequestParam MotivoMovimentacao motivo,@RequestParam(required=false)String observacao,RedirectAttributes a){s.movimentar(produtoId,tipo,quantidade,motivo,observacao,null);a.addFlashAttribute("sucesso","Movimentação registrada.");return "redirect:/estoque";}
}
