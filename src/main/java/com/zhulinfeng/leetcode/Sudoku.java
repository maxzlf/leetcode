package com.zhulinfeng.leetcode;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

class NoSolutionError extends Exception {

}

class Coord {
    final int line;
    final int row;

    Coord(int line, int row) {
        this.line = line;
        this.row = row;
    }

    @Override
    public boolean equals(Object coord) {
        Coord c = (Coord) coord;
        return c.line == line && c.row == row;
    }

    @Override
    public int hashCode() {
        return line * 1000 + row * 100;
    }
}

class Board {
    static final int LINES = 9;
    static final int ROWS = 9;
    private int[][] map;

    Board(int[][] map) {
        assert null != map;
        assert map.length == ROWS;
        assert map[0].length == LINES;
        this.map = map;
    }

    private Set<Integer> getValueSet(List<Coord> coords) throws Exception {
        Set<Integer> exists = new HashSet<>();
        for (Coord c : coords) {
            int value = map[c.line][c.row];
            if (0 == value) continue;

            if (exists.contains(value)) {
                throw new Exception("Board invalid");
            }
            exists.add(value);
        }
        return exists;
    }

    Set<Integer> getOptions(int line, int row) throws Exception {
        List<Coord> lineCoords = getLineCoordinates(line);
        Set<Integer> lineExists = getValueSet(lineCoords);
        List<Coord> rowCoords = getRowCoordinates(row);
        Set<Integer> rowExists = getValueSet(rowCoords);
        List<Coord> regionCoords = getRegionCoordinates(line, row);
        Set<Integer> regionExists = getValueSet(regionCoords);

        Set<Integer> exists = new HashSet<>();
        exists.addAll(lineExists);
        exists.addAll(rowExists);
        exists.addAll(regionExists);
        exists.remove(0);

        Set<Integer> options = new LinkedHashSet<>();
        for (int i = 1; i <= 9; i++) {
            if (!exists.contains(i)) {
                options.add(i);
            }
        }

        return options;
    }

    void set(int line, int row, int value) {
        map[line][row] = value;
    }

    int get(int line, int row) {
        return map[line][row];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] line : map) {
            for (int i : line) {
                String item = (i == 0)? "." : Integer.toString(i);
                item += " ";
                sb.append(item);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    protected Board clone() {
        int[][] copy = new int[9][9];
        for (int i = 0; i < LINES; i++) {
            for (int j = 0; j < ROWS; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return new Board(copy);
    }

    static List<Coord> getLineCoordinates(int line) {
        List<Coord> list = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            list.add(new Coord(line, i));
        }
        return list;
    }

    static List<Coord> getRowCoordinates(int row) {
        List<Coord> list = new ArrayList<>();
        for (int i = 0; i < LINES; i++) {
            list.add(new Coord(i, row));
        }
        return list;
    }

    static List<Coord> getRegionCoordinates(int line, int row) {
        List<Coord> list = new ArrayList<>();

        int lineBase = line - line % 3;
        int rowBase = row - row % 3;
        for (int L = lineBase; L < lineBase + 3; L++) {
            for (int R = rowBase; R < rowBase + 3; R++) {
                list.add(new Coord(L, R));
            }
        }

        return list;
    }
}

class BlankGrid implements Comparable {
    private Coord coord;
    private Set<Integer> options;

    BlankGrid(Coord coord, Set<Integer> options) {
        this.coord = coord;
        this.options = options;
    }

    boolean isEmpty() {
        return options.isEmpty();
    }

    int size() {
        return options.size();
    }

    void add(int option) {
        options.add(option);
    }

    void remove(int option) {
        options.remove(option);
    }

    Coord getCoord() {
        return coord;
    }

    int[] values() throws NoSolutionError {
        if (options.size() < 1) {
            throw new NoSolutionError();
        }
        int[] values = new int[options.size()];
        int index = 0;
        for (int v : options) {
            values[index++] = v;
        }
        return values;
    }

    @Override
    public String toString() {
        String str = String.format("(%d, %d): ", coord.line, coord.row);
        return str + options;
    }

    @Override
    protected BlankGrid clone() {
        Set<Integer> set = new HashSet<>();
        for (int v : options) set.add(v);
        return new BlankGrid(new Coord(coord.line, coord.row), set);
    }

    @Override
    public int compareTo(Object o) {
        BlankGrid g = (BlankGrid) o;
        return size() - g.size();
    }
}

class MinHeap extends PriorityQueue<BlankGrid> {
    MinHeap() {}

    MinHeap(Collection<BlankGrid> collection) {
        super(collection);
    }

    @Override
    protected MinHeap clone() {
        MinHeap copy = new MinHeap();
        for (BlankGrid g : this) copy.add(g.clone());
        return copy;
    }
}

public class Sudoku {
    private Board board;
    private MinHeap heap;
    private List<Board> solutions = new ArrayList<>();

    public Sudoku(Board board) {
        this.board = board;
    }

    public List<Board> solve() throws Exception {
        initQueue();
        recurse(board.clone(), heap.clone());
        return solutions;
    }

    private void recurse(Board board, MinHeap heap) {
        if (heap.isEmpty()) {
            solutions.add(board);
            return;
        }

        try {
            BlankGrid grid = heap.poll();
            int[] values = grid.values();
            for (int v : values) {
                MinHeap heapCopy = heap.clone();
                board.set(grid.getCoord().line, grid.getCoord().row, v);
                List<BlankGrid> related = getRelatedGrids(heapCopy, grid.getCoord());
                for (BlankGrid o : related) {
                    o.remove(v);
                }
                recurse(board.clone(), heapCopy);
            }
        } catch (NoSolutionError ignore) {

        }
    }

    private List<BlankGrid> getRelatedGrids(MinHeap heap, Coord coord) {
        List<BlankGrid> related = new ArrayList<>();

        for (BlankGrid g : heap) {
            if (g.getCoord().line == coord.line || g.getCoord().row == coord.row) {
                related.add(g);
                continue;
            }
            int lineBase = coord.line - coord.line % 3;
            int rowBase = coord.row - coord.row % 3;
            int L = g.getCoord().line;
            int R = g.getCoord().row;
            if (L >= lineBase && L < lineBase + 3 && R >= rowBase && R < rowBase + 3) {
                related.add(g);
            }
        }

        return related;
    }

    private void initQueue() throws Exception {
        heap = new MinHeap();
        for (int L = 0; L < Board.LINES; L++) {
            for (int R = 0; R < Board.ROWS; R++) {
                int value = board.get(L, R);
                if (0 == value) {
                    heap.add(new BlankGrid(new Coord(L, R), board.getOptions(L, R)));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int[][] map = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 8, 0, 0, 0},
                {0, 0, 0, 6, 0, 0, 8, 2, 9},
                {6, 0, 0, 9, 2, 0, 0, 1, 7},
                {0, 4, 9, 0, 3, 0, 1, 7, 0},
                {3, 7, 0, 8, 0, 0, 4, 5, 2},
                {2, 0, 5, 0, 1, 0, 0, 0, 0}
        };
        Board board = new Board(map);
        Sudoku sudoku = new Sudoku(board);
        List<Board> solutions = sudoku.solve();
        for (Board b : solutions) {
            System.out.println(b);
            System.out.println("--------------------------------");
        }
        System.out.println("total: " + solutions.size());
    }
}
