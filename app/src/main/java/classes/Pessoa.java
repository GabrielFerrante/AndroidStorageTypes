package classes;

import java.io.Serializable;

public class Pessoa implements Serializable {
    private String name;
    private String lastName;
    private int Age;
    private boolean Sex;

    public Pessoa(String name, String lastName, int age, boolean sex) {
        this.name = name;
        this.lastName = lastName;
        this.Age = age;
        this.Sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public boolean isSex() {
        return Sex;
    }

    public void setSex(boolean sex) {
        Sex = sex;
    }


}
