package com.airfrance.technicaltest.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;


/**
 * Represents the configuration class for API documentation
 *
 * @author r-fonkoue
 */
@RequiredArgsConstructor
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.airfrance.technicaltest.dao",
        entityManagerFactoryRef = TechnicalTestDatabaseConfiguration.TECHNICAL_TEST_MANAGER,
        transactionManagerRef = TechnicalTestDatabaseConfiguration.TECHNICAL_TEST_TRANSACTION_MANAGER)
public class TechnicalTestDatabaseConfiguration {

    public static final String TECHNICAL_TEST_DATASOURCE = "technicalTestDatasource";
    public static final String TECHNICAL_TEST_MANAGER = "technicalTestEntityManager";
    public static final String TECHNICAL_TEST_TRANSACTION_MANAGER = "technicalTestTransactionManager";
    public static final String TECHNICAL_TEST_UNIT_NAME = "technicalTestUnitName";

    private final Environment env;

    @Bean(name = TECHNICAL_TEST_DATASOURCE)
    @ConfigurationProperties(prefix = "spring.technical-test.datasource")
    public DataSource cockpitDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = TECHNICAL_TEST_MANAGER)
    public LocalContainerEntityManagerFactoryBean technicalTestEntityManager(@Qualifier(TECHNICAL_TEST_DATASOURCE) final DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.airfrance.technicalTest.entity");
        em.setPersistenceUnitName(TECHNICAL_TEST_UNIT_NAME);

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.technical-test.datasource.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("spring.technical-test.datasource.show-sql"));
        properties.put("hibernate.format_sql", env.getProperty("spring.technical-test.datasource.format-sql"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = TECHNICAL_TEST_TRANSACTION_MANAGER)
    public PlatformTransactionManager technicalTestTransactionManager(@Qualifier(TECHNICAL_TEST_MANAGER) final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
