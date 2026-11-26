import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<String> ids = new ArrayList<>();
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Integer> marks = new ArrayList<>();

    static LinkedList<String> attendance = new LinkedList<>();

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    static int readMarks() {
        while (true) {
            int m = readInt("Enter Student Marks (0-100): ");
            if (m >= 0 && m <= 100) {
                return m;
            }
            System.out.println("Marks must be between 0 and 100.");
        }
    }

    static String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = sc.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    static void addStudent() {
        String id = readText("Enter Student ID: ");

        if (ids.contains(id)) {
            System.out.println("Student ID already exists.");
            return;
        }

        String name = readText("Enter Student Name: ");

        if (names.contains(name)) {
            System.out.println("Student name already exists.");
            return;
        }

        int m = readMarks();

        ids.add(id);
        names.add(name);
        marks.add(m);

        System.out.println("Student Added Successfully!");
    }

    static void displayStudents() {
        if (names.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        System.out.println("\n--------- STUDENT RECORDS ---------");
        System.out.println("ID\tName\tMarks");

        for (int i = 0; i < names.size(); i++) {
            System.out.println(ids.get(i) + "\t"
                    + names.get(i) + "\t" + marks.get(i));
        }

        System.out.println("Total Students: " + names.size());
    }

    static void searchStudent() {
        String name = readText("Enter Student Name: ");
        int i = names.indexOf(name);

        if (i == -1) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student Found!");
            System.out.println("ID: " + ids.get(i));
            System.out.println("Name: " + names.get(i));
            System.out.println("Marks: " + marks.get(i));
        }
    }

    static void updateStudent() {
        String name = readText("Enter Student Name: ");
        int i = names.indexOf(name);

        if (i == -1) {
            System.out.println("Student not found.");
            return;
        }

        int m = readMarks();
        marks.set(i, m);

        System.out.println("Student Marks Updated Successfully!");
        System.out.println(names.get(i) + " - " + marks.get(i));
    }

    static void removeStudent() {
        String name = readText("Enter Student Name: ");
        int i = names.indexOf(name);

        if (i == -1) {
            System.out.println("Student not found.");
            return;
        }

        ids.remove(i);
        names.remove(i);
        marks.remove(i);
        attendance.remove(name);

        System.out.println("Student Removed Successfully!");
        displayStudents();
    }

    static void addAttendance() {
        String name = readText("Enter Student Name: ");

        if (!names.contains(name)) {
            System.out.println("Student is not registered.");
            return;
        }

        if (attendance.contains(name)) {
            System.out.println("Student already marked present.");
            return;
        }

        int choice = readInt("1. Add First  2. Add Last: ");

        if (choice == 1) {
            attendance.addFirst(name);
        } else if (choice == 2) {
            attendance.addLast(name);
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        System.out.println("Attendance Added Successfully!");
    }

    static void displayAttendance() {
        if (attendance.isEmpty()) {
            System.out.println("No students present.");
        } else {
            System.out.println("Present Students: " + attendance);
        }

        System.out.println("Total Present: " + attendance.size());
    }

    static void removeAttendance() {
        String name = readText("Enter Student Name: ");

        if (attendance.remove(name)) {
            System.out.println("Attendance Removed Successfully!");
        } else {
            System.out.println("Student not in attendance.");
        }
    }

    static void statistics() {
        if (marks.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        int total = 0;
        int above75 = 0;

        for (int m : marks) {
            total += m;
            if (m > 75) {
                above75++;
            }
        }

        double average = (double) total / marks.size();

        System.out.println("Total Students: " + names.size());
        System.out.println("Highest Marks: " + Collections.max(marks));
        System.out.println("Lowest Marks: " + Collections.min(marks));
        System.out.println("Average Marks: " + average);
        System.out.println("Students Above 75: " + above75);
    }

    static void sortNames() {
        ArrayList<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);

        System.out.println("Student Names in Alphabetical Order:");
        for (String name : sorted) {
            System.out.println(name);
        }
    }

    static void reverseNames() {
        ArrayList<String> reversed = new ArrayList<>(names);
        Collections.reverse(reversed);

        System.out.println("Student Names in Reverse Order:");
        for (String name : reversed) {
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n====================================");
            System.out.println("       STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Remove Student");
            System.out.println("6. Add Student Attendance");
            System.out.println("7. Display Attendance");
            System.out.println("8. Remove Attendance");
            System.out.println("9. Display Student Statistics");
            System.out.println("10. Sort Student Names");
            System.out.println("11. Exit");
            System.out.println("12. Reverse Student Names");

            int choice = readInt("Enter Your Choice: ");

            switch (choice) {
                case 1: addStudent(); break;
                case 2: displayStudents(); break;
                case 3: searchStudent(); break;
                case 4: updateStudent(); break;
                case 5: removeStudent(); break;
                case 6: addAttendance(); break;
                case 7: displayAttendance(); break;
                case 8: removeAttendance(); break;
                case 9: statistics(); break;
                case 10: sortNames(); break;
                case 11:
                    System.out.println("Thank you!");
                    sc.close();
                    return;
                case 12: reverseNames(); break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}