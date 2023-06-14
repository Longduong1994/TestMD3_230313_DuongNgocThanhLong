package ra.run;

import config.Config;
import ra.bussiness.Book;

import java.util.Scanner;

public class BookManagement {

    private static final int MAX_BOOKS = 100;
    private static Book[] books = new Book[MAX_BOOKS];
    private static int bookCount = 0;
    private static int nextBookId = 1;

    public static void main(String[] args) {
        int choice;

        Scanner scanner = Config.getScanner();

        while (true) {
            System.out.println("==============================MENU===========================");
            System.out.println("1. Thêm mới sách.");
            System.out.println("2. Hiển thị tất cả sách đang lưu trữ.");
            System.out.println("3. Sắp xếp sách theo lợi nhuận tăng.");
            System.out.println("4. Xóa sách trong danh sách.");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Thay đổi thông tin sách.");
            System.out.println("7. Thoát khỏi chương trình");
            System.out.println("==============================================================");

            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số sách cần nhập thông tin: ");
                    int numBooks = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < numBooks; i++) {
                        System.out.println("Nhập thông tin cho sách thứ " + (i + 1) + ":");
                        Book book = Book.inputData(scanner);
                        books[bookCount] = book;
                        bookCount++;
                    }

                    System.out.println("Đã thêm sách thành công.");
                    break;
                case 2:
                    Book.displayData(books, bookCount);
                    break;
                case 3:
                    sortBooks();
                    Book.displayData(books, bookCount);
                    break;
                case 4:
                    System.out.print("Nhập mã sách cần xóa: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();
                    deleteBook(bookId);
                    break;
                case 5:
                    System.out.print("Nhập chuỗi tìm kiếm: ");
                    String keyword = scanner.nextLine();
                    searchBooks(keyword);
                    break;
                case 6:
                    System.out.print("Nhập mã sách cần cập nhật: ");
                    int updateBookId = scanner.nextInt();
                    scanner.nextLine();
                    updateBook(updateBookId, scanner);
                    break;

                case 7:
                    System.out.println("Chương trình kết thúc.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void sortBooks() {
        if (bookCount == 0) {
            System.out.println("Không có sách trong danh sách.");
            return;
        }

        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getInterest() > books[j + 1].getInterest()) {
                    // Swap positions
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }

        System.out.println("Đã sắp xếp sách theo lợi nhuận tăng dần.");
    }

    private static void searchBooks(String keyword) {
        if (bookCount == 0) {
            System.out.println("Không có sách trong danh sách.");
            return;
        }

        System.out.println("Kết quả tìm kiếm cho từ khóa \"" + keyword + "\":");

        boolean found = false; // Biến đánh dấu sách được tìm thấy

        for (int i = 0; i < bookCount; i++) {
            Book book = books[i];
            if (book.getBookName().contains(keyword) || book.getDescription().contains(keyword)) {
                System.out.println(book.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy sách phù hợp với từ khóa \"" + keyword + "\".");
        }
    }


    private static void deleteBook(int bookId) {
        int bookIndex = -1; // Stores the index of the book to be deleted

        // Find the book in the list based on bookId
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId() == bookId) {
                bookIndex = i;
                break;
            }
        }


        if (bookIndex != -1) {
            for (int i = bookIndex; i < bookCount - 1; i++) {
                books[i] = books[i + 1];
            }

            bookCount--;

            System.out.println("Đã xóa sách có mã " + bookId);
        } else {
            System.out.println("Không tìm thấy sách có mã " + bookId);
        }
    }

    private static void updateBook(int bookId, Scanner scanner) {
        int bookIndex = -1;

        for (int i = 0; i < bookCount; i++) {
            if (books[i].getBookId() == bookId) {
                bookIndex = i;
                break;
            }
        }


        if (bookIndex != -1) {
            Book book = books[bookIndex];
            System.out.println("Thông tin sách cần cập nhật: ");
            System.out.println(book.toString());

            System.out.print("Nhập tên sách: ");
            String bookName = scanner.nextLine();
            book.setBookName(bookName);

            System.out.print("Nhập tác giả: ");
            String author = scanner.nextLine();
            book.setAuthor(author);

            System.out.print("Nhập mô tả: ");
            String description = scanner.nextLine();
            book.setDescription(description);

            System.out.print("Nhập giá nhập: ");
            double importPrice = scanner.nextDouble();
            book.setImportPrice(importPrice);

            System.out.print("Nhập giá bán: ");
            double salePrice = scanner.nextDouble();
            book.setSalePrice(salePrice);


            System.out.println("Đã cập nhật thông tin sách có mã " + bookId);
        } else {
            System.out.println("Không tìm thấy sách có mã " + bookId);
        }
    }
}
