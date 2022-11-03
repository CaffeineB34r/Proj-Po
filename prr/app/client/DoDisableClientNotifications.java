package prr.app.client;

import prr.core.Network;
import prr.core.exception.IllegalModeException;
import prr.core.exception.UnknownKeyException;
import prr.app.exception.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

  DoDisableClientNotifications(Network receiver) {
    super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
    addStringField("id", Message.key());
  }
  
  @Override
  protected final void execute() throws CommandException{
    String clientId = stringField("id");
    try {
      _receiver.disableClientNotifications(clientId);
    } catch (UnknownKeyException e) {
      throw new UnknownClientKeyException(clientId);
    } catch (IllegalModeException e) {
      System.out.println(Message.clientNotificationsAlreadyDisabled());
    }
  }
}
