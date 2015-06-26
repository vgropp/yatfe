package org.gropp.yatfe;

import java.util.ArrayList;
import java.util.List;

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

}
