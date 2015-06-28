package org.gropp.yatfe;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

public class BoardViewTest {

	@Test
	public void simple4x4Board() throws Exception {
		Board board = new Board(4);
		BoardView boardView = EasyMock.createMockBuilder(BoardView.class)
				.withConstructor(board)
				.addMockedMethod("printLine")
				.createStrictMock();

		boardView.printLine("Score: 0\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");

		EasyMock.replay(boardView);
		boardView.print();
		EasyMock.verify(boardView);
	}

	@Test
	public void simple4x4BoardFilled() throws Exception {
		Board board = new Board(4);
		board.getRows().get(0).get(1).setValue(4);
		board.getRows().get(2).get(2).setValue(8);
		BoardView boardView = EasyMock.createMockBuilder(BoardView.class)
				.withConstructor(board)
				.addMockedMethod("printLine")
				.createStrictMock();

		boardView.printLine("Score: 0\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   | 4 |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   | 8 |   |\n");
		boardView.printLine("|---|---|---|---|\n");
		boardView.printLine("|   |   |   |   |\n");
		boardView.printLine("|---|---|---|---|\n");

		EasyMock.replay(boardView);
		boardView.print();
		EasyMock.verify(boardView);
	}

	@Test
	public void printEmptyCell() throws Exception {
		assertEquals("   ",new Cell().getStringValue());
	}

	@Test
	public void print2Cell() throws Exception {
		Cell cell = new Cell();
		cell.setValue(2);
		assertEquals(" 2 ",cell.getStringValue());
	}

}
