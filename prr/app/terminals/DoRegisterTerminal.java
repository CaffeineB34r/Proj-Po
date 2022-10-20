package prr.app.terminals;

import prr.core.Client;
import prr.core.Network;
import prr.core.exception.DuplicateKeyException;
import prr.core.exception.InvalidKeyException;
import prr.core.exception.UnknownKeyException;

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
    addStringField("key", Message.terminalKey());
    addOptionField("type", Message.terminalType(), "BASIC", "FANCY");
    addStringField("client", Message.clientKey());
  }

  @Override
  protected final void execute() throws CommandException {

    try {
      _receiver.registerTerminal(optionField("type"), stringField("key"), stringField("client"));
    } catch (DuplicateKeyException e) {
      throw new DuplicateTerminalKeyException(e.getKey());
    } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(e.getKey());
    } catch (InvalidKeyException e) {
      throw new InvalidTerminalKeyException(e.getKey());
    }
  }
}
