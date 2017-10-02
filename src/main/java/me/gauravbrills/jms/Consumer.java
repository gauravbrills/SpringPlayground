package me.gauravbrills.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Consumer {

	@JmsListener(destination = "TRADE_EB_POST_QUEUE")
	public void receiveQueue(String text) {
		LOG.info(text);
	}

}