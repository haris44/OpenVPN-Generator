package entity

import play.api.libs.json.{Json, Reads, Writes}
/**
  * Created by Alex on 28/10/2016.
  */

case class User(nom: String, prenom: String, email: String){
  def getUsername(): String ={
    prenom.charAt(0) + nom
  }
}

object User {

  implicit val userWrites : Writes[User] = Json.writes[User]
  implicit val userReads : Reads[User] = Json.reads[User]

}

