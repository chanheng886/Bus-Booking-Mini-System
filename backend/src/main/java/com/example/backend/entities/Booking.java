package com.example.backend.entities;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.example.backend.enums.BookingStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table (name = "tb_booking")
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Entity 
public class Booking {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "trip_schedule_id", nullable = false)
    private TripSchedule schedule;

    @Column (name = "booking_code", nullable = false, unique = true)
    private String bookingCode;

    @Column (name = "total_amout", nullable = false)
    private BigDecimal totalAmount;

    @Enumerated (EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.PENDING;

    @Column (name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany (mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingSeat> bookingSeat = new ArrayList<>();

    @PrePersist 
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        if(this.bookingStatus==null){
            this.bookingStatus = BookingStatus.PENDING;
        }
    }

    public void addBookingSeat(BookingSeat seat){
        this.getBookingSeat().add(seat);
        seat.setBooking(this);
    }
}