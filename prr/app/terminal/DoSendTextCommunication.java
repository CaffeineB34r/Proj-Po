package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.IllegalModeException;
import prr.core.exception.UnknownKeyException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.Display;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

  DoSendTextCommunication(Network context, Terminal terminal) {
    super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    addStringField("to", Message.terminalKey()); 
    addStringField("message", Message.textMessage());
  }
  
  @Override
  protected final void execute() throws CommandException {
    String destinationKey = stringField("to");
    try {
      _network.sendTextCommunication(_receiver,destinationKey,stringField("message"));
    } catch (UnknownKeyException e) {
      throw new UnknownTerminalKeyException(stringField("to"));
    } catch (IllegalModeException e) {
      _display.addLine(Message.destinationIsOff(destinationKey));
      _display.display();
    }
  }
} 
