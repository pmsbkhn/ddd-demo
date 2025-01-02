package vn.softwaredesign.ddd.common.event;

public class Destination {
    private final String name;        // Kafka Topic hoặc RabbitMQ Exchange
    private final String type;        // direct, fanout, topic (RabbitMQ) | standard (Kafka)
    private final String routingKey;  // Partition Key (Kafka) | Routing Key (RabbitMQ)
    private final boolean durable;    // Dành cho RabbitMQ (nếu cần)
    private final String format;      // JSON, AVRO

    public Destination(String name,
                       String type,
                       String routingKey,
                       boolean durable,
                       String format) {
        this.name = name;
        this.type = type;
        this.routingKey = routingKey;
        this.durable = durable;
        this.format = format;
    }

    public String name() {
        return name;
    }

    public String type() {
        return type;
    }

    public String routingKey() {
        return routingKey;
    }

    public boolean durable() {
        return durable;
    }

    public String format() {
        return format;
    }
}
