package prr.app.terminals;

import prr.core.Network;
import prr.core.exception.DuplicateKeyException;
import prr.core.exception.UnknowKeyException;
import prr.app.exception.DuplicateTerminalKeyException;
import prr.app.exception.InvalidTerminalKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

  DoRegisterTerminal(Network receiver) {
    super(Label.REGISTER_TERMINAL, receiver);
    addStringField("id", Message.terminalKey());
    addOptionField("type", Message.terminalType(), "BASIC", "FANCY");
    addStringField("client", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {
    try {
      _receiver.registerTerminal(null, null, null);
    } catch (UnknowKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    } catch (DuplicateKeyException e) {
      throw new DuplicateTerminalKeyException(e.getKey());
    }
  }
}
