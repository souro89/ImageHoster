package ImageHoster.service;

import ImageHoster.model.User;
import ImageHoster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Call the registerUser() method in the UserRepository class to persist the user record in the database
    public void registerUser(User newUser) {
        userRepository.registerUser(newUser);
    }

    //Since we did not have any user in the database, therefore the user with username 'upgrad' and password 'password' was hard-coded
    //This method returned true if the username was 'upgrad' and password is 'password'
    //But now let us change the implementation of this method
    //This method receives the User type object
    //Calls the checkUser() method in the Repository passing the username and password which checks the username and password in the database
    //The Repository returns User type object if user with entered username and password exists in the database
    //Else returns null
    public User login(User user) {
        User existingUser = userRepository.checkUser(user.getUsername(), user.getPassword());
        if (existingUser != null) {
            return existingUser;
        } else {
            return null;
        }
    }

    public Boolean passwordStrengthCheck(String password){
        Pattern pattern1 = Pattern.compile("[a-z]",Pattern.CASE_INSENSITIVE);
        Pattern pattern2 = Pattern.compile("[0-9]",Pattern.CASE_INSENSITIVE);
        Pattern pattern3 = Pattern.compile("[^a-z0-9]",Pattern.CASE_INSENSITIVE);
        Matcher m1 = pattern1.matcher(password);
        Matcher m2 = pattern2.matcher(password);
        Matcher m3 = pattern3.matcher(password);

        if(m1.find() & m2.find() & m3.find()){
            return true;
        }else{
            return false;
        }


    }

}
