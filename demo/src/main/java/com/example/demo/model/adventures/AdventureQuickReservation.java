package com.example.demo.model.adventures;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "adventureQuickReservation")
public class AdventureQuickReservation{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private Double price;
    @NotNull
    private LocalDateTime validUntil;

    @ManyToOne()
    @JoinColumn(name = "adventure_id", nullable = false)
    private Adventure adventure;

    private int guestLimit;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "adventure_quick_reservation_adventure_utility",
            joinColumns = @JoinColumn(name = "adventure_quick_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "adventure_utility_id"))
    private Set<AdventureUtility> adventureUtilities;

    private boolean isReserved = false;
    private boolean isDeleted = false;

    public AdventureQuickReservation() {
    }

    public AdventureQuickReservation(Long id, LocalDateTime startTime, LocalDateTime endTime, Double price, LocalDateTime validUntil, Adventure adventure, int guestLimit, Set<AdventureUtility> adventureUtilities, boolean isReserved,boolean isDeleted) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.validUntil = validUntil;
        this.adventure = adventure;
        this.guestLimit = guestLimit;
        this.adventureUtilities = adventureUtilities;
        this.isReserved = isReserved;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }

    public int getGuestLimit() {
        return guestLimit;
    }

    public void setGuestLimit(int guestLimit) {
        this.guestLimit = guestLimit;
    }

    public Set<AdventureUtility> getAdventureUtilities() {
        return adventureUtilities;
    }

    public void setAdventureUtilities(Set<AdventureUtility> adventureUtilities) {
        this.adventureUtilities = adventureUtilities;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
