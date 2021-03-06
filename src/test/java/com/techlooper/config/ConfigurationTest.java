package com.techlooper.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.techlooper.converter.LocaleConverter;
import com.techlooper.repository.JobSearchAPIConfigurationRepository;
import com.techlooper.repository.JsonConfigRepository;
import com.techlooper.repository.TechnicalTermRepository;
import com.techlooper.repository.talentsearch.query.GithubTalentSearchQuery;
import com.techlooper.repository.talentsearch.query.VietnamworksTalentSearchQuery;
import com.techlooper.service.*;
import com.techlooper.service.impl.*;
import freemarker.template.Template;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URISyntaxException;


/**
 * Created by phuonghqh on 10/20/14.
 */

@Configuration
@PropertySources({
        @PropertySource("classpath:techlooper.properties"),
        @PropertySource("classpath:secret.properties")})
public class ConfigurationTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Resource
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JobSearchAPIConfigurationRepository apiConfiguration() {
        return new JobSearchAPIConfigurationRepository();
    }

    @Bean
    public JobSearchService jobSearchService() {
        return new VietnamWorksJobSearchService();
    }

    @Bean
    public TechnicalTermRepository technicalTermRepository() {
        return new TechnicalTermRepository();
    }

    @Bean
    public JsonConfigRepository jsonConfigRepository() {
        return new JsonConfigRepository();
    }

    @Bean
    public JobQueryBuilder jobQueryBuilder() {
        return new JobQueryBuilderImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public VietnamWorksUserService vietnamworksUserService() {
        return new VietnamWorksUserServiceImpl();
    }

    @Bean
    public Mapper dozerBeanMapper() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            protected void configure() {
                mapping(FacebookProfile.class, com.techlooper.entity.FacebookProfile.class).fields("locale", "locale", FieldsMappingOptions.customConverter(LocaleConverter.class));
            }
        };
        dozerBeanMapper.addMapping(builder);
        return dozerBeanMapper;
    }

    @Bean(name = "GITHUBTalentSearchDataProcessor")
    public GithubTalentSearchDataProcessor githubTalentSearchDataProcessor() {
        return new GithubTalentSearchDataProcessor();
    }

    @Bean(name = "VIETNAMWORKSTalentSearchDataProcessor")
    public VietnamworksTalentSearchDataProcessor vietnamworksTalentSearchDataProcessor() {
        return new VietnamworksTalentSearchDataProcessor();
    }

    @Bean(name = "GITHUBTalentSearchQuery")
    public GithubTalentSearchQuery githubTalentSearchQuery() {
        return new GithubTalentSearchQuery();
    }

    @Bean(name = "VIETNAMWORKSTalentSearchQuery")
    public VietnamworksTalentSearchQuery vietnamworksTalentSearchQuery() {
        return new VietnamworksTalentSearchQuery();
    }

    @Bean
    public TextEncryptor textEncryptor() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(environment.getProperty("core.textEncryptor.password"));
        return textEncryptor;
    }

    @Bean
    public JobStatisticService jobStatisticService() {
        return new VietnamWorksJobStatisticService();
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyServiceImpl();
    }

    @Bean
    public SalaryReviewService salaryReviewService() {
        return new SalaryReviewServiceImpl();
    }

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        return mailSender;
    }

    @Bean
    public freemarker.template.Configuration freemakerConfig() throws IOException, URISyntaxException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_22);
        return cfg;
    }

    @Bean
    public Template salaryReviewReportTemplateEn(freemarker.template.Configuration freemakerConfig) throws IOException {
        return null;
    }

    @Bean
    public Template salaryReviewReportTemplateVi(freemarker.template.Configuration freemakerConfig) throws IOException {
        return null;
    }

    @Bean
    public JsonNode vietnamworksConfiguration() throws IOException {
        return null;
    }

    @Bean
    public MimeMessage salaryReviewMailMessage(JavaMailSender mailSender) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        return mailMessage;
    }

    @Bean
    public MimeMessage getPromotedMailMessage(JavaMailSender mailSender) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        return mailMessage;
    }

    @Bean
    public Template getPromotedTemplateEn(freemarker.template.Configuration freemakerConfig) throws IOException {
        return null;
    }

    @Bean
    public Template getPromotedTemplateVi(freemarker.template.Configuration freemakerConfig) throws IOException {
        return null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
