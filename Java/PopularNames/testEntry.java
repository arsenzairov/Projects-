package namesurferr;
import acm.program.*;

public class testEntry extends ConsoleProgram {
	private NameSurferEntry entry;
		public void run(){
			entry = new NameSurferEntry(test);
			System.out.println("name: " + entry.getName() + "lastname: " + entry.getRank(2));
		}
		
		String test = "Sam 58 69 99 131 168 236 278 380 467 408 446 997";
}
