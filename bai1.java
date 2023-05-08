package newpackage;

/**
 *
 * author ADMIN
 */
import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private int sotk;
    private String tentk;
    private double sotien;
    private String trangthai;
    private static final double LAI_SUAT = 0.035;

    public Account() {
        this.sotk = 999999;
        this.tentk = "chưa xác định";
        this.sotien = 50;
    }
    

    public Account(int sotk, String tentk) {
        this.sotk = sotk;
        this.tentk = tentk;
        this.sotien = 50;
    }

    public static double getLAI_SUAT() {
        return LAI_SUAT;
    }

    public int getSotk() {
        return sotk;
    }

    public void setSotk(int sotk) {
        if (sotk > 0 && sotk != 999999) {
            this.sotk = sotk;
        } else {
            this.sotk = 999999;
            this.trangthai = "Số tài khoản không hợp lệ. Số tài khoản đã được gán giá trị mặc định (999999).";
        }
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        if (!tentk.isEmpty()) {
            this.tentk = tentk;
        } else {
            this.tentk = "chưa xác định";
            this.trangthai = "Tên tài khoản không hợp lệ. Tên tài khoản đã được gán giá trị mặc định (chưa xác định).";
        }
    }

    public double getSotien() {
        return sotien;
    }

    public void setSotien(double sotien) {
        if (sotien >= 50) {
            this.sotien = sotien;
        } else {
            this.sotien = 50;
            this.trangthai = "Số tiền không hợp lệ. Số tiền đã được gán giá trị mặc định (50).";
        }
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public void napTien(double amount) {
        if (amount > 0) {
            sotien += amount;
            System.out.println("Nạp tiền thành công.");
        } else {
            System.out.println("Số tiền nạp không hợp lệ.");
        }
    }

    public void rutTien(double amount) {
        if (amount > 0 && amount <= sotien) {
            sotien -= amount;
            System.out.println("Rút tiền thành công.");
        } else {
            System.out.println("Số tiền rút không hợp lệ hoặc lớn hơn số tiền trong tài khoản.");
        }
    }

    public void chuyenKhoan(Account receiver, double amount) {
        if (amount > 0 && amount <= sotien) {
            sotien -= amount;
            receiver.napTien(amount);
            System.out.println("Chuyển khoản thành công.");
        } else {
            System.out.println("Số tiền chuyển khoản không hợp lệ hoặc lớn hơn số tiền trong tài khoản.");
        }
    }

    public long getAccountNumber() {
        return sotk;
    }
//chuyển tiền 
    public void transfer(Account receiverAccount, double amount) {
        if (amount > 0 && amount <= sotien) {
            sotien -= amount;
            receiverAccount.deposit(amount);
            System.out.println("Chuyển khoản thành công.");
        } else {
            System.out.println("Số tiền chuyển khoản không hợp lệ hoặc lớn hơn số tiền trong tài khoản.");
        }
    }
//rút tiền 
    public void withdraw(double amount) {
        if (amount > 0 && amount <= sotien) {
            sotien -= amount;
            System.out.println("Rút tiền thành công.");
        } else {
            System.out.println("Số tiền rút không hợp lệ hoặc lớn hơn số tiền trong tài khoản.");
        }
    }
//nạp tiền 
    public void deposit(double amount) {
        if (amount > 0) {
            sotien += amount;
            System.out.println("Nạp tiền thành công.");
        } else {
            System.out.println("Số tiền nạp không hợp lệ.");
        }
    }
}

package newpackage;

/**
 *
 * @author ADMIN
 */


public class AccountList {
    private Account[] accList;
    private int count;

    public AccountList() {
        this.accList = new Account[10]; // Khởi tạo một mảng tài khoản với kích thước ban đầu là 10
        this.count = 0;
    }
    

    public AccountList(int size) {
        if (size > 0) {
            this.accList = new Account[size]; // Khởi tạo một mảng tài khoản với kích thước được truyền vào
        } else {
            this.accList = new Account[10]; // Khởi tạo một mảng tài khoản với kích thước mặc định là 10
        }
        this.count = 0;
    }
    

    // Thêm một tài khoản vào danh sách
    public void addAccount(Account account) {
        if (count < accList.length) {
            accList[count] = account;
            count++;
            System.out.println("Account added successfully.");
        } else {
            System.out.println("Account list is full. Cannot add more accounts.");
        }
    }

    // Tìm tài khoản theo số tài khoản
    public Account findAccount(long accountNumber) {
        for (int i = 0; i < count; i++) {
            if (accList[i].getAccountNumber() == accountNumber) {
                return accList[i];
            }
        }
        return null; // Trả về null nếu không tìm thấy tài khoản
    }

