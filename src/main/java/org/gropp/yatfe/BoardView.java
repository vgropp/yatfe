package org.gropp.yatfe;

import java.util.List;

public class BoardView {

	private Board board;

	public BoardView(Board board) {
		this.board = board;
	}

	public void print() {
		printLine("Score: " + board.getScore() + "\n");
		for (List<Cell> row : board.getRows()) {
			printDividerLine(row);

			StringBuffer line = new StringBuffer();
			for (Cell cell : row) {
				line.append("|" + getStringValue(cell));
			}
			line.append("|\n");
			printLine(line.toString());
		}
		printDividerLine(board.getRows().get(0));
	}

	private void printDividerLine(List<Cell> row) {
		StringBuffer line = new StringBuffer();
		for (int i = 0; i < row.size(); i++) {
			line.append("|------");
		}
		line.append("|\n");
		printLine(line.toString());
	}


	String getStringValue(Cell cell) {
		return cell.getValue() == null ? "      " : " " + String.format("%4d", cell.getValue()) + " ";
	}

	void printLine(String string) {
		System.out.print(string);
	}
}
