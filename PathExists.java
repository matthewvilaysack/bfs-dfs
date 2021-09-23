import java.util.*;
public class PathExists {
    public static boolean doesPathExist(char[][] grid, int sourceRow, int sourceCol) {
        boolean pathExists = false;
        // pathExists = BFS(grid,sourceRow,sourceCol);
        boolean[][] visitedArray = new boolean[grid.length][grid[0].length];
        int[] startPos = new int[]{sourceRow,sourceCol};
        pathExists = BFS(grid, sourceRow, sourceCol);
        return pathExists;
    }

    //keep track of the starting vertex in the parameter
    public static boolean BFS(char[][] grid, int row, int col) {
        // grid[sourceRow][sourceCol].equals(arr[endingRow][endingCol])

        //p = path
        //x = wall
        //v = start/end vertex
        //o = visited
        char[][] gridCopy = new char[grid.length][grid[0].length];

        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[i].length; j++)
                gridCopy[i][j]=grid[i][j];
        int[] startPos = new int[]{row,col};
        Queue<int[]> queue = new LinkedList<int[]>(); // queue contains integer pairs of where we need to fill newColor
        queue.add(new int[]{row, col}); // add the first index [row,col]
        while (!queue.isEmpty()) {
            int[] removedIndicies = queue.remove(); // remove the last integer pair []
            int r = removedIndicies[0];
            int c = removedIndicies[1];
            if (gridCopy[r][c] != 'v') {
                gridCopy[r][c] = 'o'; // o = visited
            }

            //check and verifies if the end of the path equals the correct 'v'
            if (r-1 >= 0 &&  (r-1!=startPos[0]) && gridCopy[r-1][c] == 'v') { // if r-1 is inbounds, not equal to the starting coordinate, and equals 'v'
                return true;
            }
            if (r+1 < gridCopy.length && (r+1!=startPos[0]) && gridCopy[r+1][c] == 'v') {

                return true;
            }
            if (c-1 >= 0 && (c-1!=startPos[1]) && gridCopy[r][c-1] == 'v') {

                return true;
            }
            if (c+1 < gridCopy[0].length && (c+1!=startPos[1]) && gridCopy[r][c+1] == 'v') {
                return true;
            }


            //add verified neighbors ('p') to the queue and set them to visited
            if (r-1 >= 0 && gridCopy[r-1][c] == 'p' ) {
                queue.offer(new int[]{r-1,c});
                gridCopy[r-1][c] = 'o'; // set as visited
            }
            // the
            if (r+1 < gridCopy.length && gridCopy[r+1][c] == 'p') {
                queue.offer(new int[]{r + 1, c}); // add to the end
                gridCopy[r+1][c] = 'o'; // set as visited

            }
            if (c-1 >= 0 && c-1 < gridCopy[0].length && gridCopy[r][c-1] == 'p') {
                queue.offer(new int[]{r, c-1});
                gridCopy[r][c-1] = 'o'; // set as visited

            }
            if (c+1 < gridCopy[0].length && gridCopy[r][c+1] == 'p') {
                queue.offer(new int[]{r, c + 1});
                gridCopy[r][c+1] = 'o'; // set as visited
            }
        }

        return false;

        //time complexity O(min(r*c))
    }
    public static boolean DFS(char[][] grid, boolean[][] visitedArray, int row, int col, int[] startPos) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == 'x' ||  visitedArray[row][col]) {
            return false; // void? false?
        }
        if (grid[row][col] == 'v' && (startPos[0]!=row||startPos[1]!=col)) {
            return true;
        }
        if (grid[row][col] == 'p') {
            visitedArray[row][col] = true;
        }
        return DFS(grid, visitedArray, row - 1, col, startPos) ||
        DFS(grid, visitedArray, row + 1, col, startPos) ||
        DFS(grid, visitedArray, row, col - 1, startPos) ||
        DFS(grid, visitedArray, row, col + 1, startPos);
    }

}