package me.kire.re.studentcontroller.registration;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RegistrationRepository extends ReactiveMongoRepository<Registration, String> {

    Flux<Registration> findByStudentId(String studentId);

}
