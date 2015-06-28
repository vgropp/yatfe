package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

	private List<List<Cell>> rows;

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
		for (List<Cell> row : rows) {
			mergeCells(row);
			collapseCells(row);
		}
	}

	private void moveRight(List<List<Cell>> rows) {
		for (List<Cell> row : rows) {
			ArrayList<Cell> reversedRow = new ArrayList<>(row);
			Collections.reverse(reversedRow);
			mergeCells(reversedRow);
			collapseCells(reversedRow);
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

	void collapseCells(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		for (Cell currentCell : cells) {
			if (filledCells.isEmpty()) {
				return;
			}
			if (currentCell.isEmpty()) {
				Cell sourceCell = filledCells.get(0);
				currentCell.setValue(sourceCell.getValue());
				sourceCell.setValue(null);
				filledCells.remove(sourceCell);
			}
		}
	}

	void mergeCells(List<Cell> cells) {
		List<Cell> filledCells = filterEmptyCells(cells);
		Cell leftCell = null;
		for (Cell currentCell : filledCells) {
			if (leftCell == null) {
				leftCell  = currentCell;
				continue;
			}
			if (leftCell.merge(currentCell)) {
				leftCell = null;
			} else {
				leftCell  = currentCell;
			}
		}
	}

}
