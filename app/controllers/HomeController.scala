package controllers

import java.io.File
import javax.inject._

import entity.{User, VPNUser}
import play.api.libs.json.Json
import play.api.mvc._

import services.{UserService, VpnService}

@Singleton
class HomeController extends Controller {

  val vpnService = new VpnService
  val userService = new UserService

  def create = Action(BodyParsers.parse.json) {
    request =>
      request.session.get("connected").map { login =>
        request.body.validate[VPNUser].fold(
          errors => BadRequest("Please check your request"),
          user => {
            val test = vpnService.createAccess(user)
            Ok.sendFile(new File(test))
          }
        )
      }.getOrElse {
        Unauthorized("Oops, you are not connected")
      }
  }

  def login = Action(BodyParsers.parse.json) {
    request =>
      request.body.validate[User].map(user =>
        userService.login(user) match {
          case None => Unauthorized
          case Some(login) => Ok("Welcome!").withSession("connected" -> login)
        }).getOrElse(BadRequest)
  }

  def logout = Action {
      Ok("Bye").withNewSession
  }

  def user = Action {
    request =>
      request.session.get("connected").map { login =>
        Ok(Json.toJson(userService.get(login).get))
      }.getOrElse(Unauthorized)
  }

}


