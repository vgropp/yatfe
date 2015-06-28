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

	public List<List<Cell>> getRows() {
		return rows;
	}

	public List<Cell> getColumn(int columnIndex) {
		return rows.stream().map(row -> row.get(columnIndex)).collect(Collectors.toList());
	}

}
