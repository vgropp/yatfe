package org.gropp.yatfe;

import java.util.ArrayList;
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
