package org.gropp.yatfe;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class BoardAccessTest {

	/**
	 * test for retrieving the correct column
	 */
	@Test
	public void getColumn() {
		int size = 3;
		Board board = new Board(size);
		int columnIndex = 1;
		List<Cell> column2 = board.getColumn(columnIndex);
		assertEquals(size,column2.size());
		for (int i = 0; i < size; i++) {
			assertEquals(board.getRows().get(i).get(columnIndex), column2.get(i));
		}
	}
}
