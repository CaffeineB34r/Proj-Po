package prr.app.lookup;

import prr.app.exception.InvalidTerminalKeyException;
import prr.core.Network;
import prr.core.exception.UnknownKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

  DoShowCommunicationsToClient(Network receiver) {
    super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
    addStringField("key", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _display.add(_receiver.showCommunicationsToClient(stringField("key")));
    
    } catch (UnknownKeyException e) {
      throw new InvalidTerminalKeyException(e.getKey());
    }
  }
}
