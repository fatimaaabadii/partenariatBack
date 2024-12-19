package com.entraide.part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.entraide.part.entity.AuthRequest;
import com.entraide.part.entity.UserInfo;
import com.entraide.part.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
  
    
    

    @GetMapping("/welcome")
    public String welcome(){
        return "Bienvenue sur votre application de gestion des déplacements professionnels !!";
    }

    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('SIEGE_ROLES')  OR hasAuthority('TECHNIQUE_ROLES')")
    public String addUser(@RequestBody UserInfo userInfo){
        return userInfoService.addUser(userInfo);

    }
    @PostMapping("/login")
    public String addUser(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUserName());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }
    
    
    @GetMapping("/email/{userName}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public Optional<UserInfo> getCurrentUser(@PathVariable String userName){
        return userInfoService.getCurrentUser(userName);
    }
    
    
    
    
    
    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public List<UserInfo> getAllUsers(){
        return userInfoService.getAllUser();
    }
    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public UserInfo getAllUsers(@PathVariable Integer id){
        return userInfoService.getUser(id);
    }
    
    
    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userInfoService.deleteUser(id);
        return ResponseEntity.ok("Employé supprimé avec succès !.");
    }
 
 
 @PutMapping("/updateUser/{id}")
 @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable("id") Long id, @RequestBody UserInfo user){
        return new ResponseEntity<>(userInfoService.updateUser(id, user), HttpStatus.OK);
    }
 @PutMapping("/changepsw/{userId}")
 @PreAuthorize("hasAuthority('ADMIN_ROLES') OR hasAuthority('USER_ROLES') OR hasAuthority('SIEGE_ROLES') OR hasAuthority('DELEGUE_ROLES') OR hasAuthority('COORDINATEUR_ROLES') OR hasAuthority('TECHNIQUE_ROLES')")
 public ResponseEntity<String> updatePassword(@PathVariable("userId") Integer userId,
         @RequestBody RequestBodyObject passwordUpdate) {

       String oldPassword = passwordUpdate.getOldPassword();
       String newPassword = passwordUpdate.getNewPassword();
       String confirmPassword = passwordUpdate.getConfirmPassword();

       UserInfo user = userInfoService.getUser(userId);
       if (user == null) {
       return ResponseEntity.notFound().build();
      }

      boolean passwordUpdated = userInfoService.updatePassword(user, oldPassword, newPassword, confirmPassword);
      if (passwordUpdated) {
      return ResponseEntity.ok("Mot de passe mis à jour avec succès.");
         } else {
       return ResponseEntity.badRequest().body("La mise à jour du mot de passe a échoué. Vérifiez vos informations.");
}
}
 }
