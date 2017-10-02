package me.gauravbrills.person;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PersonEventListener {

	@EventListener
	public void listenPersonEvent(PersonEvent event) throws InterruptedException {
		LOG.info("Got Person event {}", event.toString());
		Thread.sleep(1000);
	}

	@EventListener
	@Async
	public void listenPersonEnrollEvent(PersonEnrollEvent event) throws InterruptedException {
		LOG.info("Got enroll event {}", event.toString());
		Thread.sleep(2000);
	}
}
