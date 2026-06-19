import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentGrading {
    private Scanner  scanner; 
    private String command;
    private static List<DataRow> studentTable = new ArrayList<>(); 

    StudentGrading(){
        this.scanner = new Scanner(System.in);   
    }
    public static void main(String[] args){
        StudentGrading program = new StudentGrading();

        System.out.println("Starting grading system");
        program.runMenu();   
        return;     
    }

    public void runMenu() {
        System.out.println("commands:");
        while (true){
            System.out.println("\n   q - close the app");
            System.out.println("   n - add new student");
            System.out.println("   p - print students info");
            System.out.println("   g - get student grade");
            System.out.println("   s - get students ranks");
            command = scanner.nextLine(); 
            switch (command) {
                case "n":
                    this.scanNewStudent();
                    break;
                case "p":
                    this.printStudentsInfo();
                    break;
                case "g":
                    this.getStudentGrade();
                    break;
                case "s":
                    this.getStudentsRank();
                    break;
                case "q":
                    return;
                default:
                    System.out.println("wrong command try again one of these commands");
                    break;
            }
        }
    }
    public int scanNewStudent(){
        System.out.print("Enter Student name: ");
        String name = scanner.nextLine(); 
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Invalid input: Name cannot be empty.");
            return -1;
        }

        System.out.print("Enter Student number: ");
        int id;
        try {
            id = scanner.nextInt(); 
            if (id <= 0) {
                System.out.println("Invalid input: Student number must be a positive integer.");
                scanner.nextLine(); // clear buffer
                return -1;
            }
            for (DataRow row : studentTable) {
                if (row.id == id) {
                    System.out.println("Invalid input: Student number already exists.");
                    scanner.nextLine(); // clear buffer
                    return -1;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: Student number must be an integer.");
            scanner.nextLine(); // clear invalid input
            return -1;
        }

        System.out.print("Enter C programming grade (%): ");
        int c;
        try {
            c = scanner.nextInt(); 
            if (c < 0 || c > 100) {
                System.out.println("Invalid input: Grade must be between 0 and 100.");
                scanner.nextLine(); // clear buffer
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: Grade must be a valid number.");
            scanner.nextLine(); // clear invalid input
            return -1;
        }
        
        System.out.print("Enter QT course grade (%): ");
        int qt;
        try {
            qt = scanner.nextInt(); 
            if (qt < 0 || qt > 100) {
                System.out.println("Invalid input: Grade must be between 0 and 100.");
                scanner.nextLine(); // clear buffer
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: Grade must be a valid number.");
            scanner.nextLine(); // clear invalid input
            return -1;
        }

        System.out.print("Enter Android course grade (%): ");
        int android;
        try {
            android = scanner.nextInt(); 
            if (android < 0 || android > 100) {
                System.out.println("Invalid input: Grade must be between 0 and 100.");
                scanner.nextLine(); // clear buffer
                return -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: Grade must be a valid number.");
            scanner.nextLine(); // clear invalid input
            return -1;
        }

        // to dump the last \n form the buffer
        scanner.nextLine(); 

        studentTable.add(new DataRow(name, id, c, qt, android));
        System.out.println("Student added successfully.");
        return 0;
    }
    public void printStudentsInfo(){
        if (studentTable.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }
        
        // ai enhanced
        System.out.println(String.format("%-15s %-15s %-10s %-10s %-10s", "Name", "Number", "C", "QT", "Android"));
        for (DataRow row : studentTable) {
            System.out.println(String.format("%-15s %-15d %-10.1f %-10.1f %-10.1f", 
                row.name, row.id, row.val1, row.val2, row.val3));
        }
    }
    public void getStudentGrade(){
        if (studentTable.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        System.out.print("Enter Student number: ");
        int id;
        try {
            id = scanner.nextInt();
            if (id <= 0) {
                System.out.println("Invalid input: Student number must be a positive integer.");
                scanner.nextLine(); // clear buffer
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input: Student number must be an integer.");
            scanner.nextLine(); // clear invalid input
            return;
        }

        // to dump the last \n form the buffer
        scanner.nextLine();

        boolean found = false;
        for (DataRow dataRow : studentTable) {
            if (dataRow.id == id) {
                System.out.println(String.format("%-15s %-15s %-10s", "Name", "Number", "Total"));
                System.out.println(String.format("%-15s %-15d %-10.1f", 
                dataRow.name, dataRow.id, (dataRow.val1 + dataRow.val2 + dataRow.val3)/3));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }
    public void getStudentsRank(){
        if (studentTable.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        List<DataRow> sortedList = new ArrayList<>(studentTable);
        // ai enhanced
        Collections.sort(sortedList, new Comparator<DataRow>() {
            @Override
            public int compare(DataRow a, DataRow b) {
                return Float.compare(b.totalAverage, a.totalAverage); // descending
            }
        });

        System.out.println(String.format("%-5s %-15s %-15s %-10s", "Rank", "Name", "Number", "Average"));
        
        int rank = 1;
        float previousAvg = -1.0f;

        for (int i = 0; i < sortedList.size(); i++) {
            DataRow row = sortedList.get(i);

            // Handle ties
            if (i > 0 && Float.compare(row.totalAverage, previousAvg) != 0) {
                rank = i + 1;
            }
            previousAvg = row.totalAverage;

            System.out.println(String.format("%-5d %-15s %-15d %-10.1f", rank, row.name, row.id, row.totalAverage));
        }
    }
}


class DataRow {
    String name;
    int id;
    float val1;
    float val2;
    float val3;
    float totalAverage;

    public DataRow(String name, int id, float val1, float val2, float val3) {
        this.name = name;
        this.id = id;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.totalAverage = (val1 + val2 + val3) / 3.0f;
    }
}