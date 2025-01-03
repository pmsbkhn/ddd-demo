package vn.softwaredesign.ddd.common.event;


import java.util.HashMap;
import java.util.Map;

public class MessageParameters {

    private final Destination destination;
    private final Map<String, String> headers = new HashMap<>();
    @SuppressWarnings("unused")
	private final String format; // JSON, AVRO, TEXT

    public MessageParameters(Builder builder) {
        this.destination = builder.destination;
        this.headers.putAll(builder.headers);
        this.format = builder.format;
    }

    public Destination getDestination() {
        return destination;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public static class Builder {
        private Destination destination;
        private final Map<String, String> headers = new HashMap<>();
        private String format = "JSON";

        public Builder destination(Destination destination) {
            this.destination = destination;
            return this;
        }

        public Builder addHeader(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public MessageParameters build() {
            if (destination == null) {
                throw new IllegalStateException("Destination must not be null");
            }
            return new MessageParameters(this);
        }
    }

}
