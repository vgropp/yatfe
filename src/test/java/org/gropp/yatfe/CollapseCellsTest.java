package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CollapseCellsTest {

	/**
	 * -|-|- => -|-|-
	 */
	@Test
	public void emptyCells() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,null,null);

		board.collapseCells(cells);

		Assert.assertTrue(cells.get(0).isEmpty());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 2|2|2 => 2|2|2
	 */
	@Test
	public void filledCells() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,2);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(2), cells.get(1).getValue());
		Assert.assertEquals(Integer.valueOf(2), cells.get(2).getValue());
	}

	/**
	 * 2|-|- => 2|-|-
	 */
	@Test
	public void noMove() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,null,null);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 2|-|2 => 2|2|-
	 */
	@Test
	public void moveOne() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,null,2);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(2), cells.get(1).getValue());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * -|2|4 => 2|4|-
	 */
	@Test
	public void moveAll() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,2,4);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(4), cells.get(1).getValue());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * -|-|2 => 2|-|-
	 */
	@Test
	public void moveLast() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,null,2);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * -|-|2|- => 2|-|-|-
	 */
	@Test
	public void moveOnlyOne() {
		Board board = new Board(4);
		List<Cell> cells = getCells(null,null,2,null);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
		Assert.assertTrue(cells.get(3).isEmpty());
	}

	/**
	 * -|2|-|2 => 2|2|-|-
	 */
	@Test
	public void moveTwice() {
		Board board = new Board(4);
		List<Cell> cells = getCells(null,2,null,2);

		board.collapseCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(2), cells.get(1).getValue());
		Assert.assertTrue(cells.get(2).isEmpty());
		Assert.assertTrue(cells.get(3).isEmpty());
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
