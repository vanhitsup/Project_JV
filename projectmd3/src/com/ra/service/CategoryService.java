package com.ra.service;

import com.ra.model.Book;
import com.ra.model.Category;

import java.io.*;
import java.util.*;


public class CategoryService {
    private static final BookService bookService=new BookService();
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
        Scanner sc=new Scanner(System.in);
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
        Collections.sort(categories);
        for (Category category : categories) {
            category.output();
            System.out.println("-------------");
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


    //statistics category
    public void statisticsCategory(){
        List<Category> categories=getAllToFile();
        List<Book> books= bookService.getAllToFile();

        System.out.printf("-------------------------%n");
        System.out.printf("----Thống kê thể loại----%n");
        System.out.printf("-------------------------%n");
        System.out.printf("| %-6s | %-20s | %-15s |%n", "ID","Tên thể loại","Trạng thái");
        System.out.printf("-----------------------------------------------------%n");
            for (Category category : categories) {
                System.out.printf("| %-6s | %-20s | %-15s |%n",
                        category.getId(),category.getName(),category.isStatus()?"Hoạt động":"Không hoạt động");
        }
        System.out.printf("-----------------------------------------------------%n");

    }
    //Update thể loại
    public void updateCategory(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập vào mã thể loại cần sửa: ");
        int checkIdCategory= Integer.parseInt(sc.nextLine());
        List<Category> categories=getAllToFile();
        Category category=findIdCategory(checkIdCategory,categories);
        if (category!=null){
            System.out.println("Chi tiết thể loại: ");
            category.output();
            System.out.println("-----------------------");


            do {
                try{
                    System.out.println("Nhập tên thể loại mới: ");
                    String newName= sc.nextLine();
                    category.setName(newName);

                    if (newName.isEmpty()){
                        System.err.println("Tên thể loại không được để trống! ");
                    } else if (newName.length()<6 || newName.length()>30) {
                        System.err.println(" Tên thể loại phải từ 6-30 ký tự !");
                    }
                    else {
                        break;
                    }
                }
                catch (Exception e){
                    System.err.println("Tên thể loại không được để trống!");
                }
            }
            while (true);

            do {
                System.out.println("Cập nhật lại trạng thái, yêu cầu true or false ");
                String newStatus1=sc.nextLine();
                category.setStatus(Boolean.parseBoolean(newStatus1));
                if (newStatus1.equals("true") || newStatus1.equals("false")){
                    break;
                }
                else {
                    System.err.println("Bạn cần nhập true hoặc false ");
                }
            }
            while (true);

            saveToFile(categories);
            System.out.println("Cập nhật thành công");
        }
        else {
            System.out.println(" Không tìm thấy thể loại nào !");
        }
    }

    //Check IdCate có tồn tại hay ko
    public boolean checkIdCategoryBook(int idCate){
        List<Book> books= bookService.getAllToFile();
        for (Book book : books) {
            if (idCate==book.getCategoryId()){
                return true;
            }
        }
        return false;
    }
    //Xóa thể loại
    public void deleteCategory(){
        Scanner sc=new Scanner(System.in);
        System.out.println("Nhập mã thể loại cần xóa: ");
        int checkIdCategory= Integer.parseInt(sc.nextLine());
        List<Category> categories=getAllToFile();
        Category category= findIdCategory(checkIdCategory,categories);

                if (checkIdCategoryBook(checkIdCategory)){
                    System.err.println("Thể loại đang tồn tại Sách nên không thể xóa !");
                }
                else {
                    if (category!=null){
                        categories.remove(category);
                        System.out.println("Thể loại có mã = " +checkIdCategory + " đã được xóa thành công !");
                        saveToFile(categories);
                    }
                    else {
                        System.err.println("Không tìm thấy thể loại có mã : "+checkIdCategory);

                    }
                }

    }
}