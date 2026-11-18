# Sistema de Gestão de Mercado (SGM)

Aplicação administrativa monolítica em Java 21, Spring Boot 3, Thymeleaf, Bootstrap 5 e H2 em arquivo.

## Requisitos
- JDK 21
- IntelliJ IDEA
- Maven 3.9+ (ou Maven integrado ao IntelliJ)

## Executar
1. Abra a pasta do projeto no IntelliJ e aguarde a importação Maven.
2. Configure JDK 21 em Project Structure.
3. Execute `com.mercado.MercadoApplication` ou rode `mvn spring-boot:run`.
4. Acesse http://localhost:8080.

## Acesso inicial
- Usuário: `admin`
- Senha: `admin123`

**Altere as credenciais antes de qualquer uso real.** Configure `ADMIN_USERNAME` e `ADMIN_PASSWORD` como variáveis de ambiente. O administrador inicial é criado somente se o usuário configurado ainda não existir.

## Banco
O H2 é persistido em `./data/mercado-db`. Para console local: http://localhost:8080/h2-console
JDBC URL: `jdbc:h2:file:./data/mercado-db;DB_CLOSE_ON_EXIT=FALSE`, usuário `sa`, senha vazia por padrão.
Não exponha o console H2 em produção.

## Funcionalidades incluídas
- Login e proteção das rotas administrativas com Spring Security e BCrypt.
- Cadastros de produtos, categorias e fornecedores.
- Entrada e saída de estoque com saldo anterior/posterior.
- Venda com múltiplos itens, preço e custo unitários históricos.
- Cancelamento de venda com devolução ao estoque.
- Dashboard e relatório financeiro estimado.
- Exportação CSV de vendas.

## Observações
Este é um projeto inicial funcional de portfólio, não um sistema fiscal/PDV homologado. Recursos avançados descritos no escopo original — como PDF, gráficos analíticos, filtros por período, validação matemática de CNPJ, paginação visual e suíte abrangente de testes — ainda precisam de implementação e validação antes de uso em produção.
