package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class FullMoveTest {

	private Board board;
	private ArrayList<List<Cell>> rows;

	@Before
	public void setup() {
		board = EasyMock.createMockBuilder(Board.class)
				.addMockedMethod("mergeCells")
				.addMockedMethod("collapseCells")
				.addMockedMethod("getRows")
				.addMockedMethod("getColumns")
				.createStrictMock();

		rows = new ArrayList<>();
		ArrayList<Cell> row1 = new ArrayList<>();
		row1.add(new Cell());
		row1.add(new Cell());
		rows.add(row1);
		ArrayList<Cell> row2 = new ArrayList<>();
		row2.add(new Cell());
		row2.add(new Cell());
		rows.add(row2);

	}

	@Test
	public void moveLeft() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		board.mergeCells(rows.get(0));
		board.collapseCells(rows.get(0));
		board.mergeCells(rows.get(1));
		board.collapseCells(rows.get(1));

		EasyMock.replay(board);
		board.left();
		EasyMock.verify(board);
	}

	@Test
	public void moveRight() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		ArrayList<Cell> reversedRow1 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversedRow1);
		board.mergeCells(reversedRow1);
		board.collapseCells(reversedRow1);

		ArrayList<Cell> reversedRow2 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversedRow2);
		board.mergeCells(reversedRow2);
		board.collapseCells(reversedRow2);

		EasyMock.replay(board);
		board.right();
		EasyMock.verify(board);
	}

	@Test
	public void moveUp() {
		EasyMock.expect(board.getColumns()).andReturn(rows);
		board.mergeCells(rows.get(0));
		board.collapseCells(rows.get(0));
		board.mergeCells(rows.get(1));
		board.collapseCells(rows.get(1));

		EasyMock.replay(board);
		board.up();
		EasyMock.verify(board);
	}

	@Test
	public void moveDown() {
		EasyMock.expect(board.getColumns()).andReturn(rows);
		ArrayList<Cell> reversedRow1 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversedRow1);
		board.mergeCells(reversedRow1);
		board.collapseCells(reversedRow1);

		ArrayList<Cell> reversedRow2 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversedRow2);
		board.mergeCells(reversedRow2);
		board.collapseCells(reversedRow2);

		EasyMock.replay(board);
		board.down();
		EasyMock.verify(board);
	}
}
