package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import presentation.Box;

public class TestBox {

	@Test
	public void test() {
		int x = 5;
		int y = 6;
		Box box = new Box(x, y);
		assertEquals(box.getXComponent(), x);
		assertEquals(box.getYComponent(), y);
		
	}

}
