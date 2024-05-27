package viewer3d.shared.utils;

import java.io.IOException;
import java.util.*;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.m2ee.api.IMxRuntimeRequest;
import com.mendix.m2ee.api.IMxRuntimeResponse;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.viewer3d.jtreader.JtUtils;
import com.mendix.viewer3d.jtreader.StreamType;

public class Util {
	public static final ILogNode DEFAULTLOGGER = Core.getLogger("viewer3d");	
	public static ILogNode getLogger(String id) {
		if(id.isEmpty()) { id = "viewer3d"; }
		return Core.getLogger(id);
	};
	
	public static boolean isValidModelId(String modelId) {
		if (modelId.startsWith("usermodel&")) {
			String userModelId = modelId.substring(10);
			try {
				Integer.parseInt(userModelId);
				return true;
			} catch (NumberFormatException ex) {
				return false;
			}
		} else {
			try {
				UUID.fromString(modelId);
				return true;
			} catch (IllegalArgumentException ex) {
				return false;
			}
		}
	}

	public static Set<UUID> getSegmentIds(IMxRuntimeRequest request, StreamType st) throws IOException {
		if (st == StreamType.ProductStructure) {
			return null;
		}
		return JtUtils.INSTANCE.parseSegmentIds(request.getInputStream());
	}

	public static boolean isValidRequest(IContext ctx, IMxRuntimeRequest req, IMxRuntimeResponse response) {
		var reqCsrfToken = req.getHeader("x-csrf-token");
		var sessionCsrfToken = ctx.getSession().getCsrfToken();
		if (reqCsrfToken == null || !reqCsrfToken.equals(sessionCsrfToken)) {
			response.setContentType("text/plain;charset=utf-8");
			response.setStatus(IMxRuntimeResponse.BAD_REQUEST);
			return false;
		}
		return true;
	}

	@FunctionalInterface
	public interface ExceptionConsumer<T> {
		void accept(T ctx) throws Exception;
	}

	public static void handleTransaction(IContext context, ExceptionConsumer<IContext> action) throws Exception {
		try {
			context.startTransaction();
			action.accept(context);
		} catch (Exception ex) {
			context.rollbackTransaction();
			throw ex;
		} finally {
			context.endTransaction();
		}
	}
}
