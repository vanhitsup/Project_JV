package com.ra;

import com.ra.model.Category;

import java.util.ArrayList;
import java.util.List;

public class DemoPrintTable {
    public static void main(String[] args) {
        List<Category> categories=new ArrayList<>();
        categories.add(new Category(1,"Ma",true));
        categories.add(new Category(2,"Cười",true));

        System.out.printf("--------------------------%n");
        System.out.printf("----Danh sách danh mục----%n");
        System.out.printf("--------------------------%n");
        System.out.printf("| %-6s | %-20s | %-10s |%n", "ID","Tên thể loại","Trạng thái");
    }
}
