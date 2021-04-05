package com.github.iryabov.achievements

import net.n2oapp.criteria.dataset.DataSet
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class NotificationService(val emailSender: JavaMailSender) {

    @Value("\${admins}")
    lateinit var admins: List<String>

    private fun sendMessage(from: String, to: List<String>, subject: String, text: String) {
        val message = SimpleMailMessage()
        message.setFrom(from);
        message.setSubject(subject);
        message.setText(text);
        for (email in to) {
            message.setTo(email);
            emailSender.send(message);
        }
    }

    fun notifyAboutOrder(email: String, order: String) {
        sendMessage(email, admins, "[Achievements] Order created",
                "$email ordered $order.")
    }
}