import java.util.ArrayList;
public class MonthDataFromInput {
    ArrayList<String> itemNames;
    ArrayList<Boolean> isExpense;
    ArrayList<Integer> quantity;
    ArrayList<Integer> sumOfOne;
    ArrayList<Integer> amount;


    MonthDataFromInput(){
        itemNames = new ArrayList<>();
        isExpense = new ArrayList<>();
        quantity = new ArrayList<>();
        sumOfOne = new ArrayList<>();
        amount = new ArrayList<>();
    }

    void calculateAmount() {
        for (int i = 0; i < sumOfOne.size(); i++) {
            amount.add(sumOfOne.get(i)*quantity.get(i));
        }
    }
}
