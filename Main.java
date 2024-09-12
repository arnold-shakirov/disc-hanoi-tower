import java.util.Scanner;
import java.util.Stack;


public class Main {

    public static int counter = 0;
    public static int disks;
    public static Stack<Integer> start = new Stack<Integer>();
    public static Stack<Integer> aux = new Stack<Integer>();
    public static Stack<Integer> end = new Stack<Integer>();

    public static void main(String[] args) {
        System.out.println("Enter number of disks ");
        Scanner in = new Scanner(System.in);
        disks = in.nextInt();
        String PC = "";
        Board board = new Board();
        board.populateBoard();
        System.out.println("Enter p for player or enter c for computer");
        PC = in.next();
        if (PC.equals("p") == false && PC.equals("c") == false) {
            System.out.println("Invalid input");
            return;
        }
        if (PC.equals("p")) {
            PlayerPart player = new PlayerPart();
            while (board.checkWin() == false) {
                player.nextMove();
            }
        } else if (PC.equals("c")) {
            autoSolve(disks, start, end, aux, board);
        } else {
            System.out.println("Invalid input");
        }
        System.out.println("The number of moves :" + counter);
        System.out.println("Thank you for playing");

    }

    public static class Board {
        private final int lengthOf = disks * 2;


        public void populateBoard() {
            for (int i = disks; i > 0; i--) {
                start.push(i);
            }
        }

        public boolean checkWin() {
            if (end.size() == disks) {
                return true;
            }
            return false;
        }
    }

    public static void autoSolve(int n, Stack<Integer> start, Stack<Integer> end, Stack<Integer> aux, Board board) {
        if (n > 0) {
            autoSolve(n - 1, start, aux, end, board);
            end.push(start.pop());
            counter++;
            autoSolve(n - 1, aux, end, start, board);
        }
    }

    public static class PlayerPart {
        public void nextMove() {
            Scanner in = new Scanner(System.in);
            System.out.println("Choose your stack (1,2,3)");
            int from = in.nextInt();
            System.out.println("Choose where to put it (1,2,3)");
            int to = in.nextInt();
            if (from == 1 && to == 2) {
                if (StackPart.move(start, aux) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else if (from == 1 && to == 3) {
                if (StackPart.move(start, end) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else if (from == 2 && to == 1) {
                if (StackPart.move(aux, start) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else if (from == 2 && to == 3) {
                if (StackPart.move(aux, end) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else if (from == 3 && to == 1) {
                if (StackPart.move(end, start) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else if (from == 3 && to == 2) {
                if (StackPart.move(end, aux) == true) {
                    counter++;
                } else {
                    System.out.println("Oops invalid move");
                }
            } else {
                System.out.println("Oops invalid move");
            }
        }
    }

    public static class StackPart {
        public static boolean move(Stack<Integer> first, Stack<Integer> second) {
            if (first.isEmpty()) {
                return false;
            }
            if (!second.isEmpty() && first.peek() > second.peek()) {
                return false;
            }
            second.push(first.pop());
            return true;
        }
    }
}
