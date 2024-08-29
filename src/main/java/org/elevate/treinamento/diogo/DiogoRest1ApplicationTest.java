package org.elevate.treinamento.diogo;

import java.util.Properties;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.persistence.EntityManagerFactory;

@SpringBootApplication
@EntityScan({ "org.elevate.treinamento.diogo.domain.entity" })
@ComponentScan({ 	"org.elevate.treinamento.diogo.rest", 
					"org.elevate.treinamento.diogo.service", 
					"org.elevate.treinamento.diogo.security",})
@EnableJpaRepositories(basePackages = { "org.elevate.treinamento.diogo.repository" })
@EnableConfigurationProperties
@EnableAutoConfiguration
@Profile("test")
public class DiogoRest1ApplicationTest implements WebMvcConfigurer {

	
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan("org.elevate.treinamento.diogo.repository", 
													"org.elevate.treinamento.diogo.domain.entity", 
													"org.elevate.treinamento.diogo.service");

		final Properties properties = jpaProperties();
		entityManagerFactoryBean.setJpaProperties(properties);
		
		return entityManagerFactoryBean;
	}
	  @Bean
	    public JpaVendorAdapter jpaVendorAdapter() {
	        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
	        hibernateJpaVendorAdapter.setShowSql(true);
	        hibernateJpaVendorAdapter.setGenerateDdl(true);
	        hibernateJpaVendorAdapter.setDatabase(Database.H2);
	        return hibernateJpaVendorAdapter;
	    }
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
	public static void main(String[] args) {
		SpringApplication.run(DiogoRest1ApplicationTest.class, args);
	}
	
	protected Properties jpaProperties() {
		final Properties jpaProperties = new Properties();

		// Allow Hibernate to update DB structure
		jpaProperties.put("spring.datasource.url", "jdbc:h2:mem:testdb");
		jpaProperties.put("spring.datasource.driverClassName", "org.h2.Driver");
		jpaProperties.put("spring.datasource.username", "sa");
		jpaProperties.put("spring.datasource.password", "");
		jpaProperties.put("spring.jpa.database-platform", "org.hibernate.dialect.H2Dialect");
		jpaProperties.put("spring.h2.console.enabled", true);
		
		jpaProperties.put("spring.jpa.generate-ddl", true);
		jpaProperties.put("spring.jpa.hibernate.ddl-auto", "create-drop");
			
		jpaProperties.put("hibernate.show_sql", false);
		jpaProperties.put("hibernate.format_sql", true);
		
		jpaProperties.put("org.hibernate.SQL", "true");
		

		return jpaProperties;
	}

}
