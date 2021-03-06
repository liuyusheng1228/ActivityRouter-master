package com.github.mzule.activityrouter;

import com.github.mzule.activityrouter.annotation.Modules;
import com.github.mzule.activityrouter.router.RouterCallback;
import com.github.mzule.activityrouter.router.RouterCallbackProvider;
import com.github.mzule.activityrouter.router.SimpleRouterCallback;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.util.logging.Logger;

import sun.rmi.runtime.Log;

/**
 * Created by CaoDongping on 4/6/16.
 */
@Modules({"app", "sdk"})
public class App extends Application implements RouterCallbackProvider {
    @Override
    public RouterCallback provideRouterCallback() {
        return new SimpleRouterCallback() {
            @Override
            public boolean beforeOpen(Context context, Uri uri) {
                if (uri.toString().startsWith("mzule://")) {
                    context.startActivity(new Intent(context, LaunchActivity.class));
                    return true;
                }
                return false;
            }

            @Override
            public void notFound(Context context, Uri uri) {
                context.startActivity(new Intent(context, NotFoundActivity.class));
                Log.getLog("","",0);

            }

            @Override
            public void error(Context context, Uri uri, Throwable e) {
                context.startActivity(ErrorStackActivity.makeIntent(context, uri, e));
            }
        };
    }
}
