package org.gropp.yatfe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

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

	@Test
	public void allEmptyCells() {
		int size = 3;
		Board board = new Board(size);
		assertEquals(3*3,board.getAllEmptyCells().size());
	}

	@Test
	public void allButOneEmptyCells() {
		int size = 3;
		Board board = new Board(size);
		Cell cell = board.getRows().get(1).get(1);
		cell.setValue(2);
		List<Cell> allEmptyCells = board.getAllEmptyCells();
		assertEquals(3*3-1,allEmptyCells.size());
		assertFalse(allEmptyCells.contains(cell));
	}

	@Test
	public void getNoRandomEmptyCell() throws Exception {
		int size = 3;
		Board board = new Board(size);
		board.getRows().stream().flatMap(column -> column.stream()).forEach(c -> c.setValue(2));

		assertNull(board.getRandomEmptyCell());
	}

	@Test
	public void getRandomEmptyCell() throws Exception {
		int size = 3;
		Board board = new Board(size);
		List<Cell> allCells = board.getRows().stream().flatMap(column -> column.stream()).collect(Collectors.toList());

		assertTrue(allCells.contains(board.getRandomEmptyCell()));
	}
}
