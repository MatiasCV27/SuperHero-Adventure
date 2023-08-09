package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.util.LoginSuccessMessage;

@Configuration
public class WebSecurityConfig  {
    
    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private LoginSuccessMessage successMessage;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/","/css/**","/js/**","/images/**","/Vistas/Eventos/Galeria",
                "/Vistas/SuperHeroes/Galeria","/Vistas/Habilidades/Galeria","/Vistas/Organizaciones/Galeria",
                "/Vistas/Planetas/Galeria","/Vistas/Villanos/Galeria").permitAll()
                .requestMatchers("/Vistas/Eventos/").hasAnyRole("USER")
                .requestMatchers("/Vistas/Eventos/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Eventos/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Eventos/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Eventos/Eliminar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Habilidades/").hasAnyRole("USER")
                .requestMatchers("/Vistas/Habilidades/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Habilidades/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Habilidades/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Habilidades/Eliminar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Organizaciones/").hasAnyRole("USER")
                .requestMatchers("/Vistas/Organizaciones/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Organizaciones/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Organizaciones/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Organizaciones/Eliminar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Planetas/").hasAnyRole("USER")
                .requestMatchers("/Vistas/Planetas/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Planetas/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Planetas/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Planetas/Eliminar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/SuperHeroes/").hasAnyRole("USER")
                .requestMatchers("/Vistas/SuperHeroes/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/SuperHeroes/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/SuperHeroes/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/SuperHeroes/Eliminar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Villanos/").hasAnyRole("USER")
                .requestMatchers("/Vistas/Villanos/Registrar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Villanos/Guardar").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Villanos/Editar/**").hasAnyRole("ADMIN")
                .requestMatchers("/Vistas/Villanos/Eliminar/**").hasAnyRole("ADMIN")

                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .successHandler(successMessage)
                .loginPage("/login")
                .permitAll()
            )
            .logout((logout) -> logout.permitAll());   
        return http.build();
    }
 
    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
        
        builder.jdbcAuthentication()
        .dataSource(dataSource)
        .passwordEncoder(passEncoder)
        .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
        .authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id = u.id WHERE u.username = ?");

    }
}
