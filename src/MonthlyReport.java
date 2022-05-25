import java.util.ArrayList;
public class MonthlyReport {
    ArrayList<String> itemName;
    ArrayList<Boolean> isExpense;
    ArrayList<Integer> amount;
    String monthName;

    MonthlyReport(int numberMonth, ArrayList<String> itemName, ArrayList<Boolean> isExpense,
                  ArrayList<Integer> amount) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.amount = amount;
        this.monthName = assignMonthName(numberMonth);
    }

    String assignMonthName(int number) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return monthNames[number-1];
    }
    void displayMonthStat(){
        System.out.println(monthName);
        System.out.println("Наибольшая трата: ");
        getMonthMaxExpenseOrMaxIncome(true);
        System.out.println("Наибольший доход: ");
        getMonthMaxExpenseOrMaxIncome(false);

    }

    void getMonthMaxExpenseOrMaxIncome(Boolean bool) {
        int maxAmount = 0;
        String maxAmountItemName = " ";
        for (int i = 0; i < isExpense.size(); i++) {
            if(isExpense.get(i) == bool && maxAmount < amount.get(i)) {
                maxAmount = amount.get(i);
                maxAmountItemName = itemName.get(i);
            }
        }
        System.out.println(maxAmountItemName + ", " + maxAmount);
    }

    int calculateMonthSummaryExpensesOrIncome(Boolean bool) {
        int sum = 0;
        for (int i = 0; i < isExpense.size(); i++) {
            if(isExpense.get(i) == bool) {
                sum += amount.get(i);
            }
        }
        return sum;
    }
}

