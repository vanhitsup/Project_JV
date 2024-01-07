package com.ra.service;

import com.ra.model.Category;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CategoryService {
    Scanner sc=new Scanner(System.in);
    File file;

    public CategoryService() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File categoryFile = new File("data/category.txt");
        if (!categoryFile.exists()) {
            try {
                categoryFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.file = categoryFile;
    }

    public void saveToFile(List<Category> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(this.file);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(list);
        } catch (IOException exception) {
            System.out.println("Có lỗi khi thêm mới");
        }
    }

    //Phương thức đọc file và lấy category
     public  List<Category> getAllToFile() {
        List<Category> categories = new ArrayList<>();
        try {
            FileInputStream fileInputStream= new FileInputStream(file);
            ObjectInputStream inputStream= new ObjectInputStream((fileInputStream));
            categories=(List<Category>) inputStream.readObject();
        } catch (EOFException e) {
        }
        catch (IOException e){
            System.err.println("Lỗi khi đọc file");
        }
        catch (ClassNotFoundException e){
            throw  new RuntimeException();
        }
        return categories;
    }

    //Thêm mới category
    public void addCategory(){
        List<Category> categories=getAllToFile();
        do {
            System.out.println("Nhâp vào thông tin Thể loại");

            Category category=new Category();
            category.input();
            categories.add(category);
            System.out.println("Bạn có muốn thêm nữa không ? ");
            System.out.println(" Chọn 1 là có, chọn 2 là không");
            int choice=Integer.parseInt(sc.nextLine());
            if (choice==2){
                break;
            }
        }
        while (true);
        saveToFile(categories);
    }

    //Hiển thị danh sách thể loai
    public void showCategory(){
        List<Category> categories=getAllToFile();
        for (Category category : categories) {
            category.output();
        }
    }

    //Tìm kiếm Idcategory
    public Category findIdCategory(Integer categoryCode,List<Category> categories){
        for (Category category : categories) {
            if (category.getId()==categoryCode){
                return category;
            }
        }
        return null;
    }

    //Update thể loại
    public void updateCategory(){
        System.out.println("Nhập vào mã thể loại cần sửa: ");
        int checkIdCategory= Integer.parseInt(sc.nextLine());
        List<Category> categories=getAllToFile();
        Category category=findIdCategory(checkIdCategory,categories);
        if (category!=null){
            System.out.println("Chi tiết thể loại: ");
            category.output();

            System.out.println("Nhập tên thể loại mới: ");
            category.setName(sc.nextLine());
            System.out.println("Nhập trạng thái mới");
            category.setStatus(Boolean.parseBoolean(sc.nextLine()));
            saveToFile(categories);
        }
        else {
            System.out.println(" Không tìm thấy thể loại nào !");
        }
    }
    //Xóa thể loại
    public void deleteCategory(){
        System.out.println("Nhập mã thể loại cần xóa: ");
        int checkIdCategory= Integer.parseInt(sc.nextLine());
        List<Category> categories=getAllToFile();

        Category category= findIdCategory(checkIdCategory,categories);
        if ((category!=null)){
            categories.remove(category);
            System.out.println("Thể loại có mã = " +checkIdCategory + " đã được xóa thành công !");
            saveToFile(categories);
        }
        else {
            System.out.println("Không tìm thấy thể loại có mã : "+checkIdCategory);
        }
    }
}