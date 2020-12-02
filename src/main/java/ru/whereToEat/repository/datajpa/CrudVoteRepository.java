package ru.whereToEat.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.whereToEat.model.Vote;

public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {
}
