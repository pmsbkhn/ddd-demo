package vn.softwaredesign.ddd;

import org.junit.jupiter.api.Test;
import vn.softwaredesign.ddd.common.domain.model.DomainEvent;
import vn.softwaredesign.ddd.common.domain.model.DomainEventPublisher;
import vn.softwaredesign.ddd.common.domain.model.DomainEventSubscriber;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomainEventPublisherMultithreadTest {
    static class TestEvent implements DomainEvent {
        final String message;

        public TestEvent(String message) {
            this.message = message;
        }

        @Override
        public int eventVersion() {
            return 0;
        }

        @Override
        public LocalDateTime occurredOn() {
            return null;
        }
    }

    static class TestSubscriber implements DomainEventSubscriber<TestEvent> {
        private final AtomicInteger eventCount = new AtomicInteger();

        @Override
        public void handleEvent(TestEvent aDomainEvent) {
            eventCount.incrementAndGet();
        }

        @Override
        public Class<TestEvent> subscribedToEventType() {
            return TestEvent.class;
        }

        public int getEventCount() {
            return eventCount.get();
        }
    }

    @org.junit.jupiter.api.Test
    public void testDomainEventPublisherInMultithreadedEnvironment() throws InterruptedException {
        final int THREAD_COUNT = 2;
        final int EVENTS_PER_THREAD = 5;

        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
        TestSubscriber[] subscribers = new TestSubscriber[THREAD_COUNT];

        // Tạo và chạy các luồng
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadIndex = i;
            subscribers[i] = new TestSubscriber();

            new Thread(() -> {
                try {
                    // Đăng ký subscriber cho mỗi luồng
                    DomainEventPublisher.instance().subscribe(subscribers[threadIndex]);

                    // Phát sự kiện
                    for (int j = 0; j < EVENTS_PER_THREAD; j++) {
                        DomainEventPublisher.instance().publish(new TestEvent("Event from thread " + threadIndex));
                    }

                } finally {
                    DomainEventPublisher.instance().reset();
                    latch.countDown();
                }
            }).start();
        }

        // Đợi tất cả các luồng hoàn thành
        latch.await();

        // Kiểm tra kết quả
        for (int i = 0; i < THREAD_COUNT; i++) {
            assertEquals(EVENTS_PER_THREAD, subscribers[i].getEventCount(),
                    "Subscriber in thread " + i + " should have received " + EVENTS_PER_THREAD + " events.");
        }

        System.out.println("✅ All threads processed their events correctly without interference.");
    }



}
