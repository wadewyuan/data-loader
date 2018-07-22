package com.citictel.dataloader.service

import com.citictel.dataloader.domain.Role
import com.citictel.dataloader.repository.RoleRepository
import com.citictel.dataloader.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.ArrayList

@Service
class AppUserDetailsService : UserDetailsService {

    @Autowired
    private val userRepository: UserRepository? = null

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository!!.findByName(username)
        return org.springframework.security.core.userdetails.User(
                user.username, user.password, user.enabled, true, true,
                true, getAuthorities(user.roles))
    }

    private fun getAuthorities(
            roles: Collection<Role>): Collection<GrantedAuthority> {

        return getGrantedAuthorities(getPrivileges(roles))
    }

    private fun getPrivileges(roles: Collection<Role>): List<String> {

        val privileges = ArrayList<String>()
        for (item in roles) {
            privileges.add(item.name)
        }
        return privileges
    }

    private fun getGrantedAuthorities(privileges: List<String>): List<GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        for (privilege in privileges) {
            authorities.add(SimpleGrantedAuthority(privilege))
        }
        return authorities
    }
}