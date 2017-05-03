package kr.soen.practice8;

/**
 * Created by manggi on 2017. 5. 2..
 */

public class Fruit {
    private String name = "";
    private int imgno = 0;
    private String price ="";
    static public int imgList[] ={R.drawable.abocado , R.drawable.cranberry, R.drawable.cherry, R.drawable.abocado,
    R.drawable.banana , R.drawable.orange, R.drawable.watermelon, R.drawable.grape};

    static public int priceList[] = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};

    public Fruit (String name, int imgno, String price ){
        this.name = name;
        this.imgno = imgno;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgno() {
        return imgno;
    }

    public void setImgno(int imgno) {
        this.imgno = imgno;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public static int[] getImgList() {
        return imgList;
    }

    public static void setImgList(int[] imgList) {
        Fruit.imgList = imgList;
    }

    public static int[] getPriceList() {
        return priceList;
    }

    public static void setPriceList(int[] priceList) {
        Fruit.priceList = priceList;
    }
}
