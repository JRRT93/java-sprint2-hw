import java.util.ArrayList;

public class YearDataFromInput {
    ArrayList<Integer> month;
    ArrayList<Boolean> isExpense;
    ArrayList<Integer> amount;
    ArrayList<Integer> amountExpense;
    ArrayList<Integer> amountIncome;

    YearDataFromInput() {
        month = new ArrayList<>();
        isExpense = new ArrayList<>();
        amount = new ArrayList<>();
        amountExpense = new ArrayList<>();
        amountIncome = new ArrayList<>();
    }

    void divideAmounts(){
        for (int i = 0; i < month.size(); i++) {
            if(isExpense.get(i)) {
                amountExpense.add(amount.get(i));
            } else {
                amountIncome.add(amount.get(i));
            }
        }
    }
}
