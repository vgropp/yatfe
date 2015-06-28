package org.gropp.yatfe;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BoardInitTest {

	/**
	 *	test if board is successfully initialized and empty
	 */
	@Test
	public void initBoard() {
		int size = 5;
		Board board = new Board(size);

		List<List<Cell>> rows = board.getRows();
		Assert.assertEquals(size,rows.size());
		for (List<Cell> currentRow : rows) {
			Assert.assertEquals(currentRow.size(),size);
			currentRow.stream().map(cell -> cell.getValue()).forEach(Assert::assertNull);
		}
	}
}
