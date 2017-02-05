package controllers

import java.io.File
import javax.inject._

import akka.stream.scaladsl.Source
import akka.stream.scaladsl.FileIO
import akka.util.ByteString
import entity.User
import play.api.http.HttpEntity
import play.api.mvc._
import services.VpnService
import sys.process._

@Singleton
class HomeController extends Controller {

  val vpnService = new VpnService

  def index = Action(BodyParsers.parse.json) {
    request =>
      request.body.validate[User].fold(
        errors => BadRequest("Please check your request"),
        user => {
          val test = vpnService.createAccess(user)
          Ok.sendFile(new File(test))
        }
      )
  }

  def test = Action(BodyParsers.parse.json) {
    request =>
      val generateCertif = "cd /etc/openvpn/easy-rsa/".!!
      Ok("lol")
  }

  def options(path: String) = Action {
    Ok("").withHeaders(
      "Allow" -> "*",
      "Access-Control-Allow-Methods" -> "GET, POST, OPTIONS",
      "Access-Control-Allow-Headers" -> "Accept, Origin, Content-type, X-Json, X-Prototype-Version, X-Requested-With",
      "Access-Control-Allow-Credentials" -> "true",
      "Access-Control-Max-Age" -> (60 * 60 * 24).toString
    )
  }


}


