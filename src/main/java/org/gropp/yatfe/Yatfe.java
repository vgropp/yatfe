package org.gropp.yatfe;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Yet another two thousand forty eight
 *
 */
public class Yatfe {

	private static final int MAX_CELL_VALUE = 2048;

	public static void main(String[] args) throws IOException {
		new Yatfe().run();
	}

	void run() {
		System.out.println("Enter grid size (default and minimum 4): ");
		try (Scanner s = new Scanner(new InputStreamReader(System.in, Charset.defaultCharset()))) {
			Integer size = 4;
			try {
				size = Integer.valueOf(s.next());
				if (size < 4 ) {
					System.out.println("falling back to default 4...");
					size = 4;
				}
			} catch (NumberFormatException e) {
				System.out.println("falling back to default 4...");
			}
			Board board = new Board(size);
			board.init();
			BoardView boardView = new BoardView(board);
			while (aSingleMove(board, boardView, s)) {
				;
			}
		}
	}

	boolean aSingleMove(Board board, BoardView boardView, Scanner s) {
		boardView.print();
		if (board.getHighestValue() >= MAX_CELL_VALUE) {
			System.out.println("you won!");
			return false;
		}
		if (!board.isMovePossible()) {
			System.out.println("you lost!");
			return false;
		}
		char c = getNextChar(s);
		switch (c) {
			case 'a':
				board.left();
				break;
			case 's':
				board.down();
				break;
			case 'd':
				board.right();
				break;
			case 'w':
				board.up();
				break;

			default:
				System.out.println("unknown input");
				break;
		}
		return true;
	}

	char getNextChar(Scanner s) {
		System.out.println("next move ('a' (left) , 'd' (right) , 'w' (up) and 's' (down))?");
		char c = s.next().charAt(0);
		return c;
	}
}
