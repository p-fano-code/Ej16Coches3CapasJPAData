package es.curso.cfg;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"es.curso"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"es.curso.modelo.persistencia"})
public class ConfigJPA {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/coches_jpa_data?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		ds.setUsername("root");
		ds.setPassword("");
		return ds;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean 
		entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = 
			new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		//Establecemos la implementacion de JPA que vamos a usar, en este caso
		//vamos a utilizar Hibernate
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		//IMPORTANTE, le decimos a la factoria donde tiene que ir a buscar clases
		//anotadas con anotaciones de JPA
		entityManagerFactoryBean.setPackagesToScan("es.curso.modelo.entidad");
		
		//Establecemos las propiedades de JPA, que estan muy ligadas al motor de ORM
		//que estemos usando
		Properties jpaProperties = new Properties();
		//dialecto para mysql
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		/* dialecto h2
		jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		*/
		//Establecemos como queremos que se comporte la aplicacion con las tablas
		//de base de datos.
		//2 valores principales:
		//1. update, JPA creara las tablas a partir de las clases anotadas si no existen 
		//o las modificara en caso de que cambien dichas anotaciones.
		//2. create-drop, JPA siempre borrara todas las tablas y las volvera a crear cuando
		//arranquemos la aplicacion, muy util para pruebas.
		jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		//Este propiedad sirve para ver el sql interno que ejecuta JPA cuando interactuamos
		//con la bbdd
		jpaProperties.put("hibernate.show_sql", "true");
		//Esta propiedad sirve par dar formato de salida mas legible al sql
		jpaProperties.put("hibernate.format_sql", "false");
		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}
	
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}
