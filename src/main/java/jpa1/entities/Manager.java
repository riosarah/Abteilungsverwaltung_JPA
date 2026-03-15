package jpa1.entities;

import jakarta.persistence.Entity;


@Entity
public class Manager extends Employee{
    // fields
   
    int bonus;

    boolean onEjectSeat;

    // constructor
    public Manager() {
    }

    public Manager(int bonus, boolean eject){
        this.bonus = bonus;
        this.onEjectSeat = eject;
    }

    // getters & setters

    public int getId() {
        return Id;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public boolean isOnEjectSeat() {
        return onEjectSeat;
    }

    public void setOnEjectSeat(boolean onEjectSeat) {
        this.onEjectSeat = onEjectSeat;
    }

}
