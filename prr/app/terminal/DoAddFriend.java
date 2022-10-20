package prr.app.terminal;

import prr.app.exception.UnknownTerminalKeyException;
import prr.core.Network;
import prr.core.Terminal;
import prr.core.exception.UnknowKeyException;
import pt.tecnico.uilib.menus.CommandException;


/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

  DoAddFriend(Network context, Terminal terminal) {
    super(Label.ADD_FRIEND, context, terminal);
    addStringField("id",Message.terminalKey());
  }
  
  @Override
  protected final void execute() throws CommandException {
    try {
      _network.addFriend(_receiver.getId(), stringField("id"));
    } catch (UnknowKeyException e) {
      throw new UnknownTerminalKeyException(stringField("id"));
    }
  }
}
