package services

import javax.inject.Singleton

import entity.User

/**
  * Created by Alex on 28/07/2017.
  */

@Singleton
class UserService {

  def login(user : User): Option[String] = {
    user.password == "test" && user.login == "test" match {
     case true => Some(user.login)
     case false =>  None
    }
  }

  def get(login : String) : Option[User] = {
    Some(new User("test", "test", Some("Test@gmail.com")))
  }
}
