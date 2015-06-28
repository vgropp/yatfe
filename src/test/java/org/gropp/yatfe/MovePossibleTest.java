package org.gropp.yatfe;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MovePossibleTest {

	/**
	 * -|-|-
	 */
	@Test
	public void emptyCells() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,null,null);

		assertFalse(board.areCellsMovable(cells));
	}

	/**
	 * -|-|-
	 */
	@Test
	public void emptyCellsMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,null,null);

		assertFalse(board.areCellsMergable(cells));
	}

	/**
	 * 2|2|-
	 */
	@Test
	public void simpleMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,null);

		assertTrue(board.areCellsMergable(cells));
	}

	/**
	 * 2|4|-
	 */
	@Test
	public void differentValuesMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,4,null);

		assertFalse(board.areCellsMergable(cells));
	}

	/**
	 * 2|4|2
	 */
	@Test
	public void differentValuesFullMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,4,2);

		assertFalse(board.areCellsMergable(cells));
	}


	/**
	 * 2|2|2
	 */
	@Test
	public void filledCells() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,2);

		assertFalse(board.areCellsMovable(cells));
	}

	/**
	 * -|2|2
	 */
	@Test
	public void emptyFirstCell() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,2,2);

		assertTrue(board.areCellsMovable(cells));
	}

	/**
	 * -|2|-
	 */
	@Test
	public void emptyFirstAndLastCell() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,2,null);

		assertTrue(board.areCellsMovable(cells));
	}

	/**
	 * 2|2|-
	 */
	@Test
	public void emptyLastCell() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,null);

		assertFalse(board.areCellsMovable(cells));
	}

	/**
	 * 2|-|2
	 */
	@Test
	public void emptyMiddleCell() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,null,2);

		assertTrue(board.areCellsMovable(cells));
	}

	private List<Cell> getCells(Integer... values) {
		List<Cell> cells = new ArrayList<>();
		for (Integer value : values) {
			Cell cell = new Cell();
			cell.setValue(value);
			cells.add(cell);
		}
		return cells;
	}

}
