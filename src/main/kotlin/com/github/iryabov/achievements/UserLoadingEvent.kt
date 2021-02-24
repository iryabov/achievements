package com.github.iryabov.achievements

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.stereotype.Component

@Component
class UserLoadingEvent {
    @Autowired
    lateinit var userService: UserService
    @Autowired
    lateinit var context: SecurityConfig.OAuthContextEngine

    @EventListener(AuthenticationSuccessEvent::class)
    fun onSuccessLogin(event: AuthenticationSuccessEvent) {
        val principal = event.authentication.principal
        val attributes = (principal as DefaultOidcUser).attributes
        userService.loadUser(
                attributes["email"] as String,
                attributes["name"] as String,
                attributes["picture"] as String)
        context.set(userService.loadUserData(attributes["email"] as String))
    }
}