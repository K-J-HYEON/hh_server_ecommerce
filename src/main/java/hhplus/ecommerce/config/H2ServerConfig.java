//package hhplus.ecommerce.config;
//
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import java.sql.SQLException;
//
//@Profile("local")
//@Configuration
//public class H2ServerConfig {
//
//    @Bean
//    public Server h2TcpServer() throws SQLException {
//        return Server.createTcpServer().start();
//    }
//}