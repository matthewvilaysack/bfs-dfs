import java.util.*;
public class ColorPath {
    public static int[][] colorPath(int[][] image, int sourceRow, int sourceCol, int newColor) {
        ColorPath colorpath1 = new ColorPath();
        if (image[sourceRow][sourceCol] !=newColor) {
            colorpath1.BFS(image, sourceRow, sourceCol, newColor, image[sourceRow][sourceCol]);
        }
        return image;
    }

    public void BFS(int[][] arr, int row, int col, int newColor, int oldColor) {
        Queue<int[]> queue = new LinkedList<int[]>(); // queue contains integer pairs of where we need to fill newColor
        queue.add(new int[]{row, col});


        while (!queue.isEmpty()) {
            int[] removedIndicies = queue.remove(); // remove the last integer pair []
            int r = removedIndicies[0];
            int c = removedIndicies[1];
            arr[r][c] = newColor; // assign 'this' cell to newColor

//            System.out.println(arr[r][c]);
            // the
            if (r - 1 >= 0 && arr[r - 1][c] == oldColor) {
                queue.offer(new int[]{r - 1, c}); // add to the end
                arr[r - 1][c] = newColor;

            }
            if (r + 1 < arr.length && arr[r + 1][c] != newColor && arr[r + 1][c] == oldColor) {
                queue.offer(new int[]{r + 1, c});
                arr[r + 1][c] = newColor;

            }
            if (c + 1 < arr[0].length && arr[r][c + 1] != newColor && arr[r][c + 1] == oldColor) {
                queue.offer(new int[]{r, c + 1});
                arr[r][c + 1] = newColor;


            }
            if (c - 1 >= 0 && arr[r][c - 1] != newColor && arr[r][c - 1] == oldColor) {
                queue.offer(new int[]{r, c - 1}); // add to the end
                arr[r][c - 1] = newColor;
            }

        }
    }

    public static void DFS(int[][] arr, int row, int col, int newColor, int oldColor) {
        if (row >= arr.length || row < 0 || col >= arr[0].length || col < 0 || arr[row][col] != oldColor) {
            return;
        }
        arr[row][col] = newColor;

        DFS(arr, row - 1, col, newColor, oldColor);
        DFS(arr, row + 1, col, newColor, oldColor);
        DFS(arr, row, col - 1, newColor, oldColor);
        DFS(arr, row, col + 1, newColor, oldColor);

    }
}
