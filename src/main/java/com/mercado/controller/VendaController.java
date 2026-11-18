package com.mercado.controller;
import com.mercado.model.Enums.*;import com.mercado.repository.*;import com.mercado.service.VendaService;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import java.util.*;
@Controller @RequestMapping("/vendas") public class VendaController {
 private final VendaRepository v;private final ProdutoRepository p;private final VendaService s;
 public VendaController(VendaRepository v,ProdutoRepository p,VendaService s){this.v=v;this.p=p;this.s=s;}
 @GetMapping public String listar(Model m){m.addAttribute("vendas",v.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.DESC,"dataVenda")));return "vendas";}
 @GetMapping("/nova") public String nova(Model m){m.addAttribute("produtos",p.findAll().stream().filter(x->x.isAtivo()&&x.getQuantidadeEstoque()>0).toList());m.addAttribute("pagamentos",FormaPagamento.values());return "venda-form";}
 @PostMapping("/salvar") public String salvar(@RequestParam("produtoId")List<Long> ids,@RequestParam("quantidade")List<Integer> qs,@RequestParam FormaPagamento formaPagamento,@RequestParam(required=false)String observacao,RedirectAttributes a){List<VendaService.Linha> linhas=new ArrayList<>();for(int i=0;i<ids.size();i++)linhas.add(new VendaService.Linha(ids.get(i),qs.get(i)));var venda=s.registrar(linhas,formaPagamento,observacao);return "redirect:/vendas/"+venda.getId()+"/confirmacao";}
 @GetMapping("/{id}/confirmacao") public String confirmacao(@PathVariable Long id,Model m){
  var venda=v.findByIdComItens(id).orElseThrow(()->new com.mercado.exception.BusinessException("Venda não encontrada."));
  m.addAttribute("venda",venda);return "venda-confirmacao";
 }
 @PostMapping("/{id}/cancelar") public String cancelar(@PathVariable Long id,RedirectAttributes a){s.cancelar(id);a.addFlashAttribute("sucesso","Venda cancelada e estoque devolvido.");return "redirect:/vendas";}
}
