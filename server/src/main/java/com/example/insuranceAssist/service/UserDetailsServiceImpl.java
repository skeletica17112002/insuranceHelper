    package com.example.insuranceAssist.service;

    import com.example.insuranceAssist.entity.UserMaster;
    import com.example.insuranceAssist.entity.UserPrinciple;
    import com.example.insuranceAssist.repository.UserMasterRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.stereotype.Service;

    @Service
    public class UserDetailsServiceImpl implements UserDetailsService {

        @Autowired
        private UserMasterRepository userMasterRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            UserMaster user = userMasterRepository.findByUsername(username);
            if(user == null){
                System.out.println("User not found");
                throw new UsernameNotFoundException("User not found with user name: " + username);
            }
            return new UserPrinciple(user);
        }
    }
