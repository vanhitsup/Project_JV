package com.ra.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.ra.service.CategoryService;
public class Category implements IEntity, Serializable {
    @Serial
    private static final long serialVersionUID=1L;
    private static final CategoryService categoryService=new CategoryService();


    private  int id;
    private  String name;
    private boolean status;

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //check trung id
    public boolean checkId(int catalogId) {
        List<Category> listCate = categoryService.getAllToFile();
        for (Category cate : listCate) {
            if (catalogId == cate.getId()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void input () {
        Scanner sc = new Scanner(System.in);
        do {
            try{
                System.out.println("Mời nhập mã thể loại: ");
                id=Integer.parseInt(sc.nextLine());
                if (id<=0){
                    System.err.println("Sai định dạng !");
                }else if (checkId(id)){
                    System.err.println("Mã thể loại đã tồn tại !");
                }
                else {
                    break;
                }
            }
            catch (Exception e){
                System.err.println("Mã thể loại không hợp lệ, mời bạn nhập lại !");
            }
        }
        while (true);

        do {
            try{
                System.out.println("Mời nhập tên thể loại: ");
                name=sc.nextLine();
                if (name.isEmpty()){
                    System.err.println("Tên thể loại không được để trống! ");
                } else if (name.length()<6 || name.length()>30) {
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

        //nhập trạng thái
        do {
            System.out.println("Nhập trạng thái thể loại true or false ");
            String status1=sc.nextLine();
            if (status1.equals("true") || status1.equals("false")){
                status=Boolean.parseBoolean(status1);
                break;
            }
            else {
                System.err.println("Bạn cần nhập true hoặc false ");
            }
        }
        while (true);

    }

    @Override
    public void output() {
        System.out.println("Danh sách thể loại ");
        System.out.println("Mã thể loại " + id);
        System.out.println("Tên thể loại: " + name);
        if (status){
            System.out.println("Trạng thái: Hoạt động");
        }
        else {
            System.out.println("Trạng thái: Ngừng hoạt động");
        }
    }

}
