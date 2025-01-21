package com.hospital.seguimiento_pacientes.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.accountSid}")
    private String accountSid;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.fromNumber}")
    private String fromNumber;

    public void enviarMensaje(String toNumber, String mensaje) {
        // Inicializa Twilio con las credenciales
        Twilio.init(accountSid, authToken);

        // Enviar el mensaje
        Message message = Message.creator(
                new PhoneNumber(toNumber),  // Número al que se enviará el mensaje
                new PhoneNumber(fromNumber), // Número Twilio
                mensaje                     // Contenido del mensaje
        ).create();

        // Imprimir el SID del mensaje (para debug)
        System.out.println("Mensaje enviado: " + message.getSid());
    }
}
