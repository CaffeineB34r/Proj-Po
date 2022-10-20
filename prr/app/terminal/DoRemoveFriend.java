package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknowKeyException;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

  DoRemoveFriend(Network context, Terminal terminal) {
    super(Label.REMOVE_FRIEND, context, terminal);
    addStringField("id",Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _network.removeFriend(_receiver.getId(), stringField("id"));
    } catch (UnknowKeyException e) {
      throw new UnknownTerminalKeyException(stringField("id"));
    }
  }
}
