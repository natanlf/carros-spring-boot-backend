package com.natancode.carros.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.natancode.carros.security.JWTAuthenticationFilter;
import com.natancode.carros.security.JWTAuthorizationFilter;
import com.natancode.carros.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
 	private static final String[] PUBLIC_MATCHERS = { //Tudo que virer apartir de h2-console está liberado
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = { //Acesso de leitura
			"/carros/**",
			"/categorias/**",
			"/estados/**"
			
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = { //Acesso de leitura
			"/clientes/**",
			"/clientes/picture",
			"/auth/forgot/**"
	};
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//se nos profiles ativos, estiver no profile test, significa que preciso usar o H2
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable(); //Libera o acesso ao H2
        }
		
		http.cors().and().csrf().disable();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //Só permite métodos de leitura
		.antMatchers(PUBLIC_MATCHERS).permitAll()
		.anyRequest().authenticated(); //todos os outros caminhos é exigido autenticação
		
		//Tenta autenticar usuário e senha. Caso consiga, gera o token e o envia no cabeçalho da resposta 
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		
		//Para garantir que o nosso sistema não vai usar sessão de usuário
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	//A partir do auth vou dizer quem é o UserDetailsService e quem é o passwordEncoder
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEnconder());
		}
	
	//Estou permitindo acessos ao meu endpoint usando as configurações básicas de cors
		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			
			CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
			//precisamos ter opções de cors pois o put por exemplo não estava funcionando com configurações básicas de cors
			configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);
			return source;
		}
		
		//Usado para a criptografia da senha
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEnconder() {
			return new BCryptPasswordEncoder();
		}
}
