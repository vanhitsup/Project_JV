package com.ra.service;

import com.ra.model.Book;
import com.ra.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookService {
    private static final CategoryService categoryService=new CategoryService();


    static File file;
    public BookService() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File bookFile = new File("data/book.txt");
        if (!bookFile.exists()) {
            try {
                bookFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.file = bookFile;
    }

    public void saveToFile(List<Book> list){
        try {
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream outputStream= new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
        }
        catch (IOException exception){
            System.err.println("Có lỗi khi thêm mới");
            exception.printStackTrace();
        }
    }

    public static List<Book> getAllToFile() {
        List<Book> books = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream(file);
            ObjectInputStream inputStream= new ObjectInputStream((fileInputStream));
            books=(List<Book>) inputStream.readObject();
        } catch (EOFException e) {
        }
        catch (IOException e){
            System.err.println("Lỗi khi đọc file");
        }
        catch (ClassNotFoundException e){
            throw  new RuntimeException();
        }
        return books;
    }

    //Check IdCate có tồn tại hay ko
    public boolean checkIdCategory(int idCate){
        List<Category> categories= categoryService.getAllToFile();
        for (Category category : categories) {
            if (idCate==category.getId()){
                return false;
            }
        }
        return true;
    }
    public void addBook(){
        Scanner sc=new Scanner(System.in);
        List<Book> books=getAllToFile();
        do {
            System.out.println("Nhâp vào thông tin Sách");

            Book book=new Book();
            book.input();
            books.add(book);
            System.out.println("Bạn có muốn thêm nữa không ? ");
            System.out.println(" Chọn 1 là có, chọn 2 là không");
            int choice=Integer.parseInt(sc.nextLine());
            if (choice==2){
                break;
            }
        }
        while (true);
        saveToFile(books);
    }

    public void showBook(){
        List<Book> books=getAllToFile();
        for (Book book : books) {
            book.output();
        }
    }

    //Tìm kiếm idBook
    public Book findIdBook(String bookCode,List<Book> books){
        for (Book book : books) {
            if (Objects.equals(book.getId(), bookCode)){
                return book;
            }
        }
        return null;
    }
    //Update book
    public void updateBook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập vào mã sách cần sửa: ");
        String checkIdBook= sc.nextLine();
        List<Book> books=getAllToFile();
        Book book=findIdBook(checkIdBook,books);

        if (book!=null){
            System.out.println("Chi tiết sách cần sửa !");
            System.out.println("-----------------------");
            book.output();

            //
            do {
                try{
                    System.out.println("--------------------");
                    System.out.println("Mời nhập tiêu đề mới: ");
                    String newTitle=sc.nextLine();
                    book.setTitle(newTitle);
                    if (newTitle.isEmpty()){
                        System.err.println("Tiêu đề không được để trống và có số ký tự từ 6-50! ");
                    }
                    else {
                        if (newTitle.length()<6 || newTitle.length()>50){
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
                    String newAuthor=sc.nextLine();
                    book.setAuthor(newAuthor);
                    if (newAuthor.isEmpty()){
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
                    String newPublisher=sc.nextLine();
                    book.setPublisher(newPublisher);
                    if (newPublisher.isEmpty()){
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
                    int newYear=Integer.parseInt(sc.nextLine());
                    book.setYear(newYear);
                    if (newYear<1970 || newYear>2024){
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
                    String newDes=sc.nextLine();
                    book.setDescription(newDes);
                    if (newDes.isEmpty()){
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
                    int newCategoryId=Integer.parseInt(sc.nextLine());
                    book.setCategoryId(newCategoryId);
                    if (newCategoryId<0){
                        System.err.println("Mã thể loại sách không được để trống ! ");
                    }
                    else {
                        if (checkIdCategory(newCategoryId)){
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
            System.out.println("Sửa sản phẩm thành công !");
            saveToFile(books);
        }
        else {
            System.err.println("Không tìm thấy sách !");
        }

    }

    //Delete book
    public void deleteBook(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập mã sách cần xóa: ");
        String checkIdBook= sc.nextLine();
        List<Book> books=getAllToFile();

        Book book= findIdBook(checkIdBook,books);
        if ((book!=null)){
            books.remove(book);
            System.err.println(" Sách có mã = " +checkIdBook+ " đã được xóa thành công !");
            saveToFile(books);
        }
        else {
            System.err.println("Không tìm thấy thể loại có mã : "+checkIdBook);
        }

    }

    public void bookGroupCategory(){
        List<Book> books=getAllToFile();
        List<Category> categories= categoryService.getAllToFile();

        for (Book book : books) {
            for (Category category : categories) {
                if (book.getCategoryId()==category.getId()){
                    System.out.println("Thể loại: "+category.getName());
                    System.out.println("Sách " +book.getTitle());
                }
            }
        }

    }

    //Search book
    public void searchBook(){
        Scanner sc=new Scanner(System.in);

           System.out.println("Nhập vào tên sách bạn cần tìm ");
           String nameBook=sc.nextLine();
           List<Book> books=getAllToFile();
            boolean check=false;
           try {
               for (Book value : books){
                   if (value.getTitle().contains(nameBook) || value.getAuthor().contains(nameBook) ||value.getPublisher().contains(nameBook)){
                       System.out.println("Tên sách vừa tìm được : ");
                       System.out.println("------------------");
                       check=true;
                       value.output();
                   }
               }
               if (!check){
                   System.err.println("Không tìm thấy sách theo yêu cầu !");

               }
           }
           catch (Exception e){
               System.err.println("Không tìm thấy sách theo yêu cầu !");
           }
    }


}
