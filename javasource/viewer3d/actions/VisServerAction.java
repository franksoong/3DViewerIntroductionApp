// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package viewer3d.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import viewer3d.actions.controller.MxController;

/**
 * Set up visualization server infrastructure.
 */
public class VisServerAction extends CustomJavaAction<java.lang.Void>
{
	public VisServerAction(IContext context)
	{
		super(context);
	}

	@java.lang.Override
	public java.lang.Void executeAction() throws Exception
	{
		// BEGIN USER CODE
		var endpoint = viewer3d.proxies.constants.Constants.getEndpoint();
		HttpHandler.register(endpoint);
		WebSocketHandler.register(endpoint + "_ws");
		HttpHandler.instance.addController(MxController.class);
		WebSocketHandler.instance.addController(MxController.class);
		return null;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "VisServerAction";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}
