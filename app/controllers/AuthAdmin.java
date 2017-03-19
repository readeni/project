package controllers;

import play.mvc.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.CompletableFuture;
import models.users.*;
/**
 * Created by Tudor on 19/03/2017.
 */
public class AuthAdmin extends Action.Simple{
    public CompletionStage<Result> call(Http.Context ctx){

        String id = ctx.session().get("email");
        if(id != null){

            User u = User.getUserById(id);
            if("admin".equals(u.getRole())){
                return delegate.call(ctx);
            }
        }
        ctx.flash().put("error", "Admin Login Required.");
        return CompletableFuture.completedFuture(redirect(routes.LoginController.login()));
    }
}
// Methods for admin access only to be added to the java classes
// @Security.Authenticated(Secured.class)
// @With(AuthAdmin.class)