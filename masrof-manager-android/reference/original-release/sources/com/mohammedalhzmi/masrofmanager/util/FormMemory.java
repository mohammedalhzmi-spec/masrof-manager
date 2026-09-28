package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import com.google.firebase.firestore.model.Values;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AppPreferences.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005¨\u0006\u000e"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/FormMemory;", "", "<init>", "()V", "read", "", "context", "Landroid/content/Context;", LinkHeader.Parameters.Type, "field", "fallback", "remember", "", Values.VECTOR_MAP_VECTORS_KEY, "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class FormMemory {
    public static final int $stable = 0;
    public static final FormMemory INSTANCE = new FormMemory();

    private FormMemory() {
    }

    public static /* synthetic */ String read$default(FormMemory formMemory, Context context, String str, String str2, String str3, int i, Object obj) {
        if ((i & 8) != 0) {
            str3 = "";
        }
        return formMemory.read(context, str, str2, str3);
    }

    public final String read(Context context, String type, String field, String fallback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        return AppPreferences.INSTANCE.get(context, "last_" + type + "_" + field, fallback);
    }

    public final void remember(Context context, String type, String field, String value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(value, "value");
        AppPreferences.INSTANCE.put(context, "last_" + type + "_" + field, value);
    }
}
