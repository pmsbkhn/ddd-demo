package vn.softwaredesign.ddd.common.event;

public interface MessageProducer<T> {
    void close();
    void send(Notification aNotification, MessageParameters aMessageParameters);
}
