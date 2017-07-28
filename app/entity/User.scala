package entity

import play.api.libs.json.{Reads, Json, Writes}

/**
  * Created by Alex on 27/07/2017.
  */

case class User(login : String, password : String, mail : Option[String])

object User {

  implicit val userWrites : Writes[User] = Json.writes[User]
  implicit val userReads : Reads[User]   = Json.reads[User]

}
