package com.github.iryabov.achievements

import net.n2oapp.framework.api.context.ContextEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser

@Configuration
class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().and().csrf().disable()
    }

    @Bean
    fun springContextEngine() = OAuthContextEngine()

    class OAuthContextEngine: ContextEngine {
        override fun get(param: String?, baseParams: MutableMap<String, Any>?): Any {
            TODO("Not yet implemented")
        }

        override fun get(name: String?): Any {
            val principal = SecurityContextHolder.getContext()?.authentication?.principal
            val attributes = (principal as DefaultOidcUser).attributes
            return attributes[name] ?: ""
        }

        override fun set(dataSet: MutableMap<String, Any>?, baseParams: MutableMap<String, Any>?) {
            TODO("Not yet implemented")
        }

        override fun set(dataSet: MutableMap<String, Any>?) {
            TODO("Not yet implemented")
        }

    }
}