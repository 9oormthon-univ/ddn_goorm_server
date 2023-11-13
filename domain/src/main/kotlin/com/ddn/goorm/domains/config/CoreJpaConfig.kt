package com.ddn.goorm.domains.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.ddn.goorm.domains"])
@EnableJpaRepositories(basePackages = ["com.ddn.goorm.domains"])
internal class CoreJpaConfig