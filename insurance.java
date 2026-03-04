package hackathon;

import java.io.*;
import java.util.*;

class InsuranceHolder {

    int id;
    String name;
    int age;
    double amount;
    int duration;
    int startYear;
    int interestRate;

    // Constructor
    InsuranceHolder(int id, String name, int age, double amount,
                    int duration, int startYear, int interestRate) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.amount = amount;
        this.duration = duration;
        this.startYear = startYear;
        this.interestRate = interestRate;
    }

    double premium() {
        return amount / duration;
    }

    int maturityYear() {
        return startYear + duration;
    }

    void display() {
        System.out.println("--------------------------------");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Amount: " + amount);
        System.out.println("Duration: " + duration);
        System.out.println("Start Year: " + startYear);
        System.out.println("Interest Rate: " + interestRate);
        System.out.println("--------------------------------");
    }
}

public class InsuranceSystem {

    static String filePath = "c:\\sample\\java.txt";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        InsuranceHolder[] holders = new InsuranceHolder[10];
        int count = 0;

        // ===== FILE READING =====
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");

                int id = Integer.parseInt(p[0]);
                String name = p[1];
                int age = Integer.parseInt(p[2]);
                double amount = Double.parseDouble(p[3]);
                int duration = Integer.parseInt(p[4]);
                int year = Integer.parseInt(p[5]);
                int ir = Integer.parseInt(p[6]);

                holders[count] = new InsuranceHolder(id, name, age,
                        amount, duration, year, ir);

                count++;
            }

            br.close();
            fr.close();

        } catch (Exception e) {
            System.out.println("File not found. Starting fresh.");
        }

        // ===== MENU WITH LINES =====
        System.out.println("======================================");
        System.out.println("        INSURANCE MANAGEMENT");
        System.out.println("======================================");
        System.out.println("1. Add Insurance Holder");
        System.out.println("2. Display All");
        System.out.println("3. Calculate Premium");
        System.out.println("4. Check Maturity");
        System.out.println("======================================");
        System.out.print("Enter your choice: ");

        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                try {
                    System.out.println("\nEnter ID:");
                    int id = sc.nextInt();

                    System.out.println("Enter Name:");
                    String name = sc.next();

                    System.out.println("Enter Age:");
                    int age = sc.nextInt();

                    System.out.println("Enter Amount:");
                    double amount = sc.nextDouble();

                    System.out.println("Enter Duration:");
                    int duration = sc.nextInt();

                    System.out.println("Enter Start Year:");
                    int year = sc.nextInt();

                    System.out.println("Enter Interest Rate:");
                    int ir = sc.nextInt();

                    holders[count] = new InsuranceHolder(id, name, age,
                            amount, duration, year, ir);

                    FileWriter fw = new FileWriter(filePath, true);
                    fw.write(id + "," + name + "," + age + "," +
                            amount + "," + duration + "," +
                            year + "," + ir + "\n");
                    fw.close();

                    count++;

                    System.out.println("\nInsurance Holder Added Successfully!");

                } catch (Exception e) {
                    System.out.println("Invalid Input!");
                }
                break;

            case 2:
                for (int i = 0; i < count; i++) {
                    holders[i].display();
                }
                break;

            case 3:
                System.out.println("\nEnter ID:");
                int pid = sc.nextInt();

                for (int i = 0; i < count; i++) {
                    if (holders[i].id == pid) {
                        System.out.println("Premium: " +
                                holders[i].premium());
                    }
                }
                break;

            case 4:
                System.out.println("\nEnter ID:");
                int mid = sc.nextInt();

                for (int i = 0; i < count; i++) {
                    if (holders[i].id == mid) {
                        System.out.println("Maturity Year: " +
                                holders[i].maturityYear());
                    }
                }
                break;

            default:
                System.out.println("Invalid Choice");
        }

        sc.close();
    }
}