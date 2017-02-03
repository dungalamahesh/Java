package Builder;

import java.awt.Color;
import java.util.Date;

public class L4Utest {

	public static void main(String[] args) {
		L4U L = new L4U.Builder("MAZDA", Color.black)
				.price(105.000).year(new Date()).builder();
		L.print();
	}

}
