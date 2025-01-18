/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author HP
 */
public class Brand {

    private int Id;
    private String Name;
    private String Description;
    private String Country;

    public Brand() {
    }

    public Brand(int Id, String Name, String Description, String Country) {
        this.Id = Id;
        this.Name = Name;
        this.Description = Description;
        this.Country = Country;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getCountry() {
        return Country;
    }

    @Override
    public String toString() {
        return "Brand{" + "Id=" + Id + ", Name=" + Name + ", Description=" + Description + ", Country=" + Country + '}';
    }

}
