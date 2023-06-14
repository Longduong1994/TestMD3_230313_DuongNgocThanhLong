package ra.bussiness;

import java.util.Scanner;

public class Book {
    private static int nextBookId = 1;
    private int bookId;
    private String bookName;
    private String author;
    private String description;
    private double importPrice;
    private double exportPrice;
    private float interest;
    private boolean bookStatus = true;

    public Book() {
        bookId = nextBookId;
        nextBookId++;
    }

    public Book(String bookName, String author, String description, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this();
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }

    @Override
    public String toString() {
        return "Book ||" +
                "bookId :" + bookId +
                "|| bookName:'" + bookName + '\'' +
                "|| author :'" + author + '\'' +
                "|| description :'" + description + '\'' +
                "|| importPrice :" + importPrice +
                "|| exportPrice :" + exportPrice +
                "|| interest :" + interest +
                "|| bookStatus :" + bookStatus +
                "";
    }

    public static Book inputData(Scanner scanner) {
        System.out.print("Nhập tên sách: ");
        String bookName = scanner.nextLine();

        System.out.print("Nhập tác giả: ");
        String author = scanner.nextLine();

        System.out.print("Nhập mô tả: ");
        String description = scanner.nextLine();

        System.out.print("Nhập giá nhập: ");
        double importPrice = scanner.nextDouble();

        System.out.print("Nhập giá bán: ");
        double exportPrice = scanner.nextDouble();

        float interest = (float) (exportPrice - importPrice); // Tính toán lợi nhuận

        scanner.nextLine(); // Đọc ký tự newline sau khi nhập số

        System.out.print("Nhập trạng thái sách (true/false): ");
        boolean bookStatus = scanner.nextBoolean();
        scanner.nextLine();

        return new Book(bookName, author, description, importPrice, exportPrice, interest, bookStatus);
    }


    public static void displayData(Book[] books, int bookCount) {
        if (bookCount == 0) {
            System.out.println("Không có sách trong danh sách.");
            return;
        }

        System.out.println("Danh sách sách đang lưu trữ:");

        for (int i = 0; i < bookCount; i++) {
            System.out.println(books[i].toString());
        }
    }



    public void setSalePrice(double salePrice) {
    }
}
