package org.gropp.yatfe;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.EasyMock;
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

	@Test
	public void initCells() throws Exception {
		Board board = EasyMock.createMockBuilder(Board.class)
				.addMockedMethod("getRandomEmptyCell")
				.createStrictMock();

		Cell cell1 = new Cell();
		EasyMock.expect(board.getRandomEmptyCell()).andReturn(cell1);

		EasyMock.replay(board);
		board.init();
		EasyMock.verify(board);

		assertTrue(cell1.getValue().equals(2) || cell1.getValue().equals(4));
	}
}
