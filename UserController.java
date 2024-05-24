package org.edu.chnucoin.api;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.edu.chnucoin.SendCoinDto;
import org.edu.chnucoin.entity.Transaction;
import org.edu.chnucoin.entity.User;
import org.edu.chnucoin.repository.TransactionRepository;
import org.edu.chnucoin.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/{currentUserId}/send")
    public void sendCoins(@RequestBody SendCoinDto sendCoinDto, @PathVariable Long currentUserId) {

        userService.sendCoins(sendCoinDto, currentUserId);

    }

    @PostMapping("/{currentUserId}/mine")
    public void mine(@PathVariable Long currentUserId) throws Exception {

        userService.mineTransaction(currentUserId);

    }



    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

}
