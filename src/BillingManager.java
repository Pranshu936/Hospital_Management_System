import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class BillingManager {
    private BillingDAO billingDAO;
    private Scanner scanner;
    private static int billIdCounter = 9001; // Initialize billing ID counter

    public BillingManager(BillingDAO billingDAO, Scanner scanner) {
        this.billingDAO = billingDAO;
        this.scanner = scanner;
        // Load or initialize billIdCounter from a persistent store if needed
    }

    public void manageBilling() {
        System.out.println("\nManage Billing");
        System.out.println("1. Add Billing");
        System.out.println("2. View Billing");
        System.out.println("3. Update Billing");
        System.out.println("4. Delete Billing");
        System.out.println("5. View All Billings");
        System.out.print("Choose an option: ");
        
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid choice. Please enter a number between 1 and 5: ");
            }
        }

        switch (choice) {
            case 1:
                Billing newBilling = new Billing();
                newBilling.setBillId(billIdCounter++);

                // Other billing details
                System.out.print("Enter patient ID: ");
                newBilling.setPatientId(Integer.parseInt(scanner.nextLine()));

                double totalAmount;
                while (true) {
                    try {
                        System.out.print("Enter total amount: ");
                        totalAmount = Double.parseDouble(scanner.nextLine());
                        if (totalAmount < 0) {
                            throw new NumberFormatException();
                        }
                        newBilling.setTotalAmount(totalAmount);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid total amount. Please enter a non-negative number: ");
                    }
                }

                double paidAmount;
                while (true) {
                    try {
                        System.out.print("Enter paid amount: ");
                        paidAmount = Double.parseDouble(scanner.nextLine());
                        if (paidAmount < 0) {
                            throw new NumberFormatException();
                        }
                        newBilling.setPaidAmount(paidAmount);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid paid amount. Please enter a non-negative number: ");
                    }
                }

                LocalDate paymentDate;
                while (true) {
                    try {
                        System.out.print("Enter payment date (yyyy-mm-dd): ");
                        paymentDate = LocalDate.parse(scanner.nextLine());
                        newBilling.setPaymentDate(paymentDate);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                    }
                }

                billingDAO.addBilling(newBilling);
                System.out.println("Billing added successfully.");
                break;

            case 2:
                System.out.print("Enter bill ID: ");
                int billId = Integer.parseInt(scanner.nextLine());
                Billing billing = billingDAO.getBillingById(billId);
                if (billing != null) {
                    System.out.println("Total Amount: " + billing.getTotalAmount() +
                                       ", Paid Amount: " + billing.getPaidAmount() +
                                       ", Payment Date: " + billing.getPaymentDate());
                } else {
                    System.out.println("Billing record not found.");
                }
                break;

            case 3:
                System.out.print("Enter bill ID to update: ");
                int updateBillId = Integer.parseInt(scanner.nextLine());
                Billing updateBilling = billingDAO.getBillingById(updateBillId);
                if (updateBilling != null) {
                    // Update billing details
                    double newTotalAmount;
                    while (true) {
                        try {
                            System.out.print("Enter new total amount: ");
                            newTotalAmount = Double.parseDouble(scanner.nextLine());
                            if (newTotalAmount < 0) {
                                throw new NumberFormatException();
                            }
                            updateBilling.setTotalAmount(newTotalAmount);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid total amount. Please enter a non-negative number: ");
                        }
                    }

                    double newPaidAmount;
                    while (true) {
                        try {
                            System.out.print("Enter new paid amount: ");
                            newPaidAmount = Double.parseDouble(scanner.nextLine());
                            if (newPaidAmount < 0) {
                                throw new NumberFormatException();
                            }
                            updateBilling.setPaidAmount(newPaidAmount);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Invalid paid amount. Please enter a non-negative number: ");
                        }
                    }

                    LocalDate newPaymentDate;
                    while (true) {
                        try {
                            System.out.print("Enter new payment date (yyyy-mm-dd): ");
                            newPaymentDate = LocalDate.parse(scanner.nextLine());
                            updateBilling.setPaymentDate(newPaymentDate);
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.print("Invalid date format. Please use yyyy-mm-dd: ");
                        }
                    }

                    billingDAO.updateBilling(updateBilling);
                    System.out.println("Billing updated successfully.");
                } else {
                    System.out.println("Billing record not found.");
                }
                break;

            case 4:
                System.out.print("Enter bill ID to delete: ");
                int deleteBillId = Integer.parseInt(scanner.nextLine());
                billingDAO.deleteBilling(deleteBillId);
                System.out.println("Billing deleted successfully.");
                break;

            case 5:
                List<Billing> billings = billingDAO.getAllBillings();
                if (billings.isEmpty()) {
                    System.out.println("No billing records found.");
                } else {
                    for (Billing b : billings) {
                        System.out.println("Bill ID: " + b.getBillId() +
                                           ", Total Amount: " + b.getTotalAmount() +
                                           ", Paid Amount: " + b.getPaidAmount() +
                                           ", Payment Date: " + b.getPaymentDate());
                    }
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
