package prr.app.main;

import java.io.IOException;

import prr.app.exception.FileOpenFailedException;
import prr.core.NetworkManager;
import prr.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
//import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

  DoSaveFile(NetworkManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

  @Override
  protected final void execute() throws FileOpenFailedException {
    try{
      _receiver.save();
    }catch (MissingFileAssociationException msfe){
      String foo  = Form.requestString(Message.newSaveAs());
      try {
        _receiver.saveAs(foo);
      } catch (MissingFileAssociationException | IOException e) {
        // TODO Auto-generated catch block
        throw new FileOpenFailedException(e);
      }
    }catch (IOException ioe){
      throw new FileOpenFailedException(ioe);
    }
  }
}
