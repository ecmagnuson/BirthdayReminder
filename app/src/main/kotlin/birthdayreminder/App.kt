package birthdayreminder

import org.apache.commons.mail.DefaultAuthenticator
import org.apache.commons.mail.Email
import org.apache.commons.mail.SimpleEmail

fun getAuth(path: String): List<String>? =
    object {}.javaClass.getResourceAsStream(path)?.bufferedReader()?.readLines()

fun email() {
    //building to single jar and keeping auth file in the same dir
    val auth = getAuth("/auth.txt")

    val sendFrom = auth?.get(0)
    val password = auth?.get(1)
    val sendTo = auth?.get(2)

    val email: Email = SimpleEmail()
    email.setSmtpPort(465)
    email.hostName = "smtp.gmail.com"
    email.isSSLOnConnect = true
    email.setAuthenticator(DefaultAuthenticator(sendFrom, password))
    email.addTo(sendTo)
    email.setFrom(sendFrom)

    email.subject = "TestMail"
    email.setMsg("This is a test mail ... :-)")

    email.send()
}

fun main() {
    email()
}
