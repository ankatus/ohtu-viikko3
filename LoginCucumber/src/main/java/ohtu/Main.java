import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ohtu.App;

class Main {
	public static void Main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
	 
	    App application = ctx.getBean(App.class);
	    application.run();
	}
}