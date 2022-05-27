import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FileReader fileReader = new FileReader();
        ArrayList<MonthlyReport> listOfMonthReports = new ArrayList<>();
        YearReport yearReport = new YearReport();

        while (true) {
            printMenu();
            if(scanner.hasNextInt()) {
                int command = scanner.nextInt();
                if (command == 1) {
                    readFileAndCreateMonthReport(scanner, fileReader, listOfMonthReports);
                } else if (command == 2) {
                    readFileAndCreateYearReport(fileReader, yearReport);
                } else if (command == 3) {
                    compareMonthToYearReports(listOfMonthReports, yearReport);
                } else if (command == 4) {
                    System.out.println("Введите номер месяца, за который хотите посмотреть информацию");
                    int numberMonth = checkChoosingOfMonth(scanner, listOfMonthReports);
                    if (numberMonth != 0) {
                        listOfMonthReports.get(numberMonth - 1).displayMonthStat();
                    }
                } else if (command == 5) {
                    if (yearReport.amountExpense.size() != 0) {
                        yearReport.displayYearStat();
                    } else {
                        System.out.println("Для вывода статистики необходимо сначала считать отчёты из директории!");
                    }
                }
            } else if (scanner.hasNextLine()){
                String command = scanner.nextLine();
                if (command.equals("END")) {
                    break;
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие. Для выбора пункта меню введите цифру от 1 до 5 или END, если хотите закрыть программу.");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("END - завершить работу");
    }

    public static void readFileAndCreateMonthReport(Scanner scanner, FileReader fileReader, ArrayList<MonthlyReport> listOfMonthReport) {
        System.out.println("Введите количество месячных отчётов в формате .csv, размещенных в директории resources/");
        int numberOfReports = checkNumberOfReportsToRead(scanner);
        for (int i = 0; i < numberOfReports; i++) {
            listOfMonthReport.add(new MonthlyReport(i + 1));
            String path = "resources/m.20210" + (i + 1) + ".csv";
            String content = fileReader.readFileContentsOrNull(path);
            if (content == null){
                System.exit(0);
            }
            String[] lines = content.split("\n");
            for (int j = 1; j < lines.length; j++) {
                String line = lines[j];
                String[] parts = line.split(",");
                try {
                    listOfMonthReport.get(i).monthDataLineByLine.add(new MonthInputLineByLine(parts[0], Boolean.parseBoolean(parts[1]),
                            Integer.parseInt(parts[2])*Integer.parseInt(parts[3])));
                }
                catch (Exception exception) {
                    System.out.println("Данные в отчёте не могут быть корректно считаны. Приведите данные в отчёте в соответсвие " +
                            "с утверждённой формой и перезапустите программу.");
                    System.exit(0);
                }
            }
            System.out.println("Отчёт за " + listOfMonthReport.get(i).assignMonthName(i+1) + " успешно считан.");
        }
    }

    public static void readFileAndCreateYearReport(FileReader fileReader, YearReport yearReport) {
        String path = "resources/y.2021.csv";
        String content = fileReader.readFileContentsOrNull(path);
        if (content == null){
            System.exit(0);
        }
        System.out.println("Отчёт успешно считан.");
        String[] lines = content.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            try {
                if (Boolean.parseBoolean(parts[2])) {
                    yearReport.amountExpense.add(new YearInputLineByLine(Integer.parseInt(parts[1])));
                } else {
                    yearReport.amountIncome.add(new YearInputLineByLine(Integer.parseInt(parts[1])));
                }
            }
            catch (Exception exception) {
                System.out.println("Данные в отчёте не могут быть корректно считаны. Приведите данные в отчёте в соответсвие " +
                        "с утверждённой формой и перезапустите программу.");
                System.exit(0);
            }
        }

    }

    public static void compareMonthToYearReports(ArrayList<MonthlyReport> listOfMonthReport, YearReport yearReport) {
        if(yearReport.amountExpense.size() == 0 || listOfMonthReport.size() == 0) {
            System.out.println("Для сверки отчётов необходимо сначала считать годовой и месячные отчёты из директории!");
        } else {
        for (int i = 0; i < listOfMonthReport.size(); i++) {
            String monthName = yearReport.assignMonthName(i);
            if (listOfMonthReport.get(i).calculateMonthSummaryExpensesOrIncome(false) == yearReport.amountIncome.get(i).amount &&
                    listOfMonthReport.get(i).calculateMonthSummaryExpensesOrIncome(true) == yearReport.amountExpense.get(i).amount) {
                System.out.println("Отчёт за год и отчёт за месяц " + monthName + " сверены. Ошибок нет");
            } else {
                if (listOfMonthReport.get(i).calculateMonthSummaryExpensesOrIncome(false) == yearReport.amountIncome.get(i).amount) {
                    System.out.println("Отчёт за год и отчёт за месяц " + monthName + " не бьются. Расхождение при сравнении доходов");
                }
                if (listOfMonthReport.get(i).calculateMonthSummaryExpensesOrIncome(true) == yearReport.amountExpense.get(i).amount) {
                    System.out.println("Отчёт за год и отчёт за месяц " + monthName + " не бьются. Расхождение при сравнении расходов");
                }
            }
        }
        }
    }


    public static int checkChoosingOfMonth (Scanner scanner, ArrayList<MonthlyReport> listOfMonthReport) {
        int command;
        while (true) {
            if (scanner.hasNextInt()) {
                command = scanner.nextInt();
                if(listOfMonthReport.size() == 0) {
                    System.out.println("Для вывода статистики необходимо сначала считать отчёты из директории!");
                }
                if (command> 0 && command <= listOfMonthReport.size()) {
                    return command;
                } else {
                    System.out.println("Для выбора месяца введите цифру от 1 до " + listOfMonthReport.size());
                    scanner.nextLine();
                }
            } else {
                System.out.println("Для выбора месяца введите цифру от 1 до " + listOfMonthReport.size());
                scanner.nextLine();
            }
        }
    }

    public static int checkNumberOfReportsToRead(Scanner scanner) {
        int numberOfReports;
        while (true) {
            if (scanner.hasNextInt()) {
                numberOfReports = scanner.nextInt();
                if (numberOfReports > 0) {
                    return numberOfReports;
                } else {
                    System.out.println("Количество отчётов должно быть целочисленным и больше 0");
                    scanner.nextLine();
                }
            } else {
                System.out.println("Количество отчётов должно быть целочисленным и больше 0");
                scanner.nextLine();
            }
        }
    }
}

