package com.ra.service;
import com.ra.model.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public void saveToFile(List<Book> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
        } catch (IOException exception) {
            System.out.println("Có lỗi khi thêm mới");
        }
    }

    //Phương thức đọc file và lấy Book
    public List<Book> getAllToFile() {
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
}
