package my.mins.aus.app.account;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 유저관련 컨트롤러
 *
 * @author minssogi
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid AccountDTO param) {

        Account dbParam = new Account();
        BeanUtils.copyProperties(param, dbParam);
        dbParam.setPassword(passwordEncoder.encode(param.getPassword()));

        accountRepository.save(dbParam);

        return ResponseEntity.ok("Success!!");
    }
}