    // Xóa tài khoản theo số tài khoản
    public void deleteAccount(long accountNumber) {
        for (int i = 0; i < count; i++) {
            if (accList[i].getAccountNumber() == accountNumber) {
                // Dời các phần tử phía sau lên để ghi đè tài khoản cần xóa
                for (int j = i; j < count - 1; j++) {
                    accList[j] = accList[j + 1];
                }
                count--;
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    // Đếm số lượng tài khoản trong danh sách
    public int getAccountCount() {
        return count;
    }

    // In thông tin toàn bộ tài khoản có trong danh sách
    public void printAllAccounts() {
        if (count > 0) {
            System.out.println("Account List:");
            for (int i = 0; i < count; i++) {
                System.out.println("Account " + (i + 1) + ":");
                System.out.println(accList[i].toString());
                System.out.println();
            }
        } else {
            System.out.println("No accounts found in the list.");
        }
    }
}
package newpackage;

/**
 *
 * @author ADMIN
 */


import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) {
        AccountList list = new AccountList(); // Khởi tạo đối tượng AccountList
        
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        
        while (choice != 6) {
            System.out.println("------- MENU -------");
            System.out.println("1. Thêm tài khoản");
            System.out.println("2. Số tài khoản hiện có");
            System.out.println("3. In thông tin tất cả tài khoản");
            System.out.println("4. Nạp tiền vào tài khoản");
            System.out.println("5. Rút tiền");
            System.out.println("6. Chuyển tiền");
            System.out.println("7. Kết thúc");
            System.out.print("Nhập lựa chọn của bạn: ");
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addAccount(list);
                    break;
                case 2:
                    getAccountCount(list);
                    break;
                case 3:
                    printAllAccounts(list);
                    break;
                case 4:
                    depositMoney(list);
                    break;
                case 5:
                    withdrawMoney(list);
                    break;
                case 6:
                    transferMoney(list);
                    break;
                case 7:
                    System.out.println("Kết thúc chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
            
            System.out.println();
        }
        
        scanner.close();
    }
    
    // Hàm thêm tài khoản
    private static void addAccount(AccountList list) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhập số tài khoản: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Đọc dòng mới
        
        System.out.print("Nhập tên tài khoản: ");
        String accountName = scanner.nextLine();
        
        Account account = new Account(accountNumber, accountName);
        list.addAccount(account);
        
        System.out.println();
    }
    
    // Hàm lấy số lượng tài khoản hiện có
    private static void getAccountCount(AccountList list) {
        int count = list.getAccountCount();
        System.out.println("Số tài khoản hiện có: " + count);
        System.out.println();
    }
    
    // Hàm in thông tin tất cả các tài khoản
    private static void printAllAccounts(AccountList list) {
        list.printAllAccounts();
        System.out.println();
    }
    

    
    // Hàm rút tiền từ tài khoản
    private static void withdrawMoney(AccountList list) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhập số tài khoản: ");
        int accountNumber = scanner.nextInt();
        
        Account account = list.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Nhập số tiền cần rút: ");
            double amount = scanner.nextDouble();
            
            account.rutTien(amount);
        } else {
            System.out.println("Không tìm thấy tài khoản với số tài khoản đã nhập");
        }
    }
    
    // Hàm chuyển tiền từ một tài khoản đến tài khoản khác
    private static void transferMoney(AccountList list) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhập số tài khoản nguồn: ");
        int sourceAccountNumber = scanner.nextInt();
        
        Account sourceAccount = list.findAccount(sourceAccountNumber);
        if (sourceAccount != null) {
            System.out.print("Nhập số tài khoản đích: ");
            int destinationAccountNumber = scanner.nextInt();
            
            Account destinationAccount = list.findAccount(destinationAccountNumber);
            if (destinationAccount != null) {
                System.out.print("Nhập số tiền cần chuyển: ");
                double amount = scanner.nextDouble();
                
                sourceAccount.chuyenKhoan(destinationAccount, amount);
            } else {
                System.out.println("Không tìm thấy tài khoản đích với số tài khoản đã nhập");
            }
        } else {
            System.out.println("Không tìm thấy tài khoản nguồn với số tài khoản đã nhập");
        }
    }
//     Hàm nạp tiền vào tài khoản
    private static void depositMoney(AccountList list) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhập số tài khoản: ");
        int accountNumber = scanner.nextInt();
        
        Account account = list.findAccount(accountNumber);
        if (account != null) {
            System.out.print("Nhập số tiền cần nạp: ");
            double amount = scanner.nextDouble();
            
            account.napTien(amount);
        } else {
            System.out.println("Không tìm thấy tài khoản với số tài khoản đã nhập");
        }
    }
   
}
