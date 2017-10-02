package me.gauravbrills;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.jms.JMSException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import me.gauravbrills.jms.Producer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringBootPlaygroundApplication.class)
public class ActiveMQTests {
	@Rule
	public org.springframework.boot.test.rule.OutputCapture outputCapture = new org.springframework.boot.test.rule.OutputCapture();

	@Autowired
	private Producer producer;

	@Test
	public void sendSimpleMessage() throws InterruptedException, JMSException {
		this.producer.send("BUY SHARE 200 REL");
		Thread.sleep(1000L);
		assertThat(this.outputCapture.toString().contains("BUY SHARE 200 REL"), is(true));
	}

}
