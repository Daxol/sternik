package pl.sternik.dp;

//import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration

@ComponentScan(basePackages = {"pl.sternik"})
@ImportResource("classpath:/applicationContext.xml")//,"classpath:/database-config.xml"})
//@EnableWebMvc
public class SpringBusinessConfig {


}
