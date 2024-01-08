package com.ra.model;

import com.ra.service.BookService;
import com.ra.service.CategoryService;


import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Book implements IEntity, Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    private static final BookService bookService=new BookService();
    private static final CategoryService categoryService=new CategoryService();
//    Scanner sc= new Scanner(System.in);
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public boolean checkId(String bookId) {
        List<Book> listBook = bookService.getAllToFile();
        for (Book book : listBook) {
            if (Objects.equals(book.getId(), bookId)) {
                return true;
            }
        }

        return false;
    }

    //Check mã book nhập vào có thuộc 1 trong cách mã thể loại
    public boolean checkIdCategory(int idCate){
        List<Category> categories= categoryService.getAllToFile();
        for (Category category : categories) {
            if (idCate==category.getId()){
                return false;
            }
        }
        return true;
    }
    @Override
    public void input() {
        Scanner sc =new Scanner(System.in);
        do {
            try {
                System.out.println("Nhập mã sách: ");
                String check="BBBB";
                id=sc.nextLine();
                char checkChar=check.charAt(0);
                if (check.length() - id.length() != 0){
                    System.err.println("Mã sách yêu cầu nhập đủ 4 ký tự");
                } else if (checkChar!=id.charAt(0)) {
                    System.err.println("Mã sách phải bắt đầu bằng chữ B");
                } else {
                    if (checkId(id)){
                        System.err.println("Mã đã tồn tại");
                    }
                    else
                    {
                        break;
                    }
                }

            }
            catch (Exception e){
                System.err.println("Mã sách không hợp lệ, nhập lại nào.");
            }
        }
        while (true);
//
        do {
            try{
                System.out.println("Mời nhập tiêu đề: ");
                title=sc.nextLine();
                if (title.isEmpty()){
                    System.err.println("Tiêu đề không được để trống và có số ký tự từ 6-50! ");
                }
                else {
                    if (title.length()<6 || title.length()>50){
                        System.err.println("Tiêu đề có số ký tự từ 6-50! ");
                    }
                    else {
                        break;
                    }
                }
            }
            catch (Exception e){
                System.err.println("Tiêu đề không được để trống!");
            }
        }
        while (true);

//
        do {
            try {
                System.out.println("Mời nhập tên tác giả: ");
                author=sc.nextLine();
                if (author.isEmpty()){
                    System.err.println("Tên tác giả không được để trống ! ");
                }
                else {
                    break;
                }
            }
            catch (Exception e){
                System.err.println("Tên tác giả không được để trống ");
            }
        }
        while (true);
//
        do {
            try {
                System.out.println("Mời nhập nhà xuất bản: ");
                publisher=sc.nextLine();
                if (publisher.isEmpty()){
                    System.err.println("Nhà xuất bản không được để trống ! ");
                }
                else {
                    break;
                }
            }
            catch (Exception e){
                System.err.println("Tên tác giả không được để trống ");
            }
        }
        while (true);
//
        do {
            try{
                System.out.println("Mời nhập năm xuất bản: ");
                year=Integer.parseInt(sc.nextLine());
                if (year<1970 || year>2024){
                    System.err.println("Năm xuất bản phải từ 1970 - 2024");
                }
                else {
                    break;
                }
            }catch (Exception e){
                System.err.println(" Năm xuất bản từ 1970-2024");
            }
        }
        while (true);
//
        do {
            try {
                System.out.println("Mời nhập mô tả sách: ");
                description=sc.nextLine();
                if (description.isEmpty()){
                    System.err.println("Mô tả sách không được để trống ! ");
                }
                else {
                    break;
                }
            }
            catch (Exception e){
                System.err.println("Mô tả sách không được để trống ");
            }
        }
        while (true);
//
        do {
            try {
                System.out.println("Mời nhập mã thể loại sách: ");
                categoryId=Integer.parseInt(sc.nextLine());
                if (categoryId<0){
                    System.err.println("Mã thể loại sách không được để trống ! ");
                }
                else {
                    if (checkIdCategory(categoryId)){
                        System.err.println("Mã thể loại sách không tồn tại");
                    }
                    else
                    {
                        break;
                    }
                }
            }
            catch (Exception e){
                System.err.println("Mã thể loại sách không được để trống ");
            }
        }
        while (true);
    }


    @Override
    public void output() {
        System.out.println("Danh sách Sách ");
        System.out.println("Mã sách " + id);
        System.out.println("Tiêu đề sách: " + title);
        System.out.println("Tên tác giả  "+author);
        System.out.println("Nhà xuất bản " + publisher);
        System.out.println("Năm xuất bản: " +year);
        System.out.println("Mô tả sách: " +description);
        System.out.println("Mã thể loại: " + categoryId);
    }
}
