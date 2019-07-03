package khaliscode.testa_usermanagement.controller;

import khaliscode.testa_usermanagement.exception.NotFoundException;
import khaliscode.testa_usermanagement.model.EntityUser;
import khaliscode.testa_usermanagement.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/core")
public class ControllerUser {

    @Autowired
    private RepositoryUser repoUser;

    @GetMapping("/user")
    public List<EntityUser> getAllUser() {
        return repoUser.findAll();
    }
    @GetMapping("/user/{userid}")
    public List<EntityUser> getUserByID (@PathVariable String userid){
        String superSecretId = userid;
        Integer user_id = 0;

        try {
            user_id = Integer.parseInt(userid);
        } catch (Exception ex){
            user_id = 0;
        }

        ExampleMatcher searchConf = ExampleMatcher.matchingAny()
                .withMatcher("userid", ExampleMatcher.GenericPropertyMatchers.contains().caseSensitive());
        Example dataExample = Example.of(user_id, searchConf);
        List<EntityUser> listFinal = repoUser.findAll(dataExample);
        return listFinal;
    }
    @PostMapping("/user")
    public EntityUser createData(@Valid @RequestBody EntityUser dataMain) {
//        Date date_new = new Date();
//        dataMain.setUsername(dataMain.getUsername());
//        dataMain.setUseric(dataMain.getUseric());
        return repoUser.save(dataMain);
    }
    @PutMapping("/user/{userid}")
    public EntityUser updateUser(@PathVariable String userid, @Valid @RequestBody EntityUser dataMain) {
        String superSecretId = userid;
        Integer user_id = 0;

        try {
            user_id = Integer.parseInt(userid);
        } catch (Exception ex){
            user_id = 0;
        }

        return repoUser.findById(user_id).map(
                dataUpdate -> {

                    dataMain.setUsername(dataMain.getUsername());
                    dataMain.setUseric(dataMain.getUseric());
                    return repoUser.save(dataUpdate);
                }
        ).orElseThrow(() -> new NotFoundException("Not Found - " + userid));
    }
    @DeleteMapping("/user/{userid}")
    public String deleteData(@PathVariable String userid) {
        String superSecretId = userid;
        Integer user_id = 0;

        try {
            user_id = Integer.parseInt(userid);
        } catch (Exception ex){
            user_id = 0;
        }

        return repoUser.findById(user_id).map(
                dataDelete -> {
                    repoUser.delete(dataDelete);
                    return "Done";
                }
        ).orElseThrow(() -> new NotFoundException("Not found - "+ userid));
    }
}
