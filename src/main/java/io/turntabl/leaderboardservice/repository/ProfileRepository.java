package io.turntabl.leaderboardservice.repository;

import io.turntabl.leaderboardservice.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {

    @Modifying
    @Query(value ="insert into Profiles (id) values (:username)", nativeQuery = true)
    @Transactional
    void insertIntoDatabase(@Param("username") String username);
}
