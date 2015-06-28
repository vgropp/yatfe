package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MergeTest {

	/**
	 * 2|-|- => 2|-|-
	 */
	@Test
	public void onlyOneElement() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,null,null);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(2), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 2|2|- => 4|-|-
	 */
	@Test
	public void simpleMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,null);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 2|-|2 => 4|-|-
	 */
	@Test
	public void mergeWithSpace() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,null,2);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * -|2|2 => 4|-|-
	 */
	@Test
	public void mergeAtTheEnd() {
		Board board = new Board(3);
		List<Cell> cells = getCells(null,2,2);

		board.mergeCells(cells);

		Assert.assertTrue(cells.get(0).isEmpty());
		Assert.assertEquals(Integer.valueOf(4), cells.get(1).getValue());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 2|2|2 => 4|-|2
	 */
	@Test
	public void mergeOnlyOnce() {
		Board board = new Board(3);
		List<Cell> cells = getCells(2,2,2);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertEquals(Integer.valueOf(2), cells.get(2).getValue());
	}

	/**
	 * 4|2|2 => 4|4|-
	 */
	@Test
	public void mergeWithLeadingOtherValue() {
		Board board = new Board(3);
		List<Cell> cells = getCells(4,2,2);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(4), cells.get(1).getValue());
		Assert.assertTrue(cells.get(2).isEmpty());
	}

	/**
	 * 4|2|4 => 4|2|4
	 */
	@Test
	public void noMerge() {
		Board board = new Board(3);
		List<Cell> cells = getCells(4,2,4);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertEquals(Integer.valueOf(2), cells.get(1).getValue());
		Assert.assertEquals(Integer.valueOf(4), cells.get(2).getValue());
	}

	/**
	 * 2|2|2|2 => 4|-|4|-
	 */
	@Test
	public void doubleMerge() {
		Board board = new Board(4);
		List<Cell> cells = getCells(2,2,2,2);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertEquals(Integer.valueOf(4), cells.get(2).getValue());
		Assert.assertTrue(cells.get(3).isEmpty());
	}

	/**
	 * 2|2|4|2|2 => 4|-|4|4|-
	 */
	@Test
	public void doubleWithOtherMerge() {
		Board board = new Board(5);
		List<Cell> cells = getCells(2,2,4,2,2);

		board.mergeCells(cells);

		Assert.assertEquals(Integer.valueOf(4), cells.get(0).getValue());
		Assert.assertTrue(cells.get(1).isEmpty());
		Assert.assertEquals(Integer.valueOf(4), cells.get(2).getValue());
		Assert.assertEquals(Integer.valueOf(4), cells.get(3).getValue());
		Assert.assertTrue(cells.get(4).isEmpty());
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
