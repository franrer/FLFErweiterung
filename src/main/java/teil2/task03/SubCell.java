package teil2.task03;

public class SubCell extends Cell {

    public SubCell(int amountCells) {
        this.unitList = new Cell[amountCells];
        for (int i = 0; i < amountCells; i++) {
            unitList[i] = new Cell();
        }
    }

    protected SubCell() {
    }

    @Override
    public final boolean isComposite() {
        return true;
    }

    @Override
    public int load(int amount) {
        for (Cell c : unitList) {
            if (amount <= 0) {
                break;
            }
            amount = c.load(amount);
        }
        return amount;
    }

    @Override
    public int unLoad(int amount) {
        for (Cell c : unitList) {
            if (amount <= 0) {
                break;
            }
            amount = c.unLoad(amount);
        }
        return amount;
    }

    @Override
    public int getLoad() {
        int amount = 0;
        for (Cell c : unitList) {
            amount += c.getLoad();
        }
        return amount;
    }
}
