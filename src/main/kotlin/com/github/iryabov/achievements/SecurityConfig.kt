package com.github.iryabov.achievements

import net.n2oapp.framework.api.context.ContextEngine
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
class SecurityConfig(val userService: UserService): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().and().csrf().disable()
    }

    @EventListener(AuthenticationSuccessEvent::class)
    fun onSuccessLogin(event: AuthenticationSuccessEvent) {
        val principal = event.authentication.principal
        val attributes = (principal as DefaultOidcUser).attributes
        userService.loadUser(attributes["email"] as String, attributes["name"] as String, attributes["picture"] as String)
    }

    @Bean
    fun oauthContextEngine() = OAuthContextEngine()

    class OAuthContextEngine: ContextEngine {
        override fun get(param: String?, baseParams: MutableMap<String, Any>?): Any = get(param)
        override fun get(name: String?): Any {
            val principal = SecurityContextHolder.getContext()?.authentication?.principal
            val attributes = (principal as DefaultOidcUser).attributes
            return attributes[name] ?: ""
        }
        override fun set(dataSet: MutableMap<String, Any>?, baseParams: MutableMap<String, Any>?) {}
        override fun set(dataSet: MutableMap<String, Any>?) {}
    }
}