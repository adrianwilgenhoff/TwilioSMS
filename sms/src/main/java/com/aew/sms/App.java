package com.aew.sms;

import java.net.URI;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 * Es un ejemplo basico del uso de la api de twilio para el envio de sms,
 * tambien llamadas, comunmente usado para realizar verificaciones a travez de
 * codigos enviados a los telefonos moviles.
 * 
 * @author Adrian E. Wilgenhoff
 * @version 1.0
 */
public class App {

    public static final String ACCOUNT_SID = "AC6ca7f881bbf379a86962639717dd12ec";
    public static final String AUTH_TOKEN = "41a9844b6b2cbc812994e37ce8260c9b";
    public static final String TWILIO_NUMBER = "+12567434134";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        /*
         * Twilio.init () se requiere una vez para configurar el entorno de Twilio con
         * nuestra Cuenta Sid y Token únicos
         */
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        /**
         * Message.creator () requiere 3 parámetros: A número de teléfono, Desde número
         * de teléfono y el cuerpo del mensaje
         */

        Message message = Message.creator(new PhoneNumber("+542494655632"), new PhoneNumber(TWILIO_NUMBER),
                "Sample Twilio MMS using Java").create();

        /**
         * Podemos mezclar y combinar texto e imágenes, para que esto funcione, el
         * teléfono receptor debe admitir mensajes de medios:
         */
        Message messageWithImage = Message
                .creator(new PhoneNumber("+542494655632"), new PhoneNumber(TWILIO_NUMBER),
                        "Sample Twilio MMS using Java")
                .setMediaUrl(Promoter.listOfOne(URI.create("http://link/de/una/imagen"))).create();

        /**
         * Tambien podemos recuperar el estado de nuestros mensajes enviados: En
         * cola,Envío,Enviado, Entregado,No Entregado,Falló.
         */
        ResourceSet<Message> messagesList = Message.reader().read();
        for (Message messageState : messagesList) {
            System.out.println(messageState.getSid() + " : " + messageState.getStatus());
        }

    }
}
