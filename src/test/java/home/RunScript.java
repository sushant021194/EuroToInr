package home;

import org.testng.annotations.Test;

public class RunScript extends EuroToInr {
  @Test
  public void f() throws InterruptedException {
	  login();
		getValue();
		//closeBrowser();
  }
}
