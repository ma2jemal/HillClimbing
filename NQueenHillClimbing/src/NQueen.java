// Course: CS3642
// Student name: Muna Jemal
// Student ID: 000972037
// Assignment #: #2
// Due Date: 11/08/2020

public class NQueen {
    private int row;
    private int column;

    public NQueen(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public boolean inAttack(NQueen q){
        if(row == q.getRow() || column == q.getColumn()) //Checking if queen is attacking q in the same row or column
            return true;
        else if(Math.abs(column - q.getColumn()) == Math.abs(row - q.getRow())) //checking if queen is attacking q in
            return true;                                                        // diagonally
        return false;
    }
    public void move(){
        row++;
    }

}
