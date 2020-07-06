package tommy.spring.guestbook.dao;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tommy.spring.guestbook.vo.GuestMessage;

public class NamedParameterMain {
	private GuestMessage makeGuestMessage(String guestName, String message) {
		GuestMessage msg = new GuestMessage();
		msg.setGuestName(guestName);
		msg.setMessage(message);
		msg.setRegistryDate(new Date());
		return msg;
	}
	
	public static void main(String[] args) {
		String[] configLocations = new String[] {"applicationContext.xml"};
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext(configLocations);
	
		NamedParamGuestMessageDAO dao = (NamedParamGuestMessageDAO) 
				context.getBean("namedParamGuestMessageDao");
		NamedParameterMain myTest = new NamedParameterMain();
		dao.insert(myTest.makeGuestMessage("심플jdbc인서트", "테스트"));
		dao.insert(myTest.makeGuestMessage("심플jdbc인서트", "테스트2"));
		dao.insert(myTest.makeGuestMessage("심플jdbc인서트", "테스트3"));
		int count = dao.count();
		System.out.println("전체글수: " + count);
		List<GuestMessage> list = dao.select(1, count);
		for(GuestMessage msg : list) {
			System.out.println(msg.getId() + " : " + msg.getGuestName() + " : " +
					msg.getMessage() + " : " + msg.getRegistryDate());
		}
		context.close();
	}
}
