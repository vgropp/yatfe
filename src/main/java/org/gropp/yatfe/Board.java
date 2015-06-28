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
			} else {
				if (currentCell.equals(filledCells.get(0))) {
					filledCells.remove(currentCell);
				}
			}
		}
		return result;
	}

	boolean isMovePossible() {
		for (List<Cell> row : getRows()) {
			ArrayList<Cell> reversedRow = new ArrayList<>(row);
			Collections.reverse(reversedRow);
			if (areCellsMovable(row) || areCellsMergable(row) || areCellsMovable(reversedRow) || areCellsMergable(reversedRow)) {
				return true;
			}
		}
		for (List<Cell> col : getColumns()) {
			ArrayList<Cell> reversedCol = new ArrayList<>(col);
			Collections.reverse(reversedCol);
			if (areCellsMovable(col) || areCellsMergable(col) || areCellsMovable(reversedCol) || areCellsMergable(reversedCol)) {
				return true;
			}
		}
		return false;
	}

	boolean areCellsMovable(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		return !cells.subList(0, filledCells.size()).equals(filledCells);
	}

	boolean areCellsMergable(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		Cell lastCell = null;
		for (Cell currentCell : filledCells) {
			if (lastCell != null && lastCell.getValue().equals(currentCell.getValue())) {
				return true;
			}
			lastCell = currentCell;
		}
		return false;
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
		return getAllCells().stream().filter(cell -> cell.isEmpty()).collect(Collectors.toList());
	}

	List<Cell> getAllCells() {
		return getRows().stream().flatMap(column -> column.stream()).collect(Collectors.toList());
	}

	int getHighestValue() {
		return getAllCells().stream().filter(c -> !c.isEmpty()).mapToInt(c -> c.getValue()).max().getAsInt();
	}

	int getScore() {
		return score;
	}

}
