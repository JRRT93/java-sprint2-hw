import java.util.ArrayList;

public class YearReport {
    ArrayList<Integer> amountExpense;
    ArrayList<Integer> amountIncome;

    YearReport(ArrayList<Integer> amountExpense, ArrayList<Integer> amountIncome) {
        this.amountExpense = amountExpense;
        this.amountIncome = amountIncome;
    }
    void displayYearStat(){
        System.out.println(2021);
        for (int i = 0; i < amountIncome.size(); i++) {
        String month = assignMonthName(i);
        System.out.println("За " + month + " следующие показатели:");
        System.out.println("Прибыль - " + (amountIncome.get(i) - amountExpense.get(i)));
        System.out.println("Среднемесячные доходы - " + getAverageIncomeOrExpensePerMonth(false));
        System.out.println("Среднемесячные расходы - " + getAverageIncomeOrExpensePerMonth(true));
        }
    }

    double getAverageIncomeOrExpensePerMonth(Boolean bool) {
        if (bool == false) {
            double sum = 0;
            for (Integer integer : amountIncome) {
                sum += integer;
            }
            return sum / amountIncome.size();
        }
        double sum = 0;
        for (Integer integer : amountIncome) {
            sum += integer;
        }
        return sum / amountIncome.size();
    }

    String assignMonthName(int number) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return monthNames[number];
    }

}
