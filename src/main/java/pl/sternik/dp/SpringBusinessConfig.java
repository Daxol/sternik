package pl.sternik.dp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableJpaRepositories
//@SpringBootApplication
@EnableWebMvc
//@EntityScan(basePackages = {"pl.sternik"} )
//@EnableJpaRepositories(basePackages = {"pl.sternik"})
//@EnableAutoConfiguration
@ComponentScan(basePackages = {"pl.sternik.dp"})
//@ConfigurationProperties(value = "application.properties")
//@ImportResource({"application.properties"})//,"classpath:/database-config.xml"})
public class SpringBusinessConfig {


}
