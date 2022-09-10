package mx.org.citicos.controller;

import javax.activation.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

public class ConfigDataSource {

	@Bean public static DataSource source()
    {
  
        DataSourceBuilder<?> dSB
            = DataSourceBuilder.create();
        dSB.driverClassName("com.mysql.jdbc.Driver");
  
        // MySQL specific url with database name
        dSB.url("jdbc:mysql://localhost:3306/syscadillo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
  
        // MySQL username credential
        dSB.username("root");
  
        // MySQL password credential
        dSB.password("Azul2018*11");
  
        return (DataSource) dSB.build();
    }
}
