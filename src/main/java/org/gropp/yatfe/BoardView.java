package org.gropp.yatfe;

import java.util.List;

public class BoardView {

	private Board board;

	public BoardView(Board board) {
		this.board = board;
	}

	public void print() {
		for (List<Cell> row : board.getRows()) {
			printDividerLine(row);

			StringBuffer line = new StringBuffer();
			for (Cell cell : row) {
				line.append("|" + cell.getStringValue());
			}
			line.append("|\n");
			printLine(line.toString());
		}
		printDividerLine(board.getRows().get(0));
	}

	private void printDividerLine(List<Cell> row) {
		StringBuffer line = new StringBuffer();
		for (int i = 0; i < row.size(); i++) {
			line.append("|---");
		}
		line.append("|\n");
		printLine(line.toString());
	}

	void printLine(String string) {
		System.out.print(string);
	}
}
