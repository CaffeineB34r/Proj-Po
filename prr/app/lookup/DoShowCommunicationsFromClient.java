package prr.app.lookup;

import prr.app.exception.InvalidTerminalKeyException;
import prr.core.Network;
import prr.core.exception.UnknownKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show communications from a client.
 */
class DoShowCommunicationsFromClient extends Command<Network> {

  DoShowCommunicationsFromClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_FROM_CLIENT, receiver);
    addStringField("key", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _display.add(_receiver.showCommunicationsFromClient(stringField("key")));
      _display.display();
    } catch (UnknownKeyException e) {
      throw new InvalidTerminalKeyException(e.getKey());
    }

  }
}
