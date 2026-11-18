document.addEventListener('DOMContentLoaded',()=>{
 const add=document.getElementById('adicionar'), linhas=document.getElementById('linhas');
 if(add&&linhas){add.addEventListener('click',()=>{const first=linhas.querySelector('.linha');const clone=first.cloneNode(true);clone.querySelector('select').selectedIndex=0;clone.querySelector('input').value=1;linhas.appendChild(clone);});linhas.addEventListener('click',e=>{if(e.target.classList.contains('remover')){if(linhas.querySelectorAll('.linha').length>1)e.target.closest('.linha').remove();}});}
});

// Realce da seção atual, incluindo telas internas de cada módulo.
document.addEventListener('DOMContentLoaded', () => {
 const path=window.location.pathname;
 let selected=path.startsWith('/vendas/nova')?'/vendas/nova':path.startsWith('/vendas/')?'/vendas':path==='/'?'/':('/'+path.split('/')[1]);
 document.querySelectorAll('.nav-link[data-nav]').forEach(link=>{
  const active=link.dataset.nav===selected;
  link.classList.toggle('active',active);
  if(active)link.setAttribute('aria-current','page');
 });
});
