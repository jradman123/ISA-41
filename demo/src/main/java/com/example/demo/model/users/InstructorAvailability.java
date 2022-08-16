package com.example.demo.model.users;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class InstructorAvailability {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    public InstructorAvailability() {
    }

    public InstructorAvailability(Long id, LocalDateTime startDate, LocalDateTime endDate, Instructor instructor) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.instructor = instructor;
    }
}
