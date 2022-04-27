package ru.kosmos.restaurantvoting.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kosmos.restaurantvoting.util.JsonUtil;

@Configuration
@Slf4j
public class AppConfig {

    //    https://stackoverflow.com/a/46947975/548473
    @Bean
    Module module() {
        return new Hibernate5Module();
    }

    @Autowired
    public void storeObjectMapper(ObjectMapper objectMapper) {
        JsonUtil.setMapper(objectMapper);
    }
}