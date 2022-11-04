package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.IllegalModeException;
import prr.core.exception.UnknownKeyException;
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
    addStringField("to", Message.terminalKey());
    addOptionField("type", Message.commType(), "VOICE", "VIDEO");
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _network.startInteractiveCommunication(_receiver, stringField("to"), stringField("type"));
    } catch (IllegalModeException e) {
      switch (e.getMode()) {
        case "OFF" -> _display.addLine(Message.destinationIsOff(stringField("to")));
        case "BUSY" -> _display.addLine(Message.destinationIsBusy(stringField("to")));
        case "SILENT" -> _display.addLine(Message.destinationIsSilent(stringField("to")));
      }
      _display.display();
    } catch (UnknownKeyException e) {
      switch (e.getMessage()){
        
      }
    }
  }
}
