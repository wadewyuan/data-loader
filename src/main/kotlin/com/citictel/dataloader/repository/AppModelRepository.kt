package com.citictel.dataloader.repository

import com.citictel.dataloader.domain.Role
import com.citictel.dataloader.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1")
    fun findByName(username: String): User
}

@Repository
interface RoleRepository : JpaRepository<Role, Long>