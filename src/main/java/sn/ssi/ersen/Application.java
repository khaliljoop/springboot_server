package sn.ssi.ersen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sn.ssi.ersen.controllers.ErsenTacheCentraleController;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//Demmarrer Spring
		SpringApplication.run(Application.class, args);
		//La methode devra s'executer touts les jours a 00h 00 a partir de 00h de la date de deploiment du serveur
		new Timer().scheduleAtFixedRate(new AutoReplanifyTasksEveryDayAtMidnight(), ErsenTacheCentraleController.geDateDayOrNextDayAtMidnight(new Date(),false),86400000);
	}
}

class AutoReplanifyTasksEveryDayAtMidnight extends TimerTask {
	public void run()
	{
		try {
			ErsenTacheCentraleController.replanifyTacheCentrals(ErsenTacheCentraleController.geDateDayOrNextDayAtMidnight(new Date(),true));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
