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
class SecurityConfig(): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http!!.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login().and().csrf().disable()
    }

    @Bean
    fun oauthContextEngine() = OAuthContextEngine()

    class OAuthContextEngine: UserContextEngine() {
        override fun get(name: String?): Any {
            val principal = SecurityContextHolder.getContext()?.authentication?.principal
            val attributes = (principal as DefaultOidcUser).attributes
            return attributes[name] ?: super.get(name)
        }
    }

    open class UserContextEngine: ContextEngine {
        val data: MutableMap<String, Any> = HashMap()

        override fun get(param: String?, baseParams: MutableMap<String, Any>?): Any {
            return get(param)
        }

        override fun get(name: String?): Any {
            return data[name] ?: ""
        }

        override fun set(dataSet: MutableMap<String, Any>?, baseParams: MutableMap<String, Any>?) {
            set(dataSet)
        }

        override fun set(dataSet: MutableMap<String, Any>?) {
            if (dataSet != null) {
                data.putAll(dataSet)
            }
        }

    }
}