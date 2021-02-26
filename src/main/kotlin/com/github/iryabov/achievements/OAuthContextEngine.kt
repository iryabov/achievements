package com.github.iryabov.achievements

import net.n2oapp.framework.ui.context.SessionContextEngine
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.stereotype.Component

@Component
class OAuthContextEngine: SessionContextEngine() {
    override fun get(name: String?): Any {
        val principal = SecurityContextHolder.getContext()?.authentication?.principal
        val attributes = (principal as DefaultOidcUser).attributes
        return attributes[name] ?: super.get(name)
    }
}