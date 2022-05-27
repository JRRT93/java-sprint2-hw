import java.util.ArrayList;
public class MonthlyReport {
    String monthName;
    ArrayList<MonthInputLineByLine> monthDataLineByLine;

    MonthlyReport(int numberMonth) {
        this.monthName = assignMonthName(numberMonth);
        monthDataLineByLine = new ArrayList<>();
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
        for (int i = 0; i < monthDataLineByLine.size(); i++) {
            if(monthDataLineByLine.get(i).isExpense == bool && maxAmount < monthDataLineByLine.get(i).amount) {
                maxAmount = monthDataLineByLine.get(i).amount;
                maxAmountItemName = monthDataLineByLine.get(i).itemName;
            }
        }
        System.out.println(maxAmountItemName + ", " + maxAmount);
    }

    int calculateMonthSummaryExpensesOrIncome(Boolean bool) {
        int sum = 0;
        for (int i = 0; i < monthDataLineByLine.size(); i++) {
            if(monthDataLineByLine.get(i).isExpense == bool) {
                sum += monthDataLineByLine.get(i).amount;
            }
        }
        return sum;
    }
}

