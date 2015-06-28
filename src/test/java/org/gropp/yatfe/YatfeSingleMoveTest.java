package org.gropp.yatfe;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.easymock.EasyMock;
import org.junit.Test;

public class YatfeSingleMoveTest {

	@Test
	public void moveUp() throws Exception {
		Board board = EasyMock.createStrictMock(Board.class);
		BoardView boardView = EasyMock.createStrictMock(BoardView.class);
		Yatfe yatfe = EasyMock.createMockBuilder(Yatfe.class).addMockedMethod("getNextChar").createStrictMock();

		boardView.print();
		Scanner scanner = getScanner();
		EasyMock.expect(yatfe.getNextChar(scanner)).andReturn('w');
		board.up();

		EasyMock.replay(yatfe,board,boardView);
		yatfe.aSingleMove(board, boardView, scanner);
		EasyMock.verify(yatfe,board,boardView);
	}

	@Test
	public void moveDown() throws Exception {
		Board board = EasyMock.createStrictMock(Board.class);
		BoardView boardView = EasyMock.createStrictMock(BoardView.class);
		Yatfe yatfe = EasyMock.createMockBuilder(Yatfe.class).addMockedMethod("getNextChar").createStrictMock();

		boardView.print();
		Scanner scanner = getScanner();
		EasyMock.expect(yatfe.getNextChar(scanner)).andReturn('s');
		board.down();

		EasyMock.replay(yatfe,board,boardView);
		yatfe.aSingleMove(board, boardView, scanner);
		EasyMock.verify(yatfe,board,boardView);
	}

	@Test
	public void moveLeft() throws Exception {
		Board board = EasyMock.createStrictMock(Board.class);
		BoardView boardView = EasyMock.createStrictMock(BoardView.class);
		Yatfe yatfe = EasyMock.createMockBuilder(Yatfe.class).addMockedMethod("getNextChar").createStrictMock();

		boardView.print();
		Scanner scanner = getScanner();
		EasyMock.expect(yatfe.getNextChar(scanner)).andReturn('a');
		board.left();

		EasyMock.replay(yatfe,board,boardView);
		yatfe.aSingleMove(board, boardView, scanner);
		EasyMock.verify(yatfe,board,boardView);
	}

	@Test
	public void moveRight() throws Exception {
		Board board = EasyMock.createStrictMock(Board.class);
		BoardView boardView = EasyMock.createStrictMock(BoardView.class);
		Yatfe yatfe = EasyMock.createMockBuilder(Yatfe.class).addMockedMethod("getNextChar").createStrictMock();

		boardView.print();
		Scanner scanner = getScanner();
		EasyMock.expect(yatfe.getNextChar(scanner)).andReturn('d');
		board.right();

		EasyMock.replay(yatfe,board,boardView);
		yatfe.aSingleMove(board, boardView, scanner);
		EasyMock.verify(yatfe,board,boardView);
	}

	@Test
	public void noMove() throws Exception {
		Board board = EasyMock.createStrictMock(Board.class);
		BoardView boardView = EasyMock.createStrictMock(BoardView.class);
		Yatfe yatfe = EasyMock.createMockBuilder(Yatfe.class).addMockedMethod("getNextChar").createStrictMock();

		boardView.print();
		Scanner scanner = getScanner();
		EasyMock.expect(yatfe.getNextChar(scanner)).andReturn('q');

		EasyMock.replay(yatfe,board,boardView);
		yatfe.aSingleMove(board, boardView, scanner);
		EasyMock.verify(yatfe,board,boardView);
	}

	private Scanner getScanner() {
		Scanner scanner = new Scanner(new InputStream() {

			@Override
			public int read() throws IOException {
				return 0;
			}
		});
		return scanner;
	}

}
