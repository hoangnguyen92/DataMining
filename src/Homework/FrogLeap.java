package Homework;

import java.util.Scanner;

public class FrogLeap {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Please enter number of frog for 1 side (the other side will be the same): ");
		int numFrog = sc.nextInt();
		int placesOnBoard = (numFrog * 2) + 1;

		char[] board = new char[placesOnBoard];

		for (int i = 0; i < board.length; i++) {
			if (i < numFrog) {
				board[i] = 'L';
			} else if (i == numFrog) {
				board[i] = '_';
			} else {
				board[i] = 'R';
			}
		}

		leapFrog(board, numFrog);
	}

	private static void leapFrog(char[] board, int numOfFrog) {
		for (int i = 1; i <= numOfFrog; i++) {
			if (i % 2 == 0) {
				frogJump(board, i, true, true);
			} else {
				frogJump(board, i, true, false);
			}
			printArray(board);
		}
		if (numOfFrog % 2 == 0) {
			frogJump(board, numOfFrog, null, false);
		} else {
			frogJump(board, numOfFrog, null, true);
		}
		printArray(board);
		for (int i = numOfFrog; i > 0; i--) {
			if (i % 2 == 0) {
				frogJump(board, i, false, true);
			} else {
				frogJump(board, i, false, false);
			}
			printArray(board);
		}

	}

	private static int findStartIndex(boolean isLeftMove, char[] board) {
		int startIndex = 0;
		for (int i = 0; i < board.length; i++) {
			if (board[i] == '_') {
				if (isLeftMove) {
					startIndex = i - 1;
					if(board[startIndex]!='L'){
						startIndex--;
					}
				} else {
					startIndex = i + 1;
					if(board[startIndex]!='R'){
						startIndex++;
					}
				}
			}
		}
		return startIndex;
	}

	private static void printArray(char[] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.print(board[i]);
		}
		System.out.println();
	}

	private static void frogJump(char[] board, int moves, Boolean isFirstMoves, boolean isLeftMove) {
		if (isFirstMoves == null) {
			for (int i = 0; i < moves; i++) {
					swap(board, true, findStartIndex(isLeftMove, board), isLeftMove);
			}
		} else if (isFirstMoves == true) {
			for (int i = 0; i < moves; i++) {
				if (i +1 == moves ) {
					swap(board, false, findStartIndex(isLeftMove, board), isLeftMove);
				}else{
					swap(board, true, findStartIndex(isLeftMove, board), isLeftMove);
				}
			}
		} else {
			for (int i = 0; i < moves; i++) {
				if (i  == 0 ) {
					swap(board, false, findStartIndex(isLeftMove, board), isLeftMove);
				}else{
					swap(board, true, findStartIndex(isLeftMove, board), isLeftMove);
				}
			}
		}
	}

	private static void swap(char[] board, boolean isJump, int startIndex, boolean isLeftMove) {
		char buff = board[startIndex];
		int stepMoves = 0;
		if (isJump) {
			stepMoves = 2;
		} else {
			stepMoves = 1;
		}

		if (isLeftMove) {
			board[startIndex] = board[startIndex + stepMoves];
			board[startIndex + stepMoves] = buff;
		} else {
			board[startIndex] = board[startIndex - stepMoves];
			board[startIndex - stepMoves] = buff;
		}

	}

}
