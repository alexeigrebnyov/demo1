package com.example.demo.service;

import com.example.demo.dao.UptakeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private final UptakeDao uptakeDao;

    public UserDetailsServiceImp(UptakeDao uptakeDao) {
        this.uptakeDao = uptakeDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return uptakeDao.loadUserByUsername(s);
    }
}
