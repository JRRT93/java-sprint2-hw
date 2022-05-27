import java.util.ArrayList;

public class YearReport {
    ArrayList<YearInputLineByLine> amountExpense;
    ArrayList<YearInputLineByLine> amountIncome;
    int yearNumber = 2021;

    YearReport() {
        amountExpense = new ArrayList<>();
        amountIncome = new ArrayList<>();
    }
    void displayYearStat(){
        System.out.println(yearNumber);
        System.out.println("Среднемесячные доходы - " + getAverageIncomeOrExpensePerMonth(false));
        System.out.println("Среднемесячные расходы - " + getAverageIncomeOrExpensePerMonth(true));
        for (int i = 0; i < amountIncome.size(); i++) {
        String month = assignMonthName(i);
        System.out.println("Прибыль за " + month + " - " + (amountIncome.get(i).amount - amountExpense.get(i).amount));
        }
    }

    double getAverageIncomeOrExpensePerMonth(Boolean bool) {
        if (!bool) {
            double sum = 0;
            for (YearInputLineByLine yearInputLineByLine : amountIncome) {
                sum += yearInputLineByLine.amount;
            }
            return sum / amountIncome.size();
        }
        double sum = 0;
        for (YearInputLineByLine yearInputLineByLine : amountExpense) {
            sum += yearInputLineByLine.amount;
        }
        return sum / amountIncome.size();
    }

    String assignMonthName(int number) {
        String[] monthNames = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        return monthNames[number];
    }

}
