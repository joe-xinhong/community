package lift.miao.community.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanglf
 * @description
 * @since 2019/1/4
 **/
@Configuration
public class DataSourceConfig {

    @Value("${define.datasource.type}")
    private Class<? extends DataSource> dataSourceType;


    /**
     * 主数据源
     *
     * @return
     */
    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "define.datasource.write")
    public DataSource writeDataSource() {
        DruidDataSource source = DataSourceBuilder.create().type(DruidDataSource.class).build();
        /*source.setUrl("jdbc:mysql://localhost:3306/community?characterEncoding=UTF-8&useUnicode=true");*/
        source.setUrl("jdbc:mysql://148.70.58.213:3306/community?characterEncoding=UTF-8&useUnicode=true&allowMultiQueries=true&userSSL=false&serverTimezone=GMT%2B8");
        source.setUsername("root");
        source.setPassword("mysql");
        /*source.setDriverClassName("com.mysql.jdbc.Driver");*/
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setDbType("com.alibaba.druid.pool.DruidDataSource");
        source.setInitialSize(5);
        source.setMinIdle(5);
        source.setMaxActive(20);
        source.setMaxWait(60000);
        source.setTimeBetweenEvictionRunsMillis(60000);
        source.setMinEvictableIdleTimeMillis(300000);
        source.setValidationQuery("SELECT 1 FROM DUAL");
        source.setTestWhileIdle(true);
        source.setTestOnBorrow(false);
        source.setTestOnReturn(false);
        source.setPoolPreparedStatements(true);
        source.setMaxOpenPreparedStatements(-1);
        source.setMaxPoolPreparedStatementPerConnectionSize(20);
        source.setUseGlobalDataSourceStat(true);
        return source;
    }

}
