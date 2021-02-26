package com.github.iryabov.achievements

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.EventListener
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserLoadingEvent {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var context: OAuthContextEngine

    @Value("\${admins}")
    lateinit var admins: List<String>

    @EventListener(AuthenticationSuccessEvent::class)
    fun onSuccessLogin(event: AuthenticationSuccessEvent) {
        val principal = event.authentication.principal
        val attributes = (principal as DefaultOidcUser).attributes
        val email = attributes["email"] as String
        userService.loadUser(
            email,
            attributes["name"] as String,
            attributes["picture"] as String
        )
        context.set(userService.loadUserData(email))
        context.set("roles", if (admins.contains(email)) listOf("admin") else emptyList())
    }
}