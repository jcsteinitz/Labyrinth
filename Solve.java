import java.io.*;
import java.util.*;
public class Solve {
	static boolean stop = false;
	static ArrayList<Integer> moves = new ArrayList<Integer>();
    static Labyrinth l = new Labyrinth(5, 5);
    static boolean visited[][] = new boolean[l.rows][l.cols];

	public static void solve(Labyrinth l) {
		findSafeMove(0, 0, l);
	}
	
	public static void findSafeMove(int row, int col, Labyrinth l) {
		if (stop) {
			return;
		}
		if(row == l.rows - 1 && col == l.cols - 1) {
			stop = true;
            sendSolution();
			return;
		}
        // DOWN
		if(l.isValid(row + 1, col) && l.isStone(row + 1, col) && !visited[row][col]) {
            visited[row][col] = true;
			makeMove(0);
			findSafeMove(row + 1, col, l);
			undo(0);
            visited[row][col] = false;
        }
        // UP
        if(l.isValid(row - 1, col) && l.isStone(row - 1, col) && !visited[row][col]) {
            visited[row][col] = true;
			makeMove(1);
			findSafeMove(row - 1, col, l);
			undo(1);
            visited[row][col] = false;
        }
        // LEFT
        if(l.isValid(row, col - 1) && l.isStone(row, col - 1) && !visited[row][col]) {
            visited[row][col] = true;
			makeMove(2);
			findSafeMove(row, col - 1, l);
			undo(2);
            visited[row][col] = false;
        }
        // RIGHT
        if(l.isValid(row, col + 1) && l.isStone(row, col + 1) && !visited[row][col]) {
            visited[row][col] = true;
			makeMove(3);
			findSafeMove(row, col + 1, l);
			undo(3);
            visited[row][col] = false;
        }
	}
    
	
	public static void makeMove(int move) {
		moves.add(move);
	}
	
	public static void undo(int move) {
		moves.remove(moves.size() - 1);
	}

	public static void sendSolution() {
		Integer[] send = new Integer[moves.size()];
		moves.toArray(send);
        System.out.println("Solution: ");
		for(int i = 0; i < send.length; i++) {
            if(send[i] == 0) {
                System.out.println("Down");
            }
            if(send[i] == 1) {
                System.out.println("Up");
            }
            if(send[i] == 2) {
                System.out.println("Left");
            }
            if(send[i] == 3) {
                System.out.println("Right");
            }
		}
	}

	public static void main(String[] args) {
        l.printGrid();
		solve(l);
    }
}