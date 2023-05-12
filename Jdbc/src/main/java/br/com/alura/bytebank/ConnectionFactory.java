package br.com.alura.bytebank;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionFactory {
	
	public Connection recuperarConexao() {
		try {
			return createDataSource().getConnection();
					
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}
	
	private HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://172.17.0.3:3306/byte_bank");
        config.setUsername("root");
        config.setPassword("Adison@7");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }
}
