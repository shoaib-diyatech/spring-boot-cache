package com.coderkan.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alachisoft.ncache.spring.NCacheCacheManager;
import com.alachisoft.ncache.spring.configuration.SpringConfigurationManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;

@Configuration
@EnableCaching
public class NCacheConfig {

    @Bean
    public CacheManager cacheManager() {
        SpringConfigurationManager springConfigurationManager = new SpringConfigurationManager();
        //URL resource = getClass().getClassLoader().getResource("ncache-spring.xml");
        
           // Load the external configuration file
           String configFilePath = System.getProperty("ncache.config.path", "target/ncache-spring.xml");
           Path configPath = Paths.get(configFilePath);
   
        
        // springConfigurationManager.setConfigFile(resource.getPath());
        springConfigurationManager.setConfigFile(configPath.toAbsolutePath().toString());
        NCacheCacheManager cacheManager = new NCacheCacheManager();
        cacheManager.setSpringConfigurationManager(springConfigurationManager);
        return cacheManager;
    }
}
