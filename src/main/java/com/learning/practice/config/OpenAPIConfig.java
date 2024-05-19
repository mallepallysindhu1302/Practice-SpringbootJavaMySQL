package com.learning.practice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@OpenAPIDefinition(
        info = @Info(
                title = "Patient-Doctor API",
                description = "Doing CRUD Operation",
                summary = "This API will add, delete, create and update",
                termsOfService = "T&C",
                contact = @Contact(
                        name = "Sindhu",
                        email = "mallepallysindhu1302@gmail.com"
                ),
                license = @License(
                        name = "Sindhu Mallepally"
                ),
                version = "v1"
        ),
        servers = {
                @Server(
                 description = "local1",
                        url = "http://localhost:8081/"
                ),
                @Server(
                        description = "local2",
                        url = "http://localhost:8081/"
                ),
        }
)
public class OpenAPIConfig {
}


