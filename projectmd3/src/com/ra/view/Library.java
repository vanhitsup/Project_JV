package com.ra.view;

import com.ra.model.Category;
import com.ra.service.CategoryService;

import java.util.Scanner;

public class Library  {
    private  static final Scanner sc =new Scanner(System.in);
    private static final CategoryService categoryService=new CategoryService();
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
            System.out.println("2. Cập nhật sách");
            System.out.println("3. Xóa sách");
            System.out.println("4. Tìm kiếm sách");
            System.out.println("5. Hiển thị sách theo thể loại");
            System.out.println("6. Quay lại");
            System.out.println("Mời bạn chọn từ 1-6");
            int choice= Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
