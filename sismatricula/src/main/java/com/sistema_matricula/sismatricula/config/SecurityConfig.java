package com.sistema_matricula.sismatricula.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configuração de segurança da aplicação.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Define o bean BCryptPasswordEncoder para criptografia de senhas.
     * @return instância de BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Configura o filtro de segurança HTTP.
     * Esta configuração básica permite acesso a todos os endpoints por enquanto.
     * Pode ser ajustada conforme necessário para implementar autenticação real.
     * 
     * @param http configuração de segurança HTTP
     * @return filtro de segurança configurado
     * @throws Exception se ocorrer algum erro na configuração
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Desabilitando CSRF temporariamente
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()  // Permitindo acesso a todos os endpoints por enquanto
            )
            .httpBasic(withDefaults());
        
        return http.build();
    }
} 