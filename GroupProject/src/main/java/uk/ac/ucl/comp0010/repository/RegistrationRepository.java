package uk.ac.ucl.comp0010.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.ac.ucl.comp0010.model.Registration;
import uk.ac.ucl.comp0010.model.RegistrationId;


import java.util.List;

/**
 * Repository for Registration objects.
 */

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {
    // Find all modules registered by a specific student ID
    List<Registration> findByStudent_Id(Integer studentId);

    // Find all students registered to a specific module code
    List<Registration> findByModule_Code(String moduleCode);
}

