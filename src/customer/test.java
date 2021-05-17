package customer;

import java.util.Calendar;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance ( );
		Date today = new Date ();
		if((cal.get(Calendar.MONTH) + 1) == 5) {
	    	System.out.println("1");
	    }
}
}
