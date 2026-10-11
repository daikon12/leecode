package medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: kanedai
 * @date: 2026/9/17
 */
public class _116_cycleNumber {

    public static void main(String[] args) {
        System.out.println(new _116_cycleNumber().findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));

        System.out.println(new _116_cycleNumber().bfs(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));

    }

    /**
     * 广度优先
     * 队列
     * 探索方式从"一条路走到底"换成了"一圈一圈向外扩散"。
     *  BFS 像水波扩散：从起点开始，先把紧挨着的直接邻居全部标记，再标记邻居的邻居……一层一层向外铺开。
     * @param isConnected
     * @return
     */
    public int bfs(int[][] isConnected) {
        int cityNum = isConnected.length;
        int provinceNum = 0;
        boolean[] visited = new boolean[cityNum];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < cityNum; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    visited[cur] = true;
                    for (int j = 0; j < cityNum; j++) {
                        if (isConnected[cur][j] == 1 && !visited[j]) {
                            queue.offer(j);
                        }
                    }
                }
                // 队列空了 = 这一整块省份走完了，计数 +1
                provinceNum++;
            }
        }
        return provinceNum;

    }


    /**
     * 深度遍历
     * 递归
     * @param isConnected
     * @return
     */
    public int findCircleNum(int[][] isConnected) {
        int cityNum = isConnected.length;
        boolean[] visited = new boolean[cityNum];
        int provinceNum = 0;
        for (int i = 0; i < cityNum; i++) {
            if (!visited[i]) { // 找到一个还没归属的城市
                // 用 visited 数组记录哪些城市已经"认领"完毕，外层循环每发现一个"新城市"，
                // 就派出 DFS 把和它相连的所有城市一次性全部认领，每认领完一整块就计为一个省份。
                dfs(i, isConnected, visited);  // 顺着路把整个省份走一遍
                provinceNum++; // 走完一圈，就是一个新省份
            }
        }
        return provinceNum;

    }

    private void dfs(int i, int[][] isConnected, boolean[] visited) {
        for(int j = 0 ; j < isConnected.length; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(j, isConnected, visited);
            }
        }
    }
}