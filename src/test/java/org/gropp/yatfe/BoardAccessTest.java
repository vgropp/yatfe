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

	/**
	 * transform all columns
	 */
	@Test
	public void getColumns() {
		int size = 3;
		Board board = new Board(size);
		List<List<Cell>> columns = board.getColumns();
		assertEquals(size,columns.size());
		for (int i = 0; i < size; i++) {
			for (int n = 0; n < size; n++) {
				assertEquals(board.getRows().get(i).get(n), columns.get(n).get(i));
			}
		}

	}
}
