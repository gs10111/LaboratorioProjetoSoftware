package com.sistema_matricula.sismatricula.Helpers;

/**
 * Classe auxiliar para validações gerais no sistema.
 */
public class ValidationHelper {

    /**
     * Verifica se um email é válido (exemplo simples).
     * @param email string de email
     * @return true se for válido, false caso contrário
     */
    public static boolean isEmailValido(String email) {
        // TODO: implementar validação real
        return email != null && email.contains("@");
    }

    /**
     * Verifica se uma senha atende aos requisitos mínimos de segurança.
     * @param senha string de senha
     * @return true se for válida, false caso contrário
     */
    public static boolean isSenhaValida(String senha) {
        // TODO: implementar validação real (ex: tamanho mínimo, caracteres especiais, etc)
        return senha != null && senha.length() >= 6;
    }
    
    /**
     * Verifica se um RA (Registro Acadêmico) é válido.
     * @param ra string de RA
     * @return true se for válido, false caso contrário
     */
    public static boolean isRAValido(String ra) {
        // TODO: implementar validação real
        return ra != null && !ra.isEmpty();
    }
    
    /**
     * Verifica se um SIAPE é válido.
     * @param siape string de SIAPE
     * @return true se for válido, false caso contrário
     */
    public static boolean isSIAPEValido(String siape) {
        // TODO: implementar validação real
        return siape != null && !siape.isEmpty();
    }
} 