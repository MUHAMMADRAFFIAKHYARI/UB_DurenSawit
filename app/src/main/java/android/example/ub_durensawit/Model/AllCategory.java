package android.example.ub_durensawit.Model;

import java.util.List;

public class AllCategory {
    String categoryTitle;
    List<CategoryItem> categoryItemList;
    List<Product> productList;

    public AllCategory(String categoryTitle, List<Product> productList) {
        this.categoryTitle = categoryTitle;
        this.productList = productList;
    }

    public List<CategoryItem> getCategoryItemList() {
        return categoryItemList;
    }



    public void setCategoryItemList(List<CategoryItem> categoryItemList) {
        this.categoryItemList = categoryItemList;
    }

    public List<Product> getProductList(){return productList;}

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }
}
