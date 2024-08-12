package dev.khtml.hackathon.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "dev.khtml.hackathon")
@EnableJpaRepositories(basePackages = "dev.khtml.hackathon")
@EnableJpaAuditing
class CoreJpaConfig {

}
