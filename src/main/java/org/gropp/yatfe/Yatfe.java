package org.gropp.yatfe;

/**
 * Yet another two thousand forty eight
 *
 */
public class Yatfe {
	public static void main(String[] args) {
		Board board = new Board(4);
		board.init();
		BoardView boardView = new BoardView(board);
		boardView.print();
	}
}
