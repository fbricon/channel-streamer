package foo.bar;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;
import org.eclipse.microprofile.reactive.messaging.OnOverflow.Strategy;

import io.quarkus.scheduler.Scheduled;

public class DataEmitter {
    
    @Channel("my-data")
    @OnOverflow(value = Strategy.DROP)
    Emitter<Integer> emitter;

    @Scheduled(every = "2s")
    public void pulse() {
        int payload = (int) (Math.random()*1000);
        System.out.println("Emitted "+payload);
        emitter.send(payload);
    }
}
