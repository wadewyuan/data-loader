package com.citictel.dataloader.domain

import javax.persistence.*

@Entity
data class User(
    @Id @GeneratedValue val id: Long ?= null,
    val username: String,
    val password: String,
    val enabled: Boolean,
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = [(JoinColumn(name = "user_id", referencedColumnName = "id"))],
            inverseJoinColumns = [(JoinColumn(name = "role_id", referencedColumnName = "id"))])
    val roles: List<Role>
)

@Entity
data class Role(
    @Id @GeneratedValue val id: Long ?= null,
    val name: String,
    @ManyToMany(mappedBy = "roles") val users: List<User>
)