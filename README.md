# channel-streamer Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Streaming data
Upon startup, the application will log errors because no subscriber has been created for the "my-data" channel:

```
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/
2021-09-06 18:33:43,442 INFO  [io.quarkus] (Quarkus Main Thread) channel-streamer 1.0.0-SNAPSHOT on JVM (powered by Quarkus 2.2.1.Final) started in 1.666s. Listening on: http://localhost:8080
2021-09-06 18:33:43,448 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2021-09-06 18:33:43,448 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, resteasy-reactive, scheduler, smallrye-context-propagation, smallrye-reactive-messaging, vertx]
Emitted 922
2021-09-06 18:33:44,008 ERROR [io.qua.sch.run.SimpleScheduler] (executor-thread-0) Error occured while executing task for trigger IntervalTrigger [id=1_foo.bar.DataEmitter_ScheduledInvoker_pulse_4b7d09f41d6bdfd0c06c3aaf27138ccc2843f647, interval=2000]: java.lang.IllegalStateException: SRMSG00027: No subscriber found for the channel my-data
	at io.smallrye.reactive.messaging.extension.AbstractEmitter.verify(AbstractEmitter.java:157)
	at io.smallrye.reactive.messaging.extension.AbstractEmitter.emit(AbstractEmitter.java:139)
	at io.smallrye.reactive.messaging.extension.EmitterImpl.send(EmitterImpl.java:29)
	at foo.bar.DataEmitter.pulse(DataEmitter.java:20)
	at foo.bar.DataEmitter_ScheduledInvoker_pulse_4b7d09f41d6bdfd0c06c3aaf27138ccc2843f647.invokeBean(DataEmitter_ScheduledInvoker_pulse_4b7d09f41d6bdfd0c06c3aaf27138ccc2843f647.zig:48)
	at io.quarkus.arc.runtime.BeanInvoker.invoke(BeanInvoker.java:20)
	at io.quarkus.scheduler.runtime.SimpleScheduler$ScheduledTask$1.run(SimpleScheduler.java:274)
	at io.quarkus.vertx.core.runtime.VertxCoreRecorder$13.runWith(VertxCoreRecorder.java:548)
	at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2449)
	at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1478)
	at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
	at org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
	at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
	at java.base/java.lang.Thread.run(Thread.java:831)

```

Access http://localhost:8080/stream in chrome or in a terminal via :
```
curl -N http://localhost:8080/stream
```
 And the server will stop complaining about the missing subscriber. Kill the browser or curl process, the event will still be emitted on the server but no more complaints about the missing subscriber.
