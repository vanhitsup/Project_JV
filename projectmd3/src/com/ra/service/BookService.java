package com.ra.service;

import com.ra.model.Book;
import com.ra.model.Category;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BookService {
    Scanner sc=new Scanner(System.in);
    File file;
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
            FileOutputStream fileOutputStream=new FileOutputStream(this.file);
            ObjectOutputStream outputStream= new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
        }
        catch (IOException exception){
            System.err.println("Có lỗi khi thêm mới");
            exception.printStackTrace();
        }
    }

    public  List<Book> getAllToFile() {
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

    public void addBook(){
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

    }

}
