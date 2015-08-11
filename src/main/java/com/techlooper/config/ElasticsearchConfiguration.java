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
 * Created by phuonghqh on 10/13/14.
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.techlooper.repository.elasticsearch")
public class ElasticsearchConfiguration {

  @Resource
  private Environment environment;

  @Bean
  public FactoryBean<TransportClient> transportClient() throws Exception {
    TransportClientFactoryBean factory = new TransportClientFactoryBean();
    factory.setClusterName(environment.getProperty("elasticsearch.cluster.name"));
    factory.setClusterNodes(environment.getProperty("elasticsearch.host"));
    return factory;
  }

  @Bean
  public ElasticsearchOperations elasticsearchTemplate() throws Exception {
    return new ElasticsearchTemplate(transportClient().getObject());
  }
}
