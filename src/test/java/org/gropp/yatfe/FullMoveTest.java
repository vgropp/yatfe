package org.gropp.yatfe;

import static org.junit.Assert.assertTrue;

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
				.addMockedMethod("getRandomEmptyCell")
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
		EasyMock.expect(board.mergeCells(rows.get(0))).andReturn(true);
		EasyMock.expect(board.collapseCells(rows.get(0))).andReturn(true);
		EasyMock.expect(board.mergeCells(rows.get(1))).andReturn(true);
		EasyMock.expect(board.collapseCells(rows.get(1))).andReturn(true);

		Cell emptyCell = new Cell();
		EasyMock.expect(board.getRandomEmptyCell()).andReturn(emptyCell);

		EasyMock.replay(board);
		board.left();
		EasyMock.verify(board);

		assertTrue(emptyCell.getValue().equals(2) || emptyCell.getValue().equals(4));
	}

	@Test
	public void moveLeftBoardFull() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.mergeCells(rows.get(0))).andReturn(false);
		EasyMock.expect(board.collapseCells(rows.get(0))).andReturn(false);
		EasyMock.expect(board.mergeCells(rows.get(1))).andReturn(false);
		EasyMock.expect(board.collapseCells(rows.get(1))).andReturn(false);

		EasyMock.replay(board);
		board.left();
		EasyMock.verify(board);
	}

	@Test
	public void moveRight() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		ArrayList<Cell> reversedRow1 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversedRow1);
		EasyMock.expect(board.mergeCells(reversedRow1)).andReturn(true);
		EasyMock.expect(board.collapseCells(reversedRow1)).andReturn(true);

		ArrayList<Cell> reversedRow2 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversedRow2);
		EasyMock.expect(board.mergeCells(reversedRow2)).andReturn(true);
		EasyMock.expect(board.collapseCells(reversedRow2)).andReturn(true);

		Cell emptyCell = new Cell();
		EasyMock.expect(board.getRandomEmptyCell()).andReturn(emptyCell);

		EasyMock.replay(board);
		board.right();
		EasyMock.verify(board);

		assertTrue(emptyCell.getValue().equals(2) || emptyCell.getValue().equals(4));
	}

	@Test
	public void moveRighBoardFull() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		ArrayList<Cell> reversedRow1 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversedRow1);
		EasyMock.expect(board.mergeCells(reversedRow1)).andReturn(false);
		EasyMock.expect(board.collapseCells(reversedRow1)).andReturn(false);

		ArrayList<Cell> reversedRow2 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversedRow2);
		EasyMock.expect(board.mergeCells(reversedRow2)).andReturn(false);
		EasyMock.expect(board.collapseCells(reversedRow2)).andReturn(false);

		EasyMock.replay(board);
		board.right();
		EasyMock.verify(board);
	}

	@Test
	public void moveUp() {
		EasyMock.expect(board.getColumns()).andReturn(rows);
		EasyMock.expect(board.mergeCells(rows.get(0))).andReturn(true);
		EasyMock.expect(board.collapseCells(rows.get(0))).andReturn(true);
		EasyMock.expect(board.mergeCells(rows.get(1))).andReturn(false);
		EasyMock.expect(board.collapseCells(rows.get(1))).andReturn(false);
		Cell emptyCell = new Cell();
		EasyMock.expect(board.getRandomEmptyCell()).andReturn(emptyCell);

		EasyMock.replay(board);
		board.up();
		EasyMock.verify(board);

		assertTrue(emptyCell.getValue().equals(2) || emptyCell.getValue().equals(4));
	}

	@Test
	public void moveDown() {
		EasyMock.expect(board.getColumns()).andReturn(rows);
		ArrayList<Cell> reversedRow1 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversedRow1);
		EasyMock.expect(board.mergeCells(reversedRow1)).andReturn(false);
		EasyMock.expect(board.collapseCells(reversedRow1)).andReturn(false);

		ArrayList<Cell> reversedRow2 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversedRow2);
		EasyMock.expect(board.mergeCells(reversedRow2)).andReturn(true);
		EasyMock.expect(board.collapseCells(reversedRow2)).andReturn(true);
		Cell emptyCell = new Cell();
		EasyMock.expect(board.getRandomEmptyCell()).andReturn(emptyCell);

		EasyMock.replay(board);
		board.down();
		EasyMock.verify(board);

		assertTrue(emptyCell.getValue().equals(2) || emptyCell.getValue().equals(4));
	}
}
