package com.techlooper.config;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.Resource;

/**
 * Created by NguyenDangKhoa on 28/01/15.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = {"com.techlooper.repository.userimport", "com.techlooper.repository.talentsearch"},
        elasticsearchTemplateRef = "elasticsearchTemplateUserImport")
public class ElasticsearchUserImportConfiguration {

    @Resource
    private Environment environment;

    @Resource
    private TransportClient transportClientUserImport;

    @Bean
    public FactoryBean<TransportClient> transportClientUserImport() throws Exception {
        TransportClientFactoryBean factory = new TransportClientFactoryBean();
        factory.setClusterName(environment.getProperty("elasticsearch.userimport.cluster.name"));
        factory.setClusterNodes(environment.getProperty("elasticsearch.userimport.host"));
        return factory;
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplateUserImport() {
        return new ElasticsearchTemplate(transportClientUserImport);
    }
}
