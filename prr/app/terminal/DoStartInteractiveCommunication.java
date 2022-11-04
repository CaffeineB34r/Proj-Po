package prr.app.terminal;

import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.IllegalModeException;
import prr.core.exception.UnknownKeyException;
import prr.core.exception.UnsupportedCommException;
import prr.app.exception.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.CommandException;

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
    } catch (IllegalModeException ime) {
      switch (ime.getMode()) {
        case "OFF" -> _display.addLine(Message.destinationIsOff(stringField("to")));
        case "BUSY" -> _display.addLine(Message.destinationIsBusy(stringField("to")));
        case "SILENCE" -> _display.addLine(Message.destinationIsSilent(stringField("to")));
        default -> _display.addLine(ime.getMode().toString());
      }
      _display.display();
    } catch (UnknownKeyException uke) {
      throw new UnknownTerminalKeyException(uke.getKey());
    } catch (UnsupportedCommException e) {
      switch ( e.getUnsupportedAt()){
        case "SOURCE" -> _display.addLine(Message.unsupportedAtOrigin(stringField("to"), stringField("type")));
        case "DESTINATION" -> _display.addLine(Message.unsupportedAtDestination(stringField("to"), stringField("type")));
      }
      _display.display();
    }
  }
}
