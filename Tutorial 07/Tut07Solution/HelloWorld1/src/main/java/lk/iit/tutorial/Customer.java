package lk.iit.tutorial;

/*
 * @author Mr.Nazhim
 */
public class Customer {

    private String name;
    private int age;
    private int id;

    // make sure to always keep the initial constructor when ever you perform any constructor overloading
    public Customer() {

    }

    public Customer(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Customer{" + "name=" + name + ", age=" + age + ", id=" + id + '}';
    }

}
