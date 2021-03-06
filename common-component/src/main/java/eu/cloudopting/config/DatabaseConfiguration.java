package eu.cloudopting.config;


import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@Configuration
@EnableJpaRepositories("eu.cloudopting.repository")
@EnableJpaAuditing(auditorAwareRef = "springSecurityAuditorAware")
@EnableTransactionManagement
public class DatabaseConfiguration implements EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    private RelaxedPropertyResolver propertyResolver;

    private Environment env;



    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
    }

    /*@Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingClass(name = "eu.cloudopting.config.HerokuDatabaseConfiguration")
    @Profile("!" + Constants.SPRING_PROFILE_CLOUD)
    public DataSource dataSource() {
        log.debug("Configuring Datasource");
        if (propertyResolver.getProperty("url") == null && propertyResolver.getProperty("databaseName") == null) {
            log.error("Your database connection pool configuration is incorrect! The application" +
                    "cannot start. Please check your Spring profile, current profiles are: {}",
                    Arrays.toString(env.getActiveProfiles()));

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(propertyResolver.getProperty("dataSourceClassName"));
        if (propertyResolver.getProperty("url") == null || "".equals(propertyResolver.getProperty("url"))) {
            config.addDataSourceProperty("databaseName", propertyResolver.getProperty("databaseName"));
            config.addDataSourceProperty("serverName", propertyResolver.getProperty("serverName"));
        } else {
            config.addDataSourceProperty("url", propertyResolver.getProperty("url"));
        }
        config.addDataSourceProperty("user", propertyResolver.getProperty("username"));
        config.addDataSourceProperty("password", propertyResolver.getProperty("password"));

        return new HikariDataSource(config);
    }*/

    @Bean
    public DataSource dataSource() {
    	log.debug("Setting datasource");
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(propertyResolver.getProperty("driver"));
        dataSource.setUrl(propertyResolver.getProperty("url"));
        dataSource.setUsername(propertyResolver.getProperty("username"));
        dataSource.setPassword(propertyResolver.getProperty("password"));
        dataSource.setMaxActive(100);
        dataSource.setMaxIdle(8);
        dataSource.setMaxWait(30000);
        //Start Issue #104 Fix
        //See http://stackoverflow.com/questions/29620265/postgres-connection-has-been-closed-error-in-spring-boot
        dataSource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty("test-on-borrow")));
        dataSource.setRemoveAbandoned(Boolean.valueOf(propertyResolver.getProperty("remove-abandoned")));
        dataSource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty("test-while-idle")));
        dataSource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty("test-on-return")));
        dataSource.setValidationQuery(propertyResolver.getProperty("validation-query"));
        //End Issue #104 Fix
       /* List<String> sqls=new ArrayList<String>();
        sqls.add("SET SCHEMA = '" + propertyResolver.getProperty("databaseName") + "'");*/
//        dataSource.setConnectionInitSqls(sqls);
        return dataSource;
    }



    @Bean
    public Hibernate4Module hibernate4Module() {
        return new Hibernate4Module();
    }
}
