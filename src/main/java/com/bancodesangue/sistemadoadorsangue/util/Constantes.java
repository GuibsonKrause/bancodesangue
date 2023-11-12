package com.bancodesangue.sistemadoadorsangue.util;

public class Constantes {

    // Exemplo de constantes relacionadas a validação de candidatos a doadores
    public static final int IDADE_MINIMA_DOADOR = 16;
    public static final int IDADE_MAXIMA_DOADOR = 69;
    public static final double PESO_MINIMO_DOADOR = 50.0;

    // Constantes de mensagens de erro
    public static final String ERRO_IDADE_INVALIDA = "Idade do doador fora do intervalo permitido.";
    public static final String ERRO_PESO_INVALIDO = "Peso do doador abaixo do mínimo requerido.";
    
    // Adicione outras constantes conforme necessário...

    private Constantes() {
        // Esta é uma classe de utilidades e não deve ser instanciada.
    }
}
