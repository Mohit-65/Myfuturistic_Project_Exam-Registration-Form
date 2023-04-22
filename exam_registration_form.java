import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class exam_registration_form {

    private static final String FILE_NAME = "exam_registration.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Please select an option:");
            System.out.println("1. Register a new student");
            System.out.println("2. View all registered students");
            System.out.println("3. Search for a student by name");
            System.out.println("4. Exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void registerStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the student's name:");
        String name = input.nextLine();
        System.out.println("Please enter the student's ID:");
        String id = input.nextLine();
        System.out.println("Please enter the student's email:");
        String email = input.nextLine();
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true));
            writer.write(name + "," + id + "," + email);
            writer.newLine();
            writer.close();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("An error occurred while registering the student.");
        }
    }

    private static void viewAllStudents() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                System.out.println("Name: " + fields[0]);
                System.out.println("ID: " + fields[1]);
                System.out.println("Email: " + fields[2]);
                System.out.println();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }

    private static void searchStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the name of the student you are searching for:");
        String name = input.nextLine();
        ArrayList<String[]> results = new ArrayList<String[]>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equalsIgnoreCase(name)) {
                    results.add(fields);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
        if (results.size() == 0) {
            System.out.println("No matching results found.");
        } else {
            System.out.println("Matching results:");
            for (String[] fields : results) {
                System.out.println("Name: " + fields[0]);
                System.out.println("ID: " + fields[1]);
                System.out.println("Email: " + fields[2]);
                System.out.println();
            }
        }
    }

}