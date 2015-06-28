package org.gropp.yatfe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class GameLostFullTest {

	private Board board;
	private ArrayList<List<Cell>> rows;
	private ArrayList<List<Cell>> cols;

	@Before
	public void setup() {
		board = EasyMock.createMockBuilder(Board.class)
				.addMockedMethod("areCellsMergable")
				.addMockedMethod("areCellsMovable")
				.addMockedMethod("getRows")
				.addMockedMethod("getColumns")
				.createStrictMock();

		rows = new ArrayList<>();
		ArrayList<Cell> row1 = new ArrayList<>();
		Cell cell1 = new Cell();
		row1.add(cell1);
		Cell cell2 = new Cell();
		row1.add(cell2);
		rows.add(row1);
		ArrayList<Cell> row2 = new ArrayList<>();
		Cell cell3 = new Cell();
		row2.add(cell3);
		Cell cell4 = new Cell();
		row2.add(cell4);
		rows.add(row2);
		ArrayList<Cell> col1 = new ArrayList<>();
		col1.add(cell1);
		col1.add(cell3);
		ArrayList<Cell> col2 = new ArrayList<>();
		col2.add(cell2);
		col2.add(cell4);
		cols = new ArrayList<>();
		cols.add(col1);
		cols.add(col2);
	}

	@Test
	public void nothingPossible() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(rows.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(1))).andReturn(false);
		ArrayList<Cell> reversed1 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversed1);
		EasyMock.expect(board.areCellsMovable(reversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed1)).andReturn(false);

		EasyMock.expect(board.getColumns()).andReturn(cols);
		EasyMock.expect(board.areCellsMovable(cols.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(cols.get(0))).andReturn(false);
		ArrayList<Cell> colReversed0 = new ArrayList<>(cols.get(0));
		Collections.reverse(colReversed0);
		EasyMock.expect(board.areCellsMovable(colReversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(colReversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(cols.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(cols.get(1))).andReturn(false);
		ArrayList<Cell> colReversed1 = new ArrayList<>(cols.get(1));
		Collections.reverse(colReversed1);
		EasyMock.expect(board.areCellsMovable(colReversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(colReversed1)).andReturn(false);


		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertFalse(movePossible);
	}

	@Test
	public void movableDown() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(rows.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(1))).andReturn(false);
		ArrayList<Cell> reversed1 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversed1);
		EasyMock.expect(board.areCellsMovable(reversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed1)).andReturn(false);

		EasyMock.expect(board.getColumns()).andReturn(cols);
		EasyMock.expect(board.areCellsMovable(cols.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(cols.get(0))).andReturn(false);
		ArrayList<Cell> colReversed0 = new ArrayList<>(cols.get(0));
		Collections.reverse(colReversed0);
		EasyMock.expect(board.areCellsMovable(colReversed0)).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void mergableUp() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(rows.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(1))).andReturn(false);
		ArrayList<Cell> reversed1 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversed1);
		EasyMock.expect(board.areCellsMovable(reversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed1)).andReturn(false);

		EasyMock.expect(board.getColumns()).andReturn(cols);
		EasyMock.expect(board.areCellsMovable(cols.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(cols.get(0))).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void movableUp() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(rows.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(1))).andReturn(false);
		ArrayList<Cell> reversed1 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversed1);
		EasyMock.expect(board.areCellsMovable(reversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed1)).andReturn(false);

		EasyMock.expect(board.getColumns()).andReturn(cols);
		EasyMock.expect(board.areCellsMovable(cols.get(0))).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void mergableDown() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMovable(rows.get(1))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(1))).andReturn(false);
		ArrayList<Cell> reversed1 = new ArrayList<>(rows.get(1));
		Collections.reverse(reversed1);
		EasyMock.expect(board.areCellsMovable(reversed1)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed1)).andReturn(false);

		EasyMock.expect(board.getColumns()).andReturn(cols);
		EasyMock.expect(board.areCellsMovable(cols.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(cols.get(0))).andReturn(false);
		ArrayList<Cell> colReversed0 = new ArrayList<>(cols.get(0));
		Collections.reverse(colReversed0);
		EasyMock.expect(board.areCellsMovable(colReversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(colReversed0)).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}


	@Test
	public void movableRight() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void movableLeft() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void mergableLeft() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

	@Test
	public void mergableRight() {
		EasyMock.expect(board.getRows()).andReturn(rows);
		EasyMock.expect(board.areCellsMovable(rows.get(0))).andReturn(false);
		EasyMock.expect(board.areCellsMergable(rows.get(0))).andReturn(false);
		ArrayList<Cell> reversed0 = new ArrayList<>(rows.get(0));
		Collections.reverse(reversed0);
		EasyMock.expect(board.areCellsMovable(reversed0)).andReturn(false);
		EasyMock.expect(board.areCellsMergable(reversed0)).andReturn(true);

		EasyMock.replay(board);
		boolean movePossible = board.isMovePossible();
		EasyMock.verify(board);
		assertTrue(movePossible);
	}

}
