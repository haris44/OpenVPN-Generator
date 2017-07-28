package entity

import play.api.libs.json.{Json, Reads, Writes}
/**
  * Created by Alex on 28/10/2016.
  */

case class VPNUser(nom: String, prenom: String, email: String){
  def getUsername(): String ={
    prenom.charAt(0) + nom
  }
}

object VPNUser {

  implicit val VpnUserWrites : Writes[VPNUser] = Json.writes[VPNUser]
  implicit val VpnUserReads : Reads[VPNUser]   = Json.reads[VPNUser]

}

