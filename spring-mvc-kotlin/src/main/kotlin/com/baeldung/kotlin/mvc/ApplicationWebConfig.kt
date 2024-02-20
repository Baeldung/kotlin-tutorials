package com.baeldung.kotlin.mvc

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring6.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

@Configuration
open class ApplicationWebConfig : WebMvcConfigurer, ApplicationContextAware {

    private var applicationContext: ApplicationContext? = null

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/welcome").setViewName("welcome");
    }

    @Bean
    open fun templateResolver(): SpringResourceTemplateResolver {
        return SpringResourceTemplateResolver()
                .apply { prefix = "classpath:/templates/" }
                .apply { suffix = ".html"}
                .apply { templateMode = TemplateMode.HTML }
                .apply { applicationContext?.let { setApplicationContext(it) } }
    }

    @Bean
    open fun templateEngine(): SpringTemplateEngine {
        return SpringTemplateEngine()
                .apply { setTemplateResolver(templateResolver()) }
    }

    @Bean
    open fun viewResolver(): ThymeleafViewResolver {
        return ThymeleafViewResolver()
                .apply { templateEngine = templateEngine() }
                .apply { order = 1 }
                .apply { excludedViewNames =  arrayOf("error") }
    }
}