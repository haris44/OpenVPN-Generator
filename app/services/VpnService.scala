package services

import java.io.{File, PrintWriter}
import java.nio.file.{FileAlreadyExistsException, Files, Paths}
import javax.inject.Singleton

import entity.{VPNUser, VPNUser$}

import scala.io.Source
import scala.sys.process._

/**
  * Created by Alex on 28/10/2016.
  */

@Singleton
class VpnService {

  def createAccess(user: VPNUser) = {

    // generate certif
    val generateCertif = ("ressource/script.sh " + user.getUsername).!!

    // create the tmp dir of this username
    val dir = new File("gentmp/" + user.getUsername)
    dir.mkdirs()

    // create config file
    val lines = Source.fromFile("labo.ovpn").getLines
      .map(element => element.replace("$CLIENT", user.getUsername()))

    // write config file
    val writer = new PrintWriter(new File("gentmp/" + user.getUsername() + "/labo.ovpn"))
    lines.foreach(line => {
      writer.write(line)
      writer.write("\r\n")
    })
    writer.close()

    // copy certificate to the temp folder
    copyFile("ressource/ca.crt", "gentmp/" + user.getUsername() + "/" + "ca.crt")
    copyFile("ressource/dh2048.pem", "gentmp/" + user.getUsername() + "/" + "dh2048.pem")
    copyFile("ressource/ta.key", "gentmp/" + user.getUsername() + "/" + "ta.key")
    copyFile("/etc/openvpn/easy-rsa/keys/" + user.getUsername() + ".key", "gentmp/" + user.getUsername() + "/" + user.getUsername() + ".key")
    copyFile("/etc/openvpn/easy-rsa/keys/" + user.getUsername() + ".crt", "gentmp/" + user.getUsername() + "/" + user.getUsername() + ".crt")
    copyFile("/etc/openvpn/easy-rsa/keys/" + user.getUsername() + ".csr", "gentmp/" + user.getUsername() + "/" + user.getUsername() + ".csr")

    val zip = ("zip -r /gentmp/" + user.getUsername() + ".zip /gentmp/" + user.getUsername()).!!

    // return the zip
    "gentmp/"+ user.getUsername() + ".zip"
  }

  def copyFile(input: String, output: String): Unit = {
    try {
      val in = Paths.get(input)
      val out = Paths.get(output)
      Files.copy(in, out)
    } catch {
      case e: FileAlreadyExistsException => println("already copied !")
      case e: Exception => throw e
    }
  }


}
