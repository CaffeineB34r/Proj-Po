package prr.app.terminal;

import prr.app.exception.UnknownClientKeyException;
import prr.core.Network;
import prr.core.Terminal;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

  DoEndInteractiveCommunication(Network context, Terminal terminal) {
    super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
  }
  
  @Override
  protected final void execute() throws CommandException {
    //FIXME implement command
    throw new UnknownClientKeyException("not implemented"); 
      
    
  }
}
