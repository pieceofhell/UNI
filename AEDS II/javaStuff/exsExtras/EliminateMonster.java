import java.util.Arrays;

class EliminateMonster extends Solution {

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int monstersKilled = 0;

        for (int i = 0; i < n; i++) {
            dist[i] = (int) Math.ceil((double) dist[i] / speed[i]);
        }

        Arrays.sort(dist);

        for (int i = 0; i < n; i++) {
            if (dist[i] <= i) {
                break;
            }
            monstersKilled++;
        }

        for (int i = 0; i < n; i++) {
            dist[i]--;
        }
        return monstersKilled;
    }
}