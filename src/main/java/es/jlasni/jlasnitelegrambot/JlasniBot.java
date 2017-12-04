package es.jlasni.jlasnitelegrambot;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import es.juanjosalvador.jlassni.JLasni;

public class JlasniBot extends TelegramLongPollingBot {
	
	private Properties botProperties;
	private JLasni jlasni;
	public JlasniBot() {
		botProperties = new Properties();
		try(InputStream in = getClass().getResourceAsStream("/bot.properties")){
			botProperties.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jlasni = new JLasni();
		
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
			msg = createMessage(JlasniCommand(arg0.getMessage().getText()), arg0.getMessage().getChatId());
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
	
	private String JlasniCommand(String command){
		String msg = null;
		switch(command){
			
		case "/botonaco":
			msg = jlasni.botonaco();
			break;
		case "/roto":
			msg = jlasni.roto();
			break;
		case "/guapisimo":
			msg = jlasni.guapisimo();
			break;
		case "/felicidad":
			msg = jlasni.felicidad();
			break;
		case "/croqueta":
			msg = jlasni.croqueta();
			break;
		case "/apastar":
			msg = jlasni.aPastar();
			break;
		case "/hipazo":
			msg = jlasni.hipazo();
			break;
		case "/otp":
			msg = jlasni.otp();
			break;
		default:
			msg = "Comando no encontrado";
		}
		
		return msg;
	}
	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return botProperties.getProperty("token");
	}

}
