package org.gropp.yatfe;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BoardInit {

	/**
	 *	test if board is successfully initialized and empty
	 */
	@Test
	public void initBoard() {
		int size = 5;
		Board board = new Board(size);

		Assert.assertEquals(board.getRows().size(),size);
		for (int i = 0; i < size; i++) {
			List<Cell> currentRow = board.getRows().get(i);
			Assert.assertEquals(currentRow.size(),size);
			for (int n = 0; n < size; n++) {
				Assert.assertNull(currentRow.get(n).getValue());
			}
		}
	}
}
