package rpn.bus;

import rpn.bus.messages.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryBus implements Bus {
    private final HashMap<String, List<Consumer>> consumerByType = new HashMap<>();


    @Override
    public void publish(Message message) {
        List<Consumer> consumers = consumerByType.get(message.messageType());

        if(consumers == null) { return; }

        consumers.forEach(e -> e.receive(message));
    }

    @Override
    public void subscribe(String messageType, Consumer consumer) {
        List<Consumer> consumers = consumerByType.computeIfAbsent(messageType, k -> new ArrayList<>());

        consumers.add(consumer);
    }
}
