package com.example.parking.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis - plus 配置
 */
@Configuration
@MapperScan("${mybatis-plus.mapper-package}")
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
