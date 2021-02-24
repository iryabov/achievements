package com.github.iryabov.achievements

import net.n2oapp.framework.api.context.ContextEngine
import net.n2oapp.framework.ui.context.SessionContextEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser

@Configuration
class SecurityConfig(): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().and().csrf().disable()
    }

    @Bean
    fun oauthContextEngine() = OAuthContextEngine()

    class OAuthContextEngine: SessionContextEngine() {
        override fun get(name: String?): Any {
            val principal = SecurityContextHolder.getContext()?.authentication?.principal
            val attributes = (principal as DefaultOidcUser).attributes
            return attributes[name] ?: super.get(name)
        }
    }

}