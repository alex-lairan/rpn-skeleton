package rpn.bus;

import rpn.bus.messages.Message;

public interface Bus {
    void publish(Message message);
    void subscribe(String messageType, Consumer consumer);
}
