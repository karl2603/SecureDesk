package com.Karl.SecureDesk.repository;

import com.Karl.SecureDesk.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query(value = "SELECT * FROM tickets WHERE status IN ('ACTIVE', 'IN_PROGRESS')", nativeQuery = true)
    List<Ticket> findActiveTickets();
}
