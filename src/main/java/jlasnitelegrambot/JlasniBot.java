package jlasnitelegrambot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class JlasniBot extends TelegramLongPollingBot {
	
	private Properties botProperties;
	
	public JlasniBot() {
		botProperties = new Properties();
		try(InputStream in = getClass().getResourceAsStream("/bot.properties")){
			botProperties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getBotUsername() {
		// TODO Auto-generated method stub
		return botProperties.getProperty("username");
	}

	@Override
	public void onUpdateReceived(Update arg0) {
		
		SendMessage msg=null;
		if(arg0.getMessage().isCommand())
			;
		else
			msg= createMessage("Error, no es un comando", arg0.getMessage().getChatId());
			
		try {
			execute(msg);
		}catch (TelegramApiException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	private SendMessage createMessage(String message, Long chatid) {
		SendMessage sendmessage= new SendMessage();
		sendmessage.setChatId(chatid);
		sendmessage.setText(message);
		return sendmessage;
	}
	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return botProperties.getProperty("token");
	}

}
