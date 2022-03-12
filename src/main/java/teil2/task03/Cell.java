package teil2.task03;

public class Cell {
    protected Cell[] unitList;
    private boolean load;

    public Cell() {
        this.unitList = null;
        this.load = false;
    }

    public boolean isComposite() {
        if (unitList != null) {
            return true;
        } else {
            return false;
        }
    }

    public Cell[] getUnitList() {
        if (unitList != null) return unitList;
        else return null;
    }

    public int getCapacity() {
        if (unitList == null) {
            return 1;
        } else {
            int cap = 0;
            for (Cell c : unitList) {
                cap += c.getCapacity();
            }
            return cap;
        }
    }

    public int load(int amount) {
        if (load) {
            return amount;
        } else {
            load = true;
            return (--amount);
        }
    }

    public int unLoad(int amount) {
        if (load) {
            load = false;
            return (--amount);
        } else {
            return amount;
        }
    }

    public int getLoad() {
        if (load) {
            return 1;
        } else {
            return 0;
        }

    }
}

