package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnrecognizedEntryException;
import pt.tecnico.uilib.Display;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

  DoShowOngoingCommunication(Network context, Terminal terminal) {
    super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _display.addLine(_receiver.showOngoingCommunication());
      _display.display();
    } catch (UnrecognizedEntryException e) {
      _display.addLine(Message.noOngoingCommunication());
      _display.display();
    }
  }
}
