import static org.junit.Assert.assertEquals;

import org.junit.Test;

import presentation.Box;

public class BoxTest {

	@Test
	public void test() {
		int x = 5;
		int y = 6;
		Box box = new Box(x, y);
		assertEquals(box.getXComponent(), x);
		//assertEquals(box.getYComponent(), y);
		
	}

}
