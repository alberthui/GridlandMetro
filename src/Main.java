import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        long m = sc.nextInt();
        int k = sc.nextInt();

        HashMap<Integer,ArrayList<Pair>> map = new HashMap<>();
        long count = n*m;

        for (int i =0;i<k;i++) {
            int row = sc.nextInt();
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();

            if (map.containsKey(row)) {
                ArrayList<Pair> pairList = map.get(row);
                ArrayList<Pair> toBeRemoved = new ArrayList<>();
                ArrayList<Pair> toBeAdded = new ArrayList<>();

                Pair addedPair = null;
                for (Pair pair : pairList) {
                    int curMin = pair.getStart();
                    int curMax = pair.getEnd();

                    if (pair.isOverlap(c1, c2)) {
                        if (addedPair == null) {
                            if (c1 < curMin) pair.setStart(c1);
                            if (c2 > curMax) pair.setEnd(c2);
                            addedPair = pair;
                        } else {
                            if (pair.isOverlap(addedPair.getStart(), addedPair.getEnd())) {
                                if (pair.getStart() < addedPair.getStart()) addedPair.setStart(pair.getStart());
                                if (pair.getEnd() > addedPair.getEnd()) addedPair.setEnd(pair.getEnd());
                                toBeRemoved.add(pair);
                            }
                        }
                    } else {
                        Pair newPair = new Pair(c1, c2);
                        toBeAdded.add(newPair);
                    }
                }
                pairList.addAll(toBeAdded);
                pairList.removeAll(toBeRemoved);
            } else {
                Pair pair = new Pair(c1, c2);
                ArrayList<Pair> pairList = new ArrayList<>();
                pairList.add(pair);
                map.put(row,pairList);
            }
        }

        //start to count
        for (int row : map.keySet()) {
            for (Pair pair : map.get(row)) {
                count -= (pair.getEnd() - pair.getStart() + 1);
            }
        }

        System.out.println(count);
    }

    static class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public boolean isOverlap(int c1, int c2) {
            if (start <= c1 && end >= c1) return true;
            if (start <= c2 && end >= c2) return true;
            return false;
        }
    }
}
