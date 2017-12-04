package jlasnitelegrambot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

	public static void main(String[] args) {
		ApiContextInitializer.init();
		TelegramBotsApi tlgrambotapi = new TelegramBotsApi();
		
		try {
			tlgrambotapi.registerBot(new JlasniBot());
		} catch (TelegramApiRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
