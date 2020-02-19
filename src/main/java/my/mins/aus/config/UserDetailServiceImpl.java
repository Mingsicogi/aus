package my.mins.aus.config;

import my.mins.aus.app.account.Account;
import my.mins.aus.app.account.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 유저 정보를 h2 db에서 가져옴
 *
 * @author minssogi
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetailServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmpId(empId).orElseThrow(() -> new UsernameNotFoundException("H2 DB에 저장된 계정정보가 없습니다. 먼저 계정을 추가해주세요."));

        return User.builder()
                .username(account.getEmpId())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }
}
