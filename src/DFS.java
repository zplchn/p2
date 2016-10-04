import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by zplchn on 9/8/16.
 */
public class DFS {



    //200

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < grid.length; ++i){
            for (int j = 0; j < grid[0].length; ++j){
                if (grid[i][j] == '1'){
                    ++res;
                    numIslandsHelper(grid, i, j);
                }
            }
        }
        for (int i = 0; i < grid.length; ++i){
            for (int j = 0; j < grid[0].length; ++j){
                if (grid[i][j] == 'x'){
                    grid[i][j] = '1';
                }
            }
        }

        return res;
    }

    private void numIslandsHelper(char[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1')
            return;
        grid[i][j] = 'x';
        numIslandsHelper(grid, i - 1, j);
        numIslandsHelper(grid, i + 1, j);
        numIslandsHelper(grid, i, j - 1);
        numIslandsHelper(grid, i, j + 1);
    }
}
