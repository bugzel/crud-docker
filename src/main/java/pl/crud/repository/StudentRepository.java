package pl.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.crud.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	Student findOneById(Long id);
}
