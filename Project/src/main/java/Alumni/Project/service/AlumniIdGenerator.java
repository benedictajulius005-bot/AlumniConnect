
package Alumni.Project.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlumniIdGenerator {

    private final JdbcTemplate jdbcTemplate;

    public AlumniIdGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public String generateAlumniId() {

        // Atomically reserve the next number.
        jdbcTemplate.update(
                "UPDATE alumni_id_sequence " +
                        "SET next_number = LAST_INSERT_ID(next_number + 1) " +
                        "WHERE id = 1"
        );

        // Retrieve the reserved number from the same connection.
        Long nextNumber = jdbcTemplate.queryForObject(
                "SELECT next_number FROM alumni_id_sequence WHERE id = 1",
                Long.class
        );

        if (nextNumber == null) {
            throw new IllegalStateException(
                    "Alumni ID sequence is not initialized"
            );
        }

        long assignedNumber = nextNumber - 1;

        return String.format("ALUMNI%03d", assignedNumber);
    }
}
