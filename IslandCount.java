import java.util.*;
public class IslandCount {
    public static int islandCount(int[][] grid) {
// 		Since this problem does not have any designated starting point, you will have to iterate
// over the entire grid to treat each position as a start for your search algorithm of choice
        boolean[][] visitedArray = new boolean[grid.length][grid[0].length];
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                //call the search algorithm when a position is false in visitedArray
                //one call of the search algorithm detects 1 island
                if (!visitedArray[i][j] && grid[i][j] == 1) {
                    DFS(grid, visitedArray, i,j,new int[]{i,j});
                    count++;
                }
                //n^2complexity
            }
        }
        return count;
    }
    public static void BFS(int[][] grid, boolean[][] visitedArray, int[] startPos) {
        Queue<int[]> queue = new LinkedList<int[]>(); // queue contains integer pairs of where we need to fill newColor
        queue.add(new int[]{startPos[0], startPos[1]}); // add the first index [row,col]
        while (!queue.isEmpty()) {
            int[] removedIndicies = queue.remove(); // remove the last integer pair []
            int r = removedIndicies[0];
            int c = removedIndicies[1];
            visitedArray[r][c] = true; // visited

            if (r - 1 >= 0  && !visitedArray[r-1][c] && grid[r - 1][c] == 1) {
                queue.offer(new int[]{r - 1, c}); // add to the end
                visitedArray[r-1][c] = true;

            }
            if (r + 1 < grid.length && !visitedArray[r+1][c] && grid[r + 1][c] == 1) {
                queue.offer(new int[]{r + 1, c});
                visitedArray[r+1][c] = true;

            }
            if (c + 1 < grid[0].length  && !visitedArray[r][c+1] && grid[r][c+1] == 1) {
                queue.offer(new int[]{r, c + 1});
                visitedArray[r][c+1] = true;
            }
            if (c - 1 >= 0 && !visitedArray[r][c-1] && grid[r][c - 1] == 1) {
                queue.offer(new int[]{r, c - 1}); // add to the end
                visitedArray[r][c-1] = true;
            }

			//o(min(r*c)) time c
			//o(r*c) space c

        }
    }
    public static void  DFS(int[][] grid, boolean[][] visitedArray,int row, int col, int[] startPos) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == 0 ||  visitedArray[row][col]) {
            return; 
        }
        if (grid[row][col] == 1 && !visitedArray[row][col]) {
            visitedArray[row][col] = true;
        }
        DFS(grid, visitedArray, row - 1, col, startPos);
        DFS(grid, visitedArray, row + 1, col, startPos);
        DFS(grid, visitedArray, row, col - 1, startPos);
        DFS(grid, visitedArray, row, col + 1, startPos);
    }
}