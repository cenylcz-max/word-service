package com.cenylcz.configuration.database;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = "com.cenylcz.jpa.repository",
        entityManagerFactoryRef = "wordEntityManager", transactionManagerRef = "wordTransactionManager")
public class WordRepositoryConfiguration {

    private final Environment environment;

    public WordRepositoryConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "wordTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("wordEntityManager")EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }

    @Bean(name = "wordEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(wordDatasource());
        em.setPackagesToScan(new String[] {"com.cenylcz.domain.dictionary"});
        em.setPersistenceProvider(new HibernatePersistenceProvider());
        em.setJpaProperties(jpaProperties());
        return em;
    }

    /**
     * Datasource
     *
     * @return
     */
    @Bean
    public DataSource wordDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.word-service.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    /**
     * Properties
     *
     * @return
     */
    Properties jpaProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        properties.setProperty("hibernate.show_sql", "false");
        return properties;
    }
}
