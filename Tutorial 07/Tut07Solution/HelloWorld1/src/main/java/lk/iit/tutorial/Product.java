package lk.iit.tutorial;

/*
 * @author Mr.Nazhim
 */
public class Product {

    private int id;
    private String name;

    // make sure to always keep the initial constructor when ever you perform any constructor overloading
    public Product() {

    }

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + '}';
    }

}
