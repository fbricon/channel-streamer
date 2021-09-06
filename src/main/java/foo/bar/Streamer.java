package foo.bar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;

import io.smallrye.mutiny.Multi;

@Path("/stream")
public class Streamer {

    @Channel("my-data")
    Multi<Integer> dataStream;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Integer> stream() {
        return dataStream;
    }

}