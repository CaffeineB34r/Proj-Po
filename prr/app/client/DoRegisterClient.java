package prr.app.client;

import prr.core.Network;
import prr.core.exception.DuplicateKeyException;
import prr.app.exception.DuplicateClientKeyException;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register new client.
 */
class DoRegisterClient extends Command<Network> {

  DoRegisterClient(Network receiver) {
    super(Label.REGISTER_CLIENT, receiver);
    
    addStringField("key", Message.key());
    addStringField("name", Message.name());
    addIntegerField("taxId", Message.taxId());
  }

  @Override
  protected final void execute() throws CommandException { 
    try {
      _receiver.registerClient(stringField("name"), stringField("key"), integerField("taxId"));
    } catch (DuplicateKeyException e) {
      throw new DuplicateClientKeyException(e.getKey());
    }
  }
}