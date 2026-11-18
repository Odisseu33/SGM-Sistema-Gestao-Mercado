package com.mercado.config;

import com.mercado.model.Categoria;
import com.mercado.model.Fornecedor;
import com.mercado.model.Produto;
import com.mercado.repository.CategoriaRepository;
import com.mercado.repository.FornecedorRepository;
import com.mercado.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class ProdutosExemploInitializer {
    @Bean
    CommandLineRunner cadastrarProdutosExemplo(ProdutoRepository produtos, CategoriaRepository categorias, FornecedorRepository fornecedores) {
        return args -> {
            if (produtos.count() > 0) return; // Não altera catálogos já cadastrados.
            Categoria bebidas = categoria(categorias, "Bebidas");
            Categoria mercearia = categoria(categorias, "Mercearia");
            Categoria limpeza = categoria(categorias, "Limpeza");
            Categoria higiene = categoria(categorias, "Higiene pessoal");
            Categoria laticinios = categoria(categorias, "Laticínios");
            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome("Distribuidora Exemplo");
            fornecedor = fornecedores.save(fornecedor);
            produto(produtos, "Arroz branco 5 kg", "7891000000011", "18.50", "26.90", 48, 10, mercearia, fornecedor);
            produto(produtos, "Feijão carioca 1 kg", "7891000000012", "5.20", "8.49", 35, 10, mercearia, fornecedor);
            produto(produtos, "Açúcar refinado 1 kg", "7891000000013", "3.10", "4.99", 42, 10, mercearia, fornecedor);
            produto(produtos, "Café torrado 500 g", "7891000000014", "15.00", "21.90", 26, 8, mercearia, fornecedor);
            produto(produtos, "Macarrão espaguete 500 g", "7891000000015", "2.80", "4.79", 52, 12, mercearia, fornecedor);
            produto(produtos, "Óleo de soja 900 ml", "7891000000016", "5.50", "7.99", 30, 10, mercearia, fornecedor);
            produto(produtos, "Leite integral 1 L", "7891000000017", "3.60", "5.49", 22, 8, laticinios, fornecedor);
            produto(produtos, "Queijo muçarela 200 g", "7891000000018", "8.90", "13.99", 14, 5, laticinios, fornecedor);
            produto(produtos, "Refrigerante cola 2 L", "7891000000019", "6.20", "9.99", 32, 8, bebidas, fornecedor);
            produto(produtos, "Água mineral 500 ml", "7891000000020", "1.10", "2.49", 90, 20, bebidas, fornecedor);
            produto(produtos, "Suco de uva 1 L", "7891000000021", "6.90", "10.99", 18, 6, bebidas, fornecedor);
            produto(produtos, "Detergente neutro 500 ml", "7891000000022", "1.60", "2.99", 40, 10, limpeza, fornecedor);
            produto(produtos, "Sabão em pó 800 g", "7891000000023", "7.50", "11.99", 24, 8, limpeza, fornecedor);
            produto(produtos, "Papel higiênico 4 rolos", "7891000000024", "4.90", "8.49", 36, 10, higiene, fornecedor);
            produto(produtos, "Creme dental 90 g", "7891000000025", "2.90", "5.99", 28, 8, higiene, fornecedor);
        };
    }
    private Categoria categoria(CategoriaRepository repo, String nome) {
        return repo.findAll().stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst().orElseGet(() -> {
            Categoria c = new Categoria(); c.setNome(nome); return repo.save(c);
        });
    }
    private void produto(ProdutoRepository repo, String nome, String codigo, String custo, String venda, int estoque, int minimo, Categoria categoria, Fornecedor fornecedor) {
        Produto p = new Produto();
        p.setNome(nome); p.setCodigoBarras(codigo); p.setPrecoCusto(new BigDecimal(custo));
        p.setPrecoVenda(new BigDecimal(venda)); p.setQuantidadeEstoque(estoque); p.setEstoqueMinimo(minimo);
        p.setCategoria(categoria); p.setFornecedor(fornecedor); repo.save(p);
    }
}
