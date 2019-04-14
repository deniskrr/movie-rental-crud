package config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

    @Bean
    JdbcOperations jdbcOperations() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.setDataSource(dataSource());

        return jdbcTemplate;
    }

    @Bean
    DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();


        basicDataSource.setUrl("jdbc:postgresql://127.0.0.1:5433/Laboratory");
        basicDataSource.setUsername(System.getProperty("USERNAMESQL"));
        basicDataSource.setPassword(System.getProperty("PASSWORDSQL"));
        basicDataSource.setInitialSize(2);
    }
}
