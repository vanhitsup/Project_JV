package com.ra.view;

import com.ra.model.Category;
import com.ra.service.BookService;
import com.ra.service.CategoryService;

import java.util.Scanner;

public class Library  {
    private  static final Scanner sc =new Scanner(System.in);
    private static final CategoryService categoryService=new CategoryService();
    private static final BookService bookService=new BookService();
    public  static void showMenu(){
        do {
            System.out.println("===== Quản Lý Thư Viện =====");
            System.out.println("1. Quản lý Thể loại");
            System.out.println("2. Quản lý Sách");
            System.out.println("3. Thoát");

            System.out.println("Mời bạn chọn từ 1-3");
            int choice= Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    menuCategory();
                    break;
                case 2:
                    menuBook();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Chọn sai, bạn vui lòng chọn lại");
            }

        }
        while (true);
    }

    //Menu Category
    public static void menuCategory(){
        do {
            System.out.println("===== Quản Lý Thể Loại =====");
            System.out.println("1. Thêm mới Thể loại");
            System.out.println("2. Hiển thị danh sách theo tên từ A-Z");
            System.out.println("3. Thống kê");
            System.out.println("4. Cập nhật thể loại");
            System.out.println("5. Xóa thể loại");
            System.out.println("6. Quay lại");
            System.out.println("Mời bạn chọn từ 1-6");
            int choice= Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    categoryService.addCategory();
                    break;
                case 2:
                    categoryService.showCategory();
                    break;
                case 3:
                    categoryService.statisticsCategory();
                    break;
                case 4:
                    categoryService.updateCategory();
                    break;
                case 5:
                    categoryService.deleteCategory();
                    break;
                case 6:
                    showMenu();
                default:
                    System.out.println("Chọn sai, bạn vui lòng chọn lại");
            }
        }
        while (true);
    }

    //Menu Book
    public static void menuBook(){
        do {
            System.out.println("===== Quản Lý Sách =====");
            System.out.println("1. Thêm mới Sách");
            System.out.println("2. Cập nhật thông tin sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị sách theo thể loại");
            System.out.println("6. Quay lại");
            System.out.println("Mời bạn chọn từ 1-6");
            int choice= Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    bookService.addBook();
                    break;
                case 2:
                    bookService.updateBook();
                    break;
                case 3:
                    bookService.deleteBook();
                    break;
                case 4:
                    bookService.searchBook();
                    break;
                case 5:
//                    bookService.bookGroupCategory();

                    bookService.showBook();
                    break;
                case 6:
                    showMenu();
                default:
                    System.out.println("Chọn sai, bạn vui lòng chọn lại");
            }
        }
        while (true);
    }
}
