package com.zhulinfeng.leetcode;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;


class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
        assert x >= 0 && y >= 0;
        this.x = x;
        this.y = y;
    }
}

class Pixel {
    public static final char path = ' ';
    public static final char block = '*';
}

class Grid implements Comparable{
    int distance = Integer.MAX_VALUE;
    Grid father = null;
    Coordinate coordinate;

    public Grid(int x, int y) {
        this.coordinate = new Coordinate(x, y);
    }
    @Override
    public int compareTo(Object o) {
        Grid g2 = (Grid)o;
        return this.distance - g2.distance;
    }
}


public class ShortestPath {
    private char[][] map;
    final int row;
    final int col;
    private Coordinate source;
    private Coordinate destination;
    private Grid[][] pathMap;
    private PriorityQueue<Grid> queue;

    public ShortestPath(int row, int col) {
        this.row = row;
        this.col = col;
        this.map = new char[row][col];
    }

    public void initMap(int percent) {
        assert percent >= 0 && percent <= 100;

        Random random = new Random();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                int rnd = random.nextInt() % 50 + 50;
                if (rnd > percent) this.map[i][j] = Pixel.path;
                else this.map[i][j] = Pixel.block;
            }
        }
    }

    public void initMap(char[][] map) {
        assert map.length == this.row;
        assert map[0].length == this.col;
        this.map = map;
    }

    public void printMap(char[][] map) {
        assert null != map;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                str.append(map[i][j]);
                //if (map[i][j] == ' ') str.append(' ');
            }
            str.append('\n');
        }
        System.out.print(str.toString());
    }

    public void printMap() {
        printMap(this.map);
    }

    public boolean checkCoordinate(Coordinate c) {
        assert null != c;
        if (c.x < 0 || c.x >= this.row ||
                c.y < 0 || c.y >= this.col ||
                this.map[c.x][c.y] == Pixel.block) return false;
        else return true;
    }

    private void initCalc(Coordinate source, Coordinate destination) throws Exception {
        assert null != source && null != destination;

        if (!checkCoordinate(source) || !checkCoordinate(destination)) {
            throw new Exception("source or destination is unreachable");
        }
        this.source = source;
        this.destination = destination;

        this.queue = new PriorityQueue<>();

        this.pathMap = new Grid[this.row][this.col];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                this.pathMap[i][j] = new Grid(i, j);
            }
        }
    }

    private void finitCalc() {
        this.source = null;
        this.destination = null;
        this.queue = null;
        this.pathMap = null;
    }

    public void calcPathTree() {
        assert checkCoordinate(source);

        Grid sourceGrid = this.pathMap[source.x][source.y];
        sourceGrid.distance = 0;
        this.queue.add(sourceGrid);

        Grid current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            Coordinate around[] = {new Coordinate(current.coordinate.x - 1, current.coordinate.y),
                    new Coordinate(current.coordinate.x + 1, current.coordinate.y),
                    new Coordinate(current.coordinate.x, current.coordinate.y - 1),
                    new Coordinate(current.coordinate.x, current.coordinate.y + 1)};

            for (Coordinate c : around) {
                if (checkCoordinate(c)) {
                    Grid grid = this.pathMap[c.x][c.y];
                    if (current.distance + 1 < grid.distance) {
                        grid.distance = current.distance + 1;
                        grid.father = current;
                        if (!queue.contains(grid)) queue.add(grid);
                    }
                }
            }
        }
    }

    public void printDistanceMap() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                str.append(String.format("%11d", this.pathMap[i][j].distance));
            }
            str.append('\n');
        }
        System.out.println(str);
    }

    public void printPath() {
        char rmap[][] = new char[this.row][this.col];
        for (int i= 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                rmap[i][j] = this.map[i][j];
            }
        }
        rmap[destination.x][destination.y] = '@';
        rmap[source.x][source.y] = '@';
        Grid child = this.pathMap[destination.x][destination.y];
        Grid father = child.father;
        while (father != null) {
            if (father.coordinate.x == child.coordinate.x) {
                rmap[father.coordinate.x][father.coordinate.y] = '-';
            } else if (father.coordinate.y == child.coordinate.y) {
                rmap[father.coordinate.x][father.coordinate.y] = '|';
            }
            child = father;
            father = father.father;
        }
        printMap(rmap);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input the size of map:");
        ShortestPath path = new ShortestPath(scanner.nextInt(), scanner.nextInt());

        System.out.println("please input the density of blocks in map:");
        path.initMap(scanner.nextInt());
//        ShortestPath path = new ShortestPath(10, 10);
//        path.initMap(ShortestPath.map2(path.row, path.col));
        path.printMap();

        while (true) {
            System.out.println("please input source point and dest point:");
            Coordinate source = new Coordinate(scanner.nextInt(), scanner.nextInt());
            Coordinate destination = new Coordinate(scanner.nextInt(), scanner.nextInt());
            System.out.println(String.format("source(%d,%d)", source.x, source.y));
            System.out.println(String.format("dest(%d,%d)", destination.x, destination.y));

            try {
                path.initCalc(source, destination);
                path.calcPathTree();
                path.printPath();
                path.finitCalc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static char[][] map1() {
        char[][] map = {{' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', '*', ' ', '*', ' ', '*', ' ', '*', ' ', '*'},
                        {' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' '}};
        return map;
    }

    public static char[][] map2(int row, int col) {
        char[][] map = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) map[i][j] = Pixel.block;
        }

        int i = 0, j = 0;
        int direction = 1;
        while (map[i][j] == Pixel.block) {
            map[i][j] = Pixel.path;
            if (1 == direction) {
                if ((i+2 < row && map[i+2][j] == Pixel.path) || i+1 >= row) {direction = 2; j++;}
                else i++;
            } else if (2 == direction) {
                if ((j+2 < col && map[i][j+2] == Pixel.path) || j+1 >= col) {direction = 3; i--;}
                else j++;
            } else if (3 == direction) {
                if ((i-2 >= 0 && map[i-2][j] == Pixel.path) || i-1 <= 0) {direction = 4; j--;}
                else i--;
            } else {
                if ((j-2 >= 0 && map[i][j-2] == Pixel.path) || j-1 <= 0) {direction = 1; i++;}
                else j--;
            }
        }
        return map;
    }
}
