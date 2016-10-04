import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 9/14/16.
 */
public class BFS {

    //130
    public void solve(char[][] board) {
        if(board == null || board.length == 0 || board[0].length == 0)
            return;
        for (int j = 0; j < board[0].length; ++j){
            if (board[0][j] == 'O')
                solveHelper(board, 0, j);
            if (board[board.length - 1][j] == 'O')
                solveHelper(board, board.length - 1, j);
        }
        for (int i = 0; i < board.length; ++i){
            if (board[i][0] == 'O')
                solveHelper(board, i, 0);
            if (board[i][board[0].length - 1] == 'O')
                solveHelper(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == '#')
                    board[i][j] = 'O';
            }
        }
    }

    private void solveHelper(char[][] board, int i, int j){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(i*board[0].length + j);
        while (!queue.isEmpty()){
            int t = queue.poll();
            int x = t / board[0].length, y = t % board[0].length;
            board[x][y] = '#';
            if (x > 0 && board[x-1][y] == 'O'){
                board[x-1][y] = '#'; //NOTE BFS MARK FIRST THEN ENQUEUE OTEHRWISE A LOT DUP TRIES
                queue.offer((x-1)*board[0].length + y);
            }
            if (x < board.length - 1 && board[x+1][y] == 'O') {
                board[x+1][y] = '#';
                queue.offer((x + 1) * board[0].length + y);
            }
            if (y > 0 && board[x][y-1] == 'O') {
                board[x][y-1] = '#';
                queue.offer(x * board[0].length + y - 1);
            }
            if (y < board[0].length - 1 && board[x][y+1] == 'O') {
                board[x][y+1] = '#';
                queue.offer(x * board[0].length + y + 1);
            }
        }
    }
}
