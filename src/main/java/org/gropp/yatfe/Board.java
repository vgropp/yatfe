package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Board {

	private List<List<Cell>> rows;
	private int score = 0;

	public Board(int size) {
		rows = new ArrayList<List<Cell>>(size);
		for (int i = 0; i < size; i++) {
			ArrayList<Cell> currentRow = new ArrayList<Cell>(size);
			rows.add(currentRow);
			for (int n = 0; n < size; n++) {
				currentRow.add(new Cell());
			}
		}
	}

	void init() {
		getRandomEmptyCell().newValue();
	}

	List<List<Cell>> getRows() {
		return rows;
	}

	List<Cell> getColumn(int columnIndex) {
		return rows.stream().map(row -> row.get(columnIndex)).collect(Collectors.toList());
	}

	List<List<Cell>> getColumns() {
		List<List<Cell>> columns = new ArrayList<>();
		for (int i = 0; i < rows.get(0).size(); i++) {
			columns.add(getColumn(i));
		}
		return columns;
	}

	private void moveLeft(List<List<Cell>> rows) {
		boolean anyAction = false;
		for (List<Cell> row : rows) {
			anyAction |= mergeCells(row);
			anyAction |= collapseCells(row);
		}
		if (anyAction) {
			getRandomEmptyCell().newValue();
		}
	}

	private void moveRight(List<List<Cell>> rows) {
		boolean anyAction = false;
		for (List<Cell> row : rows) {
			ArrayList<Cell> reversedRow = new ArrayList<>(row);
			Collections.reverse(reversedRow);
			anyAction |= mergeCells(reversedRow);
			anyAction |= collapseCells(reversedRow);
		}
		if (anyAction) {
			getRandomEmptyCell().newValue();
		}
	}

	public void left() {
		moveLeft(getRows());
	}

	public void right() {
		moveRight(getRows());
	}

	public void up() {
		moveLeft(getColumns());
	}

	public void down() {
		moveRight(getColumns());
	}

	private List<Cell> filterEmptyCells(List<Cell> cells) {
		return cells.stream().filter(cell -> !cell.isEmpty()).collect(Collectors.toList());
	}

	boolean collapseCells(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		boolean result = false;

		for (Cell currentCell : cells) {
			if (filledCells.isEmpty()) {
				return result;
			}
			if (currentCell.isEmpty()) {
				Cell sourceCell = filledCells.get(0);
				currentCell.setValue(sourceCell.getValue());
				if (!sourceCell.isEmpty()) {
					result = true;
				}
				sourceCell.setValue(null);
				filledCells.remove(sourceCell);
			}
		}
		return result;
	}

	boolean mergeCells(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		boolean result = false;
		Cell leftCell = null;
		for (Cell currentCell : filledCells) {
			if (leftCell == null) {
				leftCell  = currentCell;
				continue;
			}
			if (leftCell.merge(currentCell)) {
				score += leftCell.getValue();
				leftCell = null;
				result = true;
			} else {
				leftCell  = currentCell;
			}
		}
		return result;
	}

	Cell getRandomEmptyCell() {
		List<Cell> allEmptyCells = getAllEmptyCells();
		if (allEmptyCells.isEmpty()) {
			return null;
		}
		return allEmptyCells.get(new Random().nextInt(allEmptyCells.size()));
	}

	List<Cell> getAllEmptyCells() {
		return getRows().stream().flatMap(column -> column.stream()).filter(cell -> cell.isEmpty()).collect(Collectors.toList());
	}

	int getScore() {
		return score;
	}

}
