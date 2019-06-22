package rpn.bus;

import rpn.bus.messages.Message;

public interface Consumer {
    void receive(Message message);
}
