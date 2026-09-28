package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import com.google.firebase.firestore.model.Values;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppPreferences.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0005J\u0016\u0010\r\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\u000e"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/DocumentNumbering;", "", "<init>", "()V", LinkHeader.Rel.Next, "", "context", "Landroid/content/Context;", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "setStart", "", Values.VECTOR_MAP_VECTORS_KEY, "consume", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class DocumentNumbering {
    public static final int $stable = 0;
    public static final DocumentNumbering INSTANCE = new DocumentNumbering();

    private DocumentNumbering() {
    }

    public final int next(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        Integer intOrNull = StringsKt.toIntOrNull(AppPreferences.INSTANCE.get(context, "next_number_" + type.name(), "1"));
        if (intOrNull != null) {
            return RangesKt.coerceAtLeast(intOrNull.intValue(), 1);
        }
        return 1;
    }

    public final void setStart(Context context, DocumentType type, int value) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        AppPreferences.INSTANCE.put(context, "next_number_" + type.name(), String.valueOf(RangesKt.coerceAtLeast(value, 1)));
    }

    public final void consume(Context context, DocumentType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        setStart(context, type, next(context, type) + 1);
    }
}
