package teil2.task03;

public class MainCell extends SubCell {
    public MainCell(int amountSubCells, int amountCells) {
        this.unitList = new SubCell[amountSubCells];
        for (int i = 0; i < amountSubCells; i++) {
            unitList[i] = new SubCell(amountCells);
        }
    }
}
