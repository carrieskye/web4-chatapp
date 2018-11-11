package caro.controller;

import caro.domain.MessageService;
import caro.domain.PersonService;

public class ControllerFactory {

    public RequestHandler getController(String key, PersonService personService, MessageService messageService) {
        return createHandler(key, personService, messageService);
    }

    private RequestHandler createHandler(String handlerName, PersonService personService, MessageService messageService) {
        RequestHandler handler;
        try {
            Class<?> handlerClass = Class.forName("caro.controller." + handlerName);
            Object handlerObject = handlerClass.newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setModel(personService, messageService);
        } catch (Exception e) {
            throw new RuntimeException("Deze pagina bestaat niet!!!!");
        }
        return handler;
    }

}
