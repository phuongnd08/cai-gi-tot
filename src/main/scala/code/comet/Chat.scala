package code.comet

import net.liftweb._
import http._
import actor._
import scala.xml.NodeSeq

object ChatServer extends LiftActor with ListenerManager {

	private var messages = List("Welcome")

		def createUpdate = messages

		override def lowPriority = {
			case s: String => messages ::= s ; updateListeners()
		}
}

class Chat extends CometActor with CometListener { 

	private var msgs: List[String] = Nil 

		def registerWith = ChatServer 

		override def lowPriority = {
			case m: List[String] => msgs = m; reRender(false) 
		} 

	def render = bind("chat", // the namespace for binding
			"line" -> lines _, // bind the function lines
			"input" -> SHtml.text("", s => ChatServer ! s))

		private def lines(xml: NodeSeq): NodeSeq =
																		 msgs.reverse.flatMap(m => bind("chat", xml, "msg" -> m))
}
