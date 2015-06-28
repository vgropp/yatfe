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
	public static void main(String[] args) throws IOException {
		new Yatfe().run();
	}

	void run() {
		System.out.println("Enter grid size: ");
		try (Scanner s = new Scanner(new InputStreamReader(System.in, Charset.defaultCharset()))) {
			Board board = new Board(s.nextInt());
			board.init();
			BoardView boardView = new BoardView(board);
			while (true) {
				aSingleMove(board, boardView, s);
			}
		}
	}

	void aSingleMove(Board board, BoardView boardView, Scanner s) {
		boardView.print();
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
	}

	char getNextChar(Scanner s) {
		System.out.println("next move ('a' (left) , 'd' (right) , 'w' (up) and 's' (down))?");
		char c = s.next().charAt(0);
		return c;
	}
}
