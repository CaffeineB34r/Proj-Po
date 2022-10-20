package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.IllegalModeException;
import prr.app.exception.UnknownTerminalKeyException;
//import pt.tecnico.uilib.Display;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

  DoStartInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.setOnBusy();
    } catch (IllegalModeException e) {
      _display.addLine("This option should not be available");
      _display.display();
      throw new UnknownTerminalKeyException("TODO");
    }
  }
}
