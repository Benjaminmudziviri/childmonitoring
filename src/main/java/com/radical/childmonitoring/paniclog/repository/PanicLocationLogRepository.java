package com.radical.childmonitoring.paniclog.repository;

import com.radical.childmonitoring.paniclog.entity.PanicLocationLog;
import com.radical.childmonitoring.panic.entity.Panic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PanicLocationLogRepository extends JpaRepository<PanicLocationLog, Long> {
    List<PanicLocationLog> findByPanic(Panic panic);

    List<PanicLocationLog> findByPanicId(Long panicId);
}
