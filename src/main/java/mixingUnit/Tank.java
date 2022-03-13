package mixingUnit;

import teil2.task08.TankSensor;

public abstract class Tank {

    private boolean[][][] capacity;
    private TankSensor sensor;

    public Tank(int length, int width, int height) {
        capacity = new boolean[length][width][height];
    }

    public Tank(int length, int width, int height,TankSensor tankSensor) {
        capacity = new boolean[length][width][height];
        this.sensor=tankSensor;
    }

    public Tank() {

    }

    public void fill(int amount) {
        int[] pos = getPositionFirstEmpty();
        int x = pos[0];
        int y = pos[1];
        int z = pos[2];
        for (int i = 0; i < amount; i++) {
            capacity[x][y][z] = true;
            if (x == capacity.length - 1 && y == capacity[0].length - 1) z++;
            if (x == capacity.length - 1) y = ++y % capacity[0].length;
            x = ++x % capacity.length;
        }
        if(sensor!=null){
            sensor.measure(((double)occupiedSpace()/(double)maxCapacity())*100);
        }
    }

    public int emptySpace() {
        int[] pos = getPositionFirstEmpty();
        int z = (capacity[0][0].length - (pos[2] + 1)) * capacity[0].length * capacity.length;
        int y = (capacity[0].length - (pos[1] + 1)) * capacity.length;
        int x = (capacity.length - (pos[0]));
        return x + y + z;
    }

    public int maxCapacity() {
        return capacity.length * capacity[0].length * capacity[0][0].length;
    }

    public int occupiedSpace() {
        return maxCapacity() - emptySpace();
    }

    public void fillToMax() {
        fill(emptySpace());
    }

    public TankSensor getSensor() {
        return sensor;
    }

    public void setSensor(TankSensor sensor) {
        this.sensor = sensor;
    }

    public int takeOut(int amount) {
        int[] pos = getPositionFirstEmpty();
        int x = pos[0];
        int y = pos[1];
        int z = pos[2];
        for (int i = 0; i < amount; i++) {

            if (x == 0 && y == 0) {
                z--;
                x = capacity.length;
                y = capacity[0].length - 1;
            }
            if (x == 0) {
                y--;
                x = capacity.length;
            }
            x--;
            capacity[x][y][z] = false;
        }
        if(sensor!=null){
            sensor.measure(((double)occupiedSpace()/(double)maxCapacity())*100);
        }
        return amount;

    }

    public boolean[][][] getCapacity() {
        return capacity;
    }

    private int[] getPositionFirstEmpty() {
        int[] pos = new int[3];
        for (int i = capacity[0][0].length - 1; i >= 0; i--) {
            if (capacity[capacity.length - 1][capacity[0].length - 1][i]) {
                pos[2] = i + 1;
                break;
            }
        }
        if (pos[2] == capacity[0][0].length) return pos;

        for (int i = capacity[0].length - 1; i >= 0; i--) {
            if (capacity[capacity.length - 1][i][pos[2]]) {
                pos[1] = i + 1;
                break;
            }
        }

        for (int i = capacity.length - 1; i >= 0; i--) {
            if (capacity[i][pos[1]][pos[2]]) {
                pos[0] = i + 1;
                break;
            }
        }
        return pos;
    }


}
