package com.mercado.model;
public final class Enums {
 private Enums() {}
 public enum StatusVenda { CONCLUIDA, CANCELADA }
 public enum FormaPagamento { DINHEIRO, PIX, CARTAO_CREDITO, CARTAO_DEBITO }
 public enum TipoMovimentacao { ENTRADA, SAIDA }
 public enum MotivoMovimentacao { COMPRA, VENDA, DEVOLUCAO, PERDA, PRODUTO_DANIFICADO, VENCIMENTO, AJUSTE, OUTROS }
}
